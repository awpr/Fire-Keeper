package com.svetroid.main.bot.command.commands.entertainment;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import java.security.SecureRandom;

public class RNG extends Command {

  public RNG() {
    settings.setNameAndAliases("rng", "random", "generate", "gennum", "randgen", "r", "rand");
    settings.setHelp(Main.bot.getPrefix() + "rng [origin] [bound]");
    settings.setDescription(
        "Displays a random number between the origin and bound specified.\nIf the origin is larger than the bound, they will be swapped.\nIf you omit the origin, it will display a random number between 0 and the bound.\nIf you omit the origin and the bound, it will display a random number between 0 and 10.");
  }

  @Override
  protected void execute(Input input, Output output) {
    long origin;
    long bound;
    if (input.getArguments().length == 1) {
      origin = 0;
      bound = Math.abs(Long.parseLong(input.getArgument(0)));
    } else if (input.getArguments().length == 2) {
      origin = Long.parseLong(input.getArgument(0));
      bound = Long.parseLong(input.getArgument(1));
    } else {
      origin = 0;
      bound = 10;
    }
    if (origin > bound) {
      long temp = origin;
      origin = bound;
      bound = temp;
      output.sendMessage("Origin was greater than bound, so I switched them for you.");
    } else if (origin == bound) {
      output.sendMessage("`Error:` Origin is equal to bound.");
      return;
    }
    SecureRandom r = new SecureRandom();
    long rand = r.longs(origin, bound).findAny().getAsLong();
    output.sendMessage(
        "Generated random number between **" + origin + "** and **" + bound + "**: **`" + rand
            + "`**");
  }

}
