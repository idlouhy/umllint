package net.umllint.server.view.pattern;

import net.umllint.server.core.Persistence;
import net.umllint.server.model.ULPattern;
import net.umllint.server.view.BaseView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by idlouhy on 5/1/14.
 */
public class PatternListView extends BaseView {

    public PatternListView(HttpServletRequest request) {
        super(request);
    }

    public String getTemplateID() {
        return "pattern/pattern-list";
    }

    @Override
    public Map<String, Object> getResponse(HttpServletResponse response) {
        Map<String, Object> input = new HashMap<String, Object>();

        //title
        input.put("title", "List of Patterns");

        //pattern list

        List<ULPattern> patterns = null;
        try {
            patterns = Persistence.getInstance().getAllPatterns();
            input.put("patterns", patterns);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return input;
    }


}
