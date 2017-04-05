package com.svetroid.main.bot.command.commands.owner;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.MessageUtils;

public class Avatar extends Command {

  public Avatar() {
    settings.setOwnerCommand(true);
    settings.setNameAndAliases("avatar", "getavatar");
    settings.setHelp(Main.bot.getPrefix() + "avatar [user]");
    settings.setDescription("Gets the avatar of the user specified.");
  }

  @Override
  protected void execute(Input input, Output output) {
    if (MessageUtils.getUser(input) != null) {
      output.sendMessage(
          input.getJDA().getUserById(MessageUtils.getUser(input).getId()).getAvatarUrl());
    }
  }

}
