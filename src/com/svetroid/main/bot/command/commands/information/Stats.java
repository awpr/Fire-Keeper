package com.svetroid.main.bot.command.commands.information;

import com.svetroid.main.Main;
import com.svetroid.main.bot.UptimeTracker;
import com.svetroid.main.bot.command.Command;
import com.svetroid.main.bot.command.CommandHandler;
import com.svetroid.main.event.Input;
import com.svetroid.main.event.Output;

public class Stats extends Command {

  public Stats() {
    settings.setNameAndAliases("stats", "statistics");
    settings.setHelp(Main.bot.getPrefix() + "stats");
    settings.setDescription("Displays the bot's statistics.");
    settings.setEmbedColor(45, 110, 205);
  }

  @Override
  protected void execute(Input input, Output output) {
    String uptime = "**Uptime (Session)**: " + UptimeTracker.getUptime();
    String serverCount = "**Servers**: " + Main.bot.getJDA().getGuilds().size();
    String userCount = "**Users**: " + getUserCount();
    String commandCount = "**Commands**: " + CommandHandler.commands.size();
    String memoryUsage = "**Memory Usage**: "
        + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024)
        + "MB";

    output.sendMessage(createEmbed(settings.getEmbedColor(), "Statistics",
        uptime + "\n" + serverCount + "\n" + commandCount + "\n" + userCount + "\n" + memoryUsage));
  }

  private long getUserCount() {
    long totalUserCount = 0L;
    for (int i = 0; i < Main.bot.getJDA().getGuilds().size(); i++) {
      totalUserCount += Main.bot.getJDA().getGuilds().get(i).getMembers().size();
    }
    return totalUserCount;
  }

}
