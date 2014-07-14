package net.geht.mc.turmites.bukkit;

// This will be - incompatibly(!) - restructured into net.geht.mc.turmites.data.books in future

import net.geht.mc.turmites.ex.DataErrorException;
import net.geht.mc.turmites.ex.MalformedBookNameException;
import net.geht.mc.turmites.ex.ReadErrorException;
import net.geht.mc.turmites.ex.WriteErrorException;
import net.geht.mc.turmites.util;
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
import java.util.Arrays;
import java.util.regex.Pattern;

public class bookIO
  {
  private File path;

  private static final String DIR = "books/";
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

  public bookIO(File p)
    {
      path = p;
    }

  public boolean validBookName(CharSequence name)
    {
      return check.matcher(name).matches();
    }

  private File bookFile(String name, String ext)
    {
      if (!validBookName(name))
	throw new MalformedBookNameException();
      return new File(path, DIR + name + ext);
    }

  private File bookFile(String name)
    {
      return bookFile(name, EXT);
    }

  private File bookDir(String name)
    {
      return bookFile(name, "");
    }

  private ItemStack load1(File f)
    {
      YamlConfiguration y = new YamlConfiguration();
      try
	{
	  y.load(f);
	} catch(IOException e)
	{
	  throw new ReadErrorException().e(e);
	} catch(InvalidConfigurationException e)
	{
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

  private ItemStack load1(String name)
    {
      return load1(bookFile(name));
    }

  public ItemStack[] load(String name)
    {
      File dir;

      // load normal files
      if (!(dir = bookDir(name)).isDirectory())
	return new ItemStack[] { load1(name) };

      // load a book bundle (directory)
      File[] files = util.sortFilesByName(dir.listFiles(extFilter));

      ItemStack is[] = new ItemStack[files.length];
      for (int i=0; i<files.length; i++)
        is[i] = load1(files[i]);

      return is;
    }

  public String load(String name, Player p)
    {
      ItemStack[] is = load(name);
      util.assertSlots(p, is.length);

      PlayerInventory pi = p.getInventory();
      for (ItemStack item : is)
	pi.addItem(item);
      return null;
    }

  public String save(String name, BookMeta book)
    {
      YamlConfiguration y = new YamlConfiguration();
      y.set("t", book.getTitle());
      y.set("a", book.getAuthor());
      y.set("p", book.getPages());
      try
	{
	  y.save(bookFile(name));
	} catch(IOException e)
	{
	  throw new WriteErrorException().e(e);
	}
      return null;
    }

  public String save(String name, ItemStack i)
    {
      if (null == i)
	return "you must hold something";

      Material m = i.getType();
      if (!m.equals(Material.WRITTEN_BOOK) && !m.equals(Material.BOOK_AND_QUILL))
	return "this is not a book: " + i.getType().toString();
      return save(name, (BookMeta) i.getItemMeta());
    }

  public String save(String name, Player p)
    {
      return save(name, p.getItemInHand());
    }
  }
