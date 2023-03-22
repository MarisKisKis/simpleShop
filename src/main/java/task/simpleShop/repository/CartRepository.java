package task.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.simpleShop.model.Cart;



public interface CartRepository extends JpaRepository <Cart, Long> {

    Cart findCartByUser_IdAndCartStatus_New(long userId);
}
