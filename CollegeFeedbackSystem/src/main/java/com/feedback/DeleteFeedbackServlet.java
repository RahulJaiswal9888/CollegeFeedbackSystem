package com.feedback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteFeedbackServlet")
public class DeleteFeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        String id = request.getParameter("id");

        String sql = "DELETE FROM feedback WHERE id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

            ps.executeUpdate();

            ps.close();
            con.close();

            response.sendRedirect("ViewFeedbackServlet");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println(
                "Error while deleting feedback: " + e.getMessage()
            );
        }
    }
}