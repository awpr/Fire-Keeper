package com.svetroid.main.bot.command.commands.owner;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.StringUtils;
import java.util.Arrays;

public class Say extends Command {

  public Say() {
    settings.setOwnerCommand(true);
    settings.setNameAndAliases("say", "echo");
    settings.setHelp(Main.bot.getPrefix() + "say");
    settings.setDescription("The bot says whatever you tell it to say.");
  }

  @Override
  protected void execute(Input input, Output output) {
    if (input.getArguments().length >= 3) {
      System.out.println(input.getArgument(0) + "|" + input.getArgument(1));
      String serverID = input.getArgument(0);
      String channelID = input.getArgument(1);
      String messageString = StringUtils
          .arrayToString(Arrays.copyOfRange(input.getArguments(), 2, input.getArguments().length),
              " ");
      if (serverID.matches("\\d+") && channelID.matches("\\d+")) {
        input.getJDA().getGuildById(serverID).getTextChannelById(channelID)
            .sendMessage(messageString).queue();
      } else {
        output.sendPrivateMessage("Incorrect syntax.");
        return;
      }
    }
  }

}
