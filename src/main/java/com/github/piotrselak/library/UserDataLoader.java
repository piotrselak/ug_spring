package com.github.piotrselak.library;


import com.github.piotrselak.library.user.domain.Role;
import com.github.piotrselak.library.user.domain.User;
import com.github.piotrselak.library.user.repository.RoleRepository;
import com.github.piotrselak.library.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByName("admin") == null) {
            User newUser = new User();
            newUser.setName("admin");
            newUser.setPassword(passwordEncoder.encode("admin"));

            Role adminRole = new Role();
            adminRole.setName("ADMIN");

            Role userRole = new Role();
            userRole.setName("USER");
//            roleRepository.save(adminRole);

            newUser.setRoles(List.of(adminRole, userRole));
            userRepository.save(newUser);
        }
    }
}