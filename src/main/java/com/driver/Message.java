//package com.driver;
//
//import java.util.Date;
//
//public class Message {
//    private int id;
//    private String content;
//    private Date timestamp;
//    public Message(){}
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public void setTimestamp(Date timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public Date getTimestamp() {
//        return timestamp;
//    }
//
//    public Message(int id,String content){
//        setContent(content);
//        setTimestamp(timestamp);
//    }
//}
package com.driver;




import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Message {

    private int id;
    private String content;
    private Date timestamp;

    private Message message;
    private Group group;

    public Message getMessage() {
        return message;
    }

    public Message(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Message() {
    }
}
