package com.example.intermove.Controllers.User;



import com.example.intermove.Entities.User.Role;
import com.example.intermove.Entities.User.User;
import com.example.intermove.Services.UserService.IUserService;
import com.example.intermove.Services.UserService.UserService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Set;


@RestController
@AllArgsConstructor
@RequestMapping("/user")

public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    UserService userService2;


    @GetMapping("/retrieve-all-users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> listUsers = userService.retrieveAllUsers();
        return new ResponseEntity<List<User>>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/retrieve-user/{user-id}")
    public ResponseEntity<User> getUserById(@PathVariable("user-id") Integer userid) {
        User u = userService.retrieveUserById(userid);
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    @Transactional
    public ResponseEntity<String> addUser(@RequestPart User request, @RequestPart("image") MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imagePath = userService.saveImage(image,request);
            request.setProfilepicture(imagePath);

            userService.addUser(request).getRoles();
            return ResponseEntity.ok("User was successfully added");
        }
        return ResponseEntity.ok("erreur");
    }

    @DeleteMapping("/remove-user/{user-id}")
    @Transactional
    public ResponseEntity<String> removeEtudiant(@PathVariable("user-id") Integer userid) {
        userService.deleteUser(userid);
        return new ResponseEntity<String>("User with '"+userid+"' has been sucessfully deleted",HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
    @GetMapping("/sendmail/{id}")
    public User sendmail(@PathVariable("id") int id){

        String email= userService2.findById(id).getEmail();

        userService2.sendSimpleEmail(email,
                "Please verify your email to secure your account.\n" +
                        "http://localhost:63342/SpringTwilio/templates/index.html?_ijt=pt7r2borai41v6o1o5o0j8rpua&_ij_reload=RELOAD_ON_SAVE\n"+
                        "Now you can logged in\n" +
                        "\n" +

                         "\n" +
                        "Thank you for taking this important step to verify your account. " +
                        "After clicking the button below you will be able to use the full range of services with your account",
                "Verification"
        );

        return userService2.findById(id);
    }












}
