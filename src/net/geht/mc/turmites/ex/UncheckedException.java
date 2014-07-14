package net.geht.mc.turmites.ex;

// Why never use checked exceptions in Java?
//
// Because if you augment some sub-sub-sub-class with a new exception,
// you definitively do not want to pollute zillions of methods with those new exceptions,
// perhaps even some readonly standard library methods which are in the way, too.
//
// Checked exceptions mean death to OOP.  Period.
//
// This here is the one to bind them all, such that you can easily refer to them

@SuppressWarnings("MethodReturnOfConcreteClass")
public class UncheckedException extends RuntimeException
  {
  protected String hint;

  public String getHint() { return hint; }
  public UncheckedException e(String s) { hint += ": "+s; return this; }
  public UncheckedException e(Exception e) { return this.e(e.toString()); }
  }
