package com.svetroid.main.util;

import com.svetroid.main.bot.command.Command;
import com.svetroid.main.bot.command.CommandHandler;

import java.util.Arrays;

public class CommandUtils {

  private CommandUtils() {
  }

  public static Command getCommand(String name) {
    for (Command command : CommandHandler.commands) {
      if (Arrays.asList(command.getSettings().getNameAndAliases()).contains(name)) {
        return command;
      }
    }
    return null;
  }

  public static boolean isCommand(String name) {
    if (CommandHandler.commands.stream()
        .anyMatch(cmd -> Arrays.asList(cmd.getSettings().getNameAndAliases()).contains(name))) {
      return true;
    }
    return false;
  }

  public static boolean isCategory(String category) {
    if (CommandHandler.commands.stream()
        .anyMatch(cmd -> cmd.getSettings().getCategory().equalsIgnoreCase(category))) {
      return true;
    }
    return false;
  }

}
