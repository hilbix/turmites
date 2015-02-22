package net.geht.mc.turmites.ex;

public class NeedMoreInventoryException extends UncheckedException
  {
  public NeedMoreInventoryException()
    {
      hint = "not enough inventory slots";
    }
  public NeedMoreInventoryException(int n)
    {
      hint = "need "+n+" more inventory slots";
    }
  };
