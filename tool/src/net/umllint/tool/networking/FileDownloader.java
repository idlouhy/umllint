package net.umllint.tool.networking;

import net.umllint.tool.cli.IPercentUpdateListener;
import net.umllint.tool.cli.TextUIProgressbar;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class FileDownloader {

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


  public static void progressDownload(URL url, OutputStream outputStream, IPercentUpdateListener listener) throws IOException {

    URLConnection urlConnection = url.openConnection();
    InputStream inputStream = urlConnection.getInputStream();
    Long sizeTotal = urlConnection.getContentLengthLong();
    Float sizeDownloaded = new Float(0.0);

    Float percentDownloaded = new Float(0.0);


    byte[] buffer = new byte[4096];

    int i;

    while ((i = inputStream.read(buffer)) > 0) {
      sizeDownloaded = sizeDownloaded + i;
      percentDownloaded = sizeDownloaded / sizeTotal;
      listener.setPercent(percentDownloaded);
      outputStream.write(buffer, 0, i);
    }

    inputStream.close();
    outputStream.close();

  }


  public static void main(String[] args) throws IOException {
    String fileSmall = "http://localhost:8080/api/xml/patterns.xml";

    TextUIProgressbar textUIProgressbar = new TextUIProgressbar("patterns.xml");
    textUIProgressbar.render();

    FileOutputStream fileOutputStream = new FileOutputStream(new File("download"));
    FileDownloader.progressDownload(new URL(fileSmall), fileOutputStream, textUIProgressbar);

  }


}
