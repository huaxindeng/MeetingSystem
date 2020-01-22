package ncu.stu.util;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CheckCodeUtils {
    private CheckCodeUtils() {};
    private static final Random ran = new Random();
    private static final int width = 100;
    private static final int height = 50;
    private static final String str= "qazxswedcvfrtgbnhyujmkiolpAQZSWXDCEFVRGTBHYNJUMKILOP0123456789";
    public static final BufferedImage createImage(HttpSession session) {
        //1.绘制图像
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //2. 美化图像
        //创建画笔
        Graphics g = image.getGraphics();
        //填充矩形背景色
        g.setColor(Color.pink);
        g.fillRect(0,0,width,height);
        //绘制边框
        g.setColor(Color.BLUE);
        g.drawRect(0,0,width-1,height-1);
        //内容
        //生成随机角标
        //设置字体
        g.setFont(new Font("宋体",Font.BOLD,20));
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++){
            int index = ran.nextInt(str.length());
            //获取字符
            char ch = str.charAt(index);
            sb.append(ch);
            g.drawString(ch+"",width/5*i+20,height/2);
            //绘制干扰线
            int x1 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int x2 = ran.nextInt(width);
            int y2 = ran.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }
        String checkCode_session = sb.toString();
        session.setAttribute("checkCode_session",checkCode_session);
       //关闭画笔
        g.dispose();
        return image;
    }

}
