package net.umllint.common.model.uml;

import net.umllint.common.model.xml.model.XMLElement;

import java.util.LinkedList;
import java.util.List;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public abstract class UMLContainer extends UMLAbstractEntity {

    private List<UMLAbstractEntity> entities = new LinkedList<UMLAbstractEntity>();

    //custom

    public void addEntity(UMLAbstractEntity entity) {
        this.entities.add(entity);
    }

    //generated

    public List<UMLAbstractEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<UMLAbstractEntity> entities) {

        this.entities = entities;
    }

    //export
    public XMLElement toXML() {

        XMLElement xmlElement = super.toXML();

        for (UMLAbstractEntity entity : entities) {
            XMLElement xmlElementChild = entity.toXML();
            xmlElementChild.setName("ownedMember");
            xmlElement.addElement(xmlElementChild);
        }

        return xmlElement;
    }
}
