package com.driver;

import java.util.*;

import com.sun.jdi.event.ExceptionEvent;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("whatsapp")
public class WhatsappController {
    WhatsappService whatsappService = new WhatsappService();

    @PostMapping("/add-user")
    public String createUser(String name, String mobile) throws Exception {
        if(!whatsappService.whatsappRepository.userMap.containsKey(mobile)) {
            return whatsappService.createUser(name,mobile);
        }
        else throw new Exception("User already exists");
    }
    @PostMapping("/add-group")
    public Group createGroup(List<User> users){
        return whatsappService.createGroup(users);
    }
    @PostMapping("/add-message")
    public int createMessage(String content){
        return whatsappService.createMessage(content);
    }
//
    @PutMapping("/send-message")
    public int sendMessage(Message message, User sender, Group group) throws Exception{
        try{
            return whatsappService.sendMessage(message,sender,group);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }
    @PutMapping("/change-admin")
    public String changeAdmin(User approver, User user, Group group) throws Exception{
        try{
            return whatsappService.changeAdmin(approver, user, group);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }
    @DeleteMapping("/remove-user")
    public int removeUser(User user) throws Exception{
        return whatsappService.removeUser(user);
    }
//    @GetMapping("/find-messages")
//    public String findMessage(Date start, Date end, int K) throws Exception{
//        // This is a bonus problem and does not contains any marks
//        // Find the Kth latest message between start and end (excluding start and end)
//        // If the number of messages between given time is less than K, throw "K is greater than the number of messages" exception
//
//        return whatsappService.findMessage(start, end, K);
//    }
}
