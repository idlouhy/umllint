package net.umllint.common.model.uml;

import net.umllint.common.model.xml.model.XMLElement;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLModel extends UMLContainer {

  @Override
  public String toString() {
    return String.format("[Model] name=%s id=%s", getClass().getSimpleName(), getName(), getXmiId());
  }

  public XMLElement toXML() {
    XMLElement element = super.toXML();
    element.setName("uml:Model");
    return element;
  }

}
