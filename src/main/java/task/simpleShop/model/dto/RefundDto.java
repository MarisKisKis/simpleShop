package task.simpleShop.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import task.simpleShop.model.Item;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Jacksonized
public class RefundDto {
    private Item item;
    private String reason;
    private LocalDateTime created;
}
