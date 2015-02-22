package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.CmdMain;
import net.geht.mc.turmites.bukkit.Cmds;

import java.util.List;

@SuppressWarnings("ALL")
public class Book extends Cmds
  {
  public Book()
    {
      super("book", 0, 1);
    }

  @Override
  public String run(CmdMain main, List<String> args)
    {
      return "not yet implemented";
      //return main.getBookIO();
    }
  }
