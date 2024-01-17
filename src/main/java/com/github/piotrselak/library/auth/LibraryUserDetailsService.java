package com.github.piotrselak.library.auth;


import com.github.piotrselak.library.user.domain.Role;
import com.github.piotrselak.library.user.domain.User;
import com.github.piotrselak.library.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LibraryUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public LibraryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with name: " + name);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(
                user.getName(), user.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));
    }

    private static Collection<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (var role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}