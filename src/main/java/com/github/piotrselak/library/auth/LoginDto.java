package com.github.piotrselak.library.auth;

import lombok.Data;

@Data
public class LoginDto {
    private String name;
    private String password;
}
