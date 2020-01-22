package ncu.stu.servlet;

import ncu.stu.beans.MyUser;
import ncu.stu.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录验证
 */
public class loginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");

        System.out.println("输入验证码："+checkCode);
        UserDao userDao = new UserDao();
        MyUser user = new MyUser();
        user = userDao.login(name,password);
        //获取生成的验证码
        HttpSession session = req.getSession();
        String checkCode_session =(String) session.getAttribute("checkCode_session");
        System.out.println("生成验证码："+checkCode_session);
        //删除验证码
        session.removeAttribute("checkCode_session");
        if(name==""&&password==""&&checkCode==""){
            System.out.println("please");
            req.setAttribute("please","请输入以下内容");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
        else{
            //判断输入是否为空
            if(name==""||password==""||checkCode==""){
                System.out.println("用户名:"+name);
                System.out.println("密码:"+password);
                System.out.println("验证码:"+checkCode);
                req.setAttribute("null_error","输入不得为空");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
            else{
                //先判断验证码是否正确
                if(checkCode_session!=null&&checkCode_session.equalsIgnoreCase(checkCode)){
                    //忽略大小写比较字符串
                    if(user.getRole()==1){

                        session.setAttribute("user",user);
                        resp.sendRedirect(req.getContextPath()+"/otherMeetingsServlet");
                    }
                    else if(user.getRole()==2){
                        session.setAttribute("user",user);
                        resp.sendRedirect(req.getContextPath()+"/findAllServlet");
                    }
                    else if(user.getRole()==3){
                        session.setAttribute("user",user);
                        resp.sendRedirect(req.getContextPath()+"/findInitsServlet");
                    }
                    else{
                        req.setAttribute("login_error","用户名或密码错误");
                        req.getRequestDispatcher("/login.jsp").forward(req,resp);
                    }
                }
                else {
                    //验证码不一致
                    //存储提示信息到request
                    req.setAttribute("cc_error","验证码错误");
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);

                }
            }
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
