package net.geht.mc.turmites.bukkit;

import net.geht.mc.turmites.bukkit.cmd.book;
import net.geht.mc.turmites.bukkit.cmd.cont;
import net.geht.mc.turmites.bukkit.cmd.debug;
import net.geht.mc.turmites.bukkit.cmd.exec;
import net.geht.mc.turmites.bukkit.cmd.halt;
import net.geht.mc.turmites.bukkit.cmd.help;
import net.geht.mc.turmites.bukkit.cmd.list;
import net.geht.mc.turmites.bukkit.cmd.load;
import net.geht.mc.turmites.bukkit.cmd.save;
import net.geht.mc.turmites.bukkit.cmd.set;
import net.geht.mc.turmites.bukkit.cmd.slow;
import net.geht.mc.turmites.bukkit.cmd.step;
import net.geht.mc.turmites.bukkit.cmd.stop;

public final class cmdList
  {
  public static final Class[] commands =
    {
      help.class,
      save.class,
      load.class,
      book.class,
      exec.class,
      list.class,
      slow.class,
      stop.class,
      halt.class,
      cont.class,
      step.class,
      debug.class,
      set.class,
    };
  }
