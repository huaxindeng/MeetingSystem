package ncu.stu.dao;

import ncu.stu.beans.Attendees;
import ncu.stu.beans.Meeting;
import ncu.stu.beans.MyUser;
import ncu.stu.util.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendeesDao {
    private static DataSource ds;
    private static Connection conn;
    private  static Statement stmt;
    private static PreparedStatement pstmt;
    private  static ResultSet rs;

    /*
    填写信息
     */
    public boolean addAttendee(Attendees attendee, MyUser user){
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into attendees() values(?,default,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,user.getUserID());
            pstmt.setString(2,attendee.getName());
            pstmt.setString(3,attendee.getWorkplace());
            pstmt.setString(4,attendee.getIdentify_id());
            pstmt.setString(5,attendee.getPhone());
            pstmt.setString(6,attendee.getMeetTime());
            pstmt.setString(7,attendee.getSex());
            pstmt.setString(8,attendee.getRoom());
            pstmt.setInt(9,attendee.getMeetingID());
            int count= pstmt.executeUpdate();
            if(count==1){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            JdbcUtils.close(null,pstmt,conn);
            e.printStackTrace();
            return false;
        }
    }

    /*
    查找某一会议参会人员名单
     */
    public List<Attendees> findAttendees(int id){
        Attendees attendee ;
        List<Attendees> attendees = new ArrayList<Attendees>();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from attendees where meetingID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                attendee = new Attendees(rs.getInt("id"),
                        rs.getString("name"),rs.getString("workplace"),
                        rs.getString("identify_id"),rs.getString("phone"),
                        rs.getString("meetTime"),rs.getString("sex"),
                        rs.getString("room"),rs.getInt("meetingID"));
                attendees.add(attendee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(rs,pstmt,conn);
        }
        return attendees;
    }
}
