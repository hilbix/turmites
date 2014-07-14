package net.geht.mc.turmites.ex;

public class MalformedBookNameException extends UncheckedException
  {
  public MalformedBookNameException()
    {
      hint = "malformed book name";
    }
  };
