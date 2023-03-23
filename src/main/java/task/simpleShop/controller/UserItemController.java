package task.simpleShop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.simpleShop.model.Rating;
import task.simpleShop.model.dto.FeedbackDto;
import task.simpleShop.model.dto.ItemRequestDto;
import task.simpleShop.service.ItemService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Valid
@RestController
@RequestMapping("/items")
public class UserItemController {

    private final ItemService itemService;

    @Autowired
    public UserItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    //добавление товара в корзину
    @PostMapping("/{itemId}/add")
    public void addItemToCart(@RequestHeader("X-Sharer-User-Id")  Long userId,
                                 @PathVariable @NotNull Long itemId) {
        log.info("Add item");
        itemService.addItemToCart(userId, itemId);
    }

    //добавление отзыва к товару пользователем
    @PostMapping("{/{itemId}/feedbacks")
    public void addFeedbackByUser(@RequestHeader("X-Sharer-User-Id") Long userId,
                                        @PathVariable @NotNull Long itemId,
                                        @RequestBody @Valid FeedbackDto feedbackDto) {
        log.info("User with ID = {} add a feedback for item with ID = {}", userId, itemId);
        itemService.addFeedbackByUser(userId, itemId, feedbackDto);
    }

    //добавление оценки пользователем
    @PostMapping("/{itemId}/ratings")
    public void addRating(@RequestHeader("X-Sharer-User-Id")  Long userId,
                            @PathVariable @NotNull Long itemId, Rating rating) {
        log.info("User with ID = {} add a rating for item with ID = {}", userId, itemId);
        itemService.addRatingByUser(userId, itemId, rating);
    }

    //создание пользователем запроса на добавление вещи
    @PostMapping("/requests")
    public void createRequest(@RequestHeader("X-Sharer-User-Id")  Long userId, @RequestParam long organisationId,
                              @RequestBody ItemRequestDto itemRequestDto) {
        log.info("User with ID = {} created a request", userId);
        itemService.createRequest(userId, organisationId, itemRequestDto);
    }
}
