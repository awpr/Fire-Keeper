package com.svetroid.main.util;

import com.svetroid.main.event.Input;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;

public class MessageUtils {

  private MessageUtils() {
  }

  public static String[] validRoles = {"PC", "PlayStation", "Xbox", "Scholars", "Dark Souls I",
      "Dark Souls II", "Dark Souls III", "Demon's Souls", "Bloodborne"};

  public static Role parseRole(
      Input input) { // Strictly for Fire Keeper (and hardcoded unfortunately)
    if (StringUtils.stringContainsItemFromList(input.getRawContent().toLowerCase(),
        new String[]{"pc", "computer", "steam"})) {
      return input.getGuild().getRolesByName("PC", true).get(0);
    } else if (StringUtils.stringContainsItemFromList(input.getRawContent().toLowerCase(),
        new String[]{"playstation", "ps", "ps3", "ps4", "playstation3", "playstation4",
            "play station 3", "play station 4"})) {
      return input.getGuild().getRolesByName("PlayStation", true).get(0);
    } else if (StringUtils.stringContainsItemFromList(input.getRawContent().toLowerCase(),
        new String[]{"xbox", "xbox360", "xbox 360", "xbone", "xbox one", "xboxone", "xbox1",
            "xbox 1", "xb1", "x bone"})) {
      return input.getGuild().getRolesByName("Xbox", true).get(0);
    } else if (StringUtils.stringContainsItemFromList(input.getRawContent().toLowerCase(),
        new String[]{"scholars", "scholar"})) {
      return input.getGuild().getRolesByName("Scholars", true).get(0);
    } else if (StringUtils.stringContainsItemFromList(input.getRawContent().toLowerCase(),
        new String[]{"demon's souls", "demon souls", "demonsouls", "dms"})) {
      return input.getGuild().getRolesByName("Demon's Souls", true).get(0);
    } else if (StringUtils.stringContainsItemFromList(input.getRawContent().toLowerCase(),
        new String[]{"dark souls 3", "ds3", "ds 3", "darksouls3", "darksouls 3", "dks3",
            "dks 3"})) {
      return input.getGuild().getRolesByName("Dark Souls III", true).get(0);
    } else if (StringUtils.stringContainsItemFromList(input.getRawContent().toLowerCase(),
        new String[]{"dark souls 2", "ds2", "ds 2", "darksouls2", "darksouls 2", "dks2",
            "dks 2"})) {
      return input.getGuild().getRolesByName("Dark Souls II", true).get(0);
    } else if (StringUtils.stringContainsItemFromList(input.getRawContent().toLowerCase(),
        new String[]{"dark souls", "dark souls 1", "ds1", "ds", "ds 1", "darksouls1", "darksouls",
            "darksouls 1", "dks", "dks1", "dks 1"})) {
      return input.getGuild().getRolesByName("Dark Souls I", true).get(0);
    } else if (StringUtils.stringContainsItemFromList(input.getRawContent().toLowerCase(),
        new String[]{"bloodborne", "blood borne", "bb"})) {
      return input.getGuild().getRolesByName("Bloodborne", true).get(0);
    } else {
      return null;
    }
  }

  public static List<User> getUsers(Input input) {
    List<User> result = new ArrayList<>();
    if (input.getMessage().getMentionedUsers().size() > 0) {
      result.addAll(input.getMessage().getMentionedUsers());
    }
    for (String s : input.getRawContent().split(" ")) {
      if (s.contains("#") && s.split("#").length == 2 && s.split("#")[1].length() == 4) {
        List<User> potentialUsers = input.getJDA().getUsersByName(s.split("#")[0], true);
        for (User user : potentialUsers) {
          if (user.getDiscriminator().equals(s.split("#")[1])) {
            result.add(user);
          }
        }
      } else if (s.matches("\\d+") && s.length() == 18) {
        if (input.getJDA().getUserById(s) != null) {
          result.add(input.getJDA().getUserById(s));
        }
      }
    }
    return result.stream().distinct().collect(Collectors.toList());
  }

  public static User getUser(Input input) {
    if (getUsers(input).size() == 1) {
      return getUsers(input).get(0);
    } else {
      for (String s : input.getRawContent().split(" ")) {
        if (input.getJDA().getUsersByName(s, true).size() > 0) {
          return input.getJDA().getUsersByName(s, true).get(0);
        }
      }
    }
    return null;
  }

}
