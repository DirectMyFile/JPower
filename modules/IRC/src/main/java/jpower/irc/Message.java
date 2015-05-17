package jpower.irc;

import java.util.List;
import java.util.Map;

public class Message {

   private String line;
   private String command;
   private String message;
   private String hostmask;
   private Map<String, String> tags;
   private List<String> parameters;

   public Message(String line, String command, String message, String hostmask, Map<String, String> tags, List<String> parameters) {
      this.line = line;
      this.command = command;
      this.message = message;
      this.hostmask = hostmask;
      this.parameters = parameters;
      this.tags = tags;
   }

   public String getLine() {
      return line;
   }

   public String getCommand() {
      return command;
   }

   public String getMessage() {
      return message;
   }

   public String getPlainHostmask() {
      return hostmask;
   }

   public Map<String, String> getTags() {
      return tags;
   }

   public List<String> getParameters() {
      return parameters;
   }

}
