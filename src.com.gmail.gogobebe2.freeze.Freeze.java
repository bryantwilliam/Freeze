package com.gmail.gogobebe2.freeze;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Freeze extends JavaPlugin implements Listener {

	ArrayList<String> frozen = new ArrayList<String>();

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (frozen.contains(p.getName())) {
			e.setTo(e.getFrom());
			p.sendMessage(ChatColor.RED + "Mad bro?" + ChatColor.ITALIC
					+ " hahahha an admin or someone froze you!!");
		}
	}

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		
		if (!sender.hasPermission("freeze.use")) {
			sender.sendMessage(ChatColor.RED
					+ "You are not allowed to use this command!");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("freeze")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED
						+ "Are you kidding me? Please tell me how just typing /freeze without a parameter would work. Please. Idiot...");
				return true;
			}
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED
						+ " isn't a player or can't be found moron.");
				return true;
			}
			if (frozen.contains(target.getName())) {
				frozen.remove(target.getName());
				sender.sendMessage(ChatColor.GREEN + "Player "
						+ target.getName() + " has been unfrozen.");
				target.sendMessage(ChatColor.GOLD
						+ "ok ok, you're unfrozen. Off you go.");
				return true;
			}
			frozen.add(target.getName());
			target.sendMessage(ChatColor.GOLD
					+ "xD someone just froze you. Get rekt.");
			sender.sendMessage(ChatColor.GREEN + "Player " + target.getName()
					+ " has been frozen! " + ChatColor.RED + "hehe >:}"
					+ ChatColor.DARK_PURPLE + " r8 8/8");
		}
		return true;
	}

}
