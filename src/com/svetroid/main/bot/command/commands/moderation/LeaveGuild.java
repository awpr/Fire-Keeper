package com.svetroid.main.bot.command.commands.moderation;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import net.dv8tion.jda.core.Permission;

public class LeaveGuild extends Command {

  public LeaveGuild() {
    settings.setGuildOnly(true);
    settings.setAuthorPerms(Permission.ADMINISTRATOR);
    settings.setNameAndAliases("leave", "leaveguild", "leaveserver", "exitguild", "exitserver");
    settings.setHelp(Main.bot.getPrefix() + "leave");
    settings.setDescription("Leaves the current guild.\nRequires administrator permission.");
  }

  @Override
  protected void execute(Input input, Output output) {
    output.sendPrivateMessage(
        "Bye!\nIf you wish to re-invite me, just type `" + Main.bot.getPrefix() + "invite`!");
    input.getGuild().leave().queue();
  }

}
