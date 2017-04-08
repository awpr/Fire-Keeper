package com.svetroid.main.bot.command.commands.utility;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;

import java.time.temporal.ChronoUnit;

public class Ping extends Command {

  public Ping() {
    settings.setName("ping");
    settings.setHelp(Main.bot.getPrefix() + "ping");
    settings.setDescription("Tests to see if the bot is online and functional.");
  }

  @Override
  protected void execute(Input input, Output output) {
    output.sendMessage("Pong!", message -> message
        .editMessage("Pong! :table_tennis: | Response in `" + input.getMessage().getCreationTime()
            .until(message.getCreationTime(), ChronoUnit.MILLIS) + "ms`").queue());
  }

}
