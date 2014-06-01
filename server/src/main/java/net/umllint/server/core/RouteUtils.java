package net.umllint.server.core;

import net.umllint.server.view.IView;

/**
 * Created by idlouhy on 2014-05-14.
 */
public class RouteUtils {

  public static IView.Action parseAction(String fragment) {

    for (IView.Action action : IView.Action.values()) {
      if (action.getValue().equals(fragment)) {
        return action;
      }
    }

    return IView.Action.NONE;
  }

  public static Route parseRoute(String path) {
    //parse the route

    String[] routeParts = path.split("/");

    Route route = new Route();

    if (routeParts.length == 2) {
      route.setEntity(routeParts[1]);
    }

    if (routeParts.length == 3) {
      route.setEntity(routeParts[1]);
      route.setAction(parseAction(routeParts[2]));
    }

    if (routeParts.length == 4) {
      route.setEntity(routeParts[1]);
      route.setAction(parseAction(routeParts[2]));
      route.setId(routeParts[3]);
    }

    return route;
  }


  public static class Route {

    String entity;
    String id;
    IView.Action action;

    public String getEntity() {
      return entity;
    }

    public void setEntity(String entity) {
      this.entity = entity;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public IView.Action getAction() {
      return action;
    }

    public void setAction(IView.Action action) {
      this.action = action;
    }
  }


}
