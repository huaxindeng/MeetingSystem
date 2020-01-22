package ncu.stu.servlet;

import ncu.stu.beans.MyUser;
import ncu.stu.dao.MeetDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/findOneMeetServlet")
public class FindOneMeetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        MeetDao dao = new MeetDao();
        String infos = dao.findOneMeet(id);
        System.out.println(id);
        HttpSession session = req.getSession();
        session.setAttribute("infos",infos);
        System.out.println("findOneMeetServlet:"+infos);
        MyUser user = (MyUser) session.getAttribute("user");
        session.setAttribute("user",user);
        session.setAttribute("meetingID",id);
        req.getRequestDispatcher("addAttendee.jsp").forward(req,resp);
    }
}
