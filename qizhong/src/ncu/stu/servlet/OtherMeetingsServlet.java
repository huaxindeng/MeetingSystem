package ncu.stu.servlet;

import ncu.stu.beans.Meeting;
import ncu.stu.beans.MyUser;
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
 * 查询其他会议
 */
@WebServlet("/otherMeetingsServlet")
public class OtherMeetingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        MeetDao dao = new MeetDao();
        HttpSession session = req.getSession();

        MyUser user = (MyUser) session.getAttribute("user");
        session.setAttribute("user",user);
        List<Meeting> meetings = dao.otherMeetings(user);
        req.setAttribute("meetings",meetings);
        req.getRequestDispatcher("/attendees.jsp").forward(req,resp);
    }
}
