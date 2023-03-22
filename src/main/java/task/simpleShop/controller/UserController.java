package task.simpleShop.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.simpleShop.model.dto.UserDto;
import task.simpleShop.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Valid
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/users")
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Create new user {}", userDto);
        return userService.createUser(userDto);
    }

    @DeleteMapping(value = "users/{userId}")
    public void removeUser(@PathVariable Long userId) {
        log.info("Delete ru.practicum.user with id = {}", userId);
        userService.deleteUser(userId);
    }
}
