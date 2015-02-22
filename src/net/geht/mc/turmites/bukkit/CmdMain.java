package net.geht.mc.turmites.bukkit;

import net.geht.mc.turmites.ex.FailedCommandException;
import net.geht.mc.turmites.ex.NotPlayerException;
import net.geht.mc.turmites.ex.UncheckedException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class CmdMain
  {
  public CommandSender sender;
  public Command       command;
  public String        label;

  private CmdExecutor				cmd;
  private Player                		player;
  private boolean				had;
  private static LinkedHashMap<String, Cmds>	cmdMap = null;

  public void DI(String s) { cmd.DI(s); }

  private static void initCmdMap()
    {
      cmdMap = new LinkedHashMap<String, Cmds>();

      for (int i = 0; i < CmdList.commands.length; i++)
	{
	  Cmds add = CmdList.getCmds(i);
	  String name = add.getName().toLowerCase();
//	  if (cmdMap.containsKey(name))
//	    DI("duplicate " + name);
	  cmdMap.put(name, add);
	}
    }

  public CmdMain(CmdExecutor cmd, CommandSender sender, Command command, String label)
    {
      this.cmd = cmd;
      this.sender = sender;
      this.command = command;
      this.label = label;

      player = sender instanceof Player ? (Player) sender : null;

      if (cmdMap == null)
	initCmdMap();
    }

  private boolean put(String s)
    {
      had = true;
      sender.sendMessage(s);
      return true;
    }

  public boolean err(String s)  { return put(ChatColor.RED + s); }
  public boolean warn(String s) { return put(ChatColor.DARK_RED + s); }
  public boolean note(String s) { return put(ChatColor.GREEN + s); }
  public boolean out(String s)  { return put(ChatColor.WHITE + s); }
  public void    OOPS(String s) { throw new FailedCommandException().e(s); }

  public BookIO getBookIO()  { return cmd.getMain().getBookIO(); }
  public String getCmdName() { return cmd.name; }

  public boolean run(String[] args)
    {
      had = false;

      if (args.length < 1)
	return err("try 'help " + cmd.name + "' to see a list of possible commands");

      //noinspection LocalVariableOfConcreteClass
      Cmds c = cmdMap.get(args[0].toLowerCase());
      if (null == c)
	return err("unknown command, try 'help " + cmd.name + '\'');

      try
	{
	  c.checkPlayer(player);

	  List<String> l = Arrays.asList(args).subList(1, args.length);
	  c.checkArgs(l.size());

	  String s = c.run(this, l);
	  if (null != s)
	    OOPS(s);
	} catch(UncheckedException e)
	{
	  return err("'" + cmd.name + ' ' + c.getName() + "' error: " + e.getHint());
	}
      if (!had)
	note("OK: " + cmd.name + ' ' + c.getName());
      return true;
    }

  public void list()
    {
      for (Cmds c : cmdMap.values())
	out(c.getName());
    }

  public Player getPlayer()
    {
      if (null == player)
	throw new NotPlayerException();
      return player;
    }

  }
