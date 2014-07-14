package net.geht.mc.turmites.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Project: turmites
 * User: tino
 * Date: 2013-10-29 08:49
 */
public final class listen implements Listener
  {
  public listen()
    {
    }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onLogin(PlayerJoinEvent event)
    {

    }

  @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
  public void onLogout(PlayerQuitEvent event)
    {

    }
  }
