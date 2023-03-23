package task.simpleShop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.simpleShop.exception.NotFoundException;
import task.simpleShop.model.*;
import task.simpleShop.model.dto.DiscountDto;
import task.simpleShop.model.dto.FeedbackDto;
import task.simpleShop.model.dto.ItemDto;
import task.simpleShop.model.dto.ItemRequestDto;
import task.simpleShop.repository.*;
import task.simpleShop.service.mapper.DiscountMapper;
import task.simpleShop.service.mapper.FeedbackMapper;
import task.simpleShop.service.mapper.ItemRequestMapper;

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
    
    private final DiscountRepository discountRepository;

    private final OrganisationRepository organisationRepository;

    private final ItemRequestRepository itemRequestRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CartRepository cartRepository, UserRepository userRepository, FeedbackRepository feedbackRepository, DiscountRepository discountRepository, OrganisationRepository organisationRepository, ItemRequestRepository itemRequestRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.feedbackRepository = feedbackRepository;
        this.discountRepository = discountRepository;
        this.organisationRepository = organisationRepository;
        this.itemRequestRepository = itemRequestRepository;
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

    @Override
    public void updateItem(long itemId, ItemDto itemDto) {
        Item item = getItem(itemId);
        if (itemDto.getName()!= null) {
            item.setName(itemDto.getName());
        } else if (itemDto.getDescription()!= null) {
            item.setDescription(itemDto.getDescription());
        } else if (itemDto.getPrice()!=0) {
            item.setPrice(itemDto.getPrice());
        } else if (itemDto.getAmount()!=0) {
            item.setAmount(itemDto.getAmount());
        } else if (itemDto.getKeywords()!=null) {
            item.setKeywords(itemDto.getKeywords());
        } else if (itemDto.getCharacteristics()!=null) {
            item.setCharacteristics(itemDto.getCharacteristics());
        } else if (itemDto.getRating()!=null) {
            item.setRating(itemDto.getRating());
        } else if (itemDto.getFeedbacks()!=null) {
            item.setFeedbacks(itemDto.getFeedbacks());
        } else if (itemDto.getDiscountDto()!=null) {
            Optional<Discount> discountOpt = discountRepository.findById(itemDto.getDiscountDto().getId());
            Discount discount = discountOpt.get();
            item.setDiscount(discount);
        } else if (itemDto.getOrganisationDto()!=null) {
            Optional<Organisation> optionalOrganisation = organisationRepository.findById(itemDto.getOrganisationDto().getId());
            Organisation organisation = optionalOrganisation.get();
            item.setOrganisation(organisation);
        } else {
            throw new NotFoundException(String.format("Item with ID %s not found", itemId));
        }
    }

    @Override
    public void createDiscount(DiscountDto discountDto) {
        discountRepository.save(DiscountMapper.toDiscount(discountDto));
    }

    @Override
    public void createRequest(Long userId, Long organisationId, ItemRequestDto itemRequestDto) {
        User user = getUser(userId);
        Optional<Organisation> optionalOrganisation = organisationRepository.findById(organisationId);
        Organisation organisation = optionalOrganisation.get();
        ItemRequest itemRequest = ItemRequestMapper.toItemRequest(itemRequestDto, user, organisation);
        itemRequest.setStatus(RequestStatus.WAITING);
        itemRequestRepository.save(itemRequest);
    }

    //проверка запросов и публикация товаров
    @Override
    public void createItems() {
        List<ItemRequest> waitingRequests = itemRequestRepository.findItemRequestByStatusWaiting();
        List<Item> items = new ArrayList<>();
        for (ItemRequest itemRequest: waitingRequests) {
            if (itemRequest.getOrganisation()!=null) {
                items.add(itemRequest.getItem());
            } else {
                throw new NotFoundException(String.format("There must be information about organisation"));
            }
        }
        for (Item item : items) {
            itemRepository.save(item);
        }

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
