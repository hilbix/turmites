package net.geht.mc.turmites.data;

// A bunch of books
// Books have some special processing due to it's nature

import org.bukkit.Material;

public class Books extends Things
  {
  private boolean valid(Thing t)
    {
      // ENCHANTED_BOOK?
      return t.isType(Material.WRITTEN_BOOK) || t.isType(Material.BOOK_AND_QUILL);
    }
  }
