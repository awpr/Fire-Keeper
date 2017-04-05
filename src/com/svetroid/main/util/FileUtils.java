package com.svetroid.main.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

  private FileUtils() {
  }

  public static void writeToFile(File file, String line) {
    try {
      if (!file.exists()) {
        file.createNewFile();
      }
      PrintWriter out = new PrintWriter(new FileWriter(file, true));
      out.println(line);
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void replaceFileLineStartsWith(String oldLine, String newLine, String filePath) {
    Path path = Paths.get(filePath);
    List<String> fileContent;
    try {
      fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
      for (int i = 0; i < fileContent.size(); i++) {
        if (fileContent.get(i).startsWith(oldLine)) {
          fileContent.set(i, newLine);
          break;
        }
      }
      Files.write(path, fileContent, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void replaceFileLineContains(String oldLine, String newLine, String filePath) {
    Path path = Paths.get(filePath);
    List<String> fileContent;
    try {
      fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
      for (int i = 0; i < fileContent.size(); i++) {
        if (fileContent.get(i).contains(oldLine)) {
          fileContent.set(i, newLine);
          break;
        }
      }
      Files.write(path, fileContent, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
