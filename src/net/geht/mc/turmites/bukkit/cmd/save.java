package net.geht.mc.turmites.bukkit.cmd;

import net.geht.mc.turmites.bukkit.CmdMain;
import net.geht.mc.turmites.bukkit.Cmds;
import org.bukkit.entity.Player;

import java.util.List;

@SuppressWarnings("ALL")
public class save extends Cmds
  {
  public save()
    {
      super("save", 1);
      allowConsole = false;
    }

  @Override
  public String run(CmdMain main, List<String> args)
    {
      Player p = main.getPlayer();

      return main.getBookIO().save(p, args.get(0), p.getItemInHand());
    }
  }
