# Turmites for Bukkit

## Getting started

- Put `turmites.jar` into the `plugins` folder, like all the other plugins
- Restart your server or run `reload`
- Go into the `Turmites` folder
- Edit `config.yml`
- Edit the turmite container coordinate in the file
- Issue `tur boot` on the server console

Now enter the game.

- At the coordinate given in the `config.yml` file, place a chest.
- Put a book into the chest
- The first page of the book can read `a.debug "Hello World";`
- Now enter `/tur boot` on the server console
- You will see `Hello world` output of the turmite

## Operating systems for turmites

Turmites are books.  There are commands to load/save books already.  But they are perhaps not handy for turmites.

So there are some easy console commands to load/save books from files.  These files must be in `Turmites/Books`.  For your convenience names which start with a dot `.` or slash `/` are ignored.

- `/tur load BOOK`: loads `BOOK.txt` as a book.
- `/tur save BOOK`: saves the book you are holding to `BOOK.txt` and renames an existing book as `BOOK.#.txt` where `#` is a running counter.
- `/tur load BOOK #`: loads an older revision of a book
- `/tur books`: lists all known books

You can bundle books.  Just  create a folder which contains several books.  These books then are loaded together, ASCII-ordered by name.  You cannot save books named after a bundle.

### Books format

The books format is a bit different to all other book tools.  It allows you to efficiently skip pages and to more easily navigate in text books.

- The first line is the book title
- The second line is the book author
- Then pages follow

A page has following formatted lines:

- `\Page #` where `#` is a number
- Then the lines of the page.
- `\` for empty lines (else they are ignored)
- `\\` if you need to add a line which starts with a `\`
