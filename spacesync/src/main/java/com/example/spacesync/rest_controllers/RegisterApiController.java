package com.example.spacesync.rest_controllers;

import com.example.spacesync.services.UserService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class RegisterApiController {

    @Autowired
    UserService userService;


    @PostMapping("/user/register")
    public ResponseEntity registerNewUser(@RequestParam("full_name")String full_name,
                                          @RequestParam("dob") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
                                          @RequestParam("email")String email,
                                          @RequestParam("password")String password){

        if(full_name.isEmpty() || dob == null || email.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>("Please Complete all Fields", HttpStatus.BAD_REQUEST);
        }

        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        int result = userService.registerNewUserServiceMethod(full_name, dob, email, hashed_password);

        if(result != 1){
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
        System.out.println(full_name+"   "+email);
        return new ResponseEntity<>("success", HttpStatus.OK);

    }
}
