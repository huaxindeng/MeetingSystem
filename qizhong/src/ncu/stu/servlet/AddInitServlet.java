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

@WebServlet("/addInitServlet")
public class AddInitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String checkCode = req.getParameter("checkCode");
        String affirm = req.getParameter("affirm");
        System.out.println("输入验证码："+checkCode);
        HttpSession session = req.getSession();
        MyUser admin = (MyUser) session.getAttribute("session");
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        System.out.println("生成验证码："+checkCode_session);
        //创建用户信息
        MyUser user = new MyUser();
        user.setUserNmae(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setRole(2);
        //创建新用户

        if(checkCode==""&&user.getUserNmae()==""&&user.getPassword()==""&&affirm==""){
            req.setAttribute("please","请输入以下信息");
            req.getRequestDispatcher("/addInit.jsp").forward(req,resp);
        }
        else{
            if(checkCode==""||user.getUserNmae()==""||user.getPassword()==""||affirm=="") {
                req.setAttribute("null_error", "输入不得为空");
                req.getRequestDispatcher("/addInit.jsp").forward(req, resp);
            }
            else{
                //判断两次输入密码是否一致
                if(false==user.getPassword().equals(affirm)){
                    req.setAttribute("affirm_error","两次密码输入不一致");
                    req.getRequestDispatcher("/addInit.jsp").forward(req,resp);
                }
                else {
                    //判断验证码输入是否正确
                    if (null != checkCode_session && checkCode.equalsIgnoreCase(checkCode_session)) {
                        boolean flag = UserDao.judgeNoRep(user);
                        //验证码输入正确
                        if (flag) {

                            session.setAttribute("admin",admin);
                            resp.sendRedirect(req.getContextPath() + "/findInitsServlet");
                        } else {
                            //用户名已存在
                            req.setAttribute("repetition_error", "用户名已存在");
                            req.getRequestDispatcher("/addInit.jsp").forward(req, resp);
                        }
                    }
                    else {
                        //验证码不正确
                        req.setAttribute("cc_error","验证码输入错误");
                        req.getRequestDispatcher("/addInit.jsp").forward(req,resp);
                    }
                }
            }

        }

    }
}
