package net.geht.mc.turmites;

import net.geht.mc.turmites.bukkit.bookIO;
import net.geht.mc.turmites.bukkit.cmdExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.Nullable;

public final class main extends JavaPlugin
  {
  @Nullable
  private        PluginDescriptionFile              desc;
  private        net.geht.mc.turmites.bukkit.bookIO bookIO;
  private static main                               me;

  public static main getMain()
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


  public main()
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
      getCommand("turmite").setExecutor(new cmdExecutor(this, "turmite"));
      bookIO = new bookIO(getDataFolder());
    }

  public bookIO getBookIO()
    {
      return bookIO;
    }
  }
