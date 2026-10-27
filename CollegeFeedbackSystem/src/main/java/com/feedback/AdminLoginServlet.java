package com.feedback;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Demo admin credentials
        if (username.equals("admin") && password.equals("admin123")) {

            HttpSession session = request.getSession();

            session.setAttribute("admin", username);

            response.sendRedirect("ViewFeedbackServlet");

        } else {

            response.setContentType("text/html");

            response.getWriter().println(
                "<h2>Invalid Username or Password</h2>"
            );

            response.getWriter().println(
                "<a href='admin.html'>Try Again</a>"
            );
        }
    }
}