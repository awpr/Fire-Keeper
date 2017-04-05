package com.svetroid.main.bot.command;

import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

import java.awt.*;

public abstract class Command {

  protected CommandSettings settings = new CommandSettings();

  protected abstract void execute(Input input, Output output);

  public CommandSettings getSettings() {
    return settings;
  }

  public Message createEmbed(Color color, String title, String desc) {
    MessageBuilder mb = new MessageBuilder();
    mb.setEmbed(
        new EmbedBuilder().setColor(color).setTitle(title, null).setDescription(desc).build());
    return mb.build();
  }

}