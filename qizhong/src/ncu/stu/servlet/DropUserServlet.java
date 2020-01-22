package ncu.stu.servlet;

import ncu.stu.beans.MyUser;
import ncu.stu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/dropUserServlet")
public class DropUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();
        UserDao dao = new UserDao();
        boolean flag = dao.dropUser(id);
        MyUser admin = (MyUser) session.getAttribute("admin");
        session.setAttribute("admin",admin);
        req.getRequestDispatcher("findInitsServlet").forward(req,resp);
    }
}
