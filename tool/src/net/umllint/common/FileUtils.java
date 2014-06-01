package net.umllint.common;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * UMLLint
 * A Tool for Checking Correctness of Design Diagrams in UML
 *
 * Ivo Dlouhy
 * xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
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

  public static void simpleDownload(URL url, OutputStream outputStream) throws IOException {

    URLConnection urlConnection = url.openConnection();
    InputStream inputStream = urlConnection.getInputStream();

    byte[] buffer = new byte[1024];

    int len;

    while ((len = inputStream.read(buffer)) > 0) {
      outputStream.write(buffer, 0, len);
    }

    inputStream.close();
    outputStream.close();

  }

}
