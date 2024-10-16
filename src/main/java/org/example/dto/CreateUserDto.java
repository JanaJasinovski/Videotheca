package org.example.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String fullName;
    String password;
    String email;
}
