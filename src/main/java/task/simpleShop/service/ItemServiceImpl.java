package task.simpleShop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.simpleShop.model.Cart;
import task.simpleShop.model.Item;
import task.simpleShop.model.Rating;
import task.simpleShop.model.dto.FeedbackDto;
import task.simpleShop.model.dto.ItemDto;
import task.simpleShop.repository.CartRepository;
import task.simpleShop.repository.ItemRepository;

import java.util.Optional;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final CartRepository cartRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void addItemToCart(Long userId, long itemId) {
        Cart cart = cartRepository.findCartByUser_IdAndCartStatus_New(userId);
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        Item item = itemOptional.get();
        cart.getItems().add(item);
    }

    @Override
    public FeedbackDto addFeedbackByUser(Long userId, Long itemId, FeedbackDto feedbackDto) {
        return null;
    }

    @Override
    public Rating addRatingByUser(Long userId, Long itemId, Rating rating) {
        return null;
    }
}
