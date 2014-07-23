package jpower.irc;

import jpower.core.utils.IOUtils;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Map;
import java.util.HashMap;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class PowerIrcServer
{

   private int port;
   private ServerSocket socket;
   private Map<String, User> users;
   private boolean connected;

   public PowerIrcServer(int port)
   {
      this.port = port;
      try
      {
         this.socket = new ServerSocket(port);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      connected = true;

      // Create the users Map
      users = new HashMap<>();
      Reader reader = new Reader();
      reader.start();
   }

   public class Reader extends Thread
   {
      
      @Override
      public void run()
      {
         while (connected)
         {
            try
            {
               Socket client = socket.accept();
               new ClientHandler(client).start();
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
         }
      }
        
   }

   public class ClientHandler extends Thread
   {

      private Socket client;
      private PrintWriter writer;
      private BufferedReader reader;

      public ClientHandler(Socket client)
      {
         this.client = client;
         try
         {
            writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            reader = IOUtils.createBufferedReader(client.getInputStream());
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }

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
      
      @Override
      public void run()
      {
         String read;
         while ((read = readline()) != null)
         {
            System.out.println(read);
         }
      }

   }

}
