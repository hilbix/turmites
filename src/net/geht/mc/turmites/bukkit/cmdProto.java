package net.geht.mc.turmites.bukkit;

import java.util.List;
import org.bukkit.entity.Player;

public abstract class cmdProto
  {
  private String name;
  private int min, max;
  protected boolean allowConsole = true;
  protected boolean allowPlayer  = true;

  public String getName() { return name; }

  public cmdProto(String name, int min, int max)
    {
      this.name = name;
      this.min = min;
      this.max = max;
    }
  public cmdProto(String name, int min)
    {
      this.name = name;
      this.min = min;
      this.max = min;
    }
  public cmdProto(String name)
    {
      this.name = name;
      this.min = 0;
      this.max = 0;
    }

  public void checkPlayer(Player p)
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

  public void checkArgs(int l)
    {
      if (l < min)
	throw new MissingArgumentException();
      if (l > max && max >= min)
	throw new TooManyArgumentsException();
    }


  public abstract String run(cmdMain main, List<String> args);
  }
