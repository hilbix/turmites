package net.geht.mc.turmites.bukkit;

public class NotConsoleException extends UncheckedException
  {
  public NotConsoleException()
    {
      hint = "not on console";
    }
  };
