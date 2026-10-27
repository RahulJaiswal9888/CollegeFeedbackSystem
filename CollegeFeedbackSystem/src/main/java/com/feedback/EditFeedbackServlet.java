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

@WebServlet("/EditFeedbackServlet")
public class EditFeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        String id = request.getParameter("id");

        String sql = "SELECT * FROM feedback WHERE id = ?";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                response.setContentType("text/html");

                response.getWriter().println(
                    "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<meta charset='UTF-8'>" +
                    "<title>Edit Feedback</title>" +

                    "<style>" +

                    "* {" +
                    "box-sizing: border-box;" +
                    "font-family: Arial, sans-serif;" +
                    "}" +

                    "body {" +
                    "margin: 0;" +
                    "background: #f4f7fb;" +
                    "}" +

                    "header {" +
                    "height: 70px;" +
                    "background: #172554;" +
                    "color: white;" +
                    "display: flex;" +
                    "align-items: center;" +
                    "justify-content: space-between;" +
                    "padding: 0 8%;" +
                    "}" +

                    ".logo {" +
                    "font-size: 24px;" +
                    "font-weight: bold;" +
                    "}" +

                    ".back {" +
                    "color: white;" +
                    "text-decoration: none;" +
                    "}" +

                    ".container {" +
                    "display: flex;" +
                    "justify-content: center;" +
                    "padding: 40px 20px;" +
                    "}" +

                    ".card {" +
                    "width: 650px;" +
                    "background: white;" +
                    "padding: 35px;" +
                    "border-radius: 16px;" +
                    "box-shadow: 0 10px 35px rgba(0,0,0,0.10);" +
                    "}" +

                    "h1 {" +
                    "text-align: center;" +
                    "color: #172554;" +
                    "}" +

                    "label {" +
                    "display: block;" +
                    "margin-top: 15px;" +
                    "margin-bottom: 6px;" +
                    "font-weight: bold;" +
                    "}" +

                    "input, select, textarea {" +
                    "width: 100%;" +
                    "padding: 12px;" +
                    "border: 1px solid #d1d5db;" +
                    "border-radius: 8px;" +
                    "font-size: 14px;" +
                    "}" +

                    "textarea {" +
                    "resize: vertical;" +
                    "}" +

                    "button {" +
                    "width: 100%;" +
                    "margin-top: 25px;" +
                    "padding: 14px;" +
                    "border: none;" +
                    "border-radius: 8px;" +
                    "background: #2563eb;" +
                    "color: white;" +
                    "font-size: 16px;" +
                    "font-weight: bold;" +
                    "cursor: pointer;" +
                    "}" +

                    "</style>" +
                    "</head>" +

                    "<body>" +

                    "<header>" +

                    "<div class='logo'>🎓 FeedbackHub Admin</div>" +

                    "<a class='back' href='ViewFeedbackServlet'>" +
                    "← Dashboard" +
                    "</a>" +

                    "</header>" +

                    "<div class='container'>" +

                    "<div class='card'>" +

                    "<h1>Edit Feedback</h1>" +

                    "<form action='UpdateFeedbackServlet' method='post'>" +

                    "<input type='hidden' name='id' value='" +
                    rs.getInt("id") +
                    "'>" +

                    "<label>Student Name</label>" +

                    "<input type='text' name='student_name' value='" +
                    rs.getString("student_name") +
                    "' required>" +

                    "<label>Email</label>" +

                    "<input type='email' name='email' value='" +
                    rs.getString("email") +
                    "' required>" +

                    "<label>Course</label>" +

                    "<input type='text' name='course' value='" +
                    rs.getString("course") +
                    "' required>" +

                    "<label>Semester</label>" +

                    "<input type='text' name='semester' value='" +
                    rs.getString("semester") +
                    "' required>" +

                    "<label>Faculty</label>" +

                    "<input type='text' name='faculty' value='" +
                    rs.getString("faculty") +
                    "' required>" +

                    "<label>Subject</label>" +

                    "<input type='text' name='subject' value='" +
                    rs.getString("subject") +
                    "' required>" +

                    "<label>Rating</label>" +

                    "<select name='rating' required>" +
                    "<option value='5'>★★★★★ - Excellent</option>" +
                    "<option value='4'>★★★★ - Very Good</option>" +
                    "<option value='3'>★★★ - Good</option>" +
                    "<option value='2'>★★ - Average</option>" +
                    "<option value='1'>★ - Poor</option>" +
                    "</select>" +

                    "<label>Feedback</label>" +

                    "<textarea name='message' rows='5' required>" +
                    rs.getString("message") +
                    "</textarea>" +

                    "<button type='submit'>Update Feedback</button>" +

                    "</form>" +

                    "</div>" +
                    "</div>" +

                    "</body>" +
                    "</html>"
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                "Error: " + e.getMessage()
            );
        }
    }
}