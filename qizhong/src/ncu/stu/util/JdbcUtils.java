package ncu.stu.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import ncu.stu.beans.MyUser;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource ds;
    private static Connection conn;
    private  static Statement stmt;
    private static PreparedStatement pstmt;
    private  static ResultSet rs;
    static{
        try {
            Properties pro = new Properties();
            pro.load(JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    获取数据库连接
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    /*
    获取查询结果
     */
    public static ResultSet executeQuery(String sql){
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }



//    /*
//    注册新用户
//     */
//    public static boolean judegRep(MyUser user){
//        String sql = "insert into userlogin() values(null,?,?,?)";
//
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1,user.getUserNmae());
//            pstmt.setString(2,user.getPassword());
//            pstmt.setInt(3, user.getRole());
//            int count = pstmt.executeUpdate();
//            if(count==1){
//                return true;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }finally{
//            close(null,pstmt,conn);
//        }
//        return false;
//    }
    /*
    释放资源
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeRs(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
