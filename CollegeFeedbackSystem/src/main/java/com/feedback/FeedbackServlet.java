package com.feedback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String studentName = request.getParameter("student_name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        String semester = request.getParameter("semester");
        String faculty = request.getParameter("faculty");
        String subject = request.getParameter("subject");
        String rating = request.getParameter("rating");
        String message = request.getParameter("message");

        String sql = "INSERT INTO feedback "
                + "(student_name, email, course, semester, faculty, "
                + "subject, rating, message) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, studentName);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.setString(4, semester);
            ps.setString(5, faculty);
            ps.setString(6, subject);
            ps.setInt(7, Integer.parseInt(rating));
            ps.setString(8, message);

            ps.executeUpdate();

            ps.close();
            con.close();

            response.sendRedirect("success.html");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                "Error while saving feedback: " + e.getMessage()
            );
        }
    }
}