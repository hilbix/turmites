package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.cmdMain;
import net.geht.mc.turmites.bukkit.cmds;

import java.util.List;

@SuppressWarnings("ALL")
public class save extends cmds
  {
  public save()
    {
      super("save", 1);
      allowConsole = false;
    }

  @Override
  public String run(cmdMain main, List<String> args)
    {
      return main.getBookIO().save(args.get(0), main.playerItemInHand());
    }
  }
