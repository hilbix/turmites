// tool cmd:
//	Give special tool to player

package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.cmdMain;
import net.geht.mc.turmites.bukkit.cmds;

import java.util.List;

import org.bukkit.entity.Player;

@SuppressWarnings("ALL")
public class tool extends cmds
  {
  public tool()
    {
      super("tool", 0, 0);
      allowConsole = false;
    }

  @Override
  public String run(cmdMain main, List<String> args)
    {
      // main.getPlayer()
      return null;
    }
  }
