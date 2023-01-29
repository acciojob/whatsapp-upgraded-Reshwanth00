package com.driver;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WhatsappRepository {
    public Map<String ,User> userMap= new HashMap<>();
    public Map<Group,List<User>> personalGroup = new HashMap<>();
    public Map<Group,List<User>> publicGroup = new HashMap<>();
    public Map<User,Group> userGroupMap = new HashMap<>();
    public Map<User,Group> admins = new HashMap<>();
    public Map<Group,List<User>> allGroups = new HashMap<>();
    public Map<Integer,Message> messageMap = new HashMap<>();
    public Map<User,List<Message>> userMessages = new HashMap<>();
    public Map<Group,List<Message>> groupMessages = new HashMap<>();
}