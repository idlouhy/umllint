package net.umllint.common.model.uml.classes;


import net.umllint.common.model.uml.UMLEntityDirectory;
import net.umllint.common.model.uml.classes.common.UMLElement;

public class UMLGeneralization extends UMLElement {

    private String general;

    public UMLGeneralization() {
        setXmiType("uml:Generalization");
    }

    public String getGeneral() {
        return UMLEntityDirectory.resolveXmiId(general);
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    @Override
    public String toString() {
        return String.format("[%s] name=%s id=%s general=%s", getClass().getSimpleName(), getName(), getXmiId(), getGeneral());
    }
}
