package task.simpleShop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.simpleShop.exception.ExistsElementException;
import task.simpleShop.exception.NotFoundException;
import task.simpleShop.model.User;
import task.simpleShop.model.dto.NotificationDto;
import task.simpleShop.model.dto.UserDto;
import task.simpleShop.repository.NotificationRepository;
import task.simpleShop.repository.UserRepository;
import task.simpleShop.service.mapper.NotificationMapper;
import task.simpleShop.service.mapper.UserMapper;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        String email = user.getEmail();
        try {
            log.info("User with email {} was created", email);
            User createdUser = userRepository.save(user);
            return UserMapper.toUserDto(createdUser);
        } catch (ExistsElementException e) {
            log.warn("User with email {} exists", email);
            throw new ExistsElementException("User exists");
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void topUpUserBalance(long userId, double amount) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()){
            throw new NotFoundException(String.format("User with ID %s not found", userId));
        }
        User user = userOptional.get();
        user.setBalance(user.getBalance()+amount);
        log.info("Balance changed");
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();
        return UserMapper.toUserDto(user);
    }

    @Override
    public void createNotification(long userId, NotificationDto notificationDto) {
        User user = UserMapper.toUser(getUserById(userId));
        if (user == null){
            throw new NotFoundException(String.format("User with ID %s not found", userId));
        }
        notificationRepository.save(NotificationMapper.toNotification(notificationDto, user));
    }

    @Override
    public List<NotificationDto> getUserNotifications(Long userId) {
        User user = UserMapper.toUser(getUserById(userId));
        return notificationRepository.findNotificationByUser(user)
                .stream()
                .map(NotificationMapper::toNotificationDto)
                .collect(Collectors.toList());
    }
}
