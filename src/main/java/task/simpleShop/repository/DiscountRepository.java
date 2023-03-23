package task.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.simpleShop.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
