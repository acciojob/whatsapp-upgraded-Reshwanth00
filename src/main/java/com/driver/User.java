//package com.driver;
//public class User {
//    private String name;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    private String mobile;
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public User (String name, String mobile){
//        setMobile(mobile);
//        setName(name);
//    }
//    public User(){}
//}
package com.driver;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String mobile;

    private List<Message> messageList = new ArrayList<>();
    private Group group;

    public List<Message> getMessageList() {
        return messageList;
    }


    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}