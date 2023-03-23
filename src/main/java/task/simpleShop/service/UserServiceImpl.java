package task.simpleShop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.simpleShop.exception.ExistsElementException;
import task.simpleShop.exception.NotFoundException;
import task.simpleShop.model.User;
import task.simpleShop.model.dto.UserDto;
import task.simpleShop.repository.UserRepository;
import task.simpleShop.service.mapper.UserMapper;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

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
        getUserById(userId);
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
        return null;
    }

    @Override
    public List<UserDto> getUserById(long userId) {
        return null;
    }

    private UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with ID %s not found", userId)));
        return UserMapper.toUserDto(user);
    }
}
