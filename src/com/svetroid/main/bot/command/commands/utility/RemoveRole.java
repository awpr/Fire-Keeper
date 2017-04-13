package com.svetroid.main.bot.command.commands.utility;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.MessageUtils;
import com.svetroid.main.util.StringUtils;
import net.dv8tion.jda.core.entities.Role;

public class RemoveRole extends Command {

  public RemoveRole() {
    settings.setNameAndAliases("removerole", "remrole");
    settings.setHelp(Main.bot.getPrefix() + "removerole");
    settings.setDescription(
        "Allows users to remove a role. Valid roles are PC, PlayStation, Xbox, Demon's Souls, Bloodborne, Dark Souls, Dark Souls 2, Dark Souls 3, and Scholars.");
  }

  @Override
  protected void execute(Input input, Output output) {
    Role r = MessageUtils.parseRole(input);
    if (r != null && StringUtils.stringContainsItemFromList(r.getName(), MessageUtils.validRoles)) {
      if (input.getMember().getRoles().contains(r)) {
        removeRole(input, output, r);
      } else {
        output.sendMessage("Ashen One, I cannot take away that which you do not have.");
      }
    } else {
      output.sendMessage("I cannot grant thine wishes, for I know not of what you speak.");
    }
  }

  private void removeRole(Input input, Output output, Role r) {
    input.getGuild().getController().removeRolesFromMember(input.getMember(), r).queue();
    output.sendMessage(
        "If that is what you wish, Ashen One. I have removed the role of `" + r.getName()
            + "` from thee.");
  }

}
