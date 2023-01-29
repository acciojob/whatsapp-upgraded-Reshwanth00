//package com.driver;
//
//public class Group {
//    private String name;
//    private int numberOfParticipants;
//    public Group(){}
//    public Group(String name,int numberOfParticipants){
//        setName(name);
//        setNumberOfParticipants(numberOfParticipants);
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setNumberOfParticipants(int numberOfParticipants) {
//        this.numberOfParticipants = numberOfParticipants;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getNumberOfParticipants() {
//        return numberOfParticipants;
//    }
//}
package com.driver;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private int numberOfParticipants;

    public Group(String name, int numberOfParticipants) {
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
    }

    private User admin;
    private List<User> userList = new ArrayList<>();
    private List<Message> messageList = new ArrayList<>();

    public User getAdmin() {
        return admin;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Group() {
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
}