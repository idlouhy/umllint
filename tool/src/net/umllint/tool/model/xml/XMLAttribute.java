package net.umllint.tool.model.xml;

public class XMLAttribute {

    private String name;
    private String value;

    public XMLAttribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    //generated

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //export

    @Override
    public String toString() {
        return String.format("%s=\"%s\"", name, value);
    }
}
