package task.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.simpleShop.model.Notification;
import task.simpleShop.model.User;

import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findNotificationByUser(User user);
}
