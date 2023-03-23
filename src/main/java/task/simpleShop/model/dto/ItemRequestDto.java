package task.simpleShop.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import task.simpleShop.model.Item;

@Getter
@Setter
@Builder
@Jacksonized
public class ItemRequestDto {

    private Item item;

    private long userId;

    private long organisationId;

}
