package com.github.piotrselak.library.auth;

import com.github.piotrselak.library.user.domain.Role;
import com.github.piotrselak.library.user.domain.User;
import com.github.piotrselak.library.user.domain.UserDto;
import com.github.piotrselak.library.user.repository.UserRepository;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ImportResource("classpath:applicationContext.xml")
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    boolean checkIfUserExistsByName(String name) {
        User user = userRepository.findByName(name);
        return user != null && user.getName() != null && !user.getName().isEmpty();
    }

    void saveUser(UserDto userDto) {
        Role role = new Role();
        role.setName("USER");
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(List.of(role));
        userRepository.save(user);
    }
}
