**Note: This is work in progress, it does not do something reasonable today**

**Warning!**  This is alpha-code.  It should do no harm, but perhaps it must be disabled on the server because it causes trouble.


Turmites for Bukkit
===================

This is CLLed, see COPYRIGHT.CLL or http://permalink.de/tino/cll

Copyright is slavery in disguise.  So you are not allowed to encumber this with a Copyright, ever.
So this is and says free as in free speech, free beer and free man.


Build
-----

This needs `pkg-config`:

```bash
apt-get install pkg-config
```


```bash
cd ~/bukkit/plugins # where your bukkit plugins are located
git clone https://github.com/hilbix/turmites.git
cd turmites
git submodule update --init
make
make install
```

`reload` Bukkit (enter `reload` on the server console) or restart it.


Usage
-----

- enter `/turmites help` in chat

- permissions: T.B.D. (I never came around to understand the Bukkit permission system yet, sorry.)

- For Bukkit configuration, see the [doc/bukkit.md](doc/bukkit.md)

- For introduction, see the [doc/turmites.md](doc/turmites.md)

- If you think something is missing there, give me a note at [https://hydra.geht.net/pager.php](https://hydra.geht.net/pager.php)

- The source is an IntelliJ IDEA repository.  I don't know if it works with the community edition.

- **Sorry, I cannot help you with installation problems as I have no time for this.**


Notes
-----

- This was tested on CraftBukkit-1.8 only, sorry.

- Turmites probably interfere with other plugins which modify Droppers.

- On my server Turmites will replace every other plugin, except dynmap ;)

- Feel free to clone it on GitHub and change it.

- If you wan to send me PullRequests, you previously must agree to drop your Copyright on all changes you made!

- My server http://mc18.geht.net/ is nonpublic and perhaps never will go public.

- It is MC 1.8 and it will stay there for a while, as I only update my MC world when it is worth the effort.  Before it was 1.5.2 and "horses only" was not enough to have me update.

