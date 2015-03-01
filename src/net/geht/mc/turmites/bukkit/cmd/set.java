// Stub class, everything is done in GetSet
// to be reused in "get"

package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.CmdMain;

import java.util.List;

@SuppressWarnings("ALL")
public class set extends GetSet
  {
  public set()
    {
      super("set", 0, 2);
//      allowConsole = false;
    }

  @Override
  public String run(CmdMain main, List<String> args)
    {
      return getset(main, args);
    }
  }
