package net.geht.mc.turmites.ex;

public class NotPlayerException extends UncheckedException
  {
  public NotPlayerException()
    {
      hint = "not a player";
    }
  };
