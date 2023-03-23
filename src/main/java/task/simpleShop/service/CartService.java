package task.simpleShop.service;

import task.simpleShop.model.dto.ItemDto;
import task.simpleShop.model.dto.RefundDto;

import java.util.List;

public interface CartService {

    List<ItemDto> getUserHistory(long userId);

    void makeOrder(Long userId);

    void makeRefund(Long userId, RefundDto refundDto);
}
