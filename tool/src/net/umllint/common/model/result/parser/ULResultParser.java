package net.umllint.common.model.result.parser;

import net.umllint.common.XMLUtils;
import net.umllint.common.model.result.model.ULResultManager;
import net.umllint.common.model.result.model.ULResult;
import net.umllint.common.model.result.model.ULResultBinding;
import net.umllint.common.model.result.model.ULResultElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by idlouhy on 4/12/14.
 */



/*
<?xml version="1.0" encoding="ASCII"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:ulresults="http://umllint.dlouho.net/schema/0.3/ulresult.ecore">
    <ulresults:Pattern id="987" name="NAEM2">
        <element id="zxcvbbsdfsd4365">
            <element id="fdsafdsfgsd33333">
                <element id="zaaaaaaaaaaaaaaaa"/>
            </element>
        </element>
    </ulresults:Pattern>
</xmi:XMI>
*/


public class ULResultParser {

  String ns = "umllint";

  private List<File> files = new LinkedList<File>();


  public ULResultParser() {
    files.add(new File("resources/xmi/target-direct.xmi"));
    files.add(new File("resources/xmi/target-xmi.xmi"));
  }

  public List<File> getFiles() {
    return files;
  }


  private ULResult parsePattern(Element patternElement) {

        /*
            <umllint:Pattern id="8" name="JustAllInterfaces">
                <binding />
                <binding />
            </umllint:Pattern>
         */

    ULResult pattern = new ULResult();

    pattern.setId(patternElement.getAttribute("id"));
    pattern.setName(patternElement.getAttribute("name"));

    //bindings
    List<Element> bindingElements = XMLUtils.getChildElementsByTagName(patternElement, "binding");
    for (Element bindingElement : bindingElements) {
      ULResultBinding binding = parseBinding(bindingElement);
      pattern.addBinding(binding);
    }

    return pattern;
  }


  private ULResultBinding parseBinding(Element bindingElement) {

        /*
            <binding id="1" name="" type="interface">
                <binding id="456" name="property2" type="property">
                    <element xsi:type="uml:Property" href="resources/xmi/input.xmi#_Oyhtcbk2Edyv6qRmEdcM-A222"/>
                </binding>
                <element xsi:type="uml:Interface" href="resources/xmi/input.xmi#_OyhtcLk2Edyv6qRmEdcM-A"/>
            </binding>
        */

    ULResultBinding binding = new ULResultBinding();
    binding.setId(bindingElement.getAttribute("id"));
    binding.setName(bindingElement.getAttribute("name"));
    binding.setType(bindingElement.getAttribute("type"));

    //binding
    Element bindingChildElement = XMLUtils.getFirstChildElementByTagName(bindingElement, "binding");

    if (bindingChildElement != null) {
      ULResultBinding bindingChild = parseBinding(bindingChildElement);
      binding.setBinding(bindingChild);
    }

    //element

    String elementAttribute = bindingElement.getAttribute("element");

    if (elementAttribute != null && !elementAttribute.equals("")) {
      ULResultElement element = new ULResultElement();
      element.setId(parseHrefSlash(elementAttribute));
      binding.setElement(element);
    } else {
      //sub element
      Element elementElement = XMLUtils.getFirstChildElementByTagName(bindingElement, "element");

      if (elementElement != null) {
        ULResultElement element = parseElement(elementElement);
        binding.setElement(element);
      }
    }


    return binding;
  }


  private ULResultElement parseElement(Element elementElement) {

        /*
            <element xsi:type="uml:Interface" href="resources/xmi/input.xmi#_OyhtcLk2Edyv6qRmEdcM-A"/>
         */

    ULResultElement element = new ULResultElement();

    element.setId(elementElement.getAttribute("id"));
    element.setType(elementElement.getAttribute("xsi:type"));
    element.setHref(elementElement.getAttribute("href"));
    element.setId(parseHref(elementElement.getAttribute("href")));

    return element;
  }


  private String parseHref(String href) {

        /*
            "resources/xmi/input.xmi#_Oyhtcbk2Edyv6qRmEdcM-A111"
         */

    if (href != null) {
      String[] idArray = href.split("#");
      if (idArray.length > 1) {
        return idArray[1];
      } else {
        return href;
      }
    } else {
      return "";
    }

  }

  private String parseHrefSlash(String href) {

        /*
            "resources/xmi/input.xmi#_Oyhtcbk2Edyv6qRmEdcM-A111"
         */

    if (href != null) {
      String[] idArray = href.split("/");
      if (idArray.length > 1) {
        return idArray[1];
      } else {
        return href;
      }
    } else {
      return "";
    }

  }

  public ULResultManager parse(String source) throws IOException, SAXException, ParserConfigurationException {

    //List<ULResult> patterns = new LinkedList<ULResult>();
    ULResultManager manager = new ULResultManager();


    InputSource is = new InputSource(new ByteArrayInputStream(source.getBytes("utf-8")));

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(is);

    document.getDocumentElement().normalize();
    Element documentElement = document.getDocumentElement();

    String patternElementName = String.format("%s:Pattern", ns);

    if (documentElement.getTagName().equals(patternElementName)) {
      //just one pattern
      // -> pattern is document element
      ULResult pattern = parsePattern(documentElement);
      manager.add(pattern);

    } else {
      //multiple patterns

      List<Element> patternElements = XMLUtils.getElementsByTagName(document.getDocumentElement(), patternElementName);

      for (Element patternElement : patternElements) {
        ULResult pattern = parsePattern(patternElement);
        manager.add(pattern);
      }
    }

    return manager;

  }


  public static void main(String[] args) throws Exception {

    /*
    ULResultXMIParser resultXMIParser = new ULResultXMIParser();

    for (File file : resultXMIParser.getFiles()) {

      String source = FileUtils.readFromFile(file);
      System.out.println(String.format("%nLoading file %s...", file.getName()));

      List<ULResult> resultPatterns = resultXMIParser.parse(source);

      CLIResultsRenderer resultPatternRenderer = new CLIResultsRenderer();
      String table = resultPatternRenderer.renderTable(resultPatterns);
      System.out.print(table);
    }
    */
  }


}

