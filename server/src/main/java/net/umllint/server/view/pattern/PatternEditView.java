package net.umllint.server.view.pattern;

import net.umllint.server.core.Persistence;
import net.umllint.server.model.ULPattern;
import net.umllint.server.model.ULPatternCategory;
import net.umllint.server.model.ULPatternReference;
import net.umllint.server.model.ULPatternSeverity;
import net.umllint.server.view.BaseView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by idlouhy on 5/1/14.
 */
public class PatternEditView extends BaseView {

  private String id;
  private String redirect = null;


  public PatternEditView(HttpServletRequest request) {
    super(request);
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTemplateID() {
    return "pattern/pattern-edit";
  }

  public String getRedirect() {
    return redirect;
  }

  @Override
  public Map<String, Object> getResponse(HttpServletResponse response) {
    Map<String, Object> input = new HashMap<String, Object>();

    input.put("title", "Pattern - Edit");

    if (request.getParameter("submit") != null) {
      int id = Integer.parseInt(request.getParameter("pattern-id"));
      //out.print("UPDATING "+id);
      ULPattern pattern = new ULPattern();
      pattern.setId(String.valueOf(id));
      pattern.setName(request.getParameter("pattern-name"));
      pattern.setTitle(request.getParameter("pattern-title"));
      pattern.setEnabled(request.getParameter("pattern-enabled"));
      pattern.setDescription(request.getParameter("pattern-description"));
      pattern.setCode(request.getParameter("pattern-code"));

      ULPatternCategory category = new ULPatternCategory();
      category.setId(Integer.parseInt(request.getParameter("pattern-category")));
      pattern.setCategory(category);

      ULPatternReference reference = new ULPatternReference();
      reference.setId(Integer.parseInt(request.getParameter("pattern-reference")));
      pattern.setReference(reference);

      ULPatternSeverity severity = new ULPatternSeverity();
      severity.setId(Integer.parseInt(request.getParameter("pattern-severity")));
      pattern.setSeverity(severity);

      try {
        Persistence.getInstance().updatePattern(pattern);
        redirect = "/app/pattern/list";
      } catch (SQLException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

    int id = -1;
    try {
      id = Integer.parseInt(this.id);
    } catch (NumberFormatException e) {
      //number ofrmat exception
    }

    input.put("title", "UMLCheck :: Patterns");

    if (id > 0) {
      ULPattern pattern = null;
      try {
        pattern = Persistence.getInstance().getPattern(id);
        input.put("pattern", pattern);
        input.put("severities", Persistence.getInstance().getAllSeverity());
        input.put("categories", Persistence.getInstance().getAllCategory());
        input.put("references", Persistence.getInstance().getAllReference());

      } catch (SQLException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

    }


    return input;
  }


}
