package task.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.simpleShop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
