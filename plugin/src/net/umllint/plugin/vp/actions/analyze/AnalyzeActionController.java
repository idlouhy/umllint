package net.umllint.plugin.vp.actions.analyze;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ModelConvertionManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IDiagramElement;
import com.vp.plugin.diagram.IDiagramUIModel;
import net.umllint.common.FileUtils;
import net.umllint.common.XMIPreProcessor;
import net.umllint.common.model.pattern.ULPattern;
import net.umllint.common.model.pattern.ULPatternManager;
import net.umllint.common.model.pattern.parser.ULPatternDatabase;
import net.umllint.common.model.result.model.ULResult;
import net.umllint.common.model.result.model.ULResultBinding;
import net.umllint.common.model.result.model.ULResultManager;
import net.umllint.common.model.result.parser.ULResultParser;
import net.umllint.common.qvt.MediniQVTIntegrationSetup;
import net.umllint.common.qvt.MediniQVTIntegrationWrapper;
import net.umllint.common.qvt.QVTComposer;
import net.umllint.plugin.vp.ULPluginContext;
import net.umllint.plugin.vp.ULPluginLog;
import net.umllint.plugin.vp.model.ULVisualLint;
import net.umllint.plugin.vp.model.ULVisualLintManager;
import net.umllint.plugin.vp.ui.result.ResultDialog;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class AnalyzeActionController implements VPActionController {

  ULPatternDatabase database = new ULPatternDatabase();

  ULPatternManager patternManager;
  ULResultManager resultManager;
  ULVisualLintManager lintManager;

  List<IDiagramElement> elements = new ArrayList<IDiagramElement>();

  @Override
  public void performAction(VPAction vpAction) {


    try {

      //load patterns
      String patternfilename = ULPluginContext.instance().get("umllint.file.patterns");
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("File: %s", patternfilename));
      File patternfile = new File(patternfilename);
      patternManager = database.load(patternfile);


      //DEBUG: loaded patterns
      for (ULPattern pattern : patternManager.getPatterns()) {
        ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("Pattern: %s", pattern.getTitle()));
      }

      //load elements
      IDiagramUIModel diagramUIModel = ApplicationManager.instance().getDiagramManager().getActiveDiagram();
      Iterator diagramElementIterator = diagramUIModel.diagramElementIterator();

      while (diagramElementIterator.hasNext()) {
        IDiagramElement diagramElement = (IDiagramElement) diagramElementIterator.next();
        elements.add(diagramElement);
      }


      //Create Input
      String inputfilename = ULPluginContext.instance().get("umllint.input");
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("Input: %s", inputfilename));
      File inputfile = new File(inputfilename);
      ApplicationManager.instance().getModelConvertionManager().exportXMI(inputfile, ModelConvertionManager.EXPORT_XMI_2_1);

      //Process input
      XMIPreProcessor xmiPreProcessor = new XMIPreProcessor();
      String input = FileUtils.readFromFile(inputfile);
      String input_processed = xmiPreProcessor.process(input);
      FileUtils.writeToFile(inputfile, input_processed);

      //Create QVT
      String workspacepath = ULPluginContext.instance().get("umllint.workspace");
      String qvtpath = workspacepath + "/umllint.qvt";
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("QVT: %s", qvtpath));
      File qvtfile = new File(qvtpath);
      QVTComposer composer = new QVTComposer();
      String qvt = composer.compose(patternManager.getPatterns());
      FileUtils.writeToFile(qvtfile, qvt);

      //Setup MediniQVT Transformation
      MediniQVTIntegrationSetup setup = new MediniQVTIntegrationSetup();
      String workspace = ULPluginContext.instance().get("umllint.workspace");
      setup.setWorkspace(workspace);
      setup.setUmllint(ULPluginContext.instance().get("umllint.file.model.umllint"));
      setup.setUml(ULPluginContext.instance().get("umllint.file.model.uml"));
      setup.setQvt(workspace + "/umllint.qvt");
      setup.setSource(ULPluginContext.instance().get("umllint.input"));
      setup.setTarget(workspace + "/umllint.xmi");
      setup.setOutput(new FileOutputStream(new File(workspace + "/transformation.log")));
      setup.setOutput(System.out);
      setup.setDebug(ULPluginContext.instance().getBoolean("umllint.debug.qvt"));

      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("MediniQVT Workspace: %s", setup.getWorkspace()));
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("MediniQVT Umllint: %s", setup.getUmllint()));
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("MediniQVT Uml: %s", setup.getUml()));
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("MediniQVT Qvt: %s", setup.getQvt()));
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("MediniQVT Source: %s", setup.getSource()));
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("MediniQVT Target: %s", setup.getTarget()));
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("MediniQVT Debug: %s", setup.getDebug()));

      MediniQVTIntegrationWrapper wrapper = new MediniQVTIntegrationWrapper(setup);
      wrapper.execute();

      //load Results
      ULResultParser ulResultXMIParser = new ULResultParser();
      String target = FileUtils.readFromFile(new File(workspace + "/umllint.xmi"));
      resultManager = ulResultXMIParser.parse(target);

      for (ULResult result : resultManager.getResults()) {
        ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, String.format("%s %s %s", result.getId(), result.getName(), result.getBindings().get(0).getName()));
      }

      //Build Lints
      lintManager = new ULVisualLintManager();

      for (ULResult result : resultManager.getResults()) {

        for (ULResultBinding binding : result.getBindings()) {
          ULVisualLint lint = new ULVisualLint();

          //add result
          lint.setResult(result);

          //add binding
          lint.setBinding(binding);

          //add pattern
          ULPattern pattern = patternManager.getPatternById(result.getId());
          if (pattern == null) {
            String message = String.format("Pattern for Result ID %s not found!", result.getId());
            ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.DEBUG, message);
            continue;
          }
          lint.setPattern(pattern);

          //find element
          try {

            for (IDiagramElement element : elements) {
              if (element.getMetaModelElement().getId().matches(lint.getBinding().getElement().getId())) {
                lint.setDiagramElement(element);
              }
            }
          } catch (Exception e) {
            ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.INFO, e.getMessage());
          }

          //only add lints with diagram in active diagram
          if (lint.getDiagramElement() != null) {
            lintManager.addLint(lint);
          }
          else {
            ULResultBinding lintBinding = lint.getBinding();
            String lintBindingName = "";
            if (lintBinding != null) {
              lintBindingName = lintBinding.getName();
            }
            String message = String.format("Skipped Lint: %s %s %s", lint.getResult().getId(), lint.getPattern().getTitle(), lintBindingName);
            ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.INFO, message);
          }
        }
      }

      //view the results
      ApplicationManager.instance().getViewManager().showDialog(new ResultDialog(lintManager));

    } catch (IOException e) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.getMessage());
      //ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.toString());
    } catch (ParserConfigurationException e) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.getMessage());
      //ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.toString());
    } catch (SAXException e) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.getMessage());
      //ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, e.toString());
    } catch (Throwable t) {
      ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, t.getMessage());
      //ULPluginLog.getInstance().log(net.umllint.common.ULLog.LogLevel.ERROR, t.toString());
    }
  }

  @Override
  public void update(VPAction vpAction) {

  }

}
