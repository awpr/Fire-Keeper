package com.svetroid.main.bot.command.commands.information;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.StringUtils;

public class Invite extends Command {

  public Invite() {
    settings.setNameAndAliases("invite", "getinvite");
    settings.setHelp(Main.bot.getPrefix() + "invite");
    settings.setDescription("Displays the bot's invite links.");
    settings.setEmbedColor(40, 110, 205);
  }

  @Override
  protected void execute(Input input, Output output) {
    String inviteURL = Main.bot.getAuth().getInvite();
    String minimalInviteURL = StringUtils
        .replaceLast(inviteURL.replace(inviteURL.split("&")[2], ""), "&", "");
    output.sendMessage(createEmbed(settings.getEmbedColor(), "Invites",
        "Here's my [invite link](" + inviteURL
            + ") if you want to invite me to your server.\nI also have a [minimal invite]("
            + minimalInviteURL
            + ")!\n\nYou can find my support guild [here](https://discord.gg/KSWjuaw)."));
  }

}
