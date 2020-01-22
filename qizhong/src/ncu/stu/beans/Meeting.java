package ncu.stu.beans;

public class Meeting {
    private int id;
    private String time;
    private int hour;
    private String place;
    private String hotel;
    private String infos;

    public Meeting(){

    }
    public Meeting(String time,int hour,String place,String hotel,String infos){
        this.time = time;
        this.hour = hour;
        this.place =place;
        this.hotel = hotel;
        this.infos = infos;
    }

    public Meeting(int id,String time,int hour,String place,String hotel,String infos){
        this.id=id;
        this.time = time;
        this.hour = hour;
        this.place =place;
        this.hotel = hotel;
        this.infos = infos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }
}
