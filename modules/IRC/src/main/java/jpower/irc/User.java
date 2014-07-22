package jpower.irc;

public class User implements Sendable
{

   private final PowerIrc client;
   private final String username;
   private final String realname;
   private final String hostname;
   private String nickname;

   public User(PowerIrc client, String nickname)
   {
      this(client, "", nickname);
   }

   public User(PowerIrc client, String username, String nickname)
   {
      this(client, username, nickname, "");
   }

   public User(PowerIrc client, String username, String nickname, String hostname)
   {
      this(client, username, nickname, hostname, "");
   }

   public User(PowerIrc client, String username, String nickname, String hostname, String realname)
   {
      this.client = client;
      this.username = username;
      this.nickname = nickname;
      this.hostname = hostname;
      this.realname = realname;
   }

   public String getName()
   {
      return nickname;
   }

   public void queueWhois()
   {
      client.whois(nickname);
   }

   public String getUsername()
   {
      return username;
   }

   public String getNickname()
   {
      return nickname;
   }

   public void setNickname(String nickname)
   {
      this.nickname = nickname;
   }

   public String getHost()
   {
      return hostname;
   }

   public void message(String message)
   {
      client.privmsg(this, message);
   }

}
