package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.CmdMain;
import net.geht.mc.turmites.bukkit.Cmds;

import java.util.List;

@SuppressWarnings("ALL")
public class load extends Cmds
  {
  public load()
    {
      super("load", 1);
      allowConsole = false;
    }

  @Override
  public String run(CmdMain main, List<String> args)
    {
      return main.getBookIO().load(main.getPlayer(), args.get(0));
    }
  }
