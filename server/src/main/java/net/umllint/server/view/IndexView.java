package net.umllint.server.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by idlouhy on 5/1/14.
 */
public class IndexView extends BaseView {

    public IndexView(HttpServletRequest request) {
        super(request);
    }

    public String getTemplateID() {
        return "index";
    }

    @Override
    public Map<String, Object> getResponse(HttpServletResponse response) {
        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", "Index");

        return input;
    }


}
