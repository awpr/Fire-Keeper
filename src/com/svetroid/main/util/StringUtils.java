package com.svetroid.main.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

  private StringUtils() {
  }

  private static String unitsMap[] = {"zero", "one", "two", "three", "four", "five", "six", "seven",
      "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
      "sixteen", "seventeen", "eighteen", "nineteen"};
  private static String tensMap[] = {"zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty",
      "seventy", "eighty", "ninety"};

  public static String numberToWord(long number) {
    if (number == 0) {
      return "zero";
    }

    if (number < 0) {
      return "-" + numberToWord(Math.abs(number));
    }

    String words = "";

    if ((number / 1000000000) > 0) {
      words += numberToWord(number / 1000000000) + " billion ";
      number %= 1000000000;
    }

    if ((number / 1000000) > 0) {
      words += numberToWord(number / 1000000) + " million ";
      number %= 1000000;
    }

    if ((number / 1000) > 0) {
      words += numberToWord(number / 1000) + " thousand ";
      number %= 1000;
    }

    if ((number / 100) > 0) {
      words += numberToWord(number / 100) + " hundred ";
      number %= 100;
    }

    if (number > 0) {
      if (number < 20) {
        words += unitsMap[(int) number];
      } else {
        words += tensMap[(int) (number / 10)];
        if ((number % 10) > 0) {
          words += "-" + unitsMap[(int) (number % 10)];
        }
      }
    }
    return words;
  }

  public static String splitEvery(String s, String delimiter, int partitionSize) {
    StringBuilder sb = new StringBuilder();
    for (Iterable<String> iterable : Iterables
        .partition(Splitter.on(delimiter).split(s), partitionSize)) {
      String str = Joiner.on(delimiter).join(iterable) + ",\n";
      sb.append(str);
    }
    String result = replaceLast(sb.toString(), ",", "");
    return result;
  }

  public static List<String> getPartitions(String str, int partitionSize) {
    List<String> parts = new ArrayList<>();
    int len = str.length();
    for (int i = 0; i < len; i += partitionSize) {
      parts.add(str.substring(i, Math.min(len, i + partitionSize)));
    }
    return parts;
  }

  public static String replaceLast(String input, String regex, String replacement) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);
    if (!matcher.find()) {
      return input;
    }
    int lastMatchStart;
    do {
      lastMatchStart = matcher.start();
    } while (matcher.find());
    matcher.find(lastMatchStart);
    StringBuffer sb = new StringBuffer(input.length());
    matcher.appendReplacement(sb, replacement);
    matcher.appendTail(sb);
    return sb.toString();
  }

  public static String arrayToString(String[] array, String separator) {
    StringBuilder sb = new StringBuilder();
    for (String s : array) {
      sb.append(s + separator);
    }
    return sb.toString();
  }

  public static String listToString(List<String> list, String separator) {
    StringBuilder sb = new StringBuilder();
    for (String s : list) {
      sb.append(s + separator);
    }
    return sb.toString();
  }

  public static int getWordCount(String sentence, String word) {
    int count = 0;
    for (String s : sentence.split(" ")) {
      if (s.equalsIgnoreCase(word)) {
        count++;
      }
    }
    return count;
  }

  public static boolean stringContainsItemFromList(String inputStr, String[] items) {
    return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
  }

}