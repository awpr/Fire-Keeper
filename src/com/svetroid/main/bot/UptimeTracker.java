package com.svetroid.main.bot;

import com.svetroid.main.Main;

import java.time.Duration;
import java.time.Instant;

public class UptimeTracker {

  private static long msElapsed;
  private static long secondsElapsed;
  private static int seconds;
  private static int minutes;
  private static int hours;
  private static int days;
  private static int years;

  private static StringBuilder sb;

  public static String getUptime() {
    Instant now = Instant.now();
    Duration elapsedTime = Duration.between(Main.START_TIME, now);
    return getFormattedTime(elapsedTime);
  }


  private static String getFormattedTime(Duration duration) {
    msElapsed = duration.toMillis();
    secondsElapsed = msElapsed / 1000;
    seconds = (int) secondsElapsed % 60;
    minutes = (int) (secondsElapsed / 60) % 60;
    hours = (int) (secondsElapsed / 3600) % 24;
    days = (int) (secondsElapsed / 86400) % 365;
    years = (int) (secondsElapsed / 31536000);

    sb = new StringBuilder();
    sb.append(years == 0 ? ""
        : (days == 0 && hours == 0 && minutes == 0 && seconds == 0 ? years + (years == 1 ? " year "
            : " years ")
            : years + (years == 1 ? " year, " : " years, ")));
    sb.append(days == 0 ? ""
        : (hours == 0 && minutes == 0 && seconds == 0 ? days + (days == 1 ? " day " : " days ")
            : days + (days == 1 ? " day, " : " days, ")));
    if (years == 0 && days == 0) {
      if (hours == 0) {
        if (minutes == 0) {
          sb.append(String.format("%ds", seconds));
        } else {
          sb.append(
              String.format("%d:%02d", minutes, seconds) + " (" + minutes + "m" + seconds + "s"
                  + ")");
        }
      } else {
        sb.append(
            String.format("%d:%02d:%02d", hours, minutes, seconds) + " (" + hours + "h" + minutes
                + "m" + seconds + "s" + ")");
      }
    } else {
      sb.append(hours == 0 ? "" : hours + "h");
      sb.append(minutes == 0 ? "" : minutes + "m");
      sb.append(seconds == 0 ? "" : seconds + "s");
    }
    return sb.toString().trim().replaceAll("\\s+", " ");
  }

}
