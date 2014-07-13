package net.geht.mc.turmites.bukkit;

// Why never use checked exceptions in Java?
// Because if you augment some sub-sub-sub-class with a new exception,
// you definitively do not want to alter billions of zillions of functions with those new exceptions,
// and perhaps need to alter thousands of standard library functions which are in the way, too.
// Checked exceptions are killing OOP.  Period.
@SuppressWarnings("MethodReturnOfConcreteClass")
public class UncheckedException extends RuntimeException
  {
  protected String hint;

  public String getHint() { return hint; }
  public UncheckedException e(String s) { hint += ": "+s; return this; }
  public UncheckedException e(Exception e) { return this.e(e.toString()); }
  }
