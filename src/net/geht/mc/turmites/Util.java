package net.geht.mc.turmites;

import net.geht.mc.turmites.ex.NeedMoreInventoryException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Util
  {
  static public final int freeSlots(Inventory inv)
    {
      int n = 0;
      for (ItemStack stack : inv)
	if (null == stack)
	  n++;
      return n;
    }
  static public final int usedSlots(Inventory inv)
    {
      int n = 0;
      for (ItemStack stack : inv)
	if (null != stack)
	  n++;
      return n;
    }
  static public final int usedSlots(Player p) { return usedSlots(p.getInventory()); }
  static public final int freeSlots(Player p) { return freeSlots(p.getInventory()); }

  static public void assertSlots(Player p, int n)
    {
      n -= freeSlots(p);
      if (n>0)
	throw new NeedMoreInventoryException(n);
    }

  static public final Comparator<File> FILENAMECOMPARATOR = new Comparator<File>()
  {
    @Override
    public int compare(File o1, File o2)
      {
	return o1.getName().compareTo(o2.getName());
      }
  };

  static public final File[] sortFilesByName(File[] files)
    {
      Arrays.sort(files, FILENAMECOMPARATOR);
      return files;
    }
  }
