package ncu.stu.beans;

public class Attendees {
    private int id;
    private String name;
    private String workplace;
    private String identify_id;
    private String phone;
    private String meetTime;
    private String sex;
    private String room;
    private int meetingID;

    public Attendees(){

    }
    public Attendees(String name, String workplace, String identify_id,
                     String phone, String meetTime, String sex, String room, int meetingID) {
        this.name = name;
        this.workplace = workplace;
        this.identify_id = identify_id;
        this.phone = phone;
        this.meetTime = meetTime;
        this.sex = sex;
        this.room = room;
        this.meetingID = meetingID;
    }
    public Attendees(int id, String name, String workplace, String identify_id,
                     String phone, String meetTime, String sex, String room, int meetingID) {
        this.id = id;
        this.name = name;
        this.workplace = workplace;
        this.identify_id = identify_id;
        this.phone = phone;
        this.meetTime = meetTime;
        this.sex = sex;
        this.room = room;
        this.meetingID = meetingID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public String getIdentify_id() {
        return identify_id;
    }

    public void setIdentify_id(String identify_id) {
        this.identify_id = identify_id;
    }
}
