package com.svetroid.main.bot.command.commands.entertainment;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.bot.command.type.RPSType;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import java.security.SecureRandom;

public class RPS extends Command {

  public RPS() {
    settings.setNameAndAliases("rps", "rockpaperscissor", "rockpaperscissors");
    settings.setHelp(Main.bot.getPrefix() + "rps [rock|paper|scissors|other]");
    settings.setDescription(
        "Plays a game of rock/paper/scissors with the bot.\nYou may also use lizard, Spock, bull, and well.");
  }

  @Override
  protected void execute(Input input, Output output) {
    if (input.getArguments().length == 1) {
      RPSType player = RPSType.parseType(input.getArgument(0));
      RPSType computer = RPSType.parseType(rps());
      if (player.equals(computer)) {
        output.sendMessage(
            "I choose **" + computer.toString().toLowerCase() + "**! | **It's a tie!**");
      } else if (player.beats(computer)) {
        output.sendMessage(
            "I choose **" + computer.toString().toLowerCase() + "**! | **" + input.getAuthor()
                .getName() + "** wins!");
      } else {
        output.sendMessage("I choose **" + computer.toString().toLowerCase() + "**! | **I win!**");
      }
    }
  }

  private String rps() {
    SecureRandom r = new SecureRandom();
    int rps = r.nextInt(7) + 1;
    String result = null;
    if (rps == 1) {
      result = "ROCK";
    } else if (rps == 2) {
      result = "PAPER";
    } else if (rps == 3) {
      result = "SCISSORS";
    } else if (rps == 4) {
      result = "LIZARD";
    } else if (rps == 5) {
      result = "SPOCK";
    } else if (rps == 6) {
      result = "WELL";
    } else if (rps == 7) {
      result = "BULL";
    }
    return result;
  }

}
