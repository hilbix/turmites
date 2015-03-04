// Turmite generic represented value
// May become more complex in future, but for now it basically is a string.
package net.geht.mc.turmites;

import net.geht.mc.turmites.ex.DataErrorException;

public class Val implements Setter
  {
  private String s;

  @Override
  public Val set(Val v)
    {
      return null == v ? this : from(v);
    }

  // Generic value is the empty string (currently)
  // It may become a "undefined" object which later can be defined
  public Val()
    {
      super();
      from("");
    }

  public Val(String s)
    {
      super();
      from(s);
    }

  public Val(Val v)
    {
      super();
      from(v);
    }

  public Val from(String s)
    {
      if (null == s)
	throw new DataErrorException().e("null string not supported by turmite value");
      this.s = s;
      return this;
    }

  public Val from(Val v)
    {
      return from(v.toString());
    }

  @Override
  public String toString()
    {
      return s;
    }

  @Override
  public boolean equals(Object obj)
    {
      return obj instanceof Val && obj.toString()==s;
    }

  @Override
  public int hashCode()
    {
      return s.hashCode();
    }

  @Override
  protected void finalize()
    {
      s=null;
    }

  @Override
  protected Object clone()
    {
      return new Val(this);
    }
  }
