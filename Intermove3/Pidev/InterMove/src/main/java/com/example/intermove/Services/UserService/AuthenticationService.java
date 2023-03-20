package com.example.intermove.Services.UserService;



import com.example.intermove.Entities.User.User;
import com.example.intermove.Repositories.User.UserRepository;
import com.example.intermove.Security.AuthenticationRequest;
import com.example.intermove.Security.AuthenticationResponse;
import com.example.intermove.Security.RegisterRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IJwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse register(RegisterRequest request) {
      var user = User.builder()
              .firstname(request.getFirstname())
              .lastname(request.getLastname())
              .cin(request.getCin())
              .email(request.getEmail())
              .password(passwordEncoder.encode(request.getPassword()))
              .roles(request.getRoles())
              .phone(request.getPhone())
              .address(request.getAddress())
              .profilepicture(request.getProfilepicture())
              .build();
              userRepository.save(user);
                var jwtToken = jwtService.generateTokenWithoutExtraClaims(user);
              return AuthenticationResponse.builder()
                      .token(jwtToken)
                      .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
       );
       var user = userRepository.findByEmail(request.getEmail()).orElse(null);
        var jwtToken = jwtService.generateTokenWithoutExtraClaims(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
