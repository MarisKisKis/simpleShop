package task.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.simpleShop.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
