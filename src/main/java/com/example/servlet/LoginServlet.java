package com.example.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.example.Users;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session !=null && session.getAttribute("user") != null){
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/hello.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Users instance = Users.getInstance();
        if(instance.getUsers().contains(login)  && password != null && !password.isEmpty()){
            HttpSession session = req.getSession();
            session.setAttribute("user", login);
            resp.sendRedirect(req.getContextPath() + "/hello.jsp");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}