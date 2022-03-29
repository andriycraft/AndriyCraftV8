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
		String nick = arg0.getName();
		ProxiedPlayer player = ProxyServer.getInstance().getPlayer(nick);

		if(arg1.length > 1 || arg1.length == 1) {
			arg0.sendMessage(ChatColor.RED + "Тут не треба якихось додаткових аргументів");
			return;
		}		
		if (player == null) {
			arg0.sendMessage(ChatColor.RED + "Ви не можете користуватися цією командою з консолі");
			return;
		}
		ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + nick + " lobby");
	}

}
