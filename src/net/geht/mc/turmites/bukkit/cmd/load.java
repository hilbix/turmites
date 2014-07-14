package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.cmdMain;
import net.geht.mc.turmites.bukkit.cmds;

import java.util.List;

@SuppressWarnings("ALL")
public class load extends cmds
  {
  public load()
    {
      super("load", 1);
      allowConsole = false;
    }

  @Override
  public String run(cmdMain main, List<String> args)
    {
      return main.getBookIO().load(args.get(0), main.player);
    }
  }
