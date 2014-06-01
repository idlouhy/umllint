package net.umllint.plugin.vp.actions.update;

import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import net.umllint.common.model.pattern.ULPattern;
import net.umllint.common.model.pattern.ULPatternManager;
import net.umllint.common.model.pattern.parser.ULPatternDatabase;
import net.umllint.plugin.vp.ULPluginContext;
import net.umllint.plugin.vp.ULPluginLog;
import net.umllint.plugin.vp.UMLLintManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UpdateActionController implements VPActionController {

  private ULPatternDatabase database = new ULPatternDatabase();
  private ULPatternManager manager;

  @Override
  public void performAction(VPAction aAction) {

    try {
      String filename = ULPluginContext.instance().get("umllint.file.patterns");
      String link = ULPluginContext.instance().get("umllint.server.api")  + "/xml/patterns.xml";

      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("URL: %s", link));
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("File: %s", filename));

      File file = new File(filename);
      URL url = new URL(link);

      database.update(file, url);
      manager = database.load(file);

      for (ULPattern pattern : manager.getPatterns()) {
        ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("Pattern: %s", pattern.getTitle()));
        UMLLintManager.instance().mask.add(true);
      }

    } catch (ParserConfigurationException e) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.getMessage());
    } catch (IOException e) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.getMessage());
    } catch (SAXException e) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.getMessage());
    } catch (Exception e) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.getMessage());
    }

  }

  @Override
  public void update(VPAction aAction) {
  }


}
