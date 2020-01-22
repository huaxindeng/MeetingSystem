package ncu.stu.servlet;

import ncu.stu.beans.Meeting;
import ncu.stu.beans.MyUser;
import ncu.stu.dao.MeetDao;
import ncu.stu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 会议发起人新建会议
 */
@WebServlet("/addMeetingServlet")
public class AddMeeting extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Meeting meet ;
        String [] infos = req.getParameterValues("info");
        int[] nums={0,0,0,0,0,0,0};

        for(String info:infos){
            int num = Integer.parseInt(info);
            nums[num-1]=1;
        }
        String info = "";
        for(int i =0;i<7;i++){
            info+=nums[i];
        }
        System.out.println(info);
        String time = req.getParameter("time");
        int hour = Integer.parseInt(req.getParameter("hour"));
        String place = req.getParameter("place");
        String hotel = req.getParameter("hotel");
        meet = new Meeting(time,hour,place,hotel,info);

        MeetDao dao = new MeetDao();

        String checkCode = req.getParameter("checkCode");
        System.out.println("输入验证码："+checkCode);
        HttpSession session = req.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        System.out.println("生成验证码："+checkCode_session);

        //判断验证码输入是否正确
        if (null != checkCode_session && checkCode.equalsIgnoreCase(checkCode_session)) {
            boolean flag= dao.addMeeting(meet);
            MyUser user = (MyUser) session.getAttribute("user");
            session.setAttribute("user",user);
            //验证码输入正确
            if (flag) {

                resp.sendRedirect(req.getContextPath() + "/findAllServlet");
            } else {
                //用户名已存在
                req.setAttribute("repetition_error", "添加失败");

                req.getRequestDispatcher("/addMeeting.jsp").forward(req, resp);
            }
        }
        else {
            System.out.println("777");
            //验证码不正确
            req.setAttribute("cc_error","验证码输入错误");
            req.getRequestDispatcher("/addMeeting.jsp").forward(req,resp);
        }
    }
}
