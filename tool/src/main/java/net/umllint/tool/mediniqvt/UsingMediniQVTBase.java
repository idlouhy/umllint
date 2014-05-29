package net.umllint.tool.mediniqvt;

public class UsingMediniQVTBase {

  private String modelULResults = "umllint.ecore";
  private String modelUML = "uml.ecore";
  //private String modelUML = "http://www.eclipse.org/uml2/2.1.0/UML";
  private String xmiSource = "source.xmi";
  private String xmiTarget = "target.xmi";
  private String qvtScript = "umllint.qvt";
  private String ecore = "ecore.ecore";

  public String getModelULResults() {
    return modelULResults;
  }

  public void setModelULResults(String modelULResults) {
    this.modelULResults = modelULResults;
  }

  public String getModelUML() {
    return modelUML;
  }

  public void setModelUML(String modelUML) {
    this.modelUML = modelUML;
  }

  public String getXmiSource() {
    return xmiSource;
  }

  public void setXmiSource(String xmiSource) {
    this.xmiSource = xmiSource;
  }

  public String getXmiTarget() {
    return xmiTarget;
  }

  public void setXmiTarget(String xmiTarget) {
    this.xmiTarget = xmiTarget;
  }

  public String getQvtScript() {
    return qvtScript;
  }

  public void setQvtScript(String qvtScript) {
    this.qvtScript = qvtScript;
  }

  public String getEcore() {
    return ecore;
  }

  public void setEcore(String ecore) {
    this.ecore = ecore;
  }
}
