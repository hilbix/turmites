// tool cmd:
//	Give special tool to player

package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.CmdMain;
import net.geht.mc.turmites.bukkit.Cmds;

import java.util.List;

@SuppressWarnings("ALL")
public class tool extends Cmds
  {
  public tool()
    {
      super("tool", 0, 0);
      allowConsole = false;
    }

  @Override
  public String run(CmdMain main, List<String> args)
    {
      // main.getPlayer()
      return null;
    }
  }
