package task.simpleShop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.simpleShop.model.dto.ItemDto;
import task.simpleShop.model.dto.UserDto;
import task.simpleShop.service.CartService;
import task.simpleShop.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Valid
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CartService cartService;

    private final UserService userService;

    @Autowired
    public AdminController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    //получение админом истории покупок любого пользователя
    @GetMapping("/purchases")
    /* для создания базового каркаса под историей покупок понимается перечень купленных товаров, хотя это может
     быть отдельный класс
     */
    public List<ItemDto> getUserPurchaseHistory(@RequestParam long userId) {
        log.info("Receiving user item history");
        return cartService.getUserHistory(userId);
    }

    //получение информации о всех пользователях
    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        log.info("Receiving all users information");
        return userService.getAllUsers();
    }

    //получение информации о пользователе
    @GetMapping("/users/{userId}")
    public List<UserDto> getUserById(@RequestParam long userId) {
        log.info("Receiving all users information");
        return userService.getUserById(userId);
    }


    //пополнение баланса пользователя админом
    @PatchMapping("/balance")
    public void topUpUserBalance(@RequestParam long userId, @RequestParam double amount) {
        log.info("Changing balance for user with id = {}", userId);
        userService.topUpUserBalance(userId, amount);
    }

}
