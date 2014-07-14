package net.geht.mc.turmites.ex;

public class WriteErrorException extends UncheckedException
  {
  public WriteErrorException()
    {
      hint = "write error";
    }
  };
