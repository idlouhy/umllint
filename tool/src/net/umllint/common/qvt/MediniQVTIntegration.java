package net.umllint.common.qvt;

import de.ikv.emf.qvt.EMFQvtProcessorImpl;
import de.ikv.medini.qvt.QVTProcessorConsts;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import uk.ac.kent.cs.kmf.util.ILog;
import uk.ac.kent.cs.kmf.util.OutputStreamLog;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * UMLLint
 * A Tool for Checking Correctness of Design Diagrams in UML
 *
 * Ivo Dlouhy
 * xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 *
 * Copyright (c) 2007 ikv++ technologies ag.
 * All rights reserved.
 * Hajo Eichler <eichler@ikv.de>
 *
 *
 */

public class MediniQVTIntegration {

  private MediniQVTIntegrationSetup setup;

  private ILog logger;
  private EMFQvtProcessorImpl qvtProcessorImpl;
  protected ResourceSet resourceSet;


  public MediniQVTIntegration(MediniQVTIntegrationSetup setup) {
    this.setup = setup;
    this.logger = new OutputStreamLog(setup.getOutput());
    this.qvtProcessorImpl = new EMFQvtProcessorImpl(this.logger);

    this.qvtProcessorImpl.setProperty(QVTProcessorConsts.PROP_DEBUG, setup.getDebug()? "true" : "false");
  }

  /**
   * Provide the information about the metamodels, which are involved in the transformation
   *
   * @param ePackages the metamodel packages
   */
  public void init(Collection<EPackage> ePackages) {
    Iterator<EPackage> iterator = ePackages.iterator();
    while (iterator.hasNext()) {
      this.qvtProcessorImpl.addMetaModel(iterator.next());
    }
  }

  private void clean() {
    this.qvtProcessorImpl.setModels(Collections.EMPTY_LIST);
  }

  /**
   * Before transforming, the engine has to now the model to perform the transformation on, as
   * well as a directory for the traces to store.
   *
   * @param modelResources   models for the execution - take care of the right order!
   * @param workingDirectory working directory of the QVT engine
   */
  public void preExecution(Collection<?> modelResources, URI workingDirectory) {
    this.qvtProcessorImpl.setWorkingLocation(workingDirectory);
    this.qvtProcessorImpl.setModels(modelResources);
  }

  /**
   * Transform a QVT script in a specific direction.
   *
   * @param qvtRuleSet     the QVT transformation
   * @param transformation name of the transformation
   * @param direction      name of the target - must conform to your QVT transformation definition
   * @throws Throwable
   */
  public void transform(Reader qvtRuleSet, String transformation, String direction) throws Throwable {

    //handle exceptions to be not thrown but ignored and logged!
    this.qvtProcessorImpl.evaluateQVT(qvtRuleSet, transformation, true, direction, new Object[0], null, this.logger);
    this.clean();
  }

  /**
   * Helper for XMI loading. Does lazy loading.
   *
   * @param xmlFileName file name to load
   * @return the EMF resource
   */
  public Resource getResource(String xmlFileName) {
    URI uri = URI.createFileURI(xmlFileName);
    Resource resource = null;
    try {
      resource = this.resourceSet.getResource(uri, true);
    } catch (Throwable fileNotFoundException) {
      resource = this.resourceSet.createResource(uri);
    }
    return resource;
  }

  public void execute()  throws Throwable {


    // initialize resource set of models
    this.resourceSet = new ResourceSetImpl();
    this.resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
        Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

    // TODO: collect all necessary packages from the metamodel(s)
    Collection<EPackage> metaPackages = new ArrayList<EPackage>();


    this.collectMetaModels(metaPackages);

    // make these packages known to the QVT engine
    this.init(metaPackages);

    // load the example model files - TODO: adjust the filename!
    //Resource resource1 = this.getResource("example/input.xmi");
    Resource resource1 = this.getResource(setup.getSource());
    Resource resource2 = this.getResource(setup.getTarget());

    // Collect the models, which should participate in the transformation.
    // You can provide a list of models for each direction.
    // The models must be added in the same order as defined in your transformation!
    Collection<Collection<Resource>> modelResources = new ArrayList<Collection<Resource>>();
    Collection<Resource> firstSetOfModels = new ArrayList<Resource>();
    Collection<Resource> secondSetOfModels = new ArrayList<Resource>();
    modelResources.add(firstSetOfModels);
    modelResources.add(secondSetOfModels);


    File fSource = new File(setup.getSource());
    File fTarget = new File(setup.getTarget());
    resource1 = this.getResource(fSource.getAbsolutePath());
    resource2 = this.getResource(fTarget.getAbsolutePath());


    firstSetOfModels.add(resource1);
    secondSetOfModels.add(resource2);

    // tell the QVT engine a directory to work in - e.g. to store the trace (meta)models
    URI directory = URI.createFileURI(setup.getWorkspace()+"/traces");
    this.preExecution(modelResources, directory);

    // load the QVT relations
    FileReader qvtRuleSet = null;
    try {
      qvtRuleSet = new FileReader(setup.getQvt());
    } catch (FileNotFoundException fileNotFoundException) {
      fileNotFoundException.printStackTrace();
      return;
    }
    // tell the QVT engine, which transformation to execute - there might be more than one in
    // the QVT file!
    String transformation = setup.getTransformation();
    // give the direction of the transformation (according to the transformation definition)
    String direction = setup.getDirection();

    // just do it ;-)
//    try {
      this.transform(qvtRuleSet, transformation, direction);
  //  } catch (Throwable throwable) {
      //throwable.printStackTrace();
    //}

    // Note: the engine does not save the model resources, which were participating in the
    // transformation.
    // You have to take care on this.
    try {
      resource2.save(Collections.EMPTY_MAP);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  protected void collectMetaModels(Collection<EPackage> metaPackages) {

    EPackageImpl umlMetaModelPackage = buildMetaModelFromEcoreFile(setup.getUml(), this.resourceSet);
    this.resourceSet.getPackageRegistry().put(umlMetaModelPackage.getNsURI(), umlMetaModelPackage);
    metaPackages.add(umlMetaModelPackage);

    EPackageImpl umllintMetaModelPackage = buildMetaModelFromEcoreFile(setup.getUmllint(), this.resourceSet);
    this.resourceSet.getPackageRegistry().put(umllintMetaModelPackage.getNsURI(), umllintMetaModelPackage);
    metaPackages.add(umllintMetaModelPackage);
  }

  /**
   * Loads a metamodel into the given resource set
   *
   * @param path the path to the metamodel's ECORE file
   * @param rset the resource set
   * @return the root package of the metamodel
   */
  static protected EPackageImpl buildMetaModelFromEcoreFile(String path, ResourceSet rset) {

    URI simpleMMFileUri;

    try {
      simpleMMFileUri = URI.createFileURI(path);
    } catch (Exception e) {
      simpleMMFileUri = URI.createURI(path);
    }

    Resource simpleMMResource = rset.getResource(simpleMMFileUri, true);
    return (EPackageImpl) simpleMMResource.getContents().get(0);
  }


}
