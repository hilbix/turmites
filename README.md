**Note: This is work in progress, it does not do something reasonable today**

**Warning!**  This is pre-alpha-code.  It should do no harm, but perhaps it must be disabled on the server because it causes trouble.

Bukkit Turmites
===============

License: CLL see http://permalink.de/tino/cll


Usage
-----

- This is tested on Bukkit/Spoutcraft 1.5.2 only.

- It is an IntelliJ Idea project.  No idea how to compile it from commandline, sorry.
  After build the JAR is in `out/artifacts/turmites_jar/turmites.jar`.
  If not, create the missing Artifact `turmites.jar`, see http://stackoverflow.com/a/9463915

- This needs https://github.com/hilbix/Bukkit.git as a dependency at
  `../Bukkit/target/bukkit-1.5.2-R1.0-hilbix.jar`

  To build this do from this directory here:
  `cd ..; git clone https://github.com/hilbix/Bukkit.git; cd Bukkit; mvn clean install`
  (With IntelliJ: Create a new project, View::Tool Windows::Maven Projects
   and then in Bukkit::Livecycle with both `clean` and `install` selected do a rightclick
   and choose `Run Maven Build`.  I did not find out yet how to automate this.)

- If you dare you can alternatively download my 1.5.2 JAR from
URL: http://mc.geht.net/turmites/turmites.jar
(if the server is up) but this might be an intermediate development version.

- Drop the `turmites.jar` into your `plugins/` directory.

- For Bukkit configuration, see the [doc/bukkit.md](doc/bukkit.md)

- For introduction, see the [doc/turmites.md](doc/turmites.md)

- If you think something is missing there, give me a note at [https://hydra.geht.net/pager.php](https://hydra.geht.net/pager.php)

- The source is an IntelliJ IDEA repository.  I don't know if it works with the community edition.

- **Sorry, I cannot help you with installation problems as I have no time for this.**


Notes
-----

- Turmites probably interfere with other plugins which modify Droppers.

- On my server Turmites will replace COMMAND-blocks and other plugins like WorldEdit.

- Feel free to clone it on GitHub, change it and send me PullRequests if you agree to drop your Copyright on all changes you made!

- My server mc.geht.net is 1.5.2 and I do not have the time to keep track of all newer changes.  (I do not need horses.)

- My server mc.geht.net is non-public.  Perhaps it never will go public.

- All changes to my server will be published on GitHub under https://github.com/hilbix/ when I find the time.
