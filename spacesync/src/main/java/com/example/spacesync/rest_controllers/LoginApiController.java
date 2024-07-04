package com.example.spacesync.rest_controllers;

import com.example.spacesync.model.Login;
import com.example.spacesync.model.User;
import com.example.spacesync.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class LoginApiController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity authenticateUser(@RequestBody Login login){

        List<String> userEmail = userService.checkUserEmail(login.getEmail());

        if(userEmail.isEmpty() || userEmail == null){
            return new ResponseEntity("Email does not exist", HttpStatus.NOT_FOUND);
        }

        String hashed_password = userService.checkUserPasswordByEmail(login.getEmail());

        if(!BCrypt.checkpw(login.getPassword(), hashed_password)){
            return new ResponseEntity("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserDetailsByEmail(login.getEmail());
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
