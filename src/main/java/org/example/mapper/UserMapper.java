package org.example.mapper;

import lombok.NoArgsConstructor;
import org.example.dto.UserDto;
import org.example.entities.User;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .email(object.getEmail())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}