package task.simpleShop.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private String title;

    private LocalDateTime date;

    private String text;

    private User user;

}
