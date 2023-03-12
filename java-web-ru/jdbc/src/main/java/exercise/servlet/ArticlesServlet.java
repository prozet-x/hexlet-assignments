package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();

        String page = request.getParameter("page");
        String query = "SELECT * FROM articles ORDER BY id LIMIT 10 OFFSET ?";
        try {
            PreparedStatement articleStat = connection.prepareStatement(query);
            int currentPage = page == null ? 1 : Integer.parseInt(page);
            articleStat.setInt(1, ((currentPage) - 1) * 10);
            ResultSet articleRes = articleStat.executeQuery();
            while (articleRes.next()) {
                articles.add(Map.of(
                        "id", articleRes.getString("id"),
                        "title", articleRes.getString("title")
                ));
            }

            Statement countStat = connection.createStatement();
            ResultSet countRes = countStat.executeQuery("SELECT count(*) AS cont FROM articles");
            countRes.next();

            request.setAttribute("currentPage", currentPage);
            request.setAttribute("needNext", currentPage * 10 < countRes.getInt("cont"));

            countRes.close();
            countStat.close();
            articleRes.close();
            articleRes.close();
        } catch (SQLException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        request.setAttribute("articles", articles);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String id = getId(request);

        try {
            PreparedStatement articleStat = connection.prepareStatement("SELECT title, body FROM articles WHERE id=?");
            articleStat.setInt(1, Integer.parseInt(id));
            ResultSet articleRes = articleStat.executeQuery();
            if (articleRes.next()) {
                request.setAttribute("article", Map.of(
                        "title", articleRes.getString("title"),
                        "body", articleRes.getString("body")
                ));
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
        } catch (SQLException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
