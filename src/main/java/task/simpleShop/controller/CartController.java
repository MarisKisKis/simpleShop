package task.simpleShop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.simpleShop.model.dto.ItemDto;
import task.simpleShop.model.dto.RefundDto;
import task.simpleShop.service.CartService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Valid
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // просмотр истории своих покупок пользователем
    @GetMapping("/history")
    public List<ItemDto> getUserHistory(@RequestHeader("X-Sharer-User-Id")  Long userId) {
        log.info("Receiving user item history");
        return cartService.getUserHistory(userId);
    }

    // отправка запроса на совершение покупки пользователем
    @PostMapping("/order")
    public void makeOrder(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Receiving user item history");
        cartService.makeOrder(userId);
    }

    //отправка пользователем запроса на возврат товара
    @PostMapping("/refund")
    public void makeRefund(@RequestHeader("X-Sharer-User-Id") Long userId, @RequestBody RefundDto refundDto) {
        log.info("Creating refund request");
        refundDto.setCreated(LocalDateTime.now());
        cartService.makeRefund(userId, refundDto);
    }
}
