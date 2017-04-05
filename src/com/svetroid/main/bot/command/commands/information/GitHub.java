package com.svetroid.main.bot.command.commands.information;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;

public class GitHub extends Command {

  public GitHub() {
    settings.setName("github");
    settings.setHelp(Main.bot.getPrefix() + "github");
    settings.setDescription("Displays the GitHub link for the bot.");
    settings.setEmbedColor(90, 40, 125);
  }

  @Override
  protected void execute(Input input, Output output) {
    output.sendMessage(createEmbed(settings.getEmbedColor(), "GitHub",
        "Here's a link to my [GitHub](https://github.com/Svetroid/Hobbes)."));
  }

}
