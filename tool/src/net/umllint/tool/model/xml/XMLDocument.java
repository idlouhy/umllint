package net.umllint.tool.model.xml;

public class XMLDocument {

    private String version;
    private String encoding;
    private XMLElement root;

    //generated

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

    //export

    @Override
    public String toString() {
        return String.format("[XML] %s", version);
    }
}
