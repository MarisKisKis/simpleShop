package task.simpleShop.service.mapper;

import task.simpleShop.model.Discount;
import task.simpleShop.model.dto.DiscountDto;

public class DiscountMapper {

    public static Discount toDiscount(DiscountDto discountDto) {
        return Discount.builder()
                .value(discountDto.getValue())
                .start(discountDto.getStart())
                .end(discountDto.getEnd())
                .items(discountDto.getItems())
                .build();
    }
}
