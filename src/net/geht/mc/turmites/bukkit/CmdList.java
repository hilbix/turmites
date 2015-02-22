package net.geht.mc.turmites.bukkit;

import net.geht.mc.turmites.bukkit.cmd.*;

public final class CmdList
  {
  public static final Class commands[] =
    {
      help.class,
      save.class,
      load.class,
      Book.class,
      Exec.class,
      list.class,
      slow.class,
      stop.class,
      halt.class,
      Cont.class,
      step.class,
      Debug.class,
      set.class,
      tool.class,
    };

  @SuppressWarnings("unchecked")
  public static final Cmds getCmds(int n)
    {
      try {
	  return ((Class<? extends Cmds>)commands[n]).newInstance();
      } catch(InstantiationException e) {
      } catch(IllegalAccessException e) {
      }
      return null;
      //DI("problematic " + CmdList.commands[n].toString());
    }
  }
