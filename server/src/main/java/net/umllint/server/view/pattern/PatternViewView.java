package net.umllint.server.view.pattern;

import net.umllint.server.core.Persistence;
import net.umllint.server.model.ULPattern;
import net.umllint.server.view.BaseView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by idlouhy on 5/1/14.
 */
public class PatternViewView extends BaseView {

  private String id;
  private String template = "pattern/pattern-view";

  public PatternViewView(HttpServletRequest request) {
    super(request);
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTemplateID() {
    return template;
  }

  @Override
  public Map<String, Object> getResponse(HttpServletResponse response) {
    Map<String, Object> input = new HashMap<String, Object>();

    input.put("title", "View Pattern");

    Integer id = -1;
    try {
      id = Integer.parseInt(this.id);
    } catch (NumberFormatException e) {
      //number ofrmat exception
    }

    if (id > 0) {
      ULPattern pattern = null;
      try {
        pattern = Persistence.getInstance().getPattern(id);
        input.put("title", pattern.getTitle());
        input.put("pattern", pattern);
      } catch (SQLException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

    }


    String source = request.getParameter("source");
    if (source != null && source.equals("plugin")) {
      template = "pattern/pattern-view-plugin";
    }

    return input;
  }


}
