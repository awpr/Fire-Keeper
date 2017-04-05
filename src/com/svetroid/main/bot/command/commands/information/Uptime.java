package com.svetroid.main.bot.command.commands.information;

import com.svetroid.main.Main;
import com.svetroid.main.bot.UptimeTracker;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;

public class Uptime extends Command {

  public Uptime() {
    settings.setName("uptime");
    settings.setHelp(Main.bot.getPrefix() + "uptime");
    settings.setDescription("Displays the uptime of the bot for the current session.");
    settings.setEmbedColor(45, 110, 205);
  }

  @Override
  protected void execute(Input input, Output output) {
    output.sendMessage(
        createEmbed(settings.getEmbedColor(), "Uptime (Session)", UptimeTracker.getUptime()));
  }

}
