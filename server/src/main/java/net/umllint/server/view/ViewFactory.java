package net.umllint.server.view;

import net.umllint.server.view.api.APIView;
import net.umllint.server.view.pattern.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by idlouhy on 5/1/14.
 */
public class ViewFactory {

    public static IView getView(HttpServletRequest request, String entity, IView.Action action, String id) {

        if (entity != null) {

            if (entity.equals("pattern")) {
                switch (action) {
                    case LIST: return new PatternListView(request);
                    case VIEW:
                        PatternViewView patternViewView = new PatternViewView(request);
                        patternViewView.setId(id);
                        return patternViewView;

                    case EDIT:
                        PatternEditView patternEdit = new PatternEditView(request);
                        patternEdit.setId(id);
                        return patternEdit;

                    case CREATE:
                        PatternCreateView createView = new PatternCreateView(request);
                        return createView;

                    case DELETE:
                        PatternDeleteView deleteView = new PatternDeleteView(request);
                        deleteView.setId(id);
                        return deleteView;
                }
            }

            if (entity.equals("about")) {
                return new AboutView(request);
            }

            if (entity.equals("api")) {
                return new APIView(request);
            }

        }

        return new IndexView(request);
    }

}
