package task.simpleShop.service.mapper;

import task.simpleShop.model.User;
import task.simpleShop.model.dto.UserDto;

public class UserMapper {

    public static UserDto toUserDto (User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .balance(user.getBalance())
                .build();
    }

    public static User toUser (UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.getBalance());
    }
}
