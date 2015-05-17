package jpower.irc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IRCFactory {

   // Default values
   private String hostname = "irc.esper.net";
   private int port = 6667;
   private String nickname = "PowerIrc" + new Random().nextInt(1000);
   private String username = "PowerIrc";
   private String realname = "PowerIrc";
   private List<String> initialChannels = new ArrayList<>();

   private PowerIrc irc = new PowerIrc();

   public IRCFactory setHostname(String hostname) {
      this.hostname = hostname;
      return this;
   }

   public IRCFactory setPort(int port) {
      this.port = port;
      return this;
   }

   public IRCFactory setNickname(String nickname) {
      this.nickname = nickname;
      return this;
   }

   public IRCFactory setUsername(String username) {
      this.username = username;
      return this;
   }

   public IRCFactory addChannel(String channel) {
      initialChannels.add(channel);
      return this;
   }

   public PowerIrc build() {
      irc.setHostname(hostname);
      irc.setPort(port);
      irc.setNickname(nickname);
      irc.setUsername(username);
      irc.setRealname(realname);
      irc.addInitialChannels(initialChannels);
      return irc;
   }

}
