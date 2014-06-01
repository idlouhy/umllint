package net.umllint.common.model.uml.classes;


import net.umllint.common.model.uml.classes.common.UMLElement;
import net.umllint.common.model.xml.model.XMLAttribute;
import net.umllint.common.model.xml.model.XMLElement;

public class UMLProperty extends UMLElement {


    /*
    <ownedAttribute aggregation="none" isDerived="false" name="initialValueAttribute" ownerScope="instance" visibility="private" xmi:id="B9lxRqKAUAAAaAWW" xmi:type="uml:Property">
        <defaultValue value="12345" xmi:id="B9lxRqKAUAAAaAWW_initialValue" xmi:type="uml:LiteralString"/>
    </ownedAttribute>

    <ownedEnd aggregation="none" association="MaDjEqKAUAAAaAos" isDerived="false" isNavigable="true" name="roleChild" type="DLFjEqKAUAAAaAoR" xmi:id="MaDjEqKAUAAAaAot" xmi:type="uml:Property">
        <lowerValue value="0" xmi:id="MaDjEqKAUAAAaAot_multiplicity_lowerValue" xmi:type="uml:LiteralString"/>
        <upperValue value="*" xmi:id="MaDjEqKAUAAAaAot_multiplicity_upperValue" xmi:type="uml:LiteralString"/>
    </ownedEnd>
    */

    private String aggregation;
    private String isDerived;
    private String ownerScope;
    private String visibility;

    private UMLLiteralString defaultValue = null;
    private UMLLiteralString lowerValue = null;
    private UMLLiteralString upperValue = null;

    public UMLProperty() {
        setXmiType("uml:Property");
    }

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    public String getIsDerived() {
        return isDerived;
    }

    public void setIsDerived(String isDerived) {
        this.isDerived = isDerived;
    }

    public String getOwnerScope() {
        return ownerScope;
    }

    public void setOwnerScope(String ownerScope) {
        this.ownerScope = ownerScope;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public UMLLiteralString getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(UMLLiteralString defaultValue) {
        this.defaultValue = defaultValue;
    }

    public UMLLiteralString getLowerValue() {
        return lowerValue;
    }

    public void setLowerValue(UMLLiteralString lowerValue) {
        this.lowerValue = lowerValue;
    }

    public UMLLiteralString getUpperValue() {
        return upperValue;
    }

    public void setUpperValue(UMLLiteralString upperValue) {
        this.upperValue = upperValue;
    }

    public XMLElement toXML() {

        XMLElement xmlElement = super.toXML();
        xmlElement.addAttribute(new XMLAttribute("aggregation", aggregation));
        xmlElement.addAttribute(new XMLAttribute("isDerived", isDerived));
        xmlElement.addAttribute(new XMLAttribute("ownerScope", ownerScope));
        xmlElement.addAttribute(new XMLAttribute("visibility", visibility));

        //default
        if (defaultValue != null) {
            XMLElement xmlElementDefaultValue = defaultValue.toXML();
            xmlElementDefaultValue.setName("defaultValue");
            xmlElement.addElement(xmlElementDefaultValue);
        }

        //lower
        if (lowerValue != null) {
            XMLElement xmlElementLowerValue = lowerValue.toXML();
            xmlElementLowerValue.setName("lowerValue");
            xmlElement.addElement(xmlElementLowerValue);
        }

        //upper
        if (upperValue != null) {
            XMLElement xmlElementUpperValue = upperValue.toXML();
            xmlElementUpperValue.setName("upperValue");
            xmlElement.addElement(xmlElementUpperValue);
        }

        return xmlElement;
    }

}
