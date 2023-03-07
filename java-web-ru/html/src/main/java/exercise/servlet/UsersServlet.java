package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.tomcat.jni.File;
import org.apache.tomcat.jni.User;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path path = Paths.get("src/main/resources/users.json");
        String data = Files.readAllLines(path).stream().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        List users = mapper.readValue(data, List.class);
        return users;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List users = getUsers();
        List<Map<String, String>> allUsers = users.stream().map(user -> (Map<String, String>) user).toList();

        PrintWriter writer = response.getWriter();
        writer.println("<table>");
        for (Map<String, String> user : allUsers) {
            String id = user.get("id");
            String fullName = user.get("firstName") + " " + user.get("lastName");
            writer.println("<tr><td>");
            writer.println(id);
            writer.println("</td><td>");
            writer.println("<a href=\"/users/" + id + "\">" + fullName +"</a>");
            writer.println("</td></tr>");
        }
        writer.println("</table>");
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        PrintWriter writer = response.getWriter();

        List<Map<String, String>> allUsers = getUsers().stream().map(user -> (Map<String, String>) user).toList();
        List<Map<String, String>>relatedUsers = allUsers.stream().filter(user -> user.get("id").equals(id)).toList();

        if (relatedUsers.isEmpty()) {
            response.setStatus(404);
            writer.println("Not Found");
            return;
        }

        Map<String, String> searchedUser = relatedUsers.get(0);
        writer.println(
                searchedUser.get("id") + " "
                + searchedUser.get("firstName") + " "
                + searchedUser.get("lastName") + " "
                + searchedUser.get("email")
        );
        // END
    }
}
