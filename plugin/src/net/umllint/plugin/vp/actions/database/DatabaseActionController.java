package net.umllint.plugin.vp.actions.database;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import net.umllint.common.FileUtils;
import net.umllint.common.model.pattern.ULPattern;
import net.umllint.common.model.pattern.parser.ULPatternDatabase;
import net.umllint.common.model.pattern.parser.ULPatternParser;
import net.umllint.plugin.vp.ULLog;
import net.umllint.plugin.vp.ULPluginContext;
import net.umllint.plugin.vp.ULPluginLog;
import net.umllint.plugin.vp.UMLLintManager;
import net.umllint.plugin.vp.ui.pattern.PatternListDialog;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class DatabaseActionController implements VPActionController {

  @Override
  public void performAction(VPAction vpAction) {

    try {
      String filename = ULPluginContext.instance().get("umllint.file.patterns");
      String message = String.format("Patterns database: %s", filename);
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, message);

      File file = new File(filename);
      List<ULPattern> patterns = ULPatternParser.parse(FileUtils.readFromFile(file));

      for (ULPattern pattern : patterns) {
        ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("Pattern: %s", pattern.getTitle()));
        UMLLintManager.instance().mask.add(true);
      }
      ApplicationManager.instance().getViewManager().showDialog(new PatternListDialog(patterns));

    } catch (ParserConfigurationException e) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.getMessage());
    } catch (IOException e) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.getMessage());
    } catch (SAXException e) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.getMessage());
    }

  }

  @Override
  public void update(VPAction vpAction) {

  }

}
