package net.umllint.server.view;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by idlouhy on 5/1/14.
 */
public abstract class BaseView implements IView {

  protected HttpServletRequest request;

  public BaseView(HttpServletRequest request) {
    this.request = request;
  }

  public String getRedirect() {
    return null;
  }

}
