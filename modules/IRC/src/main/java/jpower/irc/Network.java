package jpower.irc;

import java.util.ArrayList;
import java.util.List;

public class Network {

   private final String name;
   private final String server;
   private final int port;
   private final String nickname;
   private final String username;
   private final String realname;

   private List<String> init_channels;
   private PowerIrc instance;

   public Network(String name, String server, int port, String nickname, String username, String realname) {
      this.name = name;
      this.server = server;
      this.port = port;
      this.nickname = nickname;
      this.username = username;
      this.realname = realname;

      init_channels = new ArrayList<>();
   }

   public void addInitChannel(String channel) {
      init_channels.add(channel);
   }

   public void connect() {
      instance = new PowerIrc(username, nickname, realname, server, port, init_channels);
      instance.connect();
   }

}
