package jpower.irc;

public class WhoisObject {

   public String nickname;
   public String username;
   public String hostname;
   public String realname;

   public WhoisObject(String nickname, String username, String hostname, String realname) {
      this.nickname = nickname;
      this.username = username;
      this.hostname = hostname;
      this.realname = realname;
   }

   public String getNickname() {
      return nickname;
   }

   public String getUsername() {
      return username;
   }

   public String getHostname() {
      return hostname;
   }

   public String getRealname() {
      return realname;
   }

}
