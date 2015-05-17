package jpower.irc;

import java.util.HashMap;
import java.util.Map;

public class Channel implements Sendable {

   private final PowerIrc client;
   private final String name;
   private String topic;
   private Map<String, User> users;
   private Map<String, User> ops;
   private Map<String, User> voices;

   public Channel(PowerIrc client, String name) {
      this.client = client;
      this.name = name;
      users = new HashMap<>();
      ops = new HashMap<>();
      voices = new HashMap<>();
   }

   public String getName() {
      return name;
   }

   protected void addUser(User user) {
      if (users.containsKey(user)) return;
      users.put(user.getUsername(), user);
   }

   protected void setOp(User user) {
      if (ops.containsKey(user)) return;
      ops.put(user.getNickname(), user);
   }

   protected void removeOp(User user) {
      ops.remove(user);
   }

   protected void setVoice(User user) {
      try {
         if (voices.containsKey(user)) return;
         voices.put(user.getNickname(), user);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   protected void removeVoice(User user) {
      voices.remove(user);
   }

   public void userJoin(User user) {
      if (users.containsKey(user)) return;
      users.put(user.getUsername(), user);
   }

   public void userPart(User user) {
      users.remove(user);
   }

   public void message(String message) {
      client.privmsg(this, message);
   }

   public Map<String, User> getUsers() {
      return users;
   }

   public Map<String, User> getOps() {
      return ops;
   }

   public Map<String, User> getVoices() {
      return voices;
   }

   public String getTopic() {
      return topic;
   }

   public void setTopic(String topic) {
      this.topic = topic;
   }

}
