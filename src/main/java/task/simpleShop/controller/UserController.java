package task.simpleShop.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.simpleShop.model.dto.UserDto;
import task.simpleShop.service.UserService;

import javax.validation.Valid;

//контроллер-заготовка для упраления пользователями
@Slf4j
@Valid
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Create new user {}", userDto);
        return userService.createUser(userDto);
    }

    @DeleteMapping
    public void removeUser(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Delete user with id = {}", userId);
        userService.deleteUser(userId);
    }
}
