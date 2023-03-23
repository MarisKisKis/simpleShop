package task.simpleShop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.simpleShop.exception.NotFoundException;
import task.simpleShop.model.*;
import task.simpleShop.model.dto.FeedbackDto;
import task.simpleShop.repository.CartRepository;
import task.simpleShop.repository.FeedbackRepository;
import task.simpleShop.repository.ItemRepository;
import task.simpleShop.repository.UserRepository;
import task.simpleShop.service.mapper.FeedbackMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static task.simpleShop.model.CartStatus.CURRENT;


@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CartRepository cartRepository, UserRepository userRepository, FeedbackRepository feedbackRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.feedbackRepository = feedbackRepository;
    }

    //добавление товара в корзину пользователя
    @Override
    public void addItemToCart(Long userId, long itemId) {
        Item item = getItem(itemId);
        User user = getUser(userId);
        Cart cart = cartRepository.findCartByUser_IdAndCartStatus_Current(userId);
        if(cart != null) {
            cart.getItems().add(item);
        }
        else {
            List<Item> items = new ArrayList<>();
            items.add(item);
            cart = new Cart(user, items, CURRENT);
            cartRepository.save(cart);
        }
    }

    //добавление отзыва к товару
    @Override
    public void addFeedbackByUser(Long userId, Long itemId, FeedbackDto feedbackDto) {
        Item item = getItem(itemId);
        User user = getUser(userId);
        if(checkItemInUserHistory(userId, itemId) == false) {
            throw new NotFoundException(String.format("Item in cart history for User with ID %s not found", userId));
        }
        Feedback feedback = FeedbackMapper.toFeedback(feedbackDto, item, user);
        feedback.setCreated(LocalDateTime.now());
        feedbackRepository.save(feedback);
    }

    //добавление оценки товару
    @Override
    public void addRatingByUser(Long userId, Long itemId, Rating rating) {
        Item item = getItem(itemId);
        User user = getUser(userId);
        if(checkItemInUserHistory(userId, itemId) == false) {
            throw new NotFoundException(String.format("Item in cart history for User with ID %s not found", userId));
        }
        item.setRating(rating);
    }

    // приватный метод для быстрого получения товара из репозитория
    private Item getItem(long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isEmpty()) {
            throw new NotFoundException(String.format("Item with ID %s not found", itemId));
        }
        Item item = itemOptional.get();
        return item;
    }

    //приватный метод для быстрого получения пользователя из репозитория
    private User getUser(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()){
            throw new NotFoundException(String.format("User with ID %s not found", userId));
        }
        User user = userOptional.get();
        return user;
    }

    //проверка, был ли куплен товар
    private Boolean checkItemInUserHistory (long itemId, long userId) {
        Item item = getItem(itemId);
        Boolean isInHistory = false;
        if(cartRepository.findCartByUser_IdAndCartStatus_Old(userId)!= null) {
            List<List<Item>> cartItemsList = new ArrayList<>();
            List<Cart> carts = cartRepository.findCartByUser_IdAndCartStatus_Old(userId);
            for (Cart cart : carts) {
                cartItemsList.add(cart.getItems());
            }
            for (int i =0; i>cartItemsList.size(); i++) {
                for (Item item1 : cartItemsList.get(i)) {
                    if (item1==item) {
                        isInHistory = true;
                    }
                }
            }
        } else {
            throw new NotFoundException(String.format("Cart history for User with ID %s not found", userId));
        }
        return isInHistory;
    }
}
