package task.simpleShop.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.simpleShop.model.dto.NotificationDto;
import task.simpleShop.model.dto.UserDto;
import task.simpleShop.service.UserService;

import javax.validation.Valid;
import java.util.List;

//контроллер-заготовка для упраления пользователями
@Slf4j
@Valid
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //просмотр пользователем полученных уведомлений
    @GetMapping("/notification")
    public List<NotificationDto> getUserNotifications(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Receiving notifications for user with id = {}", userId);
        return userService.getUserNotifications(userId);
    }

    //создание пользователя
    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Create new user {}", userDto);
        return userService.createUser(userDto);
    }

    //удаление пользователя
    @DeleteMapping
    public void removeUser(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Delete user with id = {}", userId);
        userService.deleteUser(userId);
    }
}
