package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.cmdMain;
import net.geht.mc.turmites.bukkit.cmds;
import org.bukkit.entity.Player;

import java.util.List;

@SuppressWarnings("ALL")
public class save extends cmds
  {
  public save()
    {
      super("save", 1);
      allowConsole = false;
    }

  @Override
  public String run(cmdMain main, List<String> args)
    {
      Player p = main.getPlayer();

      return main.getBookIO().save(p, args.get(0), p.getItemInHand());
    }
  }
