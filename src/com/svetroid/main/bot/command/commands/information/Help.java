package com.svetroid.main.bot.command.commands.information;

import com.svetroid.main.Main;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.bot.command.CommandHandler;
import com.svetroid.main.bot.command.CommandSettings;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.CommandUtils;
import com.svetroid.main.util.StringUtils;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Help extends Command {

  private String[] emojis =
      {":kissing_heart:", ":ok_hand:", ":thumbsup:", ":grinning:", ":eyes:", ":upside_down_face:",
          ":slight_smile:", ":innocent:", ":tiger:", ":white_check_mark:", ":wink:", ":grin:",
          ":heart:", ":yellow_heart:", ":green_heart:", ":blue_heart:", ":purple_heart:",
          ":speech_left:"};

  String helpDescription = "Type `" + Main.bot.getPrefix()
      + "help [command]` for more information on a specific command.\nType `" + Main.bot
      .getPrefix()
      + "help categories` to see a list of categories.\nPrefixes are not required in a DM, but you can still use them.\n";

  public Help() {
    settings.setName("help");
    settings.setHelp(Main.bot.getPrefix() + "help [command]");
    settings
        .setDescription("Provides helpful information about how to use the bot and its commands.");
    settings.setEmbedColor(45, 110, 205);
  }

  @Override
  protected void execute(Input input, Output output) {
    if (input.getArguments().length == 1) {
      if (input.getArgument(0).equalsIgnoreCase("categories")) {
        sendCategoryList(input, output);
      } else if (CommandUtils.isCommand(input.getArgument(0))) {
        sendCommandHelp(input, output);
      }
    } else if (input.getArguments().length == 2) {
      if (input.getArgument(0).equalsIgnoreCase("category") && CommandUtils
          .isCategory(input.getArgument(1))) {
        sendCategoryHelp(input, output);
      }
    } else {
      sendDefaultHelp(input, output);
    }
  }

  private void sendDefaultHelp(Input input, Output output) {
    SecureRandom r = new SecureRandom();
    List<String> helpLines = new ArrayList<>();
    int max = Collections.max(CommandHandler.commands.stream().map(Command::getSettings)
        .collect(Collectors.toCollection(ArrayList::new)).stream().map(CommandSettings::getName)
        .collect(Collectors.toCollection(ArrayList::new)), Comparator.comparing(s -> s.length()))
        .length();
    for (Command command : CommandHandler.commands) {
      if (command.getSettings().getOwnerCommand()) {
        if (input.getAuthor().getId().equals(Main.bot.getAuth().getOwnerID())) {
          helpLines.add(String.format("%-" + max + "s | %s\n", command.getSettings().getName(),
              command.getSettings().getHelp()));
        }
      } else {
        helpLines.add(String.format("%-" + max + "s | %s\n", command.getSettings().getName(),
            command.getSettings().getHelp()));
      }
    }
    Collections.sort(helpLines);
    String defaultHelp = StringUtils.listToString(helpLines, "").replaceAll("`", "").trim();
    Message defaultHelpEmbed = createEmbed(settings.getEmbedColor(),
        Main.bot.getJDA().getSelfUser().getName() + " | Help",
        helpDescription + "```ini\n" + defaultHelp + "\n```");
    if (input.isFromType(ChannelType.TEXT)) {
      output.sendMessage("Sent you a DM. " + emojis[r.nextInt(emojis.length)]);
      output.sendPrivateMessage(defaultHelpEmbed);
    } else {
      output.sendMessage(defaultHelpEmbed);
    }
  }

  private void sendCommandHelp(Input input, Output output) {
    SecureRandom r = new SecureRandom();
    Command command = CommandUtils.getCommand(input.getArgument(0));
    Message commandHelpEmbed = createEmbed(command.getSettings().getEmbedColor(),
        "Help: " + command.getSettings().getName(),
        "**Category:** `" + command.getSettings().getCategory() + "`\n**Usage:** `" + command
            .getSettings()
            .getHelp() + "`\n**Description:** `" + command
            .getSettings().getDescription() + "`");
    if (input.isFromType(ChannelType.TEXT)) {
      output.sendMessage("Sent you a DM. " + emojis[r.nextInt(emojis.length)]);
      output.sendPrivateMessage(commandHelpEmbed);
    } else {
      output.sendMessage(commandHelpEmbed);
    }
  }

  private void sendCategoryHelp(Input input, Output output) {
    SecureRandom r = new SecureRandom();
    List<String> categoryHelpLines = new ArrayList<>();
    List<Command> categoryCommands = new ArrayList<>();
    int max = 0;
    for (Command command : CommandHandler.commands) {
      if (command.getSettings().getCategory().equals(input.getArgument(1))) {
        categoryCommands.add(command);
        if (command.getSettings().getName().length() > max) {
          max = command.getSettings().getName().length();
        }
      }
    }

    for (Command command : categoryCommands) {
      if (command.getSettings().getOwnerCommand()) {
        if (input.getAuthor().getId().equals(Main.bot.getAuth().getOwnerID())) {
          categoryHelpLines
              .add(String.format("%-" + max + "s | %s\n", command.getSettings().getName(),
                  command.getSettings().getHelp()));
        }
      } else {
        categoryHelpLines
            .add(String.format("%-" + max + "s | %s\n", command.getSettings().getName(),
                command.getSettings().getHelp()));
      }
    }
    Collections.sort(categoryHelpLines);
    String categoryHelp = StringUtils.listToString(categoryHelpLines, "").replaceAll("`", "")
        .trim();
    Message categoryHelpEmbed = createEmbed(settings.getEmbedColor(),
        Main.bot.getJDA().getSelfUser().getName() + " | Help",
        helpDescription + "```ini\n" + categoryHelp + "\n```");
    if (input.isFromType(ChannelType.TEXT)) {
      output.sendMessage("Sent you a DM. " + emojis[r.nextInt(emojis.length)]);
      output.sendPrivateMessage(categoryHelpEmbed);
    } else {
      output.sendMessage(categoryHelpEmbed);
    }

  }

  private void sendCategoryList(Input input, Output output) {
    SecureRandom r = new SecureRandom();
    List<String> categoryList = new ArrayList<>();
    for (Command command : CommandHandler.commands) {
      if (!categoryList.contains(command.getSettings().getCategory())) {
        if (command.getSettings().getCategory().equals("owner")) {
          if (input.getAuthor().getId().equals(Main.bot.getAuth().getOwnerID())) {
            categoryList.add(command.getSettings().getCategory());
          }
        } else {
          categoryList.add(command.getSettings().getCategory());
        }
      }
    }
    String categories = StringUtils.listToString(categoryList, "\n");
    Message categoriesEmbed = createEmbed(settings.getEmbedColor(),
        Main.bot.getJDA().getSelfUser().getName() + " | Categories",
        "Type `" + Main.bot.getPrefix()
            + "help category [category]` to see all commands in a specific category.\nPrefixes are not required in a DM, but you can still use them.\n```ini\n"
            + categories + "\n```");
    if (input.isFromType(ChannelType.TEXT)) {
      output.sendMessage("Sent you a DM. " + emojis[r.nextInt(emojis.length)]);
      output.sendPrivateMessage(categoriesEmbed);
    } else {
      output.sendMessage(categoriesEmbed);
    }
  }

}
