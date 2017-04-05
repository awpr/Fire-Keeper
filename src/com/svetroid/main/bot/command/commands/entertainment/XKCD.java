package com.svetroid.main.bot.command.commands.entertainment;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;

public class XKCD extends Command {

  public XKCD() {
    settings.setNameAndAliases("xkcd");
    settings.setHelp(Main.bot.getPrefix() + "xkcd [number]");
    settings.setDescription(
        "Gets a link for the xkcd comic of the specified number.\nIf no arguments are specified, it returns a random comic.");
  }

  @Override
  protected void execute(Input input, Output output) {
    // TODO: Write this command
  }

}
