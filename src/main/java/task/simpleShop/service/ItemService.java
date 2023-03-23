package task.simpleShop.service;

import task.simpleShop.model.Rating;
import task.simpleShop.model.dto.DiscountDto;
import task.simpleShop.model.dto.FeedbackDto;
import task.simpleShop.model.dto.ItemDto;
import task.simpleShop.model.dto.ItemRequestDto;

public interface ItemService {

    void addItemToCart(Long userId, long itemId);

    void addFeedbackByUser(Long userId, Long itemId, FeedbackDto feedbackDto);

    void addRatingByUser(Long userId, Long itemId, Rating rating);

    void updateItem(long itemId, ItemDto itemDto);

    void createDiscount(DiscountDto discountDto);

    void createRequest(Long userId, Long organisationId, ItemRequestDto itemRequestDto);

    void createItems();
}
