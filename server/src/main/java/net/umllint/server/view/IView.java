package net.umllint.server.view;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by idlouhy on 5/1/14.
 */
public interface IView {

    public enum Action {

        NONE(""),

        CREATE("create"),
        LIST("list"),
        VIEW("view"),
        EDIT("edit"),
        DELETE("delete");

        private String fragment;

        Action(String fragment) {
            this.fragment = fragment;
        }

        public String getValue() {
            return fragment;
        }
    }

    public abstract String getTemplateID();

    public abstract Map<String, Object> getResponse(HttpServletResponse response);

    public abstract String getRedirect();

}
