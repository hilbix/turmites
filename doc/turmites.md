**Note: This is work in progress, it does not do something reasonable today**

# Turmites for Bukkit

Despite the name, this plugin usually runs only a single Turmite.

It supports more than one Turmite, though, if you like.  Each Turmite runs in it's own thread and dispatches it's own events, so for N turmites, N threads are needed.  This might easily overload your server, so beware!


## Getting started

- Place some container in the game

- Configure the exact coordinates (x, y and z) of the container to the turmite configuration

- Put a book into the first slot of the container.

- Write the program into that book.

Probably it is wise to use a chest.  Double chests are not really supported.

The catch of a chest is, that you can put in more than one book, such that you can extend the basic book by them.

### Initial variables

When a turmite starts, the registers are set as follows:

- a: The object holding all library functions
- b: The book which is executed
- c: The container the turmite is in
- d to z: The NULL object

The access to the library is unlimited, so you can control the complete server.


### Running multiple turmites in parallel

Turmites are not limited to their initial book.  Any book page in the game can be executed.

This way you can implement additional turmites yourself.  A possible strategy to create more turmites in the game will be:

- Enumerate all chests in the game
- Look into each chest if a certain book is present there
- Prepare new suitable variables a, b and c for the invocation
- Evaluate the first page of the book in the given chest with the suitable parameters in parallel.

If you created everything in a suitable way, you then will have a chest which runs like a turmite.

- You can limit the functions which are available to the turmite, as it only can call the library functions which are available.
- As you are able to enumerate all chests in the game regardless of their position, they do not need to have a fixed coordinate.
- And so on.

You can implement everything yourself.  The turmite implementation just needs to provide the basic library for functions you need.

For the language see [lang.md](lang,md) and for the standard library see [lib.md](lib.md).


## About

Turmites are small turing machines living on an unlimited 2D pane.  They were introduced by Scientific American.

However there they were easy state machines not capable of many things.  This is a nice mathematical idea, but not much fun to use in a game.  To be more fun, turmites can do much more.


### What are turmites

- Turmites are blocks in Minecraft which have a fixed position in the game.  The coordinate of this blocks is given in the Turmite configuration.

- Turmites look into their inventory for a book on a certain place.  They then start to read commands from page 1 and interpret and execute them.

- The commands can refer to other books.  This books can be located anywhere on the server.

- Turmites have no state between invocations.  So when the server starts, turmites boot from the beginning.

- Turmites are object oriented.  Everything is an object.

- Turmites do not have classes nor inheritance, as classes and inheritance are the most clumsy way to do OOP.

- Objects can have an unlimited number of properties and methods.

- Operators are methods.  There is no operator precedence, so use parentheses wisely.

- Turmites are register machines.  They have 26 local registers (a-z) which can hold any object.

- Method arguments are passed in registers as unevaluated objects.  So you can have maximum 26 parameters each call.

- Turmites are stack machines.  There are an unlimited number of stacks.  Each method call has it's own stack.

- Turmites are able to evaluate net.geht.mc.turmites.data.things in parallel.  This is semi-parallel timesharing of the single execution thread.

- Turmites process a fixed number of program steps each tick.  Usually 1 step per tick, this is 20 commands per second.



### Relationship to Turtles

There are Turtles for Bukkit (TODO: Add link here).  These are (probably) very powerful thingies with a nice idea of a language.  They might be fun as well, but difficult to handle, I think.  It's like "If you have APL, why need another language?"  Also I never got them running on Bukkit, because they have trainloads of dependencies.

Turmites can act like Turtles, however they are programmed differently.  Except of this they are not related to Turtles in any way.  The idea behind Turmites is completely different.
