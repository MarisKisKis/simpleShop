package task.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.simpleShop.model.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {
}
