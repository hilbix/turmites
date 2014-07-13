package net.geht.mc.turmites.bukkit.cmds;

import net.geht.mc.turmites.bukkit.cmdProto;
import net.geht.mc.turmites.bukkit.cmdMain;

import java.util.List;

@SuppressWarnings("ALL")
public class help extends cmdProto
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
