package com.example.intermove.Security;

import com.example.intermove.Entities.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private int cin;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String address;
    @Nullable
    private String profilepicture;




    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles;
}
