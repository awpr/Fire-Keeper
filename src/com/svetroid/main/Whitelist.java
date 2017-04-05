package com.svetroid.main;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.ArrayList;
import java.util.List;

public class Whitelist {

  private List<String> guilds = new ArrayList<>();
  private List<String> channels = new ArrayList<>();

  public List<String> getGuilds() {
    return guilds;
  }

  public List<String> getChannels() {
    return channels;
  }

  public void add(Guild guild) {
    guilds.add(guild.getId());
  }

  public void add(TextChannel channel) {
    channels.add(channel.getId());
  }

  public void remove(Guild guild) {
    guilds.remove(guild.getId());
  }

  public void remove(TextChannel channel) {
    channels.remove(channel.getId());
  }

}
