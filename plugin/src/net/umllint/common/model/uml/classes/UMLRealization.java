package net.umllint.common.model.uml.classes;

import net.umllint.common.model.uml.UMLAbstractEntity;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLRealization extends UMLAbstractEntity {

    private String client;
    private String realizingClassifier;
    private String supplier;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRealizingClassifier() {
        return realizingClassifier;
    }

    public void setRealizingClassifier(String realizingClassifier) {
        this.realizingClassifier = realizingClassifier;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return String.format("[%s] name=%s id=%s", getClass().getSimpleName(), getName(), getXmiId());
    }
}
