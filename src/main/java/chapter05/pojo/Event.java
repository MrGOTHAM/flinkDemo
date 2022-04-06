package chapter05.pojo;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: an
 * Date: 2022/4/6
 * Time: 11:12
 * Description:
 */
public class Event {
    private String user;
    private String url;
    private Long timeStamp;

    public Event(){

    }
    public Event(String user, String url, Long timeStamp){
        this.user = user;
        this.url = url;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Event{"+
                "user='"+user+'\''+
                ", url='"+url+'\''+
                ", timestamp='"+new Timestamp(timeStamp)+'\''+
                '}';
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
