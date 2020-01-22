package ncu.stu.beans;

public class MyUser {
    private int userID;
    private  String userNmae;
    private String password;
    private int role;

    public MyUser(){

    }
    public MyUser(int userID,String userNmae,String password,int role){
        this.userID = userID;
        this.userNmae = userNmae;
        this.password = password;
        this.role = role;
    }

    public String getUserNmae() {
        return userNmae;
    }

    public void setUserNmae(String userNmae) {
        this.userNmae = userNmae;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
