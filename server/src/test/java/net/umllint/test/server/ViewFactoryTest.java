package net.umllint.test.server;

import net.umllint.server.view.IView;
import net.umllint.server.view.IndexView;
import net.umllint.server.view.ViewFactory;
import net.umllint.server.view.pattern.*;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

public class ViewFactoryTest {


  @BeforeClass
  public void setUp() {

  }

  @Test
  public void actionCreate() {
    IView view = ViewFactory.getView(null, "pattern", IView.Action.CREATE, String.valueOf(0));
    MatcherAssert.assertThat(view, instanceOf(PatternCreateView.class));
  }

  @Test
  public void actionDelete() {
    IView view = ViewFactory.getView(null, "pattern", IView.Action.DELETE, String.valueOf(0));
    MatcherAssert.assertThat(view, instanceOf(PatternDeleteView.class));
  }

  @Test
  public void actionEdit() {
    IView view = ViewFactory.getView(null, "pattern", IView.Action.EDIT, String.valueOf(0));
    MatcherAssert.assertThat(view, instanceOf(PatternEditView.class));
  }

  @Test
  public void actionList() {
    IView view = ViewFactory.getView(null, "pattern", IView.Action.LIST, String.valueOf(0));
    MatcherAssert.assertThat(view, instanceOf(PatternListView.class));
  }

  @Test
  public void actionView() {
    IView view = ViewFactory.getView(null, "pattern", IView.Action.VIEW, String.valueOf(0));
    MatcherAssert.assertThat(view, instanceOf(PatternViewView.class));
  }

  @Test
  public void actionNone() {
    IView view = ViewFactory.getView(null, "pattern", IView.Action.NONE, String.valueOf(0));
    MatcherAssert.assertThat(view, instanceOf(IndexView.class));
  }


}
