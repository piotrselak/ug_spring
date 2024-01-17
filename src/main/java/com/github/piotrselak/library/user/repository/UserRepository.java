package com.github.piotrselak.library.user.repository;

import com.github.piotrselak.library.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
