package net.umllint.tool.model.xml;

import java.util.*;

public class XMLElement {

    private String name;
    private Map<String, XMLAttribute> attributes = new HashMap<String, XMLAttribute>();
    private List<XMLElement> elements = new LinkedList<XMLElement>();

    public XMLElement(String name) {
        this.name = name;
    }

    public void addAttribute(XMLAttribute attribute) {
        this.attributes.put(attribute.getName(), attribute);
    }

    public void addElement(XMLElement element) {
        this.elements.add(element);
    }

    //proxy

    public XMLElement child(String key) {
        return getFirstElement(key);
    }

    //helper

    public XMLElement getFirstElement(String key) {

        for (XMLElement element : elements) {
            if (element.getName().equals(key)) {
                return element;
            }
        }

        return null;
    }

    public XMLAttribute getAttribute(String key) {
        return attributes.get(key);
    }

    public String getAttributeValue(String key) {
        XMLAttribute attribute = attributes.get(key);
        if (attribute != null) {
            return  attribute.getValue();
        }
        else {
            return null;
        }
    }

    //generated

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<XMLAttribute> getAttributes() {
        return attributes.values();
    }

    public List<XMLElement> getElements() {
        return elements;
    }

    //export

    @Override
    public String toString() {
        return String.format("[%s]", name);
    }
}
