package io.github.lst96.Information.update;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.github.lst96.Information.Information;

public class UpdateChecker
{
  public static String dcPage = "http://dev.bukkit.org/server-mods/information/";

  public static String getLatestVersion() {
    try {
      URL devPage = new URL(dcPage);
      BufferedReader in = new BufferedReader(new InputStreamReader(devPage.openStream()));

      String importantLine = "";
      String line;
      while ((line = in.readLine()) != null) {
        if (line.trim().equalsIgnoreCase("<dt>Recent files</dt>")) {
          importantLine = filterHTML(in.readLine());
        }
      }

      in.close();

      if (importantLine.equals("")) {
        return "Error while checking!";
      }
      String[] files = importantLine.split("R: ");
      return files[1].trim();
    } catch (Exception e) {
      Information.logger.log(Level.WARNING, "Error checking for updates", e);
    }
    return "Error during check!";
  }

  public static String filterHTML(String toFilter)
  {
    String HTMLfiltered = "";
    boolean HTMLtag = false;
    for (int i = 0; i < toFilter.length(); i++) {
      if (toFilter.charAt(i) == '<')
        HTMLtag = true;
      else if (toFilter.charAt(i) == '>') {
        if (!HTMLtag) {
          HTMLfiltered = HTMLfiltered + toFilter.charAt(i);
        }
        else {
          HTMLtag = false;
        }
      }
      else if (!HTMLtag) {
        HTMLfiltered = HTMLfiltered + toFilter.charAt(i);
      }
    }

    return HTMLfiltered;
  }
}