package com.github.piotrselak.library.user.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String password;
}
