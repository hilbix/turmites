package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.CmdMain;
import net.geht.mc.turmites.bukkit.Cmds;

import java.util.List;

@SuppressWarnings("ALL")
public class set extends Cmds
  {
  public set()
    {
      super("set", 0, 2);
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

  @Override
  public String run(CmdMain main, List<String> args)
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
        cmd	= Sets.valueOf(args.get(0).replace(".","_"));
      } catch (java.lang.IllegalArgumentException e) {
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
	  return "unknown "+cmd.name();

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
	  return "you cannot set "+cmd.name();

	case player_allow_fly:	main.getPlayer().setAllowFlight(from_bool(main, val));		break;
	case player_fly:	main.getPlayer().setFlySpeed (floatPercent(main, val));		break;
	case player_walk:	main.getPlayer().setWalkSpeed(floatPercent(main, val));		break;
        }
      return getters(main, cmd);
    }

  private int intRange(CmdMain main, String s, int min, int max)
    {
      int i = Integer.parseInt(s);

      if (i<min)
	main.OOPS("value less than "+min+": "+s);
      if (i>max)
	main.OOPS("value exeeds "+max+": "+s);
      return i;
    }
  private float floatPercent(CmdMain main, String s)
    {
      return intRange(main, s, 1,100)/100.f;
    }
  private String to_deniedallowed(Boolean allow)
    {
      return allow ? "allowed" : "denied";
    }
  private Boolean from_bool(CmdMain main, String s)
    {
      // String Switch is Java7
      if (s=="0" || s=="false" || s=="off" || s=="no")
	return false;

      if (s=="1" || s=="true" || s=="on" || s=="yes")
	return true;

      main.OOPS("need a boolean value (false/0/no/off, true/1/yes/on)");
      return false;
    }
  }
