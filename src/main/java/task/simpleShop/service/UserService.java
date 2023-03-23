package task.simpleShop.service;

import task.simpleShop.model.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);


    void deleteUser(Long userId);

    void topUpUserBalance(long userId, double amount);

    List<UserDto> getAllUsers();

    List<UserDto> getUserById(long userId);
}
