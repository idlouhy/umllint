package net.umllint.common.qvt;

import java.io.OutputStream;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class MediniQVTIntegrationSetup {

  private String umllint = "umllint.ecore";
  private String uml = "uml.ecore";

  private String source = "input/input.xmi";
  private String target = "workspace/umllint.xmi";

  private String qvt = "umllint.qvt";

  private String transformation = "lint";
  private String direction = "target";

  private OutputStream output = System.out;
  private Boolean debug = false;
  private String workspace = "workspace";

  public MediniQVTIntegrationSetup() {
  }

  public MediniQVTIntegrationSetup(String umllint, String uml, String qvt, String source, String target) {
    this.umllint = umllint;
    this.uml = uml;
    this.qvt = qvt;
    this.source = source;
    this.target = target;
  }

  public String getUmllint() {
    return umllint;
  }

  public void setUmllint(String umllint) {
    this.umllint = umllint;
  }

  public String getUml() {
    return uml;
  }

  public void setUml(String uml) {
    this.uml = uml;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public String getQvt() {
    return qvt;
  }

  public void setQvt(String qvt) {
    this.qvt = qvt;
  }

  public String getTransformation() {
    return transformation;
  }

  public void setTransformation(String transformation) {
    this.transformation = transformation;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public OutputStream getOutput() {
    return output;
  }

  public void setOutput(OutputStream output) {
    this.output = output;
  }

  public Boolean getDebug() {
    return debug;
  }

  public void setDebug(Boolean debug) {
    this.debug = debug;
  }

  public String getWorkspace() {
    return workspace;
  }

  public void setWorkspace(String workspace) {
    this.workspace = workspace;
  }
}
