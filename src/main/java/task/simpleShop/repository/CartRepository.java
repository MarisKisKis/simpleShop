package task.simpleShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.simpleShop.model.Cart;

import java.util.List;


public interface CartRepository extends JpaRepository <Cart, Long> {

    Cart findCartByUser_IdAndCartStatus_Current(long userId);

    List<Cart> findCartByUser_IdAndCartStatus_Old(long userId);
}
