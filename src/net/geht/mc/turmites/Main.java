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
      getLogger().info(s);
    }

  private void DIV(String s)
    {
      DI(desc.getName() + " V" + desc.getVersion() + ": " + s);
    }

  @Override
  public void onDisable()
    {
      DIV("disabled");
      desc = null;
      super.onDisable();
    }

  @Override
  public void onEnable()
    {
      desc = this.getDescription();
      super.onEnable();
      DIV("enabled");
      getCommand("turmite").setExecutor(new CmdExecutor(this, "turmite"));
      BookIO = new BookIO(getDataFolder());
      getServer().getPluginManager().registerEvents(new Listeners(this), this);
    }

  public BookIO getBookIO()
    {
      return BookIO;
    }
  }
