package net.geht.mc.turmites.ex;

public class NotConsoleException extends UncheckedException
  {
  public NotConsoleException()
    {
      hint = "not on console";
    }
  };
