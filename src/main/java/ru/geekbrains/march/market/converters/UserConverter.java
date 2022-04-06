package ru.geekbrains.march.market.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.UserDto;
import ru.geekbrains.march.market.entities.User;

@Component
public class UserConverter {

    public User dtoToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword());
    }

    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword());
    }

}
