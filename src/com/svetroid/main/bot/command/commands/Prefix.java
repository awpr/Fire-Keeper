package com.svetroid.main.bot.command.commands;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;

public class Prefix extends Command {

  public Prefix() {
    settings.setNameAndAliases("prefix", "getprefix");
    settings.setHelp(Main.bot.getPrefix() + "prefix");
    settings.setDescription("Displays the current prefix.");
  }

  @Override
  protected void execute(Input input, Output output) {
    output.sendMessage("The current prefix is `" + Main.bot.getPrefix() + "`!");
  }

}
