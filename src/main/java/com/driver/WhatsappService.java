package com.driver;
import org.springframework.stereotype.Service;
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
                int groupCount = whatsappRepository.groupIntegerMap.containsKey(group)?whatsappRepository.groupIntegerMap.get(group):0;
                whatsappRepository.groupIntegerMap.put(group,groupCount+1);
                int userCount = whatsappRepository.userIntegerMap.containsKey(sender)?whatsappRepository.userIntegerMap.get(sender):0;
                whatsappRepository.userIntegerMap.put(sender,userCount);
                whatsappRepository.setNumberOfMessages(whatsappRepository.getNumberOfMessages()+1);
                return groupCount +1;
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
            if(whatsappRepository.groupListMap.get(group).get(0)!=approver){
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
        if(whatsappRepository.everyUserMap.containsKey(user)){
            Group group = whatsappRepository.everyUserMap.get(user);
            if(whatsappRepository.groupListMap.get(group).get(0)!=user){
                int count = whatsappRepository.userIntegerMap.get(user);
                whatsappRepository.userIntegerMap.remove(user);
                List<User> userList = whatsappRepository.groupListMap.get(group);
                userList.remove(user);
                whatsappRepository.groupListMap.put(group,userList);
                int groupCount = whatsappRepository.groupIntegerMap.get(group);
                groupCount-=count;
                whatsappRepository.groupIntegerMap.put(group,groupCount);
                whatsappRepository.setNumberOfMessages(whatsappRepository.getNumberOfMessages()-count);
                return userList.size()+groupCount+ whatsappRepository.getNumberOfMessages();
            }
            else{
                throw new Exception("Cannot remove admin");
            }
        }
        else throw new Exception("User not found");
    }
    public String findMessage(Date start, Date end, int k) {
        return "";
    }
}
