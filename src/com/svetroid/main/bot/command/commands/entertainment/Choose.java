package com.svetroid.main.bot.command.commands.entertainment;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.StringUtils;
import java.security.SecureRandom;

public class Choose extends Command {

  public Choose() {
    settings.setNameAndAliases("choose", "choice", "decide", "choices");
    settings.setHelp(Main.bot.getPrefix() + "choose [choices]");
    settings.setDescription(
        "Chooses one choice out of all of the choices you provide.\nYou can separate your choices in just about any way you can think of.");
  }

  @Override
  protected void execute(Input input, Output output) {
    SecureRandom r = new SecureRandom();
    String[] choices = StringUtils.arrayToString(input.getArguments(), " ")
        .replaceAll("^[,|;/]+", "").split("[,|;/]+");
    output.sendMessage("I pick **" + choices[r.nextInt(choices.length)].trim() + "**!");
  }

}
