package net.geht.mc.turmites.ex;

public class NotFoundException extends UncheckedException
  {
  public NotFoundException()
    {
      hint = "not found";
    }
  };
