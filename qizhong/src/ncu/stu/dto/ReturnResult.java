package ncu.stu.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ReturnResult implements Serializable {

    private static final long serialVersionUID = 6816265515135254415L;
    public ReturnResult(){

    }
    public ReturnResult(int c, String m){
        this.code = c;
        this.msg = m;
    }
    public ReturnResult(int c, String m, Map<String, Object> dataMap){
        this(c, m);
        this.data = dataMap;
    }
    private Map<String, Object> data;
    protected int code = 0;
    protected String msg = "";
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    public void putAll(Map<String, Object> data){
        if(this.data == null) this.data = new HashMap<String, Object>();
        this.data.putAll(data);
    }
    public void put(String key, Object value){
        if(this.data == null) this.data = new HashMap<String, Object>();
        this.data.put(key, value);
    }
}
