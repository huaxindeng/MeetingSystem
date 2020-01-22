package ncu.stu.servlet;

import ncu.stu.beans.Attendees;
import ncu.stu.beans.Meeting;
import ncu.stu.beans.MyUser;
import ncu.stu.dao.AttendeesDao;
import ncu.stu.dao.MeetDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 查询某一会议的参会人员名单
 */
@WebServlet("/findAttendeesServlet")
public class FindAttendeesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AttendeesDao dao = new AttendeesDao();
        HttpSession session = req.getSession();
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("id:"+id);
        List<Attendees> attendees = dao.findAttendees(id);
        req.setAttribute("attendees",attendees);
        MyUser user = (MyUser) session.getAttribute("user");
        session.setAttribute("user",user);
        session.setAttribute("id",id);
        req.getRequestDispatcher("/attendeesOf.jsp").forward(req,resp);
    }
}
