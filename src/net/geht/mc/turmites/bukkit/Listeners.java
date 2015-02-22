package net.geht.mc.turmites.bukkit;

import net.geht.mc.turmites.Main;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class Listeners implements Listener
  {
  Main main;

  public Listeners(Main main)
    {
      this.main = main;
      main.DI("listeners activated");
    }

  public void DI(Event e, String... s)
    {
      String m="";

      for (int i=0; i<s.length; i++)
	m += ": "+s[i];
      main.DI("ev "+e.getEventName()+": "+e.toString()+m);
    }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onLogin(PlayerJoinEvent ev)
    {
      DI(ev);
    }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onLogout(PlayerQuitEvent ev)
    {
      DI(ev);
    }

  @EventHandler(priority = EventPriority.LOWEST)
  protected void onPlayerInteract(PlayerInteractEvent ev)
    {
      DI(ev);
    }
  }
