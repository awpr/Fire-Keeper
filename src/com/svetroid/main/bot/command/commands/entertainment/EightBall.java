package com.svetroid.main.bot.command.commands.entertainment;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import java.security.SecureRandom;

public class EightBall extends Command {

  private String[] affirmativeResponses = {"It is certain", "It is decidedly so", "Without a doubt",
      "Yes, definitely", "You may rely on it", "As I see it, yes", "Most likely",
      "Outlook good", "Yes", "Signs point to yes"};
  private String[] neutralResponses = {"Reply hazy, try again", "Ask again later",
      "Better not tell you now", "Cannot predict now", "Concentrate and ask again"};
  private String[] negativeResponses = {"Don't count on it", "My reply is no", "My sources say no",
      "Outlook not so good", "Very doubtful"};

  public EightBall() {
    settings.setNameAndAliases("8ball", "eightball");
    settings.setHelp(Main.bot.getPrefix() + "8ball [question]");
    settings.setDescription("Ask the magic 8ball a question!");
  }

  @Override
  protected void execute(Input input, Output output) {
    if (input.getArguments().length > 0) {
      SecureRandom r = new SecureRandom();
      String[] responses = null;
      switch (r.nextInt(3)) {
        default:
          break;
        case 0:
          responses = affirmativeResponses;
          break;
        case 1:
          responses = neutralResponses;
          break;
        case 2:
          responses = negativeResponses;
          break;
      }
      if (responses != null) {
        output.sendMessage(
            ":8ball: The **magic 8ball** says: *" + responses[r.nextInt(responses.length)] + "*");
      }
    }
  }

}
