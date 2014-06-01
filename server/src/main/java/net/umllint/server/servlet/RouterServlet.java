package net.umllint.server.servlet;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.umllint.server.core.RouteUtils;
import net.umllint.server.view.IView;
import net.umllint.server.view.ViewFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class RouterServlet extends TemplateServlet {

  public RouterServlet() throws Exception {
    super();
  }





  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    RouteUtils.Route route = RouteUtils.parseRoute(request.getServletPath());

    //render the view
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    IView view = ViewFactory.getView(request, route.getEntity(), route.getAction(), route.getId());
    Map<String, Object> input = view.getResponse(response);

    if (view.getRedirect() != null) {
      response.setStatus(response.SC_MOVED_TEMPORARILY);
      response.setHeader("Location", view.getRedirect());
    }

    if (view.getTemplateID() != null) {
      String templateFile = view.getTemplateID() + ".ftl";
      Template template = configuration.getTemplate(templateFile);

      try {
        template.process(input, out);
      } catch (TemplateException e) {
        log.addMessage(e.getMessage());
      }

    }


  }
}