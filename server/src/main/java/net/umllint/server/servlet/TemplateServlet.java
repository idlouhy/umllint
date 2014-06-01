package net.umllint.server.servlet;

import freemarker.template.*;
import net.umllint.server.core.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class TemplateServlet extends HttpServlet {

    public Log log = Log.getInstance();
    protected Configuration configuration = new Configuration();
    public Map<String, Object> input = new HashMap<String, Object>();

    //protected Persistence persistence;

    public TemplateServlet() throws Exception {
        configureFreemarker();
    }

    private void configureFreemarker() throws IOException {
        configuration.setClassForTemplateLoading(TemplateServlet.class, "../template");
        configuration.setIncompatibleImprovements(new Version(2, 3, 20));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.US);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse res)
            throws IOException, ServletException {
        doGet(request, res);
    }
}

