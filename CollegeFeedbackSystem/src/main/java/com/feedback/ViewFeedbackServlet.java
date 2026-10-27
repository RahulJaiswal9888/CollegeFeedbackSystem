package com.feedback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewFeedbackServlet")
public class ViewFeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        StringBuilder data = new StringBuilder();

        String sql = "SELECT * FROM feedback ORDER BY id DESC";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                data.append("<tr>");

                data.append("<td>")
                    .append(rs.getInt("id"))
                    .append("</td>");

                data.append("<td>")
                    .append(rs.getString("student_name"))
                    .append("</td>");

                data.append("<td>")
                    .append(rs.getString("email"))
                    .append("</td>");

                data.append("<td>")
                    .append(rs.getString("course"))
                    .append("</td>");

                data.append("<td>")
                    .append(rs.getString("semester"))
                    .append("</td>");

                data.append("<td>")
                    .append(rs.getString("faculty"))
                    .append("</td>");

                data.append("<td>")
                    .append(rs.getString("subject"))
                    .append("</td>");

                data.append("<td>")
                    .append(rs.getInt("rating"))
                    .append(" ⭐")
                    .append("</td>");

                data.append("<td>")
                    .append(rs.getString("message"))
                    .append("</td>");

                data.append("<td>")
                .append("<a class='edit-btn' href='EditFeedbackServlet?id=")
                .append(rs.getInt("id"))
                .append("'>Edit</a> ")
                .append("<a class='delete-btn' href='DeleteFeedbackServlet?id=")
                .append(rs.getInt("id"))
                .append("'>Delete</a>")
                .append("</td>");

                data.append("</tr>");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            data.append("<tr><td colspan='10'>")
                .append("Error loading feedback: ")
                .append(e.getMessage())
                .append("</td></tr>");
        }

        response.setContentType("text/html");

        response.getWriter().println(

            "<!DOCTYPE html>" +
            "<html>" +

            "<head>" +

            "<meta charset='UTF-8'>" +

            "<title>Admin Dashboard</title>" +

            "<style>" +

            "* { box-sizing: border-box; font-family: Arial, sans-serif; }" +

            "body {" +
            "margin: 0;" +
            "background: #f4f7fb;" +
            "color: #1f2937;" +
            "}" +

            "header {" +
            "height: 70px;" +
            "background: #172554;" +
            "color: white;" +
            "display: flex;" +
            "align-items: center;" +
            "justify-content: space-between;" +
            "padding: 0 5%;" +
            "}" +

            ".logo {" +
            "font-size: 24px;" +
            "font-weight: bold;" +
            "}" +

            ".logout {" +
            "color: white;" +
            "text-decoration: none;" +
            "background: #dc2626;" +
            "padding: 10px 18px;" +
            "border-radius: 7px;" +
            "}" +

            ".container {" +
            "padding: 40px 5%;" +
            "}" +

            "h1 {" +
            "color: #172554;" +
            "margin-bottom: 8px;" +
            "}" +

            ".subtitle {" +
            "color: #64748b;" +
            "margin-bottom: 30px;" +
            "}" +

            ".table-container {" +
            "background: white;" +
            "border-radius: 15px;" +
            "padding: 20px;" +
            "overflow-x: auto;" +
            "box-shadow: 0 10px 30px rgba(0,0,0,0.08);" +
            "}" +

            "table {" +
            "width: 100%;" +
            "border-collapse: collapse;" +
            "min-width: 1000px;" +
            "}" +

            "th {" +
            "background: #172554;" +
            "color: white;" +
            "padding: 13px;" +
            "text-align: left;" +
            "}" +

            "td {" +
            "padding: 13px;" +
            "border-bottom: 1px solid #e5e7eb;" +
            "}" +

            "tr:hover {" +
            "background: #f8fafc;" +
            "}" +
            
			".edit-btn {" +
			"background: #2563eb;" +
			"color: white;" +
			"text-decoration: none;" +
			"padding: 7px 12px;" +
			"border-radius: 6px;" +
			"margin-right: 5px;" +
			"}" +

            ".delete-btn {" +
            "background: #dc2626;" +
            "color: white;" +
            "text-decoration: none;" +
            "padding: 7px 12px;" +
            "border-radius: 6px;" +
            "}" +

            "</style>" +

            "</head>" +

            "<body>" +

            "<header>" +

            "<div class='logo'>🎓 FeedbackHub Admin</div>" +

            "<a class='logout' href='LogoutServlet'>Logout</a>" +

            "</header>" +

            "<div class='container'>" +

            "<h1>Feedback Dashboard</h1>" +

            "<p class='subtitle'>View and manage student feedback</p>" +

            "<div class='table-container'>" +

            "<table>" +

            "<tr>" +
            "<th>ID</th>" +
            "<th>Student</th>" +
            "<th>Email</th>" +
            "<th>Course</th>" +
            "<th>Semester</th>" +
            "<th>Faculty</th>" +
            "<th>Subject</th>" +
            "<th>Rating</th>" +
            "<th>Feedback</th>" +
            "<th>Action</th>" +
            "</tr>" +

            data.toString() +

            "</table>" +

            "</div>" +

            "</div>" +

            "</body>" +

            "</html>"
        );
    }
}