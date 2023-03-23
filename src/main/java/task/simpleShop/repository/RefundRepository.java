package task.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.simpleShop.model.Refund;

public interface RefundRepository extends JpaRepository<Refund, Long> {
}
