package com.svetroid.main.event;

import com.google.gson.Gson;
import com.svetroid.main.Main;
import com.svetroid.main.Whitelist;
import com.svetroid.main.bot.command.CommandHandler;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {

  private CommandHandler commandHandler = new CommandHandler();

  private Input input;
  private Output output;

  @Override
  public void onReady(ReadyEvent readyEvent) {
    initCommandBlacklist();
    initNSFWWhitelist();
    System.out.println("[ReadyEvent] All systems ready!");
  }

  @Override
  public void onMessageReceived(MessageReceivedEvent messageReceivedEvent) {
    input = new Input(messageReceivedEvent);
    output = new Output(input);

    String msg = input.getRawContent().toLowerCase();

    boolean canHandleMessages = !input.getAuthor().isBot();
    boolean isCommandForBot = (msg.startsWith(Main.bot.getPrefix().toLowerCase())) || (msg
        .startsWith(Main.bot.getDefaultPrefix().toLowerCase())) || (msg
        .startsWith(input.getJDA().getSelfUser().getAsMention()))
        || (input.isFromType(ChannelType.PRIVATE)) && canHandleMessages;

    if (isCommandForBot) {
      commandHandler.execute(input, output);
    }

  }

  private void initCommandBlacklist() {
    if (new File("res/data/command-blacklist.json").exists()) {
      try {
        Gson gson = new Gson();
        Reader reader = new FileReader("res/data/command-blacklist.json");
        Main.commandBlacklist = gson.fromJson(reader, Whitelist.class);
        reader.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void initNSFWWhitelist() {
    if (new File("res/data/nsfw-whitelist.json").exists()) {
      try {
        Gson gson = new Gson();
        Reader reader = new FileReader("res/data/nsfw-whitelist.json");
        Main.NSFWWhitelist = gson.fromJson(reader, Whitelist.class);
        reader.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
