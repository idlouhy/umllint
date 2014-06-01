package net.umllint.common.model.uml;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLDocument {

    private String xmiVersion;
    private String xmlVersion;

    private String xmlNamespace;
    private String umlNamespace;
    private String xmiNamespace;

    private String exporterVersion;
    private String exporter;


    private UMLModel umlModel = null;

    public String getXmiVersion() {
        return xmiVersion;
    }

    public void setXmiVersion(String xmiVersion) {
        this.xmiVersion = xmiVersion;
    }

    public String getXmlVersion() {
        return xmlVersion;
    }

    public void setXmlVersion(String xmlVersion) {
        this.xmlVersion = xmlVersion;
    }

    public String getXmlNamespace() {
        return xmlNamespace;
    }

    public void setXmlNamespace(String xmlNamespace) {
        this.xmlNamespace = xmlNamespace;
    }

    public String getUmlNamespace() {
        return umlNamespace;
    }

    public void setUmlNamespace(String umlNamespace) {
        this.umlNamespace = umlNamespace;
    }

    public String getXmiNamespace() {
        return xmiNamespace;
    }

    public void setXmiNamespace(String xmiNamespace) {
        this.xmiNamespace = xmiNamespace;
    }

    public String getExporterVersion() {
        return exporterVersion;
    }

    public void setExporterVersion(String exporterVersion) {
        this.exporterVersion = exporterVersion;
    }

    public String getExporter() {
        return exporter;
    }

    public void setExporter(String exporter) {
        this.exporter = exporter;
    }

    public UMLModel getUmlModel() {
        return umlModel;
    }

    public void setUmlModel(UMLModel umlModel) {
        this.umlModel = umlModel;
    }

    @Override
    public String toString() {
        return String.format("[%s] xmi-version=%s xmi-ns=%s uml-ns=%s %n \t%s%n", getClass().getSimpleName(), getXmiVersion(), getXmiNamespace(), getUmlNamespace(), getUmlModel());
    }
}
