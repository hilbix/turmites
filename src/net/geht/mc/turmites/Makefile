# Simple wrapper to call parent makefiles
# No need to "cd" first all the time

all sh:
	P=../../../../..; [ -f $$P/Makefile ] || P=..; make -C $$P $@

cd:
	exec make sh
