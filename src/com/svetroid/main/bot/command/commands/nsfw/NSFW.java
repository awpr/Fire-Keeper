package com.svetroid.main.bot.command.commands.nsfw;

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

public class NSFW extends Command {

  public NSFW() {
    settings.setOwnerCommand(true); // temp until nsfw commands added (if ever)
    settings.setGuildOnly(true);
    settings.setAuthorPerms(Permission.MANAGE_CHANNEL);
    settings.setName("nsfw");
    settings.setHelp(Main.bot.getPrefix() + "nsfw [enable|disable]");
    settings.setDescription(
        "Enables or disables NSFW commands in the channel where the command is used.\nRequires Manage Channel permission.");
    settings.setEmbedColor(165, 25, 255);
  }

  @Override
  protected void execute(Input input, Output output) {
    if (input.getArguments().length == 1) {
      if (input.getArgument(0).equalsIgnoreCase("enable")) {
        if (!Main.NSFWWhitelist.getChannels().contains(input.getTextChannel().getId())) {
          updateWhitelist(input.getTextChannel(), "add");
          output.sendPrivateMessage(
              "Enabled #" + input.getTextChannel().getName() + " for NSFW commands.");
        } else {
          output.sendPrivateMessage("That channel is already enabled for NSFW commands.");
        }
      } else if (input.getArgument(0).equalsIgnoreCase("disable")) {
        if (Main.NSFWWhitelist.getChannels().contains(input.getTextChannel().getId())) {
          updateWhitelist(input.getTextChannel(), "remove");
          output.sendPrivateMessage(
              "Disabled #" + input.getTextChannel().getName() + " for NSFW commands.");
        } else {
          output.sendPrivateMessage("That channel is already disabled for NSFW commands.");
        }
      }
    }
  }

  private void updateWhitelist(TextChannel channel, String update) {
    if (update.equals("add")) {
      Main.NSFWWhitelist.add(channel);
    } else if (update.equals("remove")) {
      Main.NSFWWhitelist.remove(channel);
    }
    Gson gson = new Gson();
    try {
      Writer writer = new FileWriter("res/data/nsfw-whitelist.json");
      writer.write(gson.toJson(Main.NSFWWhitelist));
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
