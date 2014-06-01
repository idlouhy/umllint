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
public class PatternDeleteView extends BaseView {

  private String id;


  public PatternDeleteView(HttpServletRequest request) {
    super(request);
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTemplateID() {
    return "pattern/pattern-list";
  }

  public String getRedirect() {
    return "/app/pattern/list";
  }


  @Override
  public Map<String, Object> getResponse(HttpServletResponse response) {
    Map<String, Object> input = new HashMap<String, Object>();

    try {
      ULPattern pattern = new ULPattern();
      pattern.setId(id);
      Persistence.getInstance().deletePattern(pattern);

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }


    return input;
  }
}
