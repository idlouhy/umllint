package net.umllint.test.server;

import net.umllint.server.core.XMLUtils;
import net.umllint.server.model.ULPattern;
import net.umllint.server.model.ULPatternCategory;
import net.umllint.server.model.ULPatternReference;
import net.umllint.server.model.ULPatternSeverity;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class XMLUtilsTest {


  private String xml;
  private ULPattern pattern;

  @BeforeClass
  public void setUp() {

    xml =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
            "<patterns>" +
            "<pattern>" +
            "<id>1</id>" +
            "<name>pattern1</name>" +
            "<title>Test Pattern 1</title>" +
            "<category id=\"2\" name=\"incorrectness\">Incorrectness</category>" +
            "<severity id=\"1\" name=\"error\">Error</severity>" +
            "<reference id=\"1\" name=\"mmap\">Metamodeling Anti-Patterns</reference>" +
            "<description>description of the problem</description>" +
            "<code>QVTR code</code>" +
            "</pattern>" +
            "</patterns>";

    pattern = new ULPattern();
    pattern.setId("1");
    pattern.setName("pattern1");
    pattern.setTitle("Test Pattern 1");
    pattern.setDescription("description of the problem");
    pattern.setCode("QVTR code");

    ULPatternCategory category = new ULPatternCategory();
    category.setId(Integer.parseInt("2"));
    category.setName("incorrectness");
    category.setTitle("Incorrectness");
    pattern.setCategory(category);

    ULPatternSeverity severity = new ULPatternSeverity();
    severity.setId(Integer.parseInt("1"));
    severity.setName("error");
    severity.setTitle("Error");
    pattern.setSeverity(severity);

    ULPatternReference reference = new ULPatternReference();
    reference.setId(Integer.parseInt("1"));
    reference.setName("mmap");
    reference.setTitle("Metamodeling Anti-Patterns");
    pattern.setReference(reference);
  }

  @Test
  public void parseXML() throws IOException, SAXException, ParserConfigurationException {

    List<ULPattern> patterns = XMLUtils.getPatterns(xml);
    MatcherAssert.assertThat(patterns.size(), equalTo(1));

    ULPattern pattern = patterns.get(0);
    MatcherAssert.assertThat(pattern, notNullValue());
    MatcherAssert.assertThat(pattern.getId(), equalTo("1"));
    MatcherAssert.assertThat(pattern.getName(), equalTo("pattern1"));
    MatcherAssert.assertThat(pattern.getTitle(), equalTo("Test Pattern 1"));
    MatcherAssert.assertThat(pattern.getDescription(), equalTo("description of the problem"));
    MatcherAssert.assertThat(pattern.getCode(), equalTo("QVTR code"));

    ULPatternCategory category = pattern.getCategory();
    MatcherAssert.assertThat(category, notNullValue());
    MatcherAssert.assertThat(category.getId(), equalTo(2));
    MatcherAssert.assertThat(category.getName(), equalTo("incorrectness"));
    MatcherAssert.assertThat(category.getTitle(), equalTo("Incorrectness"));

    ULPatternSeverity severity = pattern.getSeverity();
    MatcherAssert.assertThat(severity, notNullValue());
    MatcherAssert.assertThat(severity.getId(), equalTo(1));
    MatcherAssert.assertThat(severity.getName(), equalTo("error"));
    MatcherAssert.assertThat(severity.getTitle(), equalTo("Error"));

    ULPatternReference reference = pattern.getReference();
    MatcherAssert.assertThat(reference, notNullValue());
    MatcherAssert.assertThat(reference.getId(), equalTo(1));
    MatcherAssert.assertThat(reference.getName(), equalTo("mmap"));
    MatcherAssert.assertThat(reference.getTitle(), equalTo("Metamodeling Anti-Patterns"));
  }

  @Test
  public void renderPattern() throws IOException, SAXException, ParserConfigurationException {

    List<ULPattern> patterns = new ArrayList<ULPattern>();
    patterns.add(pattern);

    String result = XMLUtils.getPatternXML(patterns);
    MatcherAssert.assertThat(result, equalTo(xml));
  }




}
