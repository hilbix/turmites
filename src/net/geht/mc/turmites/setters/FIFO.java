package net.geht.mc.turmites.setters;

import net.geht.mc.turmites.Setter;
import net.geht.mc.turmites.Val;
import net.geht.mc.turmites.ex.NotFoundException;

import java.util.LinkedList;
import java.util.NoSuchElementException;

// Generic (unidirectional) queue class
// push: set(string)
// pull: set(null)
public class FIFO extends LinkedList<Val> implements Setter
  {
  @Override
  public Val set(Val v)
    {
      if (null == v)
	{
	  try {
	    v = remove();
	  } catch (NoSuchElementException e) {
	  }
	  if (null == v)
	    throw new NotFoundException().e("empty queue");
	  return v;
	}
      push(v);
      return v;
    }
  }
