package com.svetroid.main.event;

import com.svetroid.main.bot.command.Command;
import com.vdurmont.emoji.EmojiParser;
import net.dv8tion.jda.client.entities.Group;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Input {

  private MessageReceivedEvent messageReceivedEvent;
  private Command command; // The command that was used in the input
  private String commandAlias; // The alias that was used in the input
  private String[] arguments; // The arguments of the input

  public Input(MessageReceivedEvent messageReceivedEvent) {
    this.messageReceivedEvent = messageReceivedEvent;
  }

  public void setCommand(Command command) {
    this.command = command;
  }

  public void setCommandAlias(String commandAlias) {
    this.commandAlias = commandAlias;
  }

  public void setArguments(String[] arguments) {
    this.arguments = arguments;
  }

  public Command getCommand() {
    return command;
  }

  public String getCommandAlias() {
    return commandAlias;
  }

  public String[] getArguments() {
    return arguments;
  }

  public String getArgument(int index) {
    return arguments[index];
  }

  public String getRawContent() {
    return EmojiParser.parseToAliases(messageReceivedEvent.getMessage().getRawContent());
  }

  public Guild getGuild() {
    return messageReceivedEvent.getGuild();
  }

  public MessageChannel getChannel() {
    return messageReceivedEvent.getChannel();
  }

  public TextChannel getTextChannel() {
    return messageReceivedEvent.getTextChannel();
  }

  public PrivateChannel getPrivateChannel() {
    return messageReceivedEvent.getPrivateChannel();
  }

  public Group getGroup() {
    return messageReceivedEvent.getGroup();
  }

  public Message getMessage() {
    return messageReceivedEvent.getMessage();
  }

  public User getAuthor() {
    return messageReceivedEvent.getAuthor();
  }

  public Member getMember() {
    return messageReceivedEvent.getMember();
  }

  public ChannelType getChannelType() {
    return messageReceivedEvent.getChannelType();
  }

  public boolean isFromType(ChannelType channelType) {
    return messageReceivedEvent.isFromType(channelType);
  }

  public JDA getJDA() {
    return messageReceivedEvent.getJDA();
  }

}
