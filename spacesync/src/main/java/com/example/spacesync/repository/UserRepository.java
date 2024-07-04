package com.example.spacesync.repository;

import com.example.spacesync.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT email FROM users WHERE email = :email ", nativeQuery = true)
    List<String> checkUserEmail(@Param("email") String email);

    @Query(value = "SELECT password FROM users WHERE email = :email", nativeQuery = true)
    String checkUserPasswordByEmail(@Param("email") String email);

    @Query(value = " SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User GetUserDetailsByEmail(@Param("email") String email);



    @Transactional
    @Modifying
    @Query(value = "INSERT INTO USERS(full_name, dob, email, password) VALUES(:full_name, :dob, :email, :password)", nativeQuery = true)
    int registerNewUser(@Param("full_name") String full_name,
                        @Param("dob") Date dob,
                        @Param("email") String email,
                        @Param("password") String password);
}
