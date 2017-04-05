package com.svetroid.main.bot.command.commands.entertainment;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.StringUtils;
import java.security.SecureRandom;

public class Coin extends Command {

  public Coin() {
    settings.setNameAndAliases("coin", "coinflip", "cointoss", "flip");
    settings.setHelp(Main.bot.getPrefix() + "coin [number]");
    settings.setDescription(
        "Displays the results of the number of coin flips specified, up to a maximum of 300 coins at once.\nIf you omit any parameters, it will flip one coin.");
  }

  @Override
  protected void execute(Input input, Output output) {
    if (input.getArguments().length == 0) {
      output.sendMessage(
          input.getAuthor().getName() + " tossed a coin and got **" + coinToss() + "**.");
    } else if (input.getArguments().length == 1 && input.getArgument(0).matches("\\d+")) {
      int tosses = Integer.parseInt(input.getArgument(0));
      if (tosses > 300) {
        tosses = 300;
      }
      String result = multiCoinToss(tosses);
      output.sendMessage(
          "**Cointoss Results**\n```" + result + "```\n**" + input.getAuthor().getName()
              + "** tossed " + tosses + " coins, with **" + StringUtils
              .getWordCount(result, "HEADS") + "** heads and **" + StringUtils
              .getWordCount(result, "TAILS") + "** tails!");
    }
  }

  private String multiCoinToss(int tosses) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < tosses; i++) {
      sb.append(coinToss() + " ");
    }
    return sb.toString();
  }

  private String coinToss() {
    SecureRandom r = new SecureRandom();
    int coin = r.nextInt(2);
    if (coin == 0) {
      return "HEADS";
    } else {
      return "TAILS";
    }
  }

}
