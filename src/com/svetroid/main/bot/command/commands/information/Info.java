package com.svetroid.main.bot.command.commands.information;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import net.dv8tion.jda.core.JDAInfo;

public class Info extends Command {

  public Info() {
    settings.setNameAndAliases("info", "information");
    settings.setHelp(Main.bot.getPrefix() + "info");
    settings.setDescription("Displays general information about the bot.");
    settings.setEmbedColor(45, 110, 205);
  }

  @Override
  protected void execute(Input input, Output output) {
    String creator =
        "**Creator**: " + input.getJDA().getUserById("256200794850852874").getAsMention();
    String framework = "**Framework**: JDA " + JDAInfo.VERSION;
    String information =
        "Use `" + Main.bot.getPrefix() + "help` to learn more about how to use commands.";
    String support = "Join the [Support Guild](https://discord.gg/KSWjuaw) if you want more information.";

    output.sendMessage(createEmbed(settings.getEmbedColor(), "Information",
        creator + "\n" + framework + "\n" + information + "\n" + support));
  }

}
