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
public class PatternCreateView extends BaseView {

    private String id;


    public PatternCreateView(HttpServletRequest request) {
        super(request);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateID() {
        return "pattern/pattern-create";
    }

    @Override
    public Map<String, Object> getResponse(HttpServletResponse response) {
        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", "Create new pattern");

        if (request.getParameter("submit") != null) {

            //out.print("UPDATING "+id);
            ULPattern pattern = new ULPattern();
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
                Persistence.getInstance().insertPattern(pattern);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {

            input.put("severities", Persistence.getInstance().getAllSeverity());
            input.put("categories", Persistence.getInstance().getAllCategory());
            input.put("references", Persistence.getInstance().getAllReference());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return input;
    }
}
