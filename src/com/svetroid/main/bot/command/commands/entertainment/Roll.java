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

  private SecureRandom r = new SecureRandom();

  public Roll() {
    settings.setNameAndAliases("roll", "dice", "diceroll", "rolldice");
    settings.setHelp(Main.bot.getPrefix() + "roll [amount]d[sides]");
    settings.setDescription(
        "Rolls the specified number of dice with the amount of sides specified.\nYou can also add a number onto the end, like so: \"::h roll 2d6+5\"\nYou will roll a single six-sided die if you omit parameters.\nYou may also omit the amount of dice if you want to roll a single die with the amount of sides specified.");
  }

  @Override
  protected void execute(Input input, Output output) {
    int amount = 1;
    int sides = 6;
    if (input.getArguments().length == 1) {
      if (input.getArgument(0).startsWith("d") && input.getArgument(0).split("d")[1]
          .matches("^[1-9][0-9]{0,5}$")) {
        sides = Integer.parseInt(input.getArgument(0).split("d")[1]);
      } else if (input.getArgument(0).contains("d") && input.getArgument(0).split("d")[0]
          .matches("^[1-9][0-9]{0,5}$") && input.getArgument(0).split("d")[1]
          .matches("^[1-9][0-9]{0,5}$")) {
        amount = Integer.parseInt(input.getArgument(0).split("d")[0]);
        sides = Integer.parseInt(input.getArgument(0).split("d")[1]);
      }
    }

    List<Long> rolls = new ArrayList<>();
    if ((amount > 0 && sides > 0) && (amount <= 20 && sides <= 100)) {
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
    } else if (amount > 20 || sides > 100) {
      for (long l = 0; l < amount; l++) {
        rolls.add(roll(sides));
      }
      output.sendMessage(
          (":game_die: **" + amount + "d" + sides + "** | **" + input.getAuthor().getName()
              + "**, Sum: **" + rolls.stream().mapToLong(Long::longValue).sum() + "**")
              .replaceAll("\\s+", " "));
    }
    rolls.clear();
  }

  private long roll(int sides) {
    return r.ints(0, sides).findAny().getAsInt() + 1;
  }

}
