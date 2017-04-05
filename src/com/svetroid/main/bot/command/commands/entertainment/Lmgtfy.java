package com.svetroid.main.bot.command.commands.entertainment;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Lmgtfy extends Command {

  public Lmgtfy() {
    settings.setName("lmgtfy");
    settings.setHelp(Main.bot.getPrefix() + "lmgtfy [query]");
    settings.setDescription("Displays a LMGTFY link based on the query specified.");
  }

  @Override
  protected void execute(Input input, Output output) {
    if (input.getArguments().length > 0) {
      String query = StringUtils.arrayToString(input.getArguments(), " ").trim();
      try {
        query = URLEncoder.encode(query, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      output.sendMessage("http://lmgtfy.com/?q=" + query);
    } else {
      output.sendMessage("http://lmgtfy.com/");
    }
  }

}
