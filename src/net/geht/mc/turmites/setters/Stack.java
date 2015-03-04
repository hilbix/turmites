package net.geht.mc.turmites.setters;

import net.geht.mc.turmites.Setter;
import net.geht.mc.turmites.Val;
import net.geht.mc.turmites.ex.NotFoundException;

import java.util.LinkedList;
import java.util.NoSuchElementException;

// Generic stack class
// push: set(string)
// pop:  set(null)
public class Stack extends LinkedList<Val> implements Setter
  {
  @Override
  public Val set(Val v)
    {
      if (null == v)
	{
	  try {
	    v = pop();
	  } catch (NoSuchElementException e) {
	  }
	  if (null == v)
	    throw new NotFoundException().e("empty stack");
	  return v;
	}
      push(v);
      return v;
    }
  }
