package com.svetroid.main.bot.command.commands.moderation;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.MessageUtils;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.User;

public class Ban extends Command {

  public Ban() {
    settings.setGuildOnly(true);
    settings.setAuthorPerms(Permission.BAN_MEMBERS);
    settings.setSelfPerms(Permission.BAN_MEMBERS);
    settings.setName("ban");
    settings.setHelp(Main.bot.getPrefix() + "ban [users]");
    settings.setDescription(
        "Bans users from the guild. You can use mentions, userIDs, or names with discriminators.\nYou can provide as many users as you want.\nRequires Ban Members permission.");
  }

  @Override
  protected void execute(Input input, Output output) { // TODO: Allow banning users not in guild.
    for (User user : MessageUtils.getUsers(input)) {
      if (user != null) {
        input.getGuild().getController().ban(user, 0);
      }
    }
    output.sendMessage(" :ok_hand: ");
  }

}
