package task.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.simpleShop.model.ItemRequest;

import java.util.List;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {
    List<ItemRequest> findItemRequestByStatusWaiting();
}
