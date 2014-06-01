package net.umllint.common.model.xml.model;

public class XMLDocument {

    private String version;
    private String encoding;
    private XMLElement root;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public XMLElement getRoot() {
        return root;
    }

    public void setRoot(XMLElement root) {
        this.root = root;
    }

}
