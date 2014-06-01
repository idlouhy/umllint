package net.umllint.plugin.vp.actions.analyze;

import com.vp.plugin.diagram.IDiagramElement;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.IStereotype;
import net.umllint.common.ULLog;
import net.umllint.common.model.uml.UMLAbstractEntity;
import net.umllint.common.model.uml.UMLDocument;
import net.umllint.common.model.uml.UMLModel;
import net.umllint.common.model.uml.classes.UMLClass;
import net.umllint.common.model.uml.classes.UMLInterface;
import net.umllint.common.model.uml.classes.UMLOperation;
import net.umllint.common.model.uml.classes.UMLProperty;
import net.umllint.plugin.vp.ULPluginLog;

import java.util.ArrayList;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class BuildUML {

  public UMLDocument buildUML(List<IDiagramElement> elements) {


    UMLDocument umlDocument = new UMLDocument();
    UMLModel umlModel = new UMLModel();


    for (IDiagramElement element : elements) {

      String message = String.format("Processing: %s %s %s %s %n",
          element.getMetaModelElement().getId(),
          element.getMetaModelElement().getName(),
          element.getMetaModelElement().getModelType(),
          element.getMetaModelElement().childCount()
      );
      ULPluginLog.getInstance().log(ULLog.LogLevel.DEBUG, String.format("Navigate to: %s", message));

      IModelElement modelElement = element.getMetaModelElement();

      UMLAbstractEntity abstractEntity = buildUMLSection(modelElement);

      if (abstractEntity != null) {
        umlModel.addEntity(abstractEntity);
      }
    }

    umlDocument.setUmlModel(umlModel);
    return umlDocument;
  }


  public UMLAbstractEntity buildUMLSection(IModelElement modelElement) {

    String type = modelElement.getModelType();

    if (type.matches("Class")) {

      IStereotype[] stereotypes = modelElement.toStereotypeModelArray();
      if (stereotypes != null) {
        for (IStereotype stereotype : stereotypes) {
          String message = String.format("___CLASS STEREOTYPES: stereotype: %s", stereotype.getName());
          ULPluginLog.getInstance().log(ULLog.LogLevel.DEBUG, String.format("Navigate to: %s", message));
        }
      }

      //interface
      if (modelElement.hasStereotype("Interface")) {

        UMLInterface umlElement = new UMLInterface();
        umlElement.setXmiId(modelElement.getId());
        umlElement.setName(modelElement.getName());
        return umlElement;
      }

      //class
      else {
        UMLClass umlClass = new UMLClass();
        umlClass.setXmiId(modelElement.getId());
        umlClass.setName(modelElement.getName());

        Boolean isAbstract = modelElement.getModelPropertyByName("abstract").getValueAsBoolean();
        umlClass.setIsAbstract(isAbstract ? "true" : "false");
        //umlClass.setIsAbstract(modelElement.);

        //properties
        List<UMLProperty> umlProperties = new ArrayList<UMLProperty>();
        List<UMLOperation> umlOperations = new ArrayList<UMLOperation>();

        for (IModelElement modelElementChild : modelElement.toChildArray()) {

          if (modelElementChild.getModelType().matches("Attribute")) {
            UMLProperty umlProperty = new UMLProperty();
            umlProperty.setName(modelElementChild.getName());
            umlProperty.setXmiId(modelElementChild.getId());
            umlProperties.add(umlProperty);
          }

          if (modelElementChild.getModelType().matches("Operation")) {
            UMLOperation umlOperation = new UMLOperation();
            umlOperation.setName(modelElementChild.getName());
            umlOperation.setXmiId(modelElementChild.getId());
            umlOperations.add(umlOperation);
          }
        }
        umlClass.setProperties(umlProperties);
        umlClass.setOperations(umlOperations);
        return umlClass;
      }
    }

    return null;

  }




}
