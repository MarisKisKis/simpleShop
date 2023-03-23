package task.simpleShop.service.mapper;

import task.simpleShop.model.ItemRequest;
import task.simpleShop.model.Organisation;
import task.simpleShop.model.User;
import task.simpleShop.model.dto.ItemRequestDto;

public class ItemRequestMapper {

    public static ItemRequest toItemRequest(ItemRequestDto itemRequestDto, User user, Organisation organisation) {
        return ItemRequest.builder()
                .user(user)
                .organisation(organisation)
                .item(itemRequestDto.getItem())
                .build();
    }
}
