package net.geht.mc.turmites.bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdExecutor implements CommandExecutor
  {
  private main   main;
  public  String name;

  public void DI(String s) { main.DI(s); }

  public cmdExecutor(main main, String name)
    {
      this.main = main;
      this.name = name;
    }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
//      if (!this.name.equalsIgnoreCase(command.getName()))
//	return main.onCommand(sender, command, label, args);

      return new cmdMain(this, sender, command, label).run(args);
    }

   public main getMain() { return main; }
  }
