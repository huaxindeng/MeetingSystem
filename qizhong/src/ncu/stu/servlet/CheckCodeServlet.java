package ncu.stu.servlet;

import ncu.stu.util.CheckCodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * 输出验证码
 */
@WebServlet("/checkcode")
public class CheckCodeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("image/jpg");
        //指示请求或响应消息不能缓存
        resp.setHeader("Cache-Control","no-cache");

        //3. 将图片输出到页面展示
        ImageIO.write(CheckCodeUtils.createImage(req.getSession()),"jpg",resp.getOutputStream());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
