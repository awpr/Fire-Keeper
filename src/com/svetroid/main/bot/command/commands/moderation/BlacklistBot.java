package com.svetroid.main.bot.command.commands.moderation;

import com.google.gson.Gson;
import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class BlacklistBot extends Command {

  public BlacklistBot() {
    settings.setGuildOnly(true);
    settings.setAuthorPerms(Permission.MANAGE_CHANNEL);
    settings.setName("blacklist");
    settings.setHelp(Main.bot.getPrefix() + "blacklist [true|false]");
    settings.setDescription(
        "Enables or disables the bot from executing commands in the channel where the command is used.\nYou can interpret the command as a question. (i.e. Blacklist bot? true|false)\nRequires Manage Channel permission.");
  }

  @Override
  protected void execute(Input input, Output output) {
    if (input.getArguments().length == 1) {
      if (input.getArgument(0).equalsIgnoreCase("true")) {
        if (!Main.commandBlacklist.getChannels().contains(input.getTextChannel().getId())) {
          updateBlacklist(input.getTextChannel(), "add");
          output.sendPrivateMessage(
              "Disabled the bot for #" + input.getTextChannel().getName() + ".");
        } else {
          output.sendPrivateMessage("The bot is already disabled in that channel.");
        }
      } else if (input.getArgument(0).equalsIgnoreCase("false")) {
        if (Main.commandBlacklist.getChannels().contains(input.getTextChannel().getId())) {
          updateBlacklist(input.getTextChannel(), "remove");
          output
              .sendPrivateMessage("Enabled the bot for #" + input.getTextChannel().getName() + ".");
        } else {
          output.sendPrivateMessage("The bot is already allowed in that channel.");
        }
      }
    }
  }

  private void updateBlacklist(TextChannel channel, String update) {
    if (update.equals("add")) {
      Main.commandBlacklist.add(channel);
    } else if (update.equals("remove")) {
      Main.commandBlacklist.remove(channel);
    }
    Gson gson = new Gson();
    try {
      Writer writer = new FileWriter("res/data/command-blacklist.json");
      writer.write(gson.toJson(Main.commandBlacklist));
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
