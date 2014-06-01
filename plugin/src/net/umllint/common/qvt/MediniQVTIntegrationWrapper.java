package net.umllint.common.qvt;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class MediniQVTIntegrationWrapper {

  private MediniQVTIntegrationSetup setup = null;
  private MediniQVTIntegration integration = null;

  public MediniQVTIntegrationWrapper(MediniQVTIntegrationSetup setup) {
    this.setup = setup;
    this.integration = new MediniQVTIntegration(setup);
  }

  public void execute() throws Throwable {
    integration.execute();
  }

}
