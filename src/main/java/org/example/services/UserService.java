package org.example.services;

import lombok.NoArgsConstructor;
import org.example.dao.UserDao;
import org.example.dto.CreateUserDto;
import org.example.dto.UserDto;
import org.example.entities.User;
import org.example.mapper.CreateUserMapper;
import org.example.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    public Integer create(CreateUserDto userDto) {
        var userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);

        return userEntity.getId();
    }

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
