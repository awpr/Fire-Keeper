package com.svetroid.main.bot.command;

import com.svetroid.main.Main;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;
import com.svetroid.main.util.CommandUtils;
import net.dv8tion.jda.core.entities.ChannelType;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.net.URL;
import java.util.*;

public class CommandHandler {

  public static List<Command> commands = new ArrayList<>();

  public CommandHandler() {
    try {
      Set<URL> classPathList = new HashSet<>();
      classPathList.addAll(ClasspathHelper.forJavaClassPath());
      Set<Class<? extends Command>> result = new Reflections(
          new ConfigurationBuilder().setScanners(new SubTypesScanner()).setUrls(classPathList))
          .getSubTypesOf(Command.class);

      for (Class<? extends Command> c : result) {
        String[] categoryString = c.getPackage().toString().split("\\.");
        String category = categoryString[categoryString.length - 1];
        if (category.equalsIgnoreCase("commands")) {
          category = "default";
        }
        Command command = c.newInstance();
        command.getSettings().setCategory(category);
        commands.add(command);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void execute(Input input, Output output) {
    parse(input);
    Command commandResult = null;
    if (commands.contains(input.getCommand())) {
      commandResult = commands.get(commands.indexOf(input.getCommand()));
    }
    if (commandResult != null) {
      if (isValid(input, commandResult.getSettings())) {
        commandResult.execute(input, output);
      }
    }
  }

  private void parse(Input input) {
    String msg = input.getRawContent().toLowerCase();
    boolean startsWithMention = msg.startsWith(input.getJDA().getSelfUser().getAsMention());
    boolean startsWithDefaultPrefix = msg.startsWith(Main.bot.getDefaultPrefix());
    boolean startsWithPrefix = msg.startsWith(Main.bot.getPrefix());
    boolean startsWithAny = startsWithMention || startsWithDefaultPrefix || startsWithPrefix;
    String prefix = startsWithMention ? input.getJDA().getSelfUser().getAsMention()
        : (startsWithDefaultPrefix ? Main.bot.getDefaultPrefix()
            : (startsWithPrefix ? Main.bot.getPrefix() : ""));
    String[] args = input.getRawContent().replaceFirst(prefix, prefix + " ").trim().split("\\s+");

    input.setCommandAlias(args[startsWithAny ? 1 : 0]);
    input.setArguments(Arrays.copyOfRange(args, startsWithAny ? 2 : 1, args.length));
    input.setCommand(CommandUtils.getCommand(input.getCommandAlias()));
  }

  private boolean isValid(Input input, CommandSettings settings) {
    boolean canSpeakInChannel = !Main.commandBlacklist.getChannels()
        .contains(input.getChannel().getId());
    if (settings.getOwnerCommand() && !Main.bot.getAuth().getOwnerID()
        .equals(input.getAuthor().getId())) {
      return false;
    }
    if (settings.getNSFWCommand() && !Main.NSFWWhitelist.getChannels()
        .contains(input.getJDA().getTextChannelById(input.getChannel().getId()))) {
      return false;
    }
    if (settings.getGuildOnly() && input.getChannelType() != ChannelType.TEXT) {
      return false;
    }
    if (settings.getSelfPermissions() != null && !input.getGuild().getSelfMember()
        .hasPermission(settings.getSelfPermissions())) {
      return false;
    }
    if (settings.getAuthorPermissions() != null && !input.getMember()
        .hasPermission(settings.getAuthorPermissions())) {
      return false;
    }
    if (!Arrays.asList(settings.getNameAndAliases()).contains("blacklist") && !canSpeakInChannel) {
      return false;
    }
    return true;
  }

}
