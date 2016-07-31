# I like `make`.  No `maven`, no complex documentation, nothing, just `make`.
# Why re-invent the wheel in a different shape when it's already perfect?

INSTDIR=jar

ME=turmites
DEST=$(ME).jar
SRCDIR=src

JZMQ=jzmq

SUBMODULES=src/org/intellij src/org/jetbrains $(JZMQ)

PLUGIN=plugin.yml
MF=META-INF/MANIFEST.MF

# Well, we could maintain an overwhelming set of dependencies.
# However this will save less than 1s per make on my VM here, so why ever bother?
SRC=
SRC+=$(SRCDIR)/*/*/*/*.java
SRC+=$(SRCDIR)/*/*/*/*/*.java
SRC+=$(SRCDIR)/*/*/*/*/*/*.java
SRC+=$(SRCDIR)/*/*/*/*/*/*/*.java

OUT=tmp
SPIGOT=spigot
CB=spigot
CLASSPATH=src:$(CB).jar

JAVAC=javac -cp '$(CLASSPATH)'
JAVACME=mkdir -p '$(OUT)/$(ME)' && $(JAVAC) -d '$(OUT)/$(ME)'

all:	$(DEST)

it:	all
	make -C ../..
	bukkit reload
	bukkit

sh:
	exec bash

# cmd "install" is not guaranteed to be atomic, hence DIY, sigh
install:	all
	cp -f '$(DEST)' '$(INSTDIR)/$(DEST).tmp'
	mv -f '$(INSTDIR)/$(DEST).tmp' '$(INSTDIR)/$(DEST)'

$(DEST):	compile
	@echo
	jar cfm '$(DEST)' '$(SRCDIR)/$(MF)' -C '$(SRCDIR)' '$(PLUGIN)' -C '$(OUT)/$(ME)' .
	jar i '$(DEST)'

$(SUBMODULES):
	git submodule update --init

compile:	$(CB).jar $(SUBMODULES)
	[ ! -d '$(OUT)/$(ME)' ] || rm -rf '$(OUT)/$(ME)'
	$(JAVACME) $(SRC) || make lint

lint:
	$(JAVACME) -Xlint:unchecked $(SRC) 2>&1

clean:
	rm -rf '$(OUT)'

distclean: clean
	rm -f '$(CB).jar' '$(DEST)' jar
	ln -s ../../jar
	ln -vfs 'jar/$(CB)'-*.jar '$(CB).jar'

$(CB).jar:
	@echo
	@echo '!!! This needs $(CB).jar'
	@echo '!!! You need to reference it from somewhere, for example if you have it in $$HOME/bukkit'
	@echo '!!!	ln -s $$HOME/bukkit/$(CB).jar $(CB).jar'
	@echo '!!! You can also try:'
	@echo '!!!	make spigot'
	@echo
	@false

spigot:	$(OUT)/$(SPIGOT)/$(CB).jar
	rm -f '$(CB).jar'
	ln -vfs '$(OUT)/$(SPIGOT)/$(CB)'-*.jar '$(CB).jar'

$(OUT)/$(SPIGOT)/$(CB).jar:	update

update:
	@echo
	@echo '! If this fails, see "cat Makefile" for your OS dependencies'
	@echo '! If updating spigot suddenly no more works, try "make clean"'
	@echo
	-mkdir '$(OUT)/$(SPIGOT)'
	cd '$(OUT)/$(SPIGOT)' && wget -N 'https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar' && java -jar BuildTools.jar || { echo; echo 'OOPS .. that failed.  Perhaps try "make clean update"'; echo; }

#
# OS-dependencies to "make" this.
# These are for Debian, do similar for your distribution:
#	sudo apt-get install git build-essential
# And for "spigot":
#	sudo apt-get install openjdk-7-jdk
# How to use this then see README.md
#
