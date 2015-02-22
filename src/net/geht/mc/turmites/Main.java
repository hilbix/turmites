package net.geht.mc.turmites;

import net.geht.mc.turmites.bukkit.BookIO;
import net.geht.mc.turmites.bukkit.CmdExecutor;
import net.geht.mc.turmites.bukkit.Listeners;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.Nullable;

public final class Main extends JavaPlugin implements Listener
  {
  @Nullable
  private        PluginDescriptionFile desc;
  private        BookIO                BookIO;
  private static Main                  me;

  public static Main getMain()
    {
      return me;
    }

    /*
    private Economy getEcon()
      {
	if (null==Bukkit.getPluginManager().getPlugin("Vault"))
	  return null;
	RegisteredServiceProvider<Economy> sp = Bukkit.getServicesManager().getRegistration(Economy.class);
	return sp==null ? null : sp.getProvider();
      }
    */

  public Main()
    {
      desc = null;
    }

  public void DI(String s)
    {
      if (desc != null)
	getLogger().info(desc.getName() + " V" + desc.getVersion() + ": " + s);
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
      getCommand("turmite").setExecutor(new CmdExecutor(this, "turmite"));
      BookIO = new BookIO(getDataFolder());
      getServer().getPluginManager().registerEvents(new Listeners(this), this);
    }

  public BookIO getBookIO()
    {
      return BookIO;
    }
  }
