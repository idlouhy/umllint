package net.umllint.test.server;

import net.umllint.server.core.Persistence;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PersistenceTest {


  @BeforeClass
  public void setUp() {

  }

  @Test
  public void propertiesLoaded() {

    String user = null;
    Persistence persistence = null;

    try {
      persistence = Persistence.getInstance();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    MatcherAssert.assertThat(persistence, notNullValue());

    user = persistence.getUser();

    MatcherAssert.assertThat(user, equalTo("umlcheck"));

  }


}
