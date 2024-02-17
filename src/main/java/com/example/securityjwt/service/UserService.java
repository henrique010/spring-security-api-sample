package com.example.securityjwt.service;

import com.example.securityjwt.domain.user.RegisterUserDTO;
import com.example.securityjwt.domain.user.User;
import com.example.securityjwt.domain.user.UserAlreadyExistsException;
import com.example.securityjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(RegisterUserDTO data) {
        var userAlreadyExists = this.userRepository.findByLogin(data.login());

        if(userAlreadyExists != null) {
            throw new UserAlreadyExistsException();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);
    }
}
