package net.geht.mc.turmites.bukkit;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class bookIO
  {
  private File path;
  private Pattern check;

  public bookIO(File p)
    {
      path = p;
      check = Pattern.compile("[a-z0-9][-a-z0-9]*");
    }

  public boolean validBookName(CharSequence name)
    {
      return check.matcher(name).matches();
    }
  private File bookFile(String name) throws MalformedBookNameException
    {
      if (!validBookName(name))
	throw new MalformedBookNameException();
      return new File(path, name+".yml");
    }

  public BookMeta load(String name)
    {
      YamlConfiguration y = new YamlConfiguration();
      try
	{
	  y.load(bookFile(name));
	}
      catch (IOException e)
	{
	  throw new ReadErrorException().e(e);
	}
      catch (InvalidConfigurationException e)
	{
	  throw new DataErrorException().e(e);
	}

      BookMeta book = (BookMeta)new ItemStack(Material.WRITTEN_BOOK, 1).getItemMeta();
      book.setTitle(y.getString("t"));
      book.setAuthor(y.getString("a"));
      book.setPages(y.getString("p"));
      return book;
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
	}
      catch (IOException e)
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
	return "this is not a book: "+i.getType().toString();
      return save(name, (BookMeta)i.getItemMeta());
    }
  public String save(String name, Player p)
    {
      return save(name, p.getItemInHand());
    }
  }
