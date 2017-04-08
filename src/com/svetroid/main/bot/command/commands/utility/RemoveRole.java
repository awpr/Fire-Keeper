package com.svetroid.main.bot.command.commands.utility;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import java.util.Arrays;
import net.dv8tion.jda.core.entities.Role;

public class RemoveRole extends Command {

  private String[] validRoles = {"pc", "playstation", "xbox", "scholars"};

  public RemoveRole() {
    settings.setNameAndAliases("removerole", "remrole");
    settings.setHelp(Main.bot.getPrefix() + "removerole");
    settings.setDescription(
        "Allows users to remove a role. Valid roles are PC, PlayStation, Xbox, and Scholars.");
  }

  @Override
  protected void execute(Input input, Output output) {
    String requestedRole = input.getArgument(0);
    if (Arrays.stream(validRoles)
        .anyMatch(validRole -> validRole.equalsIgnoreCase(requestedRole))) {
      Role r = input.getGuild().getRolesByName(requestedRole, true).get(0);
      if (input.getMember().getRoles().contains(r)) {
        input.getGuild().getController().removeRolesFromMember(input.getMember(), r).queue();
        output.sendMessage(
            "If that is what you wish, Ashen One. I have removed the role of `" + requestedRole
                + "` from thee.");
      } else {
        output.sendMessage("Ashen One, you do not possess such knowledge.");
      }
    } else {
      output.sendMessage("I cannot grant thine wishes, for I know not of what you speak.");
    }
  }

}
