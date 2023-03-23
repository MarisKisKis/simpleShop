package task.simpleShop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.simpleShop.model.Discount;
import task.simpleShop.model.dto.*;
import task.simpleShop.service.CartService;
import task.simpleShop.service.ItemService;
import task.simpleShop.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Valid
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CartService cartService;

    private final UserService userService;

    private final ItemService itemService;

    @Autowired
    public AdminController(CartService cartService, UserService userService, ItemService itemService) {
        this.cartService = cartService;
        this.userService = userService;
        this.itemService = itemService;
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
    public UserDto getUserById(@PathVariable @NotNull long userId) {
        log.info("Receiving all users information");
        return userService.getUserById(userId);
    }

    //публикация товара в магазине
    @PostMapping("/items")
    public void createItems() {
        log.info("Checking for requests to approve");
        itemService.createItems();
    }

    //отправка пользователю уведомления
    @PostMapping("/notification")
    public void sendNotification(@RequestParam long userId, @RequestBody NotificationDto notificationDto) {
        log.info("Sending notification to user with id = {}", userId);
        notificationDto.setDate(LocalDateTime.now());
        userService.createNotification(userId, notificationDto);
    }

    //создание админом новой акции
    @PostMapping("/discount")
    public void createDiscount(@RequestBody DiscountDto discountDto) {
        log.info("Creating new discount");
        itemService.createDiscount(discountDto);
    }

    //изменение информации о товаре админом
    @PutMapping("/items/{itemId}")
    public void updateItem(@PathVariable @NotNull long itemId, @RequestBody ItemDto itemDto) {
        log.info("Updating item with id = {}", itemId);
        itemService.updateItem(itemId, itemDto);
    }

    //пополнение баланса пользователя админом
    @PatchMapping("/balance")
    public void topUpUserBalance(@RequestParam long userId, @RequestParam double amount) {
        log.info("Changing balance for user with id = {}", userId);
        userService.topUpUserBalance(userId, amount);
    }

    //удаление админом пользователя
    @DeleteMapping
    public void removeUser(@RequestParam Long userId) {
        log.info("Delete user with id = {}", userId);
        userService.deleteUser(userId);
    }
}
