package com.dogmeat.petconnect.service;

import com.dogmeat.petconnect.model.User;
import com.dogmeat.petconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPw(passwordEncoder.encode(user.getPw()));  // 비밀번호 암호화
        return userRepository.save(user);
    }
}
