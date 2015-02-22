package net.geht.mc.turmites.bukkit;

import net.geht.mc.turmites.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdExecutor implements CommandExecutor
  {
  private Main   main;
  public  String name;

  public void DI(String s) { main.DI(s); }

  public CmdExecutor(Main main, String name)
    {
      this.main = main;
      this.name = name;
    }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
//      if (!this.name.equalsIgnoreCase(command.getName()))
//	return main.onCommand(sender, command, label, args);

      return new CmdMain(this, sender, command, label).run(args);
    }

  public Main getMain() { return main; }
  }
