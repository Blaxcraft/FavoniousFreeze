package net.mcshockwave.Freeze;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FreezePlugin extends JavaPlugin implements Listener {
	public static boolean	freezeEnabled	= false;

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.isOp()) {
			freezeEnabled = !freezeEnabled;

			sender.sendMessage("§7Freeze §6" + (freezeEnabled ? "enabled" : "disabled"));
		}
		return true;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Location f = event.getFrom();
		Location t = event.getTo();
		if ((freezeEnabled) && ((f.getX() != t.getX()) || (f.getZ() != t.getZ()))) {
			while (f.getBlock().getType() != Material.AIR && f.getY() > 0) {
				f.setY(f.getY() - 0.5);
			}
			event.setTo(f);
		}
	}
}
