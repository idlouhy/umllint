package net.umllint.test.server;

import net.umllint.server.core.RouteUtils;
import net.umllint.server.view.IView;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class RouteUtilsTest {

  @BeforeClass
  public void setUp() {

  }

  @Test
  public void actionCreatePattern() {
    String path = "/pattern/create";
    RouteUtils.Route route = RouteUtils.parseRoute(path);

    MatcherAssert.assertThat(route.getAction(), equalTo(IView.Action.CREATE));
    MatcherAssert.assertThat(route.getId(), equalTo(null));
    MatcherAssert.assertThat(route.getEntity(), equalTo("pattern"));
  }

  @Test
  public void actionViewPattern() {
    String path = "/pattern/view/10";
    RouteUtils.Route route = RouteUtils.parseRoute(path);

    MatcherAssert.assertThat(route.getAction(), equalTo(IView.Action.VIEW));
    MatcherAssert.assertThat(route.getId(), equalTo("10"));
    MatcherAssert.assertThat(route.getEntity(), equalTo("pattern"));
  }

  @Test
  public void actionAbout() {
    String path = "/about";
    RouteUtils.Route route = RouteUtils.parseRoute(path);

    MatcherAssert.assertThat(route.getAction(), equalTo(null));
    MatcherAssert.assertThat(route.getId(), equalTo(null));
    MatcherAssert.assertThat(route.getEntity(), equalTo("about"));
  }

  @Test
  public void actionInvalid() {
    String path = "/invalid/none/x/y";
    RouteUtils.Route route = RouteUtils.parseRoute(path);

    MatcherAssert.assertThat(route.getAction(), equalTo(null));
    MatcherAssert.assertThat(route.getId(), equalTo(null));
    MatcherAssert.assertThat(route.getEntity(), equalTo(null));
  }


}
