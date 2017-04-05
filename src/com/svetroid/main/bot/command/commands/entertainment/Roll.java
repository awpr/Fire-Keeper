package com.svetroid.main.bot.command.commands.entertainment;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.StringUtils;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Roll extends Command {

  public Roll() {
    settings.setNameAndAliases("roll", "dice", "diceroll", "rolldice");
    settings.setHelp(Main.bot.getPrefix() + "roll [amount]d[sides]");
    settings.setDescription(
        "Rolls the specified number of dice with the amount of sides specified.\nYou can also add a number onto the end, like so: \"::h roll 1d6+5\"\nYou will roll a single six-sided die if you omit parameters.\nYou may also omit the amount of dice if you want to roll a single die with the amount of sides specified.");
  }

  @Override
  protected void execute(Input input, Output output) {
    long amount = 1;
    long sides = 6;
    if (input.getArguments().length == 1) {
      if (input.getArgument(0).startsWith("d")) {
        sides = Long.parseLong(input.getArgument(0).replaceFirst("d", ""));
      } else if (input.getArgument(0).contains("d")) {
        amount = Long.parseLong(input.getArgument(0).split("d")[0]);
        sides = Long.parseLong(input.getArgument(0).split("d")[1]);
      }
    }
    List<Long> rolls = new ArrayList<>();
    if ((amount > 0 && sides > 0) && (amount <= 12 && sides <= 100)) {
      for (int i = 0; i < amount; i++) {
        rolls.add(roll(sides));
      }
      output.sendMessage(
          (input.getAuthor().getName() + " rolled " + rolls.toString() + (amount == 1 ? ""
              : " (Sum: "
                  + rolls
                  .stream().mapToLong(Long::longValue).sum() + ")") + " on " + StringUtils
              .numberToWord(amount) + " " + sides + "-sided " + (amount == 1 ? "die" : "dice")
              + ".").replaceAll("\\s+", " "));
      rolls.clear();
    } else if (amount > 12 || sides > 100) {
      for (long l = 0; l < amount; l++) {
        rolls.add(roll(sides));
      }
      output.sendMessage(
          (":game_die: **" + amount + "d" + sides + "** | **" + input.getAuthor().getName()
              + "**, Sum: **" + rolls.stream().mapToLong(Long::longValue).sum() + "**")
              .replaceAll("\\s+", " "));
      rolls.clear();
    }
  }

  private long roll(long sides) {
    SecureRandom r = new SecureRandom();
    return r.longs(0, sides).findAny().getAsLong() + 1;
  }

}
