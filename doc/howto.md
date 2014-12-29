**This is planned.  This documentation is terribly incomplete today.**


# Turmites for Bukkit

This want to help you getting started with turmites.


## HowTo

- Place some container in the game.  I suggest to use a dropper or a normal chest.  Double chests are not supported.

- Configure the exact coordinates (x, y and z) of the container to the turmite configuration (T.B.D.)

- Put a book into the first slot of the container.

- Write the program into that book.

The catch of a container is, that you can put in more than one book, such that you can extend the basic book by them.

The downside of this is:

- The position is fixed as it is loaded at server boot time, so you must not move the initial container, ever.  If the container is not found on server startup, the turmite fails and nothing executes.

- There is only a single turmite (which can run an unlimited number of parallel functions acting like Turmites).  You can run more initial Turmites if you want to, but each consumes a full thread in the Minecraft server, so keep their count as low as possible.  Usually a single one should be enough for your needs. (There might be more in case you want to run several implementations from others.)

- You somehow should protect the contents of the container against griefing.  Perhaps you should use a written book, too, as this cannot be altered anymore.  There is no particular way to archive this.  For example you can program the turmite to defend itself, you embed it in bedrock, or use other plugins to protect the blocks.


## How turmites work

Turmites are object oriented register machines with a very basic set of commands and a very basic initial object library.

- Commands can be grouped together for form a program.
- Programs are stored in books and can be addressed via the page number.
- Turmites have 26 registers, a to z, each can hold an object.
- An object is something which can be manipulated by the Turmite, like a Minecraft block.
- Like in other programming languages, numbers, strings, arrays and so on, are objects, too.

When a turmite starts, the registers are set as follows:

- a: The object holding all library functions
- b: The book which is executed
- c: The container the turmite is in
- d to z: The NULL object

The access to the library is unlimited and unrestricted, so you can control the complete server.

- You can replace the library with one, which is restricted, to limit the functions somebody else can run.

- You can crontrol, which books are available to run.  With written books you can create limited versions of Turmites which can be freely distributed on the server and used by anybody who owns such a book.

- Using written books you can even create certain Turmite releases, which, for example, can be sold on the server, if some economy plugin is installed.  As this are normal books, trading books etc. does not interfere with Turmites.


## Your first program

T.B.D.


## Running multiple turmites in parallel

Turmites are not limited to their initial book.  Any book page in the game can be executed.  Which book is executed is controlled by the turmite.  And Turmites can execute books in parallel.  However they slow down when they spread out this way, as the number of operations per tick is limited.

Running books in parallel is the way to implement additional turmites.  A possible strategy to create more turmites in the game will be:

- Enumerate all chests in the game
- Look into each chest if a certain book is present there
- Prepare new suitable variables a, b and c for the invocation
- Start a background evaluation of the first page of the book in the given chest with this parameters.

If done correctly, you then will have a chest which runs like a turmite itself.  These chests can be movable.

- You can limit the functions which are available to the turmite, as it only can call the library functions it has access to.
- As you are able to enumerate all chests in the game regardless of their position, they do not need to have a fixed coordinate.
- And so on.

You can implement everything yourself.  The turmite implementation just needs to provide the basic library for functions you need.

For the language see [lang.md](lang,md) and for the standard library see [lib.md](lib.md).

