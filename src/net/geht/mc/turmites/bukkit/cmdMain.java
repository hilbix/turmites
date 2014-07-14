package net.geht.mc.turmites.bukkit;

import net.geht.mc.turmites.ex.FailedCommandException;
import net.geht.mc.turmites.ex.NotPlayerException;
import net.geht.mc.turmites.ex.UncheckedException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class cmdMain
  {
  public cmdExecutor   cmd;
  public CommandSender sender;
  public Command       command;
  public String        label;
  public Player        player;

  private boolean                     had;
  private LinkedHashMap<String, cmds> cmdMap;

  public void DI(String s) { cmd.DI(s); }

  public cmdMain(cmdExecutor cmd, CommandSender sender, Command command, String label)
    {
      this.cmd = cmd;
      this.sender = sender;
      this.command = command;
      this.label = label;
      had = false;
      player = sender instanceof Player ? (Player) sender : null;
      cmdMap = new LinkedHashMap<String, cmds>();

      for (int i = 0; i < cmdList.commands.length; i++)
	{
	  try
	    {
	      //noinspection LocalVariableOfConcreteClass
	      cmds add = ((Class<? extends cmds>) cmdList.commands[i]).newInstance();

	      String name = add.getName().toLowerCase();
	      if (cmdMap.containsKey(name))
		DI("duplicate " + name);
	      cmdMap.put(name, add);
	      continue;
	    } catch(InstantiationException e)
	    {
	    } catch(IllegalAccessException e)
	    {
	    }
	  DI("problematic " + cmdList.commands[i].toString());
	}
    }

  private boolean put(String s)
    {
      had = true;
      sender.sendMessage(s);
      return true;
    }

  private boolean err(String s) { return put(ChatColor.RED + s); }

  private boolean warn(String s) { return put(ChatColor.DARK_RED + s); }

  private boolean note(String s) { return put(ChatColor.GREEN + s); }

  private boolean out(String s) { return put(ChatColor.WHITE + s); }

  public boolean run(String[] args)
    {
      if (args.length < 1)
	return err("try 'help " + cmd.name + "' to see a list of possible commands");

      //noinspection LocalVariableOfConcreteClass
      cmds c = cmdMap.get(args[0].toLowerCase());
      if (null == c)
	return err("unknown command, try 'help " + cmd.name + '\'');

      try
	{
	  List<String> l = Arrays.asList(args).subList(1, args.length);

	  c.checkPlayer(player);
	  c.checkArgs(l.size());

	  String s = c.run(this, l);
	  if (null != s)
	    throw new FailedCommandException().e(s);
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
      for (cmds proto : cmdMap.values())
	out(proto.getName());
    }

  public ItemStack playerItemInHand()
    {
      if (null == player)
	throw new NotPlayerException();
      return player.getItemInHand();
    }

  public bookIO getBookIO()
    {
      return cmd.getMain().getBookIO();
    }
  }
