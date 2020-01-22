package ncu.stu.servlet;

import ncu.stu.beans.Attendees;
import ncu.stu.beans.MyUser;
import ncu.stu.dao.AttendeesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 会议参与者参与会议，填写信息
 */
@WebServlet("/addAttendeeServlet")
public class AddAttendeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Attendees attendee ;
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        String workplace = req.getParameter("workplace");
        String identify_id = req.getParameter("identify_id");
        String phone = req.getParameter("phone");
        String meetTime = req.getParameter("meetTime");
        String sex = req.getParameter("sex");
        String room = req.getParameter("room");

        int meetingID = (int) session.getAttribute("meetingID");
        attendee = new Attendees(name,workplace,identify_id,phone,meetTime,sex,room,meetingID);

        AttendeesDao dao = new AttendeesDao();

        String checkCode = req.getParameter("checkCode");
        System.out.println("输入验证码："+checkCode);

        String checkCode_session = (String) session.getAttribute("checkCode_session");
        System.out.println("生成验证码："+checkCode_session);
        //判断验证码输入是否正确
        if (null != checkCode_session && checkCode.equalsIgnoreCase(checkCode_session)) {

            MyUser user = (MyUser) session.getAttribute("user");
            session.setAttribute("user",user);
            boolean flag= dao.addAttendee(attendee,user);
            //验证码输入正确
            if (flag) {

                resp.sendRedirect(req.getContextPath() + "/otherMeetingsServlet");
            } else {
                //用户名已存在
                req.setAttribute("repetition_error", "添加失败");

                req.getRequestDispatcher("/addAttendee.jsp").forward(req, resp);
            }
        }
        else {
            System.out.println("777");
            //验证码不正确
            req.setAttribute("cc_error","验证码输入错误");
            req.getRequestDispatcher("/addAttendee.jsp").forward(req,resp);
        }

    }
}
