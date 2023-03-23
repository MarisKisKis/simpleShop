package task.simpleShop.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Jacksonized
public class NotificationDto {
    private String title;
    private LocalDateTime date;
    private String text;
}
