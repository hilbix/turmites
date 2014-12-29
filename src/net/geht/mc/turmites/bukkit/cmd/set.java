package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.cmdMain;
import net.geht.mc.turmites.bukkit.cmds;

import org.bukkit.entity.Player;

import java.util.List;

@SuppressWarnings("ALL")
public class set extends cmds
  {
  public set()
    {
      super("set", 1, 2);
//      allowConsole = false;
    }

  private static enum cmds
    {
      player_fly,
      player_walk,
    };

  @Override
  public String run(cmdMain main, List<String> args)
    {
      Enum cmd;

      try {
        cmd	= cmds.valueOf(args.get(0));
      } catch (java.lang.IllegalArgumentException e) {
        return "unknown value: "+args.get(0);
      }

      if (args.size()==1)
        return getters(main, cmds.valueOf(args.get(0)));
      else
        return setters(main, cmds.valueOf(args.get(0)), args.get(1));
    }

  private String getters(cmdMain main, cmds cmd)
    {
      String s;

      switch (cmd)
	{
	default:
	  return "unknown "+cmd.name();

	case player_fly:
	  s = "Player is " + (100*main.getPlayer().getFlySpeed());
	  break;

	case player_walk:
	  s = "Player is " + (100*main.getPlayer().getWalkSpeed());
	  break;
        }
      main.out(cmd.name() + " of " + s);
      return null;
    }

  private String setters(cmdMain main, cmds cmd, String val)
    {
      String s;

      switch (cmd)
	{
	default:
	  return "you cannot set "+cmd.name();

	case player_fly:  main.getPlayer().setFlySpeed (intRange(main, val, 1,100)/100.f); break; 
	case player_walk: main.getPlayer().setWalkSpeed(intRange(main, val, 1,100)/100.f); break;
        }
      return getters(main, cmd);
    }

  private int intRange(cmdMain main, String s, int min, int max)
    {
      int i = Integer.parseInt(s);

      if (i<min)
	main.OOPS("value less than "+min+": "+s);
      if (i>max)
	main.OOPS("value exeeds "+max+": "+s);
      return i;
    }
  }
