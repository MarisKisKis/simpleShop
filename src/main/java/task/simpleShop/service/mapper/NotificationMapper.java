package task.simpleShop.service.mapper;

import task.simpleShop.model.Notification;
import task.simpleShop.model.User;
import task.simpleShop.model.dto.NotificationDto;

public class NotificationMapper {

    public static NotificationDto toNotificationDto(Notification notification) {
        return NotificationDto.builder()
                .title(notification.getTitle())
                .date(notification.getDate())
                .text(notification.getText())
                .build();
    }

    public static Notification toNotification(NotificationDto notificationDto, User user) {
        return Notification.builder()
                .title(notificationDto.getTitle())
                .date(notificationDto.getDate())
                .text(notificationDto.getText())
                .user(user)
                .build();
    }
}
