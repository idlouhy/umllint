package net.umllint.server.view.api;

import net.umllint.server.core.Persistence;
import net.umllint.server.core.XMLUtils;
import net.umllint.server.view.BaseView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by idlouhy on 5/1/14.
 */
public class APIView extends BaseView {

  public APIView(HttpServletRequest request) {
    super(request);
  }

  public String getTemplateID() {
    return null;
  }

  @Override
  public Map<String, Object> getResponse(HttpServletResponse response) {
    Map<String, Object> input = new HashMap<String, Object>();

    response.setContentType("text/xml");
    response.setHeader("Content-disposition","attachment; filename=patterns.xml");

    PrintWriter out = null;
    try {
      out = response.getWriter();
      XMLUtils xml = new XMLUtils();
      out.print(xml.getPatternXML(Persistence.getInstance().getAllPatterns()));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }


    return input;
  }


}
