package net.geht.mc.turmites.ex;

public class FailedCommandException extends UncheckedException
  {
  public FailedCommandException()
    {
      hint = "command failed";
    }
  };
