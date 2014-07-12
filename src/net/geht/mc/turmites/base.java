package net.geht.mc.turmites;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.Nullable;

import static org.bukkit.Bukkit.getPluginManager;

/**
 * Project: turmites
 * User: tino
 * Date: 2013-10-06 19:08
 */
public final class base extends JavaPlugin
  {
    @Nullable
    private PluginDescriptionFile desc;

    /*
    private Economy getEcon()
      {
	if (null==Bukkit.getPluginManager().getPlugin("Vault"))
	  return null;
	RegisteredServiceProvider<Economy> sp = Bukkit.getServicesManager().getRegistration(Economy.class);
	return sp==null ? null : sp.getProvider();
      }
    */


    public base()
      {
	desc = null;
      }

    public void DI(String s)
      {
	if (desc != null)
          getLogger().info(desc.getName() + " V" + desc.getVersion() + " "+s);
      }
    @Override
    public void onDisable()
      {
	DI("disabled");
	desc = null;
	super.onDisable();
      }

    @Override
    public void onEnable()
      {
	desc = this.getDescription();
	DI("enabled");
	super.onEnable();
      }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
      {
	Player player = sender instanceof Player ? (Player) sender : null;

	if (!"turmite".equalsIgnoreCase(command.getName()))
	  return super.onCommand(sender, command, label, args);

	if (null == player)
	  sender.sendMessage(ChatColor.GOLD + "turmite command on console!");
	else
	  player.sendMessage(ChatColor.AQUA + "turmite command from " + player.getName());
	return true;
      }
  }
