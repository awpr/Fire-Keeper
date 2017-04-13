package com.svetroid.main.bot.command.commands.utility;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.MessageUtils;
import com.svetroid.main.util.StringUtils;
import net.dv8tion.jda.core.entities.Role;

public class RequestRole extends Command {

  public RequestRole() {
    settings.setNameAndAliases("requestrole", "reqrole");
    settings.setHelp(Main.bot.getPrefix() + "reqrole");
    settings.setDescription(
        "Allows users to request a role. Valid roles are PC, PlayStation, Xbox, Demon's Souls, Bloodborne, Dark Souls, Dark Souls 2, Dark Souls 3, and Scholars.");
  }

  @Override
  protected void execute(Input input, Output output) {
    Role r = MessageUtils.parseRole(input);
    if (r != null && StringUtils.stringContainsItemFromList(r.getName(), MessageUtils.validRoles)) {
      if (input.getMember().getRoles().contains(r)) {
        output.sendMessage("Ashen One, you already possess such knowledge.");
      } else {
        grantRole(input, output, r);
      }
    } else {
      output.sendMessage("I cannot grant thine wishes, for I know not of what you speak.");
    }
  }

  private void grantRole(Input input, Output output, Role r) {
    input.getGuild().getController().addRolesToMember(input.getMember(), r).queue();
    output.sendMessage("I have granted the role of `" + r.getName() + "` upon thee.");
  }

}
