// This is used by "get" and "set"

package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.CmdMain;
import net.geht.mc.turmites.bukkit.Cmds;

import java.util.List;

@SuppressWarnings("ALL")
public abstract class GetSet extends Cmds
  {
  public GetSet(String name, int min, int max)
    {
      super(name, min, max);
//      allowConsole = false;
    }

  private static enum Sets
    {
      player_allow_fly("walk speed in percent"),
      player_fly("fly speed in percent"),
      player_walk("walk speed in percent"),
      ;

      private final String help;
      public String getHelp() { return help; }
      public String getName() { return name().replace('_','.'); }

      Sets(String help)
        {
          this.help = help;
        }
    };

  public String getset(CmdMain main, List<String> args)
    {
      if (args.size()==0)
        {
	  main.out("keys for: " + getName(main));
	  for (Sets s : Sets.values())
	    main.out(s.getName() + ": " + s.getHelp());
	  return null;
        }

      Sets cmd;

      try {
        cmd	= Sets.valueOf(args.get(0).replace(".", "_"));
      } catch (IllegalArgumentException e) {
        return "unknown value: "+args.get(0);
      }

      if (args.size()==1)
        return getters(main, cmd);
      else
        return setters(main, cmd, args.get(1));
    }

  private String getters(CmdMain main, Sets cmd)
    {
      String s;

      switch (cmd)
	{
	default:
	  return "you canot get: "+cmd.getName();

	case player_allow_fly:	s = "Player is " + to_deniedallowed(main.getPlayer().getAllowFlight()) + " flight";	break;
	case player_fly:	s = "Player is " + (100*main.getPlayer().getFlySpeed());	break;
	case player_walk:	s = "Player is " + (100*main.getPlayer().getWalkSpeed());	break;
        }
      main.out(cmd.name() + " of " + s);
      return null;
    }

  private String setters(CmdMain main, Sets cmd, String val)
    {
      String s;

      switch (cmd)
	{
	default:
	  return "you cannot set: "+cmd.name();

	case player_allow_fly:	main.getPlayer().setAllowFlight(from_bool(main, val));		break;
	case player_fly:	main.getPlayer().setFlySpeed (floatPercent(main, val));		break;
	case player_walk:	main.getPlayer().setWalkSpeed(floatPercent(main, val));		break;
        }
      return getters(main, cmd);
    }
  }
