package net.geht.mc.turmites.ex;

public class MissingArgumentException extends UncheckedException
  {
  public MissingArgumentException()
    {
      hint = "missing argument";
    }
  };
