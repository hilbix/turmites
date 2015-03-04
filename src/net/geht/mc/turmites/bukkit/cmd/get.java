package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.CmdMain;
import net.geht.mc.turmites.bukkit.CheckArgs;
import net.geht.mc.turmites.ex.MissingArgumentException;
import net.geht.mc.turmites.ex.NotFoundException;
import net.geht.mc.turmites.ex.TooManyArgumentsException;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("ALL")
public class get extends GetSet
  {
  public get()
    {
      super("get", 0, -1);
//      allowConsole = false;
    }

  private static enum Gets implements CheckArgs
    {
      player_pos(-1, "[player..]: show players' pos"),
      ;

      private final String help;
      public String getHelp() { return help; }
      public String getName() { return name().replace('_','.'); }

      Gets(int min, int max, String help)
        {
	  this.minargs = min;
	  this.maxargs = max;
          this.help = help;
        }
      Gets(int max, String help)
	{
	  this.minargs = 0;
	  this.maxargs = max;
	  this.help = help;
	}

      private int	minargs, maxargs;

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
    };

  @Override
  public String run(CmdMain main, List<String> args)
    {
      if (args.size()==0)
        {
	  main.out("keys for: " + getName(main));
	  for (Gets s : Gets.values())
	    main.out(s.getName() + ": " + s.getHelp());
	  main.out("see also: set");
	  return null;
        }

      Gets cmd;

      try {
        cmd	= Gets.valueOf(args.get(0).replace(".", "_"));
      } catch (IllegalArgumentException e) {
	return args.size()==1 ? getset(main, args) : "unknown key: "+args.get(0);
      }

      args = args.subList(1, args.size());
      cmd.checkArgs(args);

      switch (cmd)
	{
	default:
	  return "not yet implemented: "+cmd.getName();

	case player_pos:	return player_get_pos(main, args);

	}
//      return null;
    }

  private String player_get_pos(CmdMain main, List<String> args)
    {
      List<Player> ply;

      if (args.size()>0)
	{
	  if (main.isPlayer())
	    throw new TooManyArgumentsException().e("not allowed to query position of other players");
	  ply = new LinkedList<Player>();
	  for(String s : args)
	    try {
	      ply.add(main.getPlayer(s));
	    } catch (NotFoundException e) {
	      main.warn("ignoring unknown player: " + s);
	    }
	}
      else if (main.isPlayer())
	ply = Arrays.asList( main.getPlayer() );
      else
	ply = main.getAllPlayers();

      boolean ok=false;
      for (Player p : ply)
	{
	  ok=true;
	  Location l = p.getLocation();
	  main.printf("%6d %3d %6d %s", l.getBlockX(), l.getBlockY(), l.getBlockZ(), p.getName());
	}
      if (!ok)
	main.warn("no players found");
      return null;
    }
  }
