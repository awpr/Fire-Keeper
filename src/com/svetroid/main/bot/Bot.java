package com.svetroid.main.bot;

import com.google.gson.Gson;
import com.svetroid.main.bot.authentication.Authentication;
import com.svetroid.main.event.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Bot {

  private JDA jda;
  private BotListener botListener;
  private Authentication authentication;

  public Bot() {
    this.authentication = readAuth("res/auth/bot-auth.json");
  }

  public JDA getJDA() {
    return jda;
  }

  public Authentication getAuth() {
    return authentication;
  }

  public String getDefaultPrefix() {
    return authentication.getDefaultPrefix();
  }

  public String getPrefix() {
    return authentication.getPrefix();
  }

  public void setListener(BotListener botListener) {
    this.botListener = botListener;
  }

  private Authentication readAuth(String directory) {
    File file = new File(directory);
    if (file.exists()) {
      Gson gson = new Gson();
      try {
        Reader reader = new FileReader(file);
        return gson.fromJson(reader, Authentication.class);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public void start() {
    try {
      JDA jda = new JDABuilder(AccountType.BOT).setToken(authentication.getToken()).addEventListener(botListener).buildBlocking();
      jda.setAutoReconnect(true);
      this.jda = jda;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
