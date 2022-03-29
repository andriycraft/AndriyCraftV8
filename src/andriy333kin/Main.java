package andriy333kin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class Main extends Plugin implements Listener{
    @SuppressWarnings("deprecation")

    public String host;
    public String port;
    public String host1;
    public String port1;
    public String host2;
    public String port2;
    public String finalhost;
    public String finalport;
    String serverid = "000000000000";
    String token = "00000000000000000000000000000000000000000000000000000000000000000000000000";
    int c = 0;

    @Override
    public void onEnable() {
        getLogger().info("Im loading");
        getProxy();
	ProxyServer.getInstance().getPluginManager().registerListener(this, this);
	ProxyServer.getInstance().getPluginManager().registerCommand(this, new FHubCommand(this));
	getLogger().info("I was loaded");
	new Thread(() -> {
	    	 try { 
	    	      StringBuilder result = new StringBuilder();
	    	      URL url = new URL("https://api.exaroton.com/v1/servers/" + serverid + "/start/");
	    	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    	      conn.setRequestMethod("GET");
	    	      conn.setRequestProperty("Authorization", "Bearer " + token);
	      	      conn.setRequestProperty("Content-Type", "application/json");  	      
	    	      try (BufferedReader reader = new BufferedReader(
	    	          new InputStreamReader(conn.getInputStream()))) {
	    	          for (String line; (line = reader.readLine()) != null; ) {
	    	              result.append(line);
	    	          }
	    	      }
	    	      TimeUnit.SECONDS.sleep(7);
	              StringBuilder result1 = new StringBuilder();
	              URL url1 = new URL("https://api.exaroton.com/v1/servers/");
	              HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
	              conn1.setRequestMethod("GET");
	              conn1.setRequestProperty("Authorization", "Bearer " + token);
	              conn1.setRequestProperty("Content-Type", "application/json");  	      
	              try (BufferedReader reader1 = new BufferedReader(
	        	      new InputStreamReader(conn1.getInputStream()))) {
	        	      for (String line1; (line1 = reader1.readLine()) != null; ) {
	        	              result1.append(line1);
	        	              String segments[] = result1.toString().split(",");
	        	               for (int i1 = 0; i1<30; i1++) {
	        	            	  try {
	        	            	   if(i1 > 15) {
                                              c = c + 1;
	        	            	      if (segments[i1].startsWith("\"host\"")) {
	        	            	    	  host1 = segments[i1].toString().replace("\"host\":\"","");
	        	            	    	  host2 = host1.replace("\"","");
	        	            	    	  finalhost = host2;
	        	            	    	  getLogger().info(finalhost);
	        	            	      };

	        	            	      if (segments[i1].startsWith("\"port\"")) {
	        	            	    	  port1 = segments[i1].toString().replace("\"port\":","");
	        	            	    	  finalport = port1;
	        	            	    	  getLogger().info(finalport);
	        	            	      };

	        	            	      if (!(finalhost == null) || !(finalport == null)) {
	        	            	    	    InetSocketAddress socketAddress = new InetSocketAddress(finalhost, 54138);
	        	            	    	    ServerInfo info = ProxyServer.getInstance().constructServerInfo("duels", socketAddress, "ac", false);
	        	            	    	    ProxyServer.getInstance().getServers().put("duels", info);
	        	            	      }
	        	            	   };
	        	            	  } catch (Exception e) {
	        	            		  getLogger().severe("**** UNHANDLED EXCEPTION ****");
	        	            		  getLogger().severe(e.toString());
	        	            	  }
	        	              }
	        	              result1 = null;
	        	              line1 = null;
	        	              c = 0;
	        	     }
	              }
	              TimeUnit.SECONDS.sleep(3);
	    	} catch (Exception e) {
	    		getLogger().severe("**** FAILED TO START SERVER ****");
	    		getLogger().severe(e.toString());
	    	}
	    	}).start();
    }

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent event) {
    	String s = event.getPlayer().getServer().getInfo().getName();
    	if (s == "duels") {
    		event.getPlayer().sendMessage(ChatColor.YELLOW + "Будь ласка зачекайте сервер дуелей запускається...");
    		event.getPlayer().sendMessage(ChatColor.YELLOW + "Please wait the duels server is loading...");
    		new Thread(() -> {
    	    	 try { 
    	    	      StringBuilder result = new StringBuilder();
    	    	      URL url = new URL("https://api.exaroton.com/v1/servers/" + serverid + "/start/");
    	    	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	    	      conn.setRequestMethod("GET");
    	    	      conn.setRequestProperty("Authorization", "Bearer " + token);
    	      	      conn.setRequestProperty("Content-Type", "application/json");  	      
    	    	      try (BufferedReader reader = new BufferedReader(
    	    	          new InputStreamReader(conn.getInputStream()))) {
    	    	          for (String line; (line = reader.readLine()) != null; ) {
    	    	              result.append(line);
    	    	          }
    	    	      }
    	    	      TimeUnit.SECONDS.sleep(7);
    	              StringBuilder result1 = new StringBuilder();
    	              URL url1 = new URL("https://api.exaroton.com/v1/servers/");
    	              HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
    	              conn1.setRequestMethod("GET");
    	              conn1.setRequestProperty("Authorization", "Bearer " + token);
    	              conn1.setRequestProperty("Content-Type", "application/json");  	      
    	              try (BufferedReader reader1 = new BufferedReader(
    	        	      new InputStreamReader(conn1.getInputStream()))) {
    	        	       for (String line1; (line1 = reader1.readLine()) != null; ) {
    	        	              result1.append(line1);
    	        	              String segments[] = result1.toString().split(",");
    	        	              for (int i1 = 0; i1<30; i1++) {
    	        	            	  try {
    	        	            	   if(i1 > 15) {
    	        	            	      c = c + 1;
    	        	            	      if (segments[i1].startsWith("\"host\"")) {
    	        	            	    	  host1 = segments[i1].toString().replace("\"host\":\"","");
    	        	            	    	  host2 = host1.replace("\"","");
    	        	            	    	  finalhost = host2;
    	        	            	    	  getLogger().info(finalhost);
    	        	            	      };

    	        	            	      if (segments[i1].startsWith("\"port\"")) {
    	        	            	    	  port1 = segments[i1].toString().replace("\"port\":","");
    	        	            	    	  finalport = port1;
    	        	            	    	  getLogger().info(finalport);
    	        	            	      };

    	        	            	      if (!(finalhost == null) || !(finalport == null)) {
    	        	            	    	    InetSocketAddress socketAddress = new InetSocketAddress(finalhost, 54138);
    	        	            	    	    ServerInfo info = ProxyServer.getInstance().constructServerInfo("duels", socketAddress, "ac", false);
    	        	            	    	    ProxyServer.getInstance().getServers().put("duels", info);
    	        	            	      }
    	        	            	   };
    	        	            	  } catch (Exception e) {
    	        	            		  getLogger().severe("**** UNHANDLED EXCEPTION ****");
    	        	            		  getLogger().severe(e.toString());
    	        	            	  }
    	        	              }
    	        	              result1 = null;
    	        	              line1 = null;
    	        	              c = 0;
    	        	     }
    	              }
    	              TimeUnit.SECONDS.sleep(3);
    	    	} catch (Exception e) {
    	    		getLogger().severe("**** FAILED TO START SERVER ****");
    	    		getLogger().severe(e.toString());
    	    	}
    	    	}).start();
    	}
    }
    
    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
    	new Thread(() -> {
    	 try { 
    	      StringBuilder result = new StringBuilder();
    	      URL url = new URL("https://api.exaroton.com/v1/servers/" + serverid + "/start/");
    	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	      conn.setRequestMethod("GET");
    	      conn.setRequestProperty("Authorization", "Bearer " + token);
      	      conn.setRequestProperty("Content-Type", "application/json");  	      
    	      try (BufferedReader reader = new BufferedReader(
    	          new InputStreamReader(conn.getInputStream()))) {
    	          for (String line; (line = reader.readLine()) != null; ) {
    	              result.append(line);
    	          }
    	      }
    	      TimeUnit.SECONDS.sleep(7);
              StringBuilder result1 = new StringBuilder();
              URL url1 = new URL("https://api.exaroton.com/v1/servers/");
              HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
              conn1.setRequestMethod("GET");
              conn1.setRequestProperty("Authorization", "Bearer " + token);
              conn1.setRequestProperty("Content-Type", "application/json");  	      
              try (BufferedReader reader1 = new BufferedReader(
        	      new InputStreamReader(conn1.getInputStream()))) {
        	      for (String line1; (line1 = reader1.readLine()) != null; ) {
        	              result1.append(line1);
        	              String segments[] = result1.toString().split(",");
        	              for (int i1 = 0; i1<30; i1++) {
        	            	  try {
        	            	   if(i1 > 15) {
        	            		  c = c + 1;
        	            	      if (segments[i1].startsWith("\"host\"")) {
        	            	    	  host1 = segments[i1].toString().replace("\"host\":\"","");
        	            	    	  host2 = host1.replace("\"","");
        	            	    	  finalhost = host2;
        	            	    	  getLogger().info(finalhost);
        	            	      };
        	            	      if (segments[i1].startsWith("\"port\"")) {
        	            	    	  port1 = segments[i1].toString().replace("\"port\":","");
        	            	    	  finalport = port1;
        	            	    	  getLogger().info(finalport);
        	            	      };
        	            	      if (!(finalhost ==null) || !(finalport ==null)) {
        	            	    	    InetSocketAddress socketAddress = new InetSocketAddress(finalhost, 54138);
        	            	    	    ServerInfo info = ProxyServer.getInstance().constructServerInfo("duels", socketAddress, "ac", false);
        	            	    	    ProxyServer.getInstance().getServers().put("duels", info);
        	            	      }
        	            	   };
      
        	            	  } catch (Exception e) {
        	            		  getLogger().severe("**** UNHANDLED EXCEPTION ****");
        	            		  getLogger().severe(e.toString());
        	            	  }
        	              }
        	              result1 = null;
        	              line1 = null;
        	              c = 0;
        	     }
              }
              TimeUnit.SECONDS.sleep(3);
    	} catch (Exception e) {
    		getLogger().severe("**** FAILED TO START SERVER ****");
    		getLogger().severe(e.toString());
    	}
    	}).start();
    }
}
