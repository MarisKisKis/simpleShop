package task.simpleShop.service;

import task.simpleShop.model.dto.NotificationDto;
import task.simpleShop.model.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);


    void deleteUser(Long userId);

    void topUpUserBalance(long userId, double amount);

    List<UserDto> getAllUsers();

    UserDto getUserById(long userId);

    void createNotification(long userId, NotificationDto notificationDto);

    List<NotificationDto> getUserNotifications(Long userId);
}
