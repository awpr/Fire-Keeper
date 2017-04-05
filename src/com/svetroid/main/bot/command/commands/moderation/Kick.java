package com.svetroid.main.bot.command.commands.moderation;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.MessageUtils;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.User;

public class Kick extends Command {

  public Kick() {
    settings.setGuildOnly(true);
    settings.setAuthorPerms(Permission.KICK_MEMBERS);
    settings.setSelfPerms(Permission.KICK_MEMBERS);
    settings.setName("kick");
    settings.setHelp(Main.bot.getPrefix() + "kick [users]");
    settings.setDescription(
        "Kicks users out of the guild. You can use mentions, userIDs, or names with discriminators.\nYou can provide as many users as you want.\nRequires Kick Members permission.");
  }

  @Override
  protected void execute(Input input, Output output) {
    for (User user : MessageUtils.getUsers(input)) {
      if (user != null && input.getGuild().getSelfMember()
          .canInteract(input.getGuild().getMember(user))) {
        input.getGuild().getController().kick(input.getGuild().getMember(user));
      }
    }
    output.sendMessage(" :ok_hand: ");
  }

}
