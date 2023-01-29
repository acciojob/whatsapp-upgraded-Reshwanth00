package com.driver;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WhatsappService {
    WhatsappRepository whatsappRepository = new WhatsappRepository();
    public String createUser(String name, String mobile) throws Exception {
        if(whatsappRepository.userMap.containsKey(mobile))throw new Exception("User already exists");
        else{
            User user = new User();
            user.setMobile(mobile);
            user.setName(name);
            whatsappRepository.userMap.put(mobile,user);
            return "SUCCESS";
        }
    }
    public Group createGroup(List<User> users) {
        Group group = new Group();
        if(users.size()>1){
            if(users.size()==2){
                group.setName(users.get(1).getName());
                group.setNumberOfParticipants(2);
                whatsappRepository.personalGroup.put(group,users);

            }
            if(users.size()>2){
                int count = whatsappRepository.userGroupMap.size()+1;
                group.setName("Group "+ count);
                group.setNumberOfParticipants(users.size());
                whatsappRepository.publicGroup.put(group,users);
            }
            for(int i=0;i<users.size();i++){
                whatsappRepository.userGroupMap.put(users.get(i),group);
            }
            whatsappRepository.admins.put(users.get(0),group);
            whatsappRepository.allGroups.put(group,users);
        }
        return group;
    }
    public int createMessage(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setId(whatsappRepository.messageMap.size()+1);
        whatsappRepository.messageMap.put(whatsappRepository.messageMap.size()+1,message);
        return message.getId();
    }
    public int sendMessage(Message message, User sender, Group group) throws Exception{
        if(whatsappRepository.allGroups.containsKey(group)){
            if(whatsappRepository.userGroupMap.containsKey(sender) && whatsappRepository.userGroupMap.get(sender)==group){
                List<Message> usersMessageList = whatsappRepository.userMessages.getOrDefault(sender,new ArrayList<>());
                usersMessageList.add(message);
                whatsappRepository.userMessages.put(sender,usersMessageList);
                List<Message> groupsMessageList = whatsappRepository.groupMessages.getOrDefault(group,new ArrayList<>());
                groupsMessageList.add(message);
                whatsappRepository.groupMessages.put(group,groupsMessageList);
                return groupsMessageList.size();
            }
            else throw new Exception("You are not allowed to send message");
        }
        else throw new Exception("Group does not exist");
    }
    public String changeAdmin(User approver, User user, Group group) throws Exception {
        if(whatsappRepository.allGroups.containsKey(group)){
            if(whatsappRepository.admins.get(approver)!=group){
                if(whatsappRepository.allGroups.get(group).indexOf(user)!=-1){
                    whatsappRepository.admins.remove(approver);
                    whatsappRepository.admins.put(user,group);
                    return "SUCCESS";
                }
                else{
                    throw new Exception("User is not a participant");
                }

            }
            else {
                throw new Exception("Approver does not have rights");
            }
        }
        else{
            throw new Exception("Group does not exist");
        }
    }
    public int removeUser(User user) throws Exception {
        if(whatsappRepository.userGroupMap.containsKey(user)){
            if(!whatsappRepository.admins.containsKey(user)){
                List<Message> usersMessageList = whatsappRepository.userMessages.get(user);
                whatsappRepository.userMessages.remove(user);
                Group group = whatsappRepository.userGroupMap.get(user);
                List<Message> groupsMessageList = whatsappRepository.groupMessages.get(group);
                for(Message message:usersMessageList){
                    groupsMessageList.remove(message);
                    whatsappRepository.messageMap.remove(message.getId());
                }
                whatsappRepository.groupMessages.put(group,groupsMessageList);
                List<User> userList = whatsappRepository.allGroups.get(group);
                userList.remove(user);
                whatsappRepository.allGroups.put(group,userList);
                return groupsMessageList.size()+userList.size()+whatsappRepository.messageMap.size();
            }
            else{
                throw new Exception("Cannot remove admin");
            }
        }
        else {
             throw new Exception("User not found");
        }
    }
    public String findMessage(Date start, Date end, int k) {
        return "";
    }
}