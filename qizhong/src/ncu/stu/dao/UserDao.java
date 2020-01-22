package ncu.stu.dao;

import ncu.stu.beans.MyUser;
import ncu.stu.util.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static DataSource ds;
    private static Connection conn;
    private  static Statement stmt;
    private static PreparedStatement pstmt;
    private  static ResultSet rs;
    /*
    验证登录
     */
    public MyUser login(String userName,String password){
        MyUser user = new MyUser();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from userlogin ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                if(rs.getString("userName").equals(userName)&&rs.getString("password").equals(password)){
                    user.setUserID(rs.getInt("userID"));
                    user.setRole(rs.getInt("role")) ;
                    user.setUserNmae(rs.getString("userName"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(rs,pstmt,conn);
        }
        return user;
    }

    public static int addUser(MyUser user){


        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into userlogin() values(default ,?,?,?)";
                pstmt = conn.prepareStatement(sql);
            System.out.println("******************");
                System.out.println(user.getUserNmae());
            System.out.println(user.getPassword());
            System.out.println(user.getRole());
            System.out.println("******************");
                pstmt.setString(1,user.getUserNmae());
                pstmt.setString(2,user.getPassword());
                pstmt.setInt(3, user.getRole());
                int count = pstmt.executeUpdate();
                return count;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(null,pstmt,conn);
        }
        return 0;
    }
    /*
    注册新用户
     */
    public static boolean judgeNoRep(MyUser user){

        try {
            conn = JdbcUtils.getConnection();

            String sql1 = "select * from userlogin where userName=?";
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1,user.getUserNmae());
            rs = pstmt.executeQuery();

            if(rs.next()){
                //重复，返回false，创建失败
                System.out.println("aaaaa");
                return false;
            }

            else{
                int role = user.getRole();
                if(role==1){
                    if(addUser(user)==1){
                        return true;
                    }
                    else
                        return false;
                }
                else{
                    return addInit(user);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("bbbb");
            return false;
        }finally{
            JdbcUtils.close(null,pstmt,conn);
        }
    }

    /*
    查找会议发起人信息
     */
    public List<MyUser> findInit(){
        List<MyUser> inits = new ArrayList<>();
        MyUser user = new MyUser();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from userlogin where role=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,2);
            rs = pstmt.executeQuery();
            while(rs.next()){
               user = new MyUser(rs.getInt("userID"),rs.getString("userName"),
                    rs.getString("password"),rs.getInt("role"));
               inits.add(user);
            }
            return inits;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            JdbcUtils.close(rs,pstmt,conn);
        }
    }

    /*
    删除用户
     */
    public boolean dropUser(int id){
        try {
            conn = JdbcUtils.getConnection();
            String sql = "delete from userlogin where userID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            int count = pstmt.executeUpdate();
            if(count==1){
                return true;
            }
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            JdbcUtils.close(null,pstmt,conn);
        }
    }

    /*
    添加会议发起人
     */
    public static boolean addInit(MyUser user){
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into userlogin() values(default,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getUserNmae());
            pstmt.setString(2,user.getPassword());
            pstmt.setInt(3,2);
            int count = pstmt.executeUpdate();
            if(count == 1){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            JdbcUtils.close(null,pstmt,conn);
        }
    }

}
