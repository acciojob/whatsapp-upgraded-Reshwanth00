package com.driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class WhatsappService {
    WhatsappRepository whatsappRepository = new WhatsappRepository();
    public String createUser(String name, String mobile){
        User user = new User();
        user.setName(name);
        user.setMobile(mobile);
        whatsappRepository.userMap.put(mobile,user);
        return "SUCCESS";
    }
    public Group createGroup(List<User> users) {
        Group group = new Group();
        if(users.size()==2){
            group.setName(users.get(1).getName());
            group.setNumberOfParticipants(2);
            whatsappRepository.personalGroupMap.put(group,users.get(0));
            whatsappRepository.groupListMap.put(group,users);
            for(int i=0;i<2;i++){
                whatsappRepository.everyUserMap.put(users.get(i),group);
            }
        }
        if(users.size()>2){
            int count = whatsappRepository.userGroupMap.size()+1;
            group.setName("Group "+ count);
            group.setNumberOfParticipants(users.size());
            whatsappRepository.userGroupMap.put(group,users.get(0));
            whatsappRepository.groupListMap.put(group,users);
            for(int i=0;i<users.size();i++){
                whatsappRepository.everyUserMap.put(users.get(i),group);
            }
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
        if(whatsappRepository.groupListMap.containsKey(group)){
            if(whatsappRepository.groupListMap.get(group).indexOf(sender)>-1){
                List<Message> groupMessageList = whatsappRepository.groupMessageMap.get(group);
                groupMessageList.add(message);
                whatsappRepository.groupMessageMap.put(group,groupMessageList);
                List<Message> senderMessageList = whatsappRepository.userMessageMap.get(sender);
                senderMessageList.add(message);
                whatsappRepository.userMessageMap.put(sender,senderMessageList);
                whatsappRepository.setNumberOfMessages(whatsappRepository.getNumberOfMessages()+1);
                return whatsappRepository.groupListMap.size();
            }
            else{
                throw new Exception("You are not allowed to send message");
            }
        }
        else {
            throw new Exception("Group does not exist");
        }
    }
    public String changeAdmin(User approver, User user, Group group) throws Exception {
        if(whatsappRepository.groupListMap.containsKey(group)){
            if(whatsappRepository.groupListMap.get(group).get(0)==approver){
                if(whatsappRepository.groupListMap.get(group).indexOf(user)>-1){
                    List<User> userList = whatsappRepository.groupListMap.get(group);
                    User temp = userList.get(0);
                    userList.set(0,user);
                    userList.add(temp);
                    whatsappRepository.groupListMap.put(group,userList);
                    return "SUCCESS";
                }
                else{
                    throw new Exception("User is not a participant");
                }
            }
            else{
                throw new Exception("Approver does not have rights");
            }
        }
        else {
            throw new Exception("Group does not exist");
        }
    }
    public int removeUser(User user) throws Exception {
        /*
        boolean userFound = false;
        int groupSize = 0;
        int messageCount = 0;
        int overallMessageCount = messageList.size();
        Group groupToRemoveFrom = null;
        for (Map.Entry<Group, List<User>> entry : groupHashMap.entrySet()) {
            List<User> groupUsers = entry.getValue();
            if (groupUsers.contains(user))
            {
                userFound = true;
                groupToRemoveFrom = entry.getKey();
                if (groupUsers.get(0).equals(user))
                {
                    throw new Exception("Cannot remove admin");
                }
                groupUsers.remove(user);
                groupSize = groupUsers.size();
                break;
            }
        }
        if (!userFound)
        {
            throw new Exception("User not found");
        }
        if (userMessageList.containsKey(user))
        {
            messageCount = userMessageList.get(user).size() - 2;
            userMessageList.remove(user);
        }
        return groupSize + messageCount + overallMessageCount;
    }
         */
//        if(whatsappRepository.everyUserMap.containsKey(user)){
//            Group group = whatsappRepository.everyUserMap.get(user);
//            if(whatsappRepository.groupListMap.get(group).get(0)!=user){
//                List<Message> userMessageList = whatsappRepository.userMessageMap.get(user);
//                whatsappRepository.userMessageMap.remove(user);
//                List<Message> groupMessageList = whatsappRepository.groupMessageMap.get(group);
//                for(Message message:userMessageList){
//                    groupMessageList.remove(message);
//                }
//                List<User> userGroupList = whatsappRepository.groupListMap.get(group);
//                userGroupList.remove(user);
//                whatsappRepository.groupListMap.put(group,userGroupList);
//                whatsappRepository.groupMessageMap.put(group,groupMessageList);
//                whatsappRepository.setNumberOfMessages(whatsappRepository.getNumberOfMessages()-userMessageList.size());
//                return groupMessageList.size()+userGroupList.size()+ whatsappRepository.getNumberOfMessages();
//            }
//            else{
//                throw new Exception("Cannot remove admin");
//            }
//        }
//        else
            throw new Exception("User not found");
    }
    public String findMessage(Date start, Date end, int k) {
        return "";
    }
}
