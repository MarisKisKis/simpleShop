package task.simpleShop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.simpleShop.model.Rating;
import task.simpleShop.model.dto.FeedbackDto;
import task.simpleShop.model.dto.ItemDto;
import task.simpleShop.repository.ItemRepository;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDto addItemToCart(Long userId, long itemId) {
        return null;
    }

    @Override
    public FeedbackDto addCommentByUser(Long userId, Long itemId, FeedbackDto feedbackDto) {
        return null;
    }

    @Override
    public Rating addRatingByUser(Long userId, Long itemId, Rating rating) {
        return null;
    }
}
