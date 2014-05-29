package net.umllint.tool.model.uml.relations.properties;


import net.umllint.tool.model.uml.UMLAbstractEntity;
import net.umllint.tool.model.uml.UMLEntityDirectory;

public class UMLRelationEnd extends UMLAbstractEntity {

    private String isDerived;
    private String isNavigable;

    private String association; //id
    private String aggregation; //none/composite/aggregate

    private String type; //id

    private UMLRelationEndMultiplicity lowerMultiplicity;
    private UMLRelationEndMultiplicity upperMultiplicity;

    public UMLRelationEnd() {
        setXmiType("uml:Property");
    }

    @Override
    public String toString() {
        return String.format("[%s] name=%s id=%s type=%s lower=%s upper=%s", getClass().getSimpleName(), getName(), getXmiId(), getType(), getLowerMultiplicity(), getUpperMultiplicity());
    }

    //auto

    public String getIsDerived() {
        return isDerived;
    }

    public void setIsDerived(String isDerived) {
        this.isDerived = isDerived;
    }

    public String getIsNavigable() {
        return isNavigable;
    }

    public void setIsNavigable(String isNavigable) {
        this.isNavigable = isNavigable;
    }

    public String getAssociation() {
        return UMLEntityDirectory.resolveXmiId(association);
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    public UMLRelationEndMultiplicity getLowerMultiplicity() {
        return lowerMultiplicity;
    }

    public void setLowerMultiplicity(UMLRelationEndMultiplicity lowerMultiplicity) {
        this.lowerMultiplicity = lowerMultiplicity;
    }

    public UMLRelationEndMultiplicity getUpperMultiplicity() {
        return upperMultiplicity;
    }

    public void setUpperMultiplicity(UMLRelationEndMultiplicity upperMultiplicity) {
        this.upperMultiplicity = upperMultiplicity;
    }

    public String getType() {
        return UMLEntityDirectory.resolveXmiId(type);
    }

    public void setType(String type) {
        this.type = type;
    }
}
