package org.example.dto;

import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class UserDto {
    Integer id;
    String fullName;
    String email;
}
