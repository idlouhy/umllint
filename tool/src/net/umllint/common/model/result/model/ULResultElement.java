package net.umllint.common.model.result.model;

public class ULResultElement {

    //<element xsi:type="uml:Interface" href="resources/xmi/input.xmi#_OyhtcLk2Edyv6qRmEdcM-A"/>

    private String id;
    private String type;
    private String href;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return String.format("[Element] id=%s type=%s href=%s", getId(), getType(), getHref());
    }
}
