package com.svetroid.main;

import com.svetroid.main.bot.Bot;
import com.svetroid.main.event.BotListener;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.User;

import java.time.Instant;

public class Main {

  // Bad practice. Will update later.
  public static Whitelist NSFWWhitelist = new Whitelist();
  public static Whitelist commandBlacklist = new Whitelist();
  //


  public static final Instant START_TIME = Instant.now();

  public static final Bot bot = new Bot();
  public static final BotListener botListener = new BotListener();

  public static void main(String[] args) {
    bot.setListener(botListener);
    bot.start();

    User owner = bot.getJDA().getUserById(bot.getAuth().getOwnerID());
    User botUser = bot.getJDA().getSelfUser();
    bot.getJDA().getPresence()
        .setGame(Game.of("@" + botUser.getName() + " help | " + bot.getAuth().getPrefix() + "help"
            + " | Owner: @" + owner.getName() + "#" + owner.getDiscriminator()));

  }

}