# Online Student Feedback Management System

## Project Overview

The Online Student Feedback Management System is a web-based mini project developed for the Advanced Internet Programming subject.

The main purpose of this project is to provide an easy way for students to submit feedback about their course, subject and faculty. The submitted feedback is stored in a MySQL database and can be viewed and managed by the administrator.

## Technologies Used

- HTML5
- CSS3
- Java
- Java Servlets
- JDBC
- MySQL
- Apache Tomcat
- Eclipse IDE

## Main Features

- Student feedback submission
- Student details and course information
- Faculty and subject feedback
- Rating system
- Admin login
- View submitted feedback
- Edit feedback
- Delete feedback
- Logout functionality
- MySQL database connectivity

## CRUD Operations

The project demonstrates all basic CRUD operations:

| Operation | Function |
|-----------|----------|
| Create | Submit new feedback |
| Read | View submitted feedback |
| Update | Edit existing feedback |
| Delete | Delete feedback |

## Advanced Internet Programming Concepts

This project demonstrates the following concepts:

- Types of Servlets
- HttpServletRequest
- HttpServletResponse
- GET and POST methods
- Servlet and database connectivity
- JDBC
- PreparedStatement
- ResultSet
- CRUD operations
- HttpSession
- sendRedirect()
- HTML and CSS based frontend

## Project Structure

```text
Online-Student-Feedback-System
│
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── feedback
│       │           ├── DBConnection.java
│       │           ├── FeedbackServlet.java
│       │           ├── ViewFeedbackServlet.java
│       │           ├── EditFeedbackServlet.java
│       │           ├── DeleteFeedbackServlet.java
│       │           └── LogoutServlet.java
│       │
│       └── webapp
│           ├── index.html
│           ├── feedback.html
│           ├── admin.html
│           ├── success.html
│           └── style.css
│
└── pom.xml
