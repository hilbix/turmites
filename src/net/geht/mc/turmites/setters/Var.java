package net.geht.mc.turmites.setters;

import net.geht.mc.turmites.Setter;
import net.geht.mc.turmites.Val;
import net.geht.mc.turmites.ex.NotFoundException;

import java.util.LinkedList;
import java.util.NoSuchElementException;

// Generic variable
// Holds just a value
public class Var implements Setter
  {
  protected Val v;

  Var(Val v)
    {
      this.v = v;
    }

  @Override
  public Val set(Val v)
    {
      if (null != v)
	this.v = v;
      else if (null == this.v)
	throw new NotFoundException().e("variable not yet initialized");
      return this.v;
    }
  }
