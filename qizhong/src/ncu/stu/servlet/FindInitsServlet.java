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
import java.util.List;

@WebServlet("/findInitsServlet")
public class FindInitsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao dao = new UserDao();
        List<MyUser> inits = dao.findInit();
        HttpSession session = req.getSession();
        req.setAttribute("inits",inits);
        MyUser admin = (MyUser) session.getAttribute("user");
        session.setAttribute("admin",admin);
        req.getRequestDispatcher("/admin.jsp").forward(req,resp);
    }
}
