package net.geht.mc.turmites.bukkit.cmds;

import net.geht.mc.turmites.bukkit.cmdProto;
import net.geht.mc.turmites.bukkit.cmdMain;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

@SuppressWarnings("ALL")
public class save extends cmdProto
  {
  public save()
    {
      super("save", 1);
      allowConsole = false;
    }

  @Override
  public String run(cmdMain main, List<String> args)
    {
      return main.getBookIO().save(args.get(0), main.playerItemInHand());
    }
  }
