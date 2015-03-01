package net.geht.mc.turmites.bukkit;

import net.geht.mc.turmites.ex.MissingArgumentException;
import net.geht.mc.turmites.ex.NotConsoleException;
import net.geht.mc.turmites.ex.NotPlayerException;
import net.geht.mc.turmites.ex.TooManyArgumentsException;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.List;

// Nope, we cannot be a class, because this here is for Enums!
public interface CheckArgs
  {
//
//  public MinMaxArgs(int min, int max)
//    {
//      this.minarg = min;
//      this.maxarg = max;
//    }
//
//  public MinMaxArgs(int nr)
//    {
//      this.minarg = nr;
//      this.maxarg = nr;
//    }

  // Due to a stupid Java Design Decision, you must copy this code into each of this interface classes
  // Imagine it would be 1.5 TiB of code and you have a million of classes.  Nice try, Java, you are apparently plain crap.

  // Copy following code to your class and uncomment it.  Remove the
// private int	minargs, maxargs;
  public void checkArgs(int l)
//    {
//      if (l < minargs)
//	throw new MissingArgumentException();
//      if (l > maxargs && maxargs >= minargs)
//	throw new TooManyArgumentsException();
//    }
//
//  public void checkArgs(List<?> l)
//    {
//      checkArgs(l.size());
//    }
  ;
  }
