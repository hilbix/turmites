package net.geht.mc.turmites.bukkit;

public class MissingArgumentException extends UncheckedException
  {
  public MissingArgumentException()
    {
      hint = "missing argument";
    }
  };
