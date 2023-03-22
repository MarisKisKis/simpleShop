package task.simpleShop.service;

import task.simpleShop.model.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);


    void deleteUser(Long userId);
}
