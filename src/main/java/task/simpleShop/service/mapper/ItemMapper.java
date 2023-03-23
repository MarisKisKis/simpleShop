package task.simpleShop.service.mapper;

import task.simpleShop.model.Discount;
import task.simpleShop.model.Item;
import task.simpleShop.model.Organisation;
import task.simpleShop.model.dto.ItemDto;

public class ItemMapper {

    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .amount(item.getAmount())
                .keywords(item.getKeywords())
                .characteristics(item.getCharacteristics())
                .rating(item.getRating())
                .feedbacks(item.getFeedbacks())
                .discountDto(ItemDto.DiscountShortDto.builder()
                        .value(item.getDiscount().getValue())
                        .build())
                .organisationDto(ItemDto.OrganisationShortDto.builder()
                        .name(item.getOrganisation().getName())
                        .build())
                .build();
    }

    public static Item toItem(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .price(itemDto.getPrice())
                .amount(itemDto.getAmount())
                .keywords(itemDto.getKeywords())
                .characteristics(itemDto.getCharacteristics())
                .rating(itemDto.getRating())
                .feedbacks(itemDto.getFeedbacks())
                .discount(Discount.builder()
                        .value(itemDto.getDiscountDto().getValue())
                .build())
                .organisation(Organisation.builder()
                        .name(itemDto.getOrganisationDto().getName())
                        .build())
                .build();
    }
}
