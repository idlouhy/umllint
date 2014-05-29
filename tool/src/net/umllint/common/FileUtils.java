package net.umllint.common;

import java.io.*;

/**
 * Created by idlouhy on 4/3/14.
 */
public class FileUtils {

  public static String readFromFile(File file) throws IOException {

    BufferedReader br = new BufferedReader(new FileReader(file));
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();

    while (line != null) {
      sb.append(line);
      sb.append(System.lineSeparator());
      line = br.readLine();
    }

    return sb.toString();
  }

  public static void writeToFile(File file, String content) throws IOException {

    Writer writer = null;
    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
    writer.write(content);
    writer.close();

  }


  public static void main(String[] args) throws IOException {


  }


}
