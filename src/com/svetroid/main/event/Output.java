package com.svetroid.main.event;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

public class Output {

  private Input input;

  public Output(Input input) {
    this.input = input;
  }

  public void sendMessage(String text) {
    input.getChannel().sendMessage(text).queue();
  }

  public void sendMessage(Message message) {
    input.getChannel().sendMessage(message).queue();
  }

  public void sendMessage(MessageEmbed messageEmbed) {
    input.getChannel().sendMessage(messageEmbed).queue();
  }

  public void sendFile(File file, Message message) {
    try {
      input.getChannel().sendFile(file, message).queue();
    } catch (IOException e) {
      sendMessage("Failed to send file!");
      e.printStackTrace();
    }
  }

  public void sendFile(File file, String fileName, Message message) {
    try {
      input.getChannel().sendFile(file, fileName, message).queue();
    } catch (IOException e) {
      sendMessage("Failed to send file!");
      e.printStackTrace();
    }
  }

  public void sendFile(InputStream data, String fileName, Message message) {
    input.getChannel().sendFile(data, fileName, message).queue();
  }

  public void sendFile(byte[] data, String fileName, Message message) {
    input.getChannel().sendFile(data, fileName, message).queue();
  }

  public void sendTyping() {
    input.getChannel().sendTyping().queue();
  }

  public void sendPrivateMessage(String text) {
    input.getAuthor().openPrivateChannel().queue(chan -> chan.sendMessage(text).queue());
  }

  public void sendPrivateMessage(Message message) {
    input.getAuthor().openPrivateChannel().queue(chan -> chan.sendMessage(message).queue());
  }

  public void sendPrivateMessage(MessageEmbed messageEmbed) {
    input.getAuthor().openPrivateChannel().queue(chan -> chan.sendMessage(messageEmbed).queue());
  }

  // Functional

  public void sendMessage(String text, Consumer<Message> function) {
    input.getChannel().sendMessage(text).queue(function);
  }

  public void sendMessage(Message message, Consumer<Message> function) {
    input.getChannel().sendMessage(message).queue(function);
  }

  public void sendMessage(MessageEmbed messageEmbed, Consumer<Message> function) {
    input.getChannel().sendMessage(messageEmbed).queue(function);
  }

  public void sendFile(File file, Message message, Consumer<Message> function) {
    try {
      input.getChannel().sendFile(file, message).queue(function);
    } catch (IOException e) {
      sendMessage("Failed to send file!");
      e.printStackTrace();
    }
  }

  public void sendFile(File file, String fileName, Message message, Consumer<Message> function) {
    try {
      input.getChannel().sendFile(file, fileName, message).queue(function);
    } catch (IOException e) {
      sendMessage("Failed to send file!");
      e.printStackTrace();
    }
  }

  public void sendFile(InputStream data, String fileName, Message message,
      Consumer<Message> function) {
    input.getChannel().sendFile(data, fileName, message).queue(function);
  }

  public void sendFile(byte[] data, String fileName, Message message, Consumer<Message> function) {
    input.getChannel().sendFile(data, fileName, message).queue(function);
  }

}
