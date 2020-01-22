package ncu.stu.dao;

import ncu.stu.beans.Attendees;
import ncu.stu.beans.Meeting;
import ncu.stu.beans.MyUser;
import ncu.stu.util.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeetDao {
    private static DataSource ds;
    private static Connection conn;
    private  static Statement stmt;
    private static PreparedStatement pstmt;
    private  static ResultSet rs;


    /*
    遍历数据库表信息，init.jsp页面中显示会议列表
     */
    public List<Meeting> findAll(){
        List<Meeting> meetings = new ArrayList<>();
        Meeting meeting =  new Meeting();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from meeting";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){

                meeting = new Meeting(rs.getInt("id"),rs.getString("time"),
                        rs.getInt("hour"),rs.getString("place"),rs.getString("hotel"),rs.getString("infos"));
                meetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JdbcUtils.close(rs,stmt,conn);
        }
        return meetings;
    }

    /*
    新建会议
     */
    public boolean addMeeting(Meeting meet){
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into meeting() values(default,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,meet.getTime());
            pstmt.setInt(2,meet.getHour());
            pstmt.setString(3,meet.getPlace());
            pstmt.setString(4,meet.getHotel());
            pstmt.setString(5,meet.getInfos());
            int count = pstmt.executeUpdate();
            if(count==1)
                return true;
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            JdbcUtils.close(rs,pstmt,conn);
        }
    }


    /*
    查看我的会议
     */
    public List<Meeting> findMyMeetings(MyUser user){
        List<Meeting> meetings = new ArrayList<>();
        Meeting meeting =  new Meeting();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from meeting where id in" +
                    "(select meetingID from attendees where userID=?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user.getUserID());
            rs = pstmt.executeQuery();
            while(rs.next()){

                meeting = new Meeting(rs.getInt("id"),rs.getString("time"),
                        rs.getInt("hour"),rs.getString("place"),rs.getString("hotel"),rs.getString("infos"));
                meetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JdbcUtils.close(rs,stmt,conn);
        }
        return meetings;
    }

    /*
    其他会议
     */
    public List<Meeting> otherMeetings(MyUser user){
        List<Meeting> meetings = new ArrayList<>();
        Meeting meeting =  new Meeting();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from meeting where id not in" +
                    "(select meetingID from attendees where userID=?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user.getUserID());
            rs = pstmt.executeQuery();
            while(rs.next()){

                meeting = new Meeting(rs.getInt("id"),rs.getString("time"),
                        rs.getInt("hour"),rs.getString("place"),rs.getString("hotel"),rs.getString("infos"));
                meetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JdbcUtils.close(rs,stmt,conn);
        }
        return meetings;
    }

   /*
   查找某一个会议
    */
   public String findOneMeet(int id){
       try {
           conn = JdbcUtils.getConnection();
           String sql = "select * from meeting where id=?";
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1,id);
           rs = pstmt.executeQuery();
           while(rs.next()){
               System.out.println(rs.getInt("id"));
               System.out.println("findOneMeet:"+rs.getString("infos"));
               return rs.getString("infos");
           }

       } catch (SQLException e) {
           e.printStackTrace();
           return null;
       }finally {
           JdbcUtils.close(rs,pstmt,conn);
       }
       return null;
   }
    /*
    删除会议
     */
    public boolean subMeeting(){

        return false;
    }
}
