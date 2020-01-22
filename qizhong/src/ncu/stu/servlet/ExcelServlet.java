package ncu.stu.servlet;

import ncu.stu.beans.Attendees;
import ncu.stu.beans.MyUser;
import ncu.stu.dao.AttendeesDao;
import ncu.stu.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Author:wzh
 * @Description:
 * @Date:Createed in 2019/11/1 8:59
 **/
@WebServlet("/ExcelServlet")
public class ExcelServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        AttendeesDao dao = new AttendeesDao();
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("id");
        List<Attendees> attendees = dao.findAttendees(id);
        String[] fields = { "姓名", "工作地点","身份证号", "联系方式", "参会时间", "性别","是否需要房间"};
        ExcelUtils utils = new ExcelUtils();
        HSSFWorkbook wb = utils.generateExcel();
        wb = utils.generateSheet(wb, "会议"+id+"名单", fields, attendees);
        utils.export(wb, resp);
        req.setAttribute("attendees",attendees);
        MyUser user = (MyUser) session.getAttribute("user");
        session.setAttribute("user",user);
        req.getRequestDispatcher("/attendeesOf.jsp").forward(req,resp);
    }
}