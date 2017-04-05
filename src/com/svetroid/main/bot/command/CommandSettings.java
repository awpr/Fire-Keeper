package com.svetroid.main.bot.command;

import net.dv8tion.jda.core.Permission;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommandSettings {

  private boolean isOwnerCommand;
  private boolean isNSFWCommand;
  private boolean isGuildOnly;
  private Permission[] authorPermissions;
  private Permission[] selfPermissions;
  private String name;
  private String[] aliases;
  private String help;
  private String description;
  private Color embedColor = new Color(128, 128, 128);
  private String category;

  public boolean getOwnerCommand() {
    return isOwnerCommand;
  }

  public boolean getNSFWCommand() {
    return isNSFWCommand;
  }

  public boolean getGuildOnly() {
    return isGuildOnly;
  }

  public Permission[] getAuthorPermissions() {
    return authorPermissions;
  }

  public Permission[] getSelfPermissions() {
    return selfPermissions;
  }

  public String[] getNameAndAliases() {
    List<String> result = new ArrayList<>();
    if (aliases != null) {
      for (String s : aliases) {
        result.add(s);
      }
    }
    result.add(name);
    return result.toArray(new String[aliases == null ? 1 : (aliases.length + 1)]);
  }

  public String getName() {
    return name;
  }

  public String[] getAliases() {
    return aliases;
  }

  public String getHelp() {
    return help;
  }

  public String getDescription() {
    return description;
  }

  public Color getEmbedColor() {
    return embedColor;
  }

  public String getCategory() {
    return category;
  }

  public void setOwnerCommand(boolean isOwnerCommand) {
    this.isOwnerCommand = isOwnerCommand;
  }

  public void setNSFWCommand(boolean isNSFWCommand) {
    this.isNSFWCommand = isNSFWCommand;
  }

  public void setGuildOnly(boolean isGuildOnly) {
    this.isGuildOnly = isGuildOnly;
  }

  public void setAuthorPerms(Permission... authorPermissions) {
    this.authorPermissions = authorPermissions;
  }

  public void setSelfPerms(Permission... selfPermissions) {
    this.selfPermissions = selfPermissions;
  }

  public void setNameAndAliases(String name, String... aliases) {
    this.name = name;
    this.aliases = aliases;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAliases(String... aliases) {
    this.aliases = aliases;
  }

  public void setHelp(String help) {
    this.help = help;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setEmbedColor(int r, int g, int b) {
    this.embedColor = new Color(r, g, b);
  }

  public void setCategory(String category) {
    this.category = category;
  }


}
