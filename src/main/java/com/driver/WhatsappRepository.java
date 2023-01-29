package com.driver;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WhatsappRepository {
    public Map<String, User> userMap = new HashMap<>();
    public Map<Group,User> userGroupMap = new HashMap<>();
    public Map<Group,User> personalGroupMap = new HashMap<>();
    public Map<Integer,Message> messageMap = new HashMap<>();
    public Map<Group, List<User>> groupListMap = new HashMap<>();
    public Map<Group,Integer> groupIntegerMap = new HashMap<>();
    public Map<User,Integer> userIntegerMap = new HashMap<>();
    public Map<User,Group> everyUserMap = new HashMap<>();
    private int numberOfMessages;

    public int getNumberOfMessages() {
        return numberOfMessages;
    }

    public void setNumberOfMessages(int numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }
}
