package net.geht.mc.turmites.bukkit;

import net.geht.mc.turmites.ex.MissingArgumentException;
import net.geht.mc.turmites.ex.NotConsoleException;
import net.geht.mc.turmites.ex.NotPlayerException;
import net.geht.mc.turmites.ex.TooManyArgumentsException;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Cmds implements CheckArgs
  {
  private String	name;
  protected boolean	allowConsole = true;
  protected boolean	allowPlayer  = true;

  public String getName()		{ return name; }
  public String getName(CmdMain main)	{ return main.getCmdName() + ' ' + name; }

  public Cmds(String name, int min, int max)
    {
      this.name = name;
      this.minargs = min;
      this.maxargs = max;
    }

  public Cmds(String name, int min)
    {
      this.name = name;
      this.minargs = min;
      this.maxargs = min;
    }

  public Cmds(String name)
    {
      this.name = name;
      this.minargs = 0;
      this.maxargs = 0;
    }

  protected void checkPlayer(Player p)
    {
      if (null == p)
	{
	  if (!allowConsole)
	    throw new NotPlayerException();
	}
      else
	{
	  if (!allowPlayer)
	    throw new NotConsoleException();
	}
    }

  private int		minargs, maxargs;

  // Java isn't dry.  It isn't even wet.  It's like a hurricane not leaving only debris behind.  'Nuff said.
  public void checkArgs(int l)
    {
      if (l < minargs)
	throw new MissingArgumentException();
      if (l > maxargs && maxargs >= minargs)
	throw new TooManyArgumentsException();
    }

  public void checkArgs(List<?> l)
    {
      checkArgs(l.size());
    }

  protected int intRange(CmdMain main, String s, int min, int max)
    {
      int i = Integer.parseInt(s);

      if (i<min)
	main.OOPS("value less than "+min+": "+s);
      if (i>max)
	main.OOPS("value exeeds "+max+": "+s);
      return i;
    }
  protected float floatPercent(CmdMain main, String s)
    {
      return intRange(main, s, 1,100)/100.f;
    }
  protected String to_deniedallowed(Boolean allow)
    {
      return allow ? "allowed" : "denied";
    }
  protected Boolean from_bool(CmdMain main, String s)
    {
      // String Switch is Java7
      if (s=="0" || s=="false" || s=="off" || s=="no")
	return false;

      if (s=="1" || s=="true" || s=="on" || s=="yes")
	return true;

      main.OOPS("need a boolean value (false/0/no/off, true/1/yes/on)");
      return false;
    }

  public abstract String run(CmdMain main, List<String> args);
  }
