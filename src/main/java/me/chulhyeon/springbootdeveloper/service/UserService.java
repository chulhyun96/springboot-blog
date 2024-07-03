package me.chulhyeon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.chulhyeon.springbootdeveloper.domain.User;
import me.chulhyeon.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException(email));
    }
}
