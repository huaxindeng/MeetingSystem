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
 * 打印所有表单
 */
@WebServlet("/findAllServlet")
public class FindAllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MeetDao dao = new MeetDao();
        HttpSession session = req.getSession();
        List<Meeting> meetings = dao.findAll();
        req.setAttribute("meetings",meetings);
        MyUser user = (MyUser) session.getAttribute("user");
        session.setAttribute("user",user);
        if(user.getRole()==1){
            req.getRequestDispatcher("/attendees.jsp").forward(req,resp);
        }else if(user.getRole()==2){
            req.getRequestDispatcher("/init.jsp").forward(req,resp);
        }else if(user.getRole()==3){
            req.getRequestDispatcher("/admin.jsp").forward(req,resp);
        }


    }
}
