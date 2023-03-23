package task.simpleShop.service.mapper;

import task.simpleShop.model.Refund;
import task.simpleShop.model.User;
import task.simpleShop.model.dto.RefundDto;

import java.time.LocalDateTime;

public class RefundMapper {

    public static Refund toRefund (RefundDto refundDto, User user) {
        return Refund.builder()
                .user(user)
                .item(refundDto.getItem())
                .reason(refundDto.getReason())
                .created(refundDto.getCreated())
                .build();
    }
}
