package net.geht.mc.turmites.bukkit;

public class NotPlayerException extends UncheckedException
  {
  public NotPlayerException()
    {
      hint = "not a player";
    }
  };
