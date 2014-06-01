package net.umllint.common.model.uml;


import net.umllint.common.model.xml.model.XMLElement;

public class UMLModel extends UMLContainer {

    @Override
    public String toString() {

        /*
        StringBuilder child = new StringBuilder();

        for (UMLAbstractEntity umlClass : getEntities()) {
            child.append(String.format("\t\t"));
            child.append(umlClass.toString());
            child.append(String.format("%n"));
        }

        return String.format("[%s] name=%s id=%s %n%s", getClass().getSimpleName(), getName(), getXmiId(), child.toString());
        */
        return String.format("[Model] name=%s id=%s", getClass().getSimpleName(), getName(), getXmiId());
    }

    public XMLElement toXML() {
        XMLElement element = super.toXML();
        element.setName("uml:Model");
        return element;
    }

}
