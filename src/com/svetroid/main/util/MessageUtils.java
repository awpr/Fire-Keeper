package com.svetroid.main.util;

import com.svetroid.main.event.Input;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.dv8tion.jda.core.entities.User;

public class MessageUtils {

  private MessageUtils() {
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
