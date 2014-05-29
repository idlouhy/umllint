package net.umllint.tool.mediniqvt;

/**
 * Created by idlouhy on 4/12/14.
 */
public class ULMediniQVTWrapper {

    /*
    String modelULResults = "resources/ulresult.ecore";
    String modelUML = "resources/uml.ecore";
    String xmiSource = "resources/ulresult.ecore";
    String xmiTarget = "resources/ulresult.ecore";
    String qvtScript = "resources/ulresult.ecore";

    String transformation = "checkUML"; // TODO: adjust
    // give the direction of the transformation (according to the transformation definition)
    String direction = "target"; // TODO: adjust
*/


    public void execute() {
        UsingMediniQVTfromApplications usingMediniQVTfromApplications = new UsingMediniQVTfromApplicationsReflective(System.out);
        usingMediniQVTfromApplications.launch();
    }

    public static void main(String[] args) {

        ULMediniQVTWrapper ulMediniQVTWrapper = new ULMediniQVTWrapper();
        ulMediniQVTWrapper.execute();

    }





}
