package net.geht.mc.turmites.data;

// A list of anything

import javafx.scene.text.FontSmoothingType;

import java.util.WeakHashMap;

public class Things extends WeakHashMap<Thing, Integer>
  {
  private boolean useweak = true;	// use weak references


  public Things()
    {
    }

  public synchronized void adder(Thing t,  Integer count)
    {
      if (containsKey(t))
	count += get(t);
      if (count<0)
	{
	  count = 0;
	}
    }

  public void add(Thing t, int count) 	{ adder(t, count); }
  public void add(Thing t)		{ adder(t, 1); }
  public void del(Thing t, int count)	{ adder(t, -count); }
  public void del(Thing t)		{ adder(t, -1); }
  }
