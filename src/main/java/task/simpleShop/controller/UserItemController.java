package task.simpleShop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.simpleShop.model.Rating;
import task.simpleShop.model.dto.FeedbackDto;
import task.simpleShop.model.dto.ItemDto;
import task.simpleShop.service.ItemService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Valid
@RestController
@RequestMapping("/items/{userId}/{itemId}")
public class UserItemController {

    private final ItemService itemService;

    @Autowired
    public UserItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ItemDto addItemToCart(@PathVariable @NotNull Long userId,
                                 @PathVariable @NotNull Long itemId) {
        log.info("Add item");
        return itemService.addItemToCart(userId, itemId);
    }

    @PostMapping("{/feedbacks")
    public FeedbackDto addCommentByUser(@PathVariable @NotNull Long userId,
                                        @PathVariable @NotNull Long itemId,
                                        @RequestBody @Valid FeedbackDto feedbackDto) {
        log.info("User with ID = {} add a feedback for item with ID = {}", userId, itemId);
        return itemService.addCommentByUser(userId, itemId, feedbackDto);
    }

    @PostMapping("/ratings")
    public Rating addRating(@PathVariable @NotNull Long userId,
                            @PathVariable @NotNull Long itemId, Rating rating) {
        log.info("User with ID = {} add a rating for item with ID = {}", userId, itemId);
        return itemService.addRatingByUser(userId, itemId, rating);
    }
}
