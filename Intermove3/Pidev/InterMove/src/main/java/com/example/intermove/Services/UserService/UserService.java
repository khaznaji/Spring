package com.example.intermove.Services.UserService;

import com.example.intermove.Entities.User.User;
import com.example.intermove.Repositories.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String getUserTelById(int id) {
        User user = getUserById(id);
        return user.getPhone();
    }
    @Autowired
    private JavaMailSender mailSender;


    @Override
    public User addUser(User resquest) {
        return userRepository.save(resquest);
    }
    @Override
    public List<User> retrieveAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User retrieveUserById(Integer userid){
        return userRepository.findById(userid).orElse(null);
    }

    @Override
    public void deleteUser(Integer userid){
        User u= retrieveUserById(userid);
        userRepository.delete(u);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("iheb.benothmen@esprit.tn");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send...");
    }



    @Override
    public String saveImage(MultipartFile image, User request)throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        Path path = Paths.get("uploads");
        Files.createDirectories(path);
        try (InputStream inputStream = image.getInputStream()) {
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            request.setProfilepicture(filePath.toString());
            return filePath.toString();
        } catch (IOException e) {
            throw new IOException("Could not save file " + fileName, e);
        }

    }


}
