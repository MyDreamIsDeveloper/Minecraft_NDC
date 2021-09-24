package com.windows.ndc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ndc extends JavaPlugin
  implements Listener
{
  public boolean Connection = false;
  private final String USER_AGENT = "Mozilla/5.0";
	private String Address = "http://ipip.kr";
	private URL Url;
	private BufferedReader br;
	private HttpURLConnection con;
	private String protocol = "GET";
	private String IP = null;
	private String Domain = "";

  public void onEnable()
  {
		try {
			Url = new URL(this.Address);
		} catch (MalformedURLException e) {
		}
		try {
			con = (HttpURLConnection)Url.openConnection();
		} catch (IOException e) {
		}
		try {
			con.setRequestMethod(protocol);
		} catch (ProtocolException e) {
		}
		con.setRequestProperty("USER-Agent", USER_AGENT);
		try {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		} catch (IOException e) {
		}
		String line;
		String ip = null;
		try {
			while((line = br.readLine())!= null){
			if (line.startsWith("<title>Your IP is ")){
				ip = line.replaceAll("Your IP is ", "").replaceAll("<title>", "").replaceAll("</title>", "");
			}
			}
		} catch (NullPointerException e1) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Not Double Connection ]");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "인터넷 연결 상태가 올바르지 않습니다.");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
				try {
				Thread.sleep(10000L);
			} catch (InterruptedException e) {
			}
				Bukkit.shutdown();
		} catch (IOException e) {
		}
		try {
			br.close();
		} catch (IOException e) {
		}
  	      try {
	  	        IP = InetAddress.getAllByName(Domain)[0].getHostAddress();
	  	        if (!ip.equalsIgnoreCase(IP)) {
	  	        	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Not Double Connection ]");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매자 도메인의 아이피와 일치하지 않습니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Thread.sleep(10000L);
	  				Bukkit.shutdown();
	  	        }
	  	      }
	  	      catch (UnknownHostException e1) {
		  	    	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Not Double Connection ]");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매 시 등록했던 도메인이 유효하지 않습니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				try {
						Thread.sleep(10000L);
					} catch (InterruptedException e) {
					}
	  				Bukkit.shutdown();
	  	      } catch (InterruptedException e) {
			}
		  BufferedInputStream in = null;
			String strUrl = "http://cafe.naver.com/MemoList.nhn?search.clubid=27833593&search.menuid=5";
			StringBuffer sb = new StringBuffer();
			
			try {
				URL url = new URL(strUrl);
				URLConnection urlConnection = url.openConnection();
				in = new BufferedInputStream(urlConnection.getInputStream());
				
				byte[] bufRead = new byte[40960];
				int lenRead = 0;
				while ((lenRead = in.read(bufRead)) > 0)
					sb.append(new String(bufRead, 0, lenRead));

			} catch (IOException ioe) {}
			if (sb.toString().contains("[" + Domain + "]")) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Not Double Connection ]");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "해당 플러그인은 현재 차단된 상태이므로 사용하실 수 없습니다.");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e1) {
			}
			Bukkit.shutdown();
			return;
			}
    Connection = getConfig().getBoolean("접속여부");
    getServer().getPluginManager().registerEvents(this, this);
    Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §a동시접속 방지 플러그인 활성화");
  }
  
  public void onDisable() {
	  	Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §c동시접속 방지 플러그인 비활성화");
	    saveConfig();
	  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    Player p = (Player)sender;
    if (label.equals("동접"))
    {
      if (p.hasPermission("admin"))
      {
        if (!Connection)
        {
          Connection = true;
          Bukkit.broadcastMessage(ChatColor.YELLOW + "[WINDOWS] §b동시접속 방지시스템이 시작되었습니다.");
          getConfig().set("접속여부", true);
          saveConfig();
          return true;
        }

        Connection = false;
        Bukkit.broadcastMessage(ChatColor.YELLOW + "[WINDOWS] §b동시접속 방지시스템이 중지되었습니다.");
        getConfig().set("접속여부", false);
        saveConfig();
        return true;
      }

      p.sendMessage(ChatColor.YELLOW + "[WINDOWS] §c당신은 권한이 없습니다.");
      return true;
    }
    return true;
  }
  
  @EventHandler(priority=EventPriority.HIGH)
  public void onPlayerLogin(PlayerLoginEvent event) {
	  BufferedInputStream in = null;
		String strUrl = "http://cafe.naver.com/MemoList.nhn?search.clubid=27833593&search.menuid=5";
		StringBuffer sb = new StringBuffer();
		
		try {
			URL url = new URL(strUrl);
			URLConnection urlConnection = url.openConnection();
			in = new BufferedInputStream(urlConnection.getInputStream());
			
			byte[] bufRead = new byte[40960];
			int lenRead = 0;
			while ((lenRead = in.read(bufRead)) > 0)
				sb.append(new String(bufRead, 0, lenRead));

		} catch (IOException ioe) {}
		if (sb.toString().contains("[" + Domain + "]")) {
			event.disallow(null, "§4[WINDOWS]\n§e동접방지 플러그인이 제작자에 의해 차단되었습니다.");
			for (Player p : Bukkit.getOnlinePlayers()) {
			p.kickPlayer("§4[WINDOWS]\n§e동접방지 플러그인이 제작자에 의해 차단된 상태입니다.\n차단이 풀리기전까지 해당 플러그인을 사용할 수 없습니다.");
			}
			Bukkit.shutdown();
			} else if (sb.toString().contains("[" + event.getPlayer().getName().toLowerCase() + "]")) {
			event.disallow(null, "§4[WINDOWS] §e해당 아이디는 블랙리스트에 등록된 아이디입니다.");
			}
		try {
			Url = new URL(this.Address);
		} catch (MalformedURLException e) {
		}
		try {
			con = (HttpURLConnection)Url.openConnection();
		} catch (IOException e) {
		}
		try {
			con.setRequestMethod(protocol);
		} catch (ProtocolException e) {
		}
		con.setRequestProperty("USER-Agent", USER_AGENT);
		try {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		} catch (IOException e) {
		}
		String line;
		String ip = null;
		try {
			while((line = br.readLine())!= null){
			if (line.startsWith("<title>Your IP is ")){
				ip = line.replaceAll("Your IP is ", "").replaceAll("<title>", "").replaceAll("</title>", "");
			}
			}
		} catch (IOException e) {
		}
		try {
			br.close();
		} catch (IOException e) {
		}
  	      try {
	  	        IP = InetAddress.getAllByName(Domain)[0].getHostAddress();
	  	        if (!ip.equalsIgnoreCase(IP)) {
	  	        	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Not Double Connection ]");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매자 도메인의 아이피와 일치하지 않습니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Thread.sleep(10000L);
	  				Bukkit.shutdown();
	  	        }
	  	      }
	  	      catch (UnknownHostException e1) {
		  	    	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "                       [ Not Double Connection ]");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "구매 시 등록했던 도메인이 유효하지 않습니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "서버를 종료합니다.");
	  				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●●");
	  				try {
						Thread.sleep(10000L);
					} catch (InterruptedException e) {
					}
	  				Bukkit.shutdown();
	  	      } catch (InterruptedException e) {
			}
    String User = event.getPlayer().getName();
    if (Connection)
    {
    for (Player Players : getServer().getOnlinePlayers())
    {
      String Other = Players.getPlayer().getName();
      if (event.getPlayer().hasPermission("ndc")) {
    	  return;
      }
      if (Players.getAddress().toString().split(":")[0].replaceFirst("/", "").equals(event.getAddress().getHostAddress())) {
      		event.disallow(null, "§e[WINDOWS] §4한 아이피당 하나의 계정만 접속 가능합니다.");
			Player players[] = getServer().getOnlinePlayers();
			for (int i = 0; i < players.length; i++) {
				if (players[i].isOp())
					players[i].sendMessage(ChatColor.YELLOW + "[WINDOWS] §c" + Other + " §b와 §c" + User + " §b의 동시접속이 감지되었습니다.");
			}
      }
    }
  }
  }
}