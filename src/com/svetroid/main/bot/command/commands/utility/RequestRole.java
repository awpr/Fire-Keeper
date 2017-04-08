package com.svetroid.main.bot.command.commands.utility;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import java.util.Arrays;
import net.dv8tion.jda.core.entities.Role;

public class RequestRole extends Command {

  private String[] validRoles = {"pc", "playstation", "xbox", "scholars"};

  public RequestRole() {
    settings.setNameAndAliases("requestrole", "reqrole");
    settings.setHelp(Main.bot.getPrefix() + "reqrole");
    settings.setDescription(
        "Allows users to request a role. Valid roles are PC, PlayStation, Xbox, and Scholars.");
  }

  @Override
  protected void execute(Input input, Output output) {
    String requestedRole = input.getArgument(0);
    if (Arrays.stream(validRoles)
        .anyMatch(validRole -> validRole.equalsIgnoreCase(requestedRole))) {
      Role r = input.getGuild().getRolesByName(requestedRole, true).get(0);
      if (input.getMember().getRoles().contains(r)) {
        output.sendMessage("Ashen One, you already possess such knowledge.");
      } else {
        input.getGuild().getController().addRolesToMember(input.getMember(), r).queue();
        output.sendMessage("I have granted the role of `" + requestedRole + "` upon thee.");
      }
    } else {
      output.sendMessage("I cannot grant thine wishes, for I know not of what you speak.");
    }
  }

}
