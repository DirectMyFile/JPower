package jpower.irc;

import jpower.core.utils.IOUtils;

import jpower.event.EventBus;
import jpower.event.EventHandler;

import java.lang.Thread;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PowerIrc
{

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
   private String server;
   private int port;
   private User me;
   private boolean ready;
   private List<String>  motd;
   private Map<String, User> users;
   private Map<String, Channel> channels;
   private List<String> init_channels;

   public PowerIrc(String username, String nickname, String realname, String server, int port, List<String> init_channels)
   {
      this.username = username;
      this.nickname = nickname;
      this.realname = realname;
      this.server = server;
      this.port = port;

      me = new User(this, username, nickname);

      // Initialize the Event Bus
      eventBus = new EventBus();
      eventBus.register(this);

      // Create the MOTD List
      motd = new ArrayList<>();

      // Create the Users Map
      users = new HashMap<>();
      
      // Create the Channels Map
      channels = new HashMap<>();

      // Create the init Channels List
      this.init_channels = init_channels;
   }

   public void connect()
   {
      try
      {
         socket = new Socket(server, port);
         writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
         reader = IOUtils.createBufferedReader(socket.getInputStream());
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      worker = new Reader();
      worker.start();
      nick(nickname);
      user(username, realname);
   }

   /**
    * Retrieve the event bus.
    * @return the event bus instance
    */
   public EventBus getEventBus()
   {
      return eventBus;
   }

   /**
    * Write a line to the writer;
    * @param line line to write
    */
   public void writeline(String line)
   {
      writer.write(line + "\r\n");
      writer.flush();
   }

   /**
    * Read a line from the reader;
    * @return line read from the reader
    */
   public String readline()
   {
      try
      {
         return reader.readLine();
      }
      catch (IOException e)
      {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * Send the nick command.
    * @param nick nick to set/change
    * @TODO Add support for nick-in-use
    */
   public void nick(String nick)
   {
      writeline("NICK " + nick);
      nickname = nick;
      me.setNickname(nick);
   }

   /**
    * Send the user command.
    * @param username username to send
    * @param realname realname to send
    */
   private void user(String username, String realname)
   {
      writeline("USER " + username + " 8 * :" + realname);
   }

   /**
    * Join a channel.
    * @param channel channel name
    */
   public void join(String channel)
   {
      writeline("JOIN " + channel);
      eventBus.post(new JoinEvent(channel));
   }

   /**
    * Part a channel.
    * @param channel channel name
    */
   public void part(String channel)
   {
      writeline("PART " + channel);
      // TODO: eventBus.post(new PartEvent());
   }

   /**
    * Join init_channels.
    */
   private void joinChannels()
   {
      for (String channel : init_channels)
      {
         join(channel);
      }
   }

   /**
    * Send a private message to a user.
    * @param user target user
    * @param user message sent
    */
   public void privmsg(User user, String message)
   {
      writeline("PRIVMSG " + user.getNickname() + " :" + message);
   }

   /**
    * Send a private message to a channel.
    * @param channel target channel
    * @param channel message sent
    */
   public void privmsg(Channel channel, String message)
   {
      writeline("PRIVMSG " + channel.getName() + " :" + message);
   }

   /**
    * Send a whois to the server with the specified nickname.
    * @param user target user
    */
   public void whois(String user)
   {
      writeline("WHOIS " + user);
   }

   /**
    * Parse normal whois object
    * @param params command parameters
    * @param trail command trail
    */
   public WhoisObject parseWhoisQuery(String params, String trail)
   {
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
    * @param user target user
    * @param message message sent
    */
   public void notice(User user, String message)
   {
      writeline("NOTICE " + user.getNickname() + " :" + message);
   }

   /**
    * Handle ready event: join channels.
    */
   @EventHandler
   public void ready(ReadyEvent event)
   {
      joinChannels();
   }

   /**
    * Handle message event(both private and channel)
    */
   @EventHandler
   public void msg(MessageEvent event)
   {
      System.out.println("[" + event.getSender().getName() + "] -> [" + event.getTarget().getName() + "] " + event.getMessage());
   }

   /**
    * Handle ping event
    */
   @EventHandler
   public void ping(PingEvent event)
   {
      writeline("PONG :" + event.getTrail());
   }

   @EventHandler
   public void topicAdd(TopicAddEvent event)
   {
      event.getChannel().setTopic(event.getTopic());
   }

   @EventHandler
   public void topicChange(TopicChangeEvent event)
   {
      event.getChannel().setTopic(event.getNewTopic());
   }

   @EventHandler
   public void invited(InviteEvent event)
   {
      join(event.getChannel());
   }

   /**
    * Check if a prefix is for another client.
    * @param input string to check for hostmask
    */
   public boolean isHostmask(String input)
   {
      return input.contains("!") &&
             input.contains("@");
   }

   /**
    * Check if a prefix is for a server.
    * @param input string to check for server
    */
   public boolean isServer(String input)
   {
      return !input.contains("!") &&
             !input.contains("@") &&
             input.contains(".");
   }

   /**
    * Parse a hostmask, and create a user with the supplied information.
    * @param hostmask hostmask to parse
    */
   public User parseHostmask(String hostmask)
   {
      String[] parts = HOSTMASK.split(hostmask);
      return new User(this, parts[1], parts[0], parts[2]);
   }

   /**
    * Update a user from a WhoisObject.
    * @param whois whois object to update from
    */
   public void updateUser(WhoisObject whois)
   {
      if (users.containsKey(whois.getNickname()))
      {
         users.get(whois.getUsername()).setNickname(whois.getNickname());
      }
      else
      {
         users.put(whois.getNickname(), new User(this, whois.getUsername(), whois.getNickname(), whois.getHostname(), whois.getRealname()));
      }
   }

   public class Reader extends Thread
   {
      
      @Override
      public void run()
      {
         String read;
         Message message;
         while ((read = readline()) != null)
         {
            Matcher matcher = PATTERN.matcher(read);
            matcher.matches();

            String prefix = matcher.group("prefix");
            String command = matcher.group("command");
            String params = matcher.group("params");
            String trail = matcher.group("trail");

            switch (command)
            {
               case "PING":
                  eventBus.post(new PingEvent(trail));
                  break;
               case "JOIN":
                  if (parseHostmask(prefix).getUsername().substring(1).equals(username))
                  {
                     channels.put(params, new Channel(PowerIrc.this, params));
                  }
                  break;
               case "TOPIC":
                  String targeta = params;
                  if (!channels.containsKey(targeta)) continue;
                  eventBus.post(new TopicChangeEvent(channels.get(targeta), channels.get(targeta).getTopic(), trail));
                  break;
               case "PRIVMSG":
                  if (params.equals(username))
                  {
                     eventBus.post(new MessageEvent(parseHostmask(prefix), me, trail));
                  }
                  else
                  {
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
                  // This is the ISUPPORT
                  String[] supports = params.split(" ");
                  for (String s : supports)
                  {
                     if (s.startsWith("CHANTYPES="))
                     {
                        System.out.println("Channel Prefixes: " + s.split("=")[1]);
                     }
                  }
                  break;
               case "311":
                  WhoisObject whois = parseWhoisQuery(params, trail);
                  updateUser(whois);
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
                  for (String s : trail.split(" "))
                  {
                     String real = s.replace("@", "").replace("+", "");
                     User user;
                     Channel channel = channels.get(chan);
                     if (real.equals(nickname)) continue;
                     if (users.containsKey(s))
                     {
                        user = users.get(real);
                     }
                     else
                     {
                        user = new User(PowerIrc.this, real);
                     }
                     if (s.startsWith("@"))
                     {
                        channel.setOp(user);
                     }
                     else if (s.startsWith("+"))
                     {
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
                  //System.out.println(read);
                  break;
            }
         }
      }
   }
}
