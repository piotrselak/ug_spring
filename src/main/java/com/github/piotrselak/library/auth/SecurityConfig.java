package com.github.piotrselak.library.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationConfiguration authConfiguration;

    public SecurityConfig(AuthenticationConfiguration authConfiguration) {
        this.authConfiguration = authConfiguration;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

//    @Autowired
//    public void configure(AuthenticationManagerBuilder builder,
//                          AuthenticationProvider jwtAuthenticationProvider) {
//        builder.authenticationProvider(jwtAuthenticationProvider);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
//                        .requestMatchers("/reservation/**").hasAuthority("USER")
                                .anyRequest().permitAll()
                );

        return http.build();
    }

}
