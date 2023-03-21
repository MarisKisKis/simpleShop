package task.simpleShop.service;

import task.simpleShop.model.Rating;
import task.simpleShop.model.dto.FeedbackDto;
import task.simpleShop.model.dto.ItemDto;

public interface ItemService {

    ItemDto addItemToCart(Long userId, long itemId);

    FeedbackDto addCommentByUser(Long userId, Long itemId, FeedbackDto feedbackDto);

    Rating addRatingByUser(Long userId, Long itemId, Rating rating);
}
