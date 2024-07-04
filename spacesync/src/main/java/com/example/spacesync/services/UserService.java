package com.example.spacesync.services;
import com.example.spacesync.model.User;
import com.example.spacesync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public int registerNewUserServiceMethod(String fname, Date dob, String email, String password){
        return userRepository.registerNewUser(fname,dob,email,password);
    }

    public List<String> checkUserEmail(String email){
        return userRepository.checkUserEmail(email);
    }

    public String checkUserPasswordByEmail(String email){
        return userRepository.checkUserPasswordByEmail(email);
    }

    public User getUserDetailsByEmail(String email){
        return userRepository.GetUserDetailsByEmail(email);
    }
}