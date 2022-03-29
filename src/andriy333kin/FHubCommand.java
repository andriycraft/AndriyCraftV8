package andriy333kin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class FHubCommand extends Command {

	public FHubCommand(Main main) {
	    super("fhub");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender arg0, String[] arg1) {
		if(arg1.length > 1 || arg1.length == 1) {
			arg0.sendMessage(ChatColor.RED + "Òóò íå òðåáà ÿêèõîñü äîäàòêîâèõ àðãóìåíò³â");
			return;
		}		
		String nick = arg0.getName();
		ProxiedPlayer player = ProxyServer.getInstance().getPlayer(nick);
		if (player == null) {
			arg0.sendMessage(ChatColor.RED + "Âè íå ìîæåòå êîðèñòóâàòèñÿ ö³ºþ êîìàíäîþ ç êîíñîë³");
			return;
		}
		ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + nick + " lobby"); // Convert
	}

}
