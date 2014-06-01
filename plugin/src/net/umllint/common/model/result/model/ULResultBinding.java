package net.umllint.common.model.result.model;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class ULResultBinding {

//    <binding id="1" name="" type="interface">
//      <binding id="456" name="property1" type="property">
//        <element xsi:type="uml:Property" href="resources/xmi/input.xmi#_Oyhtcbk2Edyv6qRmEdcM-A111"/>
//      </binding>
//      <element xsi:type="uml:Interface" href="resources/xmi/input.xmi#_OyhtcLk2Edyv6qRmEdcM-A"/>
//    </binding>

    private String id;
    private String name;
    private String type;
    private ULResultElement element;
    private ULResultBinding binding;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ULResultElement getElement() {
        return element;
    }

    public void setElement(ULResultElement element) {
        this.element = element;
    }

    public ULResultBinding getBinding() {
        return binding;
    }

    public void setBinding(ULResultBinding binding) {
        this.binding = binding;
    }

    @Override
    public String toString() {
        String bindingString = "";
        if (binding != null) {
            bindingString = String.format("\t\t- %s%n", getBinding().toString());
        }

        return String.format("[Binding] %s%n%s%n%s", getId(), getElement(), bindingString);
    }
}
