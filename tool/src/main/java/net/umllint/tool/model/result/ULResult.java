package net.umllint.tool.model.result;

import java.util.LinkedList;
import java.util.List;

public class ULResult {

    private String id;
    private String name;
    private List<ULResultBinding> bindings = new LinkedList<ULResultBinding>();


    public void addBinding(ULResultBinding binding) {
        this.bindings.add(binding);
    }

    //auto

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

    public List<ULResultBinding> getBindings() {
        return bindings;
    }

    public void setBindings(List<ULResultBinding> binding) {
        this.bindings = binding;
    }


    @Override
    public String toString() {

        StringBuilder bindingStrings = new StringBuilder();

        for (ULResultBinding binding : bindings) {
            bindingStrings.append(binding.toString());
        }

        return String.format("[Pattern] %s %s%n%s", getId(), getName(), bindingStrings.toString());
    }

}
