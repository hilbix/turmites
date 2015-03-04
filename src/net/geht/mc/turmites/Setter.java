package net.geht.mc.turmites;

public interface Setter
  {
  // This is a very simple interface.
  // Sets a value to the given (interpreted) string and return it's new (eventually corrected) value.
  // If string is NULL it is not set at all, so only the current (or right) value is returned.
  // This shall never return NULL if there is no data, instead throw an error:
  //	DataErrorException - if the data is unacceptable
  //	MissingArgumentException - if the string is too short
  //	TooManyArgumentsException - if the string is too long
  //	NotConsoleException - if this is not allowed in a player context (how ever this was detected)
  //	NotPlayerException - if this is not allowed in a console context (how ever this was detected)
  //	NotFoundException - if the service was shut down for some reason or another
  //
  public Val set(Val v);
  }
