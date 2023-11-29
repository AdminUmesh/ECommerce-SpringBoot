package com.umesh.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umesh.Dto.LoginResponseDto;
import com.umesh.demo.Model.UserDetails;
import com.umesh.demo.Repository.loginRepo;

@Controller
@RestController
@RequestMapping("/api")
public class api {
    @Autowired
    loginRepo LoginRep;

    @GetMapping("/login")
    public ResponseEntity<?> loginData(@RequestParam String username, String Password) {

        UserDetails DatabaseUserData = LoginRep.findByUsername(username);
        UserDetails user = new UserDetails();

        LoginResponseDto CostuOutput = new LoginResponseDto();
        // String loginResponse=null;
        if (DatabaseUserData.getUsername().equalsIgnoreCase(username)
                && DatabaseUserData.getPassword().equalsIgnoreCase(Password)) {
            // loginResponse="Success "+"userid: "+userDetails.getId()+"name:
            // "+userDetails.getName()+userDetails.getType();
            CostuOutput.setUsername(DatabaseUserData.getUsername());
            CostuOutput.setType(DatabaseUserData.getType());
            CostuOutput.setName(DatabaseUserData.getName());
            return new ResponseEntity<>(CostuOutput, HttpStatus.OK);
        } else {
            String loginResponse = "no user Found with" + "username:" + username;
            return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/DeleteUser")
    public ResponseEntity<?> DeleteData(@RequestParam String username) {

        UserDetails DatabaseUserData = LoginRep.findByUsername(username);

        if (DatabaseUserData.getUsername().equalsIgnoreCase(username)) {

            LoginRep.delete(DatabaseUserData);
            return new ResponseEntity<>(DatabaseUserData, HttpStatus.OK);
        } else {
            String loginResponse = "no user Found with" + "username:" + username;
            return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> UserSave(@RequestBody UserDetails FormDetails) {

        UserDetails DatabaseUserData = LoginRep.findByUsername(FormDetails.getUsername());
        UserDetails newUser = new UserDetails();
        newUser.setName(FormDetails.getName());
        newUser.setEmail(FormDetails.getEmail());

        if (DatabaseUserData != null && DatabaseUserData.getUsername().equalsIgnoreCase(FormDetails.getUsername())) {
            return new ResponseEntity<>("user Exist", HttpStatus.BAD_REQUEST);
        } else {
            LoginRep.save(FormDetails);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }

    }

    @GetMapping(path = "/getAllUser")
    public ResponseEntity<?> getAllUsers() {
        List<UserDetails> allUsers = LoginRep.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping(path = "/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserDetails updatedUserDetails) {
        UserDetails oldUser = LoginRep.findByUsername(updatedUserDetails.getUsername());

        if (oldUser != null) {
            // Copy the properties from the updatedUserDetails to the oldUser
            oldUser.setName(updatedUserDetails.getName());
            oldUser.setEmail(updatedUserDetails.getEmail());
            oldUser.setPassword(updatedUserDetails.getPassword());
            oldUser.setAddress(updatedUserDetails.getAddress());

            // Save the updated user details
            LoginRep.save(oldUser);

            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

}