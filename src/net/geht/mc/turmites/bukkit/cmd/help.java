package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.CmdMain;
import net.geht.mc.turmites.bukkit.Cmds;

import java.util.List;

@SuppressWarnings("ALL")
public class help extends Cmds
  {
  public help()
    {
      super("help", 0);
    }

  @Override
  public String run(CmdMain main, List<String> args)
    {
      main.list();
      return null;
    }
  }
