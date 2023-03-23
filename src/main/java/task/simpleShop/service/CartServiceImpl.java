package task.simpleShop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.simpleShop.exception.NotFoundException;
import task.simpleShop.model.Cart;
import task.simpleShop.model.CartStatus;
import task.simpleShop.model.Item;
import task.simpleShop.model.User;
import task.simpleShop.model.dto.ItemDto;
import task.simpleShop.model.dto.RefundDto;
import task.simpleShop.repository.CartRepository;
import task.simpleShop.repository.RefundRepository;
import task.simpleShop.repository.UserRepository;
import task.simpleShop.service.mapper.ItemMapper;
import task.simpleShop.service.mapper.RefundMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final RefundRepository refundRepository;

    private final UserRepository userRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, RefundRepository refundRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.refundRepository = refundRepository;
        this.userRepository = userRepository;
    }

    //получение списка купленных товаров
    @Override
    public List<ItemDto> getUserHistory(long userId) {
        List<List<Item>> cartItemsList = new ArrayList<>();
        List<ItemDto> items = new ArrayList<>();
        List<Cart> carts = cartRepository.findCartByUser_IdAndCartStatus_Old(userId);
        for (Cart cart : carts) {
            cartItemsList.add(cart.getItems());
        }
        for (int i =0; i>cartItemsList.size(); i++) {
            for (Item item1 : cartItemsList.get(i)) {
                items.add(ItemMapper.toItemDto(item1));
            }
        }
        return items;
    }

    // TO DO: доработка архитектуры для системы заказов (оформление, оплата, отмена)
    @Override
    public void makeOrder(Long userId) {
        Cart cart = cartRepository.findCartByUser_IdAndCartStatus_Current(userId);
        if (cart==null) {
            throw new NotFoundException(String.format("No items in cart for User with ID %s found", userId));
        }
        cart.setCartStatus(CartStatus.OLD);
        cart.setOrderMade(LocalDateTime.now());
    }

    @Override
    public void makeRefund(Long userId, RefundDto refundDto) {
        Item item = refundDto.getItem();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new NotFoundException(String.format("User with ID %s not found", userId));
        }
        User user = optionalUser.get();
        if(checkItemInUserHistory(item, userId) == false) {
            throw new NotFoundException(String.format("Item in cart history for User with ID %s not found", userId));
        }
        if(validateRefundPeriod(item, user) == false) {
            throw new RuntimeException("Time limit to refund exceeded");
        }
        refundRepository.save(RefundMapper.toRefund(refundDto, user));
    }

    private Boolean checkItemInUserHistory (Item item, long userId) {
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

    //костыль для верификации срока совершения заказа. При наличии проработанной модели заказа верификация делается
    // проще, по дате заказа
    private Boolean validateRefundPeriod(Item item, User user) {
        Boolean isValid = false;
        List<Cart> cartListsItems = cartRepository.findCartByUser_IdAndCartStatus_Old(user.getId());
        List<Item> items = new ArrayList<>();
        for (Cart cart : cartListsItems) {
            if (cart.getOrderMade().plusHours(24).isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Time limit to refund exceeded");
            } else {
                for (Item item1 : cart.getItems()){
                    if (item1 == item) {
                        if (cart.getOrderMade().plusHours(24).isAfter(LocalDateTime.now())) {
                            isValid = true;
                        }
                    }
                }
            }
        }
        return isValid;
    }
}
