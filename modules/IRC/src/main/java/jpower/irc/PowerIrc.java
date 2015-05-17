package jpower.irc;

import jpower.core.utils.IOUtils;
import jpower.event.EventBus;
import jpower.event.EventHandler;
import jpower.irc.events.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PowerIrc {

   private static final Pattern PATTERN = Pattern.compile("^(:(?<prefix>\\S+) )?(?<command>\\S+)( (?!:)(?<params>.+?))?( :(?<trail>.+))?$");
   private static final Pattern HOSTMASK = Pattern.compile("[!@]");

   private EventBus eventBus;
   private Socket socket;
   private PrintWriter writer;
   private BufferedReader reader;
   private Thread worker;
   private String username;
   private String nickname;
   private String realname;
   private String hostname;
   private int port;
   private User me;
   private List<String> motd;
   private Map<String, User> users;
   private Map<String, Channel> channels;
   private Map<String, Object> supports;
   private List<String> initialChannels;

   public PowerIrc() {
      // Initialize the Event Bus
      eventBus = new EventBus();
      eventBus.register(this);

      // Create the MOTD list
      motd = new ArrayList<>();

      // Create the users map
      users = new HashMap<>();

      // Create the channels map
      channels = new HashMap<>();

      // Create the supports map
      supports = new HashMap<>();

      // Create the initial channels list
      initialChannels = new ArrayList<>();
   }

   protected void setHostname(String hostname) {
      this.hostname = hostname;
   }

   protected void setPort(int port) {
      this.port = port;
   }

   protected void setNickname(String nickname) {
      this.nickname = nickname;
   }

   protected void setUsername(String username) {
      this.username = username;
   }

   protected void setRealname(String realname) {
      this.realname = realname;
   }

   protected void addInitialChannels(Collection<String> channels) {
      initialChannels.addAll(channels);
   }

   public void connect() {
      me = new User(this, username, nickname);

      try {
         socket = new Socket(hostname, port);
         writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
         reader = IOUtils.createBufferedReader(socket.getInputStream());
      } catch (IOException e) {
         e.printStackTrace();
      }
      worker = new Reader();
      worker.start();
      nick(nickname);
      user(username, realname);
   }

   /**
    * Retrieve the event bus.
    *
    * @return the event bus instance
    */
   public EventBus getEventBus() {
      return eventBus;
   }

   public Map<String, Object> getSupports() {
      return Collections.unmodifiableMap(supports);
   }

   /**
    * Write a line to the writer;
    *
    * @param line line to write
    */
   public void writeline(String line) {
      writer.write(line + "\r\n");
      writer.flush();
   }

   /**
    * Read a line from the reader.
    *
    * @return line read from the reader
    */
   public String readline() {
      try {
         return reader.readLine();
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * Send the nick command.
    *
    * @param nick nick to set/change
    * @TODO Add support for nick-in-use
    */
   public void nick(String nick) {
      writeline("NICK " + nick);
      nickname = nick;
      me.setNickname(nick);
   }

   /**
    * Send the user command.
    *
    * @param username username to send
    * @param realname realname to send
    */
   private void user(String username, String realname) {
      writeline("USER " + username + " 8 * :" + realname);
   }

   /**
    * Join a channel.
    *
    * @param channel channel name
    */
   public void join(String channel) {
      writeline("JOIN " + channel);
      eventBus.post(new JoinEvent(channel));
   }

   /**
    * Part a channel.
    *
    * @param channel channel name
    */
   public void part(String channel) {
      writeline("PART " + channel);
      eventBus.post(new PartEvent(channel));
   }

   /**
    * Join initial channels.
    */
   private void joinChannels() {
      for (String channel : initialChannels) {
         join(channel);
      }
   }

   /**
    * Send a private message to a user.
    *
    * @param user target user
    * @param user message sent
    */
   public void privmsg(User user, String message) {
      writeline("PRIVMSG " + user.getNickname() + " :" + message);
   }

   /**
    * Send a private message to a channel.
    *
    * @param channel target channel
    * @param channel message sent
    */
   public void privmsg(Channel channel, String message) {
      writeline("PRIVMSG " + channel.getName() + " :" + message);
   }

   /**
    * Send a whois to the server with the specified nickname.
    *
    * @param user target user
    */
   public void whois(String user) {
      writeline("WHOIS " + user);
   }

   /**
    * Parse normal whois object
    *
    * @param params command parameters
    * @param trail  command trail
    */
   public WhoisObject parseWhoisQuery(String params, String trail) {
      String line = params + " " + trail;
      String[] split = line.split(" ", 5);
      String nickname = split[1];
      String username = split[2];
      String hostname = split[3];
      String realname = split[4].substring(2);
      return new WhoisObject(nickname, username, hostname, realname);
   }

   /**
    * Send a notice to the specified user with a message.
    *
    * @param user    target user
    * @param message message sent
    */
   public void notice(User user, String message) {
      writeline("NOTICE " + user.getNickname() + " :" + message);
   }

   /**
    * Handle ready event: join channels.
    */
   @EventHandler
   public void ready(ReadyEvent event) {
      joinChannels();
   }

   /**
    * Handle ping event
    */
   @EventHandler
   public void ping(PingEvent event) {
      writeline("PONG :" + event.getTrail());
   }

   @EventHandler
   public void topicAdd(TopicAddEvent event) {
      event.getChannel().setTopic(event.getTopic());
   }

   @EventHandler
   public void topicChange(TopicChangeEvent event) {
      event.getChannel().setTopic(event.getNewTopic());
   }

   /**
    * Check if a prefix is for another client.
    *
    * @param input string to check for hostmask
    */
   public boolean isHostmask(String input) {
      return input.contains("!") &&
              input.contains("@");
   }

   /**
    * Check if a prefix is for a server.
    *
    * @param input string to check for server
    */
   public boolean isServer(String input) {
      return !input.contains("!") &&
              !input.contains("@") &&
              input.contains(".");
   }

   /**
    * Parse a hostmask, and create a user with the supplied information.
    *
    * @param hostmask hostmask to parse
    */
   public User parseHostmask(String hostmask) {
      String[] parts = HOSTMASK.split(hostmask);
      return new User(this, parts[1], parts[0], parts[2]);
   }

   /**
    * Update a user from a WhoisObject.
    *
    * @param whois whois object to update from
    */
   public void updateUser(WhoisObject whois) {
      if (users.containsKey(whois.getNickname())) {
         users.get(whois.getUsername()).setNickname(whois.getNickname());
      } else {
         users.put(whois.getNickname(), new User(this, whois.getUsername(), whois.getNickname(), whois.getHostname(), whois.getRealname()));
      }
   }

   public class Reader extends Thread {

      @Override
      public void run() {
         String read;
         Message message;
         while ((read = readline()) != null) {
            Matcher matcher = PATTERN.matcher(read);
            matcher.matches();

            String prefix = matcher.group("prefix");
            String command = matcher.group("command");
            String params = matcher.group("params");
            String trail = matcher.group("trail");

            switch (command) {
               case "PING":
                  eventBus.post(new PingEvent(trail));
                  break;
               case "JOIN":
                  if (parseHostmask(prefix).getUsername().substring(1).equals(username)) {
                     channels.put(params, new Channel(PowerIrc.this, params));
                  }
                  break;
               case "TOPIC":
                  if (!channels.containsKey(params)) continue;
                  eventBus.post(new TopicChangeEvent(channels.get(params), channels.get(params).getTopic(), trail));
                  break;
               case "PRIVMSG":
                  if (params.equals(username)) {
                     eventBus.post(new MessageEvent(parseHostmask(prefix), me, trail));
                  } else {
                     eventBus.post(new MessageEvent(parseHostmask(prefix), channels.get(params), trail));
                  }
                  break;
               case "INVITE":
                  if (channels.containsKey(trail)) continue;
                  eventBus.post(new InviteEvent(users.get(parseHostmask(prefix)), trail));
                  break;
               case "001":
                  // Ignore Initial NOTICE
                  break;
               case "005":
                  // ISUPPORT
                  String[] rawSupports = params.split(" ");
                  for (String s : rawSupports) {
                     if (s.contains("=")) {
                        String[] split = s.split("=");
                        String key = split[0];
                        String value = split[1];
                        supports.put(key, value);
                     } else {
                        supports.put(s, true);
                     }
                  }
                  eventBus.post(new ServerSupportsEvent(supports));
                  break;
               case "251":
                  // Network user information
                  break;
               case "252":
                  // Number of IRC operators online
                  break;
               case "254":
                  // Number of channels formed
                  break;
               case "255":
                  // Server user information
                  break;
               case "250":
                  // Past server statistics
                  break;
               case "311":
                  // Whois reply for user
                  WhoisObject whois = parseWhoisQuery(params, trail);
                  updateUser(whois);
                  break;
               case "312":
                  // Whois reply for server
                  break;
               case "313":
                  // Whois reply for IRC operator
                  break;
               case "332":
                  String target = params.split(" ")[1];
                  if (!channels.containsKey(target)) continue;
                  eventBus.post(new TopicAddEvent(channels.get(target), trail));
                  break;
               case "353":
                  // Parse a NAMES message
                  // Get #chan from "$USERNAME = $CHANNEL"
                  String chan = params.split(" ")[2];
                  if (!channels.containsKey(chan)) continue;
                  for (String s : trail.split(" ")) {
                     String real = s.replace("@", "").replace("+", "");
                     User user;
                     Channel channel = channels.get(chan);
                     if (real.equals(nickname)) continue;
                     if (users.containsKey(s)) {
                        user = users.get(real);
                     } else {
                        user = new User(PowerIrc.this, real);
                     }
                     if (s.startsWith("@")) {
                        channel.setOp(user);
                     } else if (s.startsWith("+")) {
                        channel.setVoice(user);
                     }
                     channel.addUser(user);
                  }
                  break;
               case "366":
                  // Ignore the 366 - End of NAMES
                  break;
               case "372":
                  motd.add(trail);
                  break;
               case "375":
                  // MOTD Begin Message
                  break;
               case "376":
                  // MOTD End Message
                  // We can begin to join channels
                  eventBus.post(new ReadyEvent());
                  // Post the MOTD event
                  eventBus.post(new MotdEvent(motd));
                  break;
               default:
                  System.out.println(read);
                  break;
            }
         }
      }
   }
}
