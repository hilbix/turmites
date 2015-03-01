package net.geht.mc.turmites.bukkit;

// This will be - incompatibly(!) - restructured into net.geht.mc.turmites.data.books in future

import net.geht.mc.turmites.ex.DataErrorException;
import net.geht.mc.turmites.ex.MalformedBookNameException;
import net.geht.mc.turmites.ex.NotFoundException;
import net.geht.mc.turmites.ex.ReadErrorException;
import net.geht.mc.turmites.ex.WriteErrorException;
import net.geht.mc.turmites.Util;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Pattern;

public class BookIO
  {
  private File path;

  // Filenames form Books are stored at:
  // Dir<player>Player<book>Bundle/*Ext
  // Dir<player>Player<book>Ext
  // DirPublic<book>Bundle/*Ext
  // DirPublic<book>Ext
  private static final String DIR = "books/";
  private static final String PLAYER = ".nam/";
  private static final String PUBLIC = "";
  private static final String BUNDLE = "";
  private static final String EXT = ".yml";

  private static final Pattern        check     = Pattern.compile("[a-z0-9][-a-z0-9]*");
  private static final FilenameFilter extFilter = new FilenameFilter()
  {
    @Override
    public boolean accept(File dir, String name)
      {
	return name.endsWith(EXT);
      }
  };

  public BookIO(File p)
    {
      path = p;
    }

  public boolean validBookName(CharSequence name)
    {
      return check.matcher(name).matches();
    }

  private File bookFile(String player, String name, String ext)
    {
      if (!validBookName(name))
	throw new MalformedBookNameException();
      if (null == player)
	player = "";
      return new File(path, DIR + player + name + ext);
    }

  private File bookFile(String player, String name)
    {
      return bookFile(player, name, EXT);
    }

  private String bookPlayerName(Player p)
    {
      return p.getName().toLowerCase() + PLAYER;
    }

  private File bookDir(String player, String name)
    {
      return bookFile(player, name, BUNDLE);
    }

  private ItemStack load1(File f)
    {
      YamlConfiguration y = new YamlConfiguration();
      try {
	y.load(f);
      } catch(IOException e) {
	throw new ReadErrorException().e(e);
      } catch(InvalidConfigurationException e) {
	throw new DataErrorException().e(e);
      }

      String title = y.getString("t");
      String author = y.getString("a");

      ItemStack is = new ItemStack(null==author ? Material.BOOK_AND_QUILL : Material.WRITTEN_BOOK, 1);

      BookMeta book = (BookMeta) is.getItemMeta();
      book.setTitle(title);
      book.setAuthor(author);
      book.setPages(y.getStringList("p"));
      is.setItemMeta(book);

      return is;
    }

  private ItemStack load1(String player, String name)
    {
      return load1(bookFile(player, name));
    }

  public ItemStack[] load(String player, String name)
    {
      File fd;

      fd = bookDir(player, name);
      if (!fd.isDirectory())
	{
	  fd = bookFile(player, name);
	  if (!fd.isFile())
	    return null;
	  // load normal files
	  return new ItemStack[] { load1(player, name) };
	}

      // load a book bundle (directory)
      File[] files = Util.sortFilesByName(fd.listFiles(extFilter));

      ItemStack is[] = new ItemStack[files.length];
      for (int i=0; i<files.length; i++)
	is[i] = load1(files[i]);

      return is;
    }

  public String load(Player p, String name)
    {
      ItemStack[] is;

      // Try
      // Dir<player>Player<book>Bundle/*Ext
      // Dir<player>Player<book>Ext
      is = load(bookPlayerName(p), name);
      if (null == is)
	{
	  // Try
	  // DirPublic<book>Bundle/*Ext
	  // DirPublic<book>Ext
	  is = load("", name);
	  if (null == is)
	    throw new NotFoundException();
	}

      Util.assertSlots(p, is.length);

      PlayerInventory pi = p.getInventory();
      for (ItemStack item : is)
	pi.addItem(item);
      return null;
    }

  public String save(Player p, String name, BookMeta book)
    {
      YamlConfiguration y = new YamlConfiguration();
      y.set("t", book.getTitle());
      y.set("a", book.getAuthor());
      y.set("p", book.getPages());
      try {
	y.save(bookFile(bookPlayerName(p), name));
      } catch(IOException e) {
	throw new WriteErrorException().e(e);
      }
      return null;
    }

  public String save(Player p, String name, ItemStack i)
    {
      if (null == i)
	return "you must hold something";

      Material m = i.getType();
      if (!m.equals(Material.WRITTEN_BOOK) && !m.equals(Material.BOOK_AND_QUILL))
	return "not holding a book: " + i.getType().toString();
      return save(p, name, (BookMeta) i.getItemMeta());
    }

  public String save(Player p, String name)
    {
      return save(p, name, p.getItemInHand());
    }
  }
