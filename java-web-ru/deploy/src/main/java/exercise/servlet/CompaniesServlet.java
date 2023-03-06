package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter writer = response.getWriter();
        List<String> companies = Data.getCompanies();
        String search = request.getParameter("search");

        if (search == null) {
            writer.println(companies.stream().collect(Collectors.joining("\n")));
            return;
        }
        String filteredCompanies = companies.stream()
                .filter(comp -> comp.contains(search))
                .collect(Collectors.joining("\n"));
        if (filteredCompanies.length() == 0) {
            writer.println("Companies not found");
            return;
        }

        writer.println(filteredCompanies);
        // END
    }
}
