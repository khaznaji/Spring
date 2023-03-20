package com.example.intermove.Services.UserService;

import com.example.intermove.Entities.User.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    User addUser (User user);

    List<User> retrieveAllUsers();

    User retrieveUserById(Integer userid);

    void deleteUser(Integer userid);

    User updateUser(User user);

    public User findById(int id );

    String saveImage(MultipartFile image, User request) throws IOException;
}
