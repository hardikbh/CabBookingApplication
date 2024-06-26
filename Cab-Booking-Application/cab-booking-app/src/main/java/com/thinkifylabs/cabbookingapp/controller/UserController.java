package com.thinkifylabs.cabbookingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinkifylabs.cabbookingapp.model.UserClass;
import com.thinkifylabs.cabbookingapp.service.UserService;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "add_user")
    public String add_user_controller(@RequestParam String userName, @RequestParam long userPhoneNumber, 
        @RequestParam long xCoordinate, @RequestParam long yCoordinate) throws Exception
    {
        UserClass newUser = new UserClass(userName, userPhoneNumber, xCoordinate, yCoordinate);

        if(userService.add_user_service(newUser))
            return "\nUser added successfully!";
        else
            return "\nFailed to add user! Please try again later.";        
    }

    @GetMapping(path = "update_user")
    public String update_user_controller(@RequestParam long userPhoneNumber, @RequestParam String userNewName, 
        @RequestParam long userNewPhoneNumber) throws Exception
    {
        UserClass newUser = new UserClass(userNewName, userNewPhoneNumber, 0, 0);

        if(userService.update_user_service(userPhoneNumber, newUser))
            return "\nUser details updated successfully!";
        else
            return "\nThis user doesn't exist. Please add the user first.";        
    }

    @GetMapping(path = "update_userLocation")
    public String update_userLocation_controller(@RequestParam long userPhoneNumber, 
    @RequestParam long xCoordinate, @RequestParam long yCoordinate)
    {
        UserClass newUser = new UserClass("", userPhoneNumber, xCoordinate, yCoordinate);

        if(userService.update_userLocation_service(newUser))
            return "\nUser location updated successfully!";
        else
            return "\nThis user doesn't exist. Please add the user first.";        
    }



}
