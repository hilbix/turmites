package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.cmdMain;
import net.geht.mc.turmites.bukkit.cmds;

import java.util.List;

@SuppressWarnings("ALL")
public class help extends cmds
  {
  public help()
    {
      super("help", 0);
    }

  @Override
  public String run(cmdMain main, List<String> args)
    {
      main.list();
      return null;
    }
  }
