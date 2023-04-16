package io.mars.cereal.data.cart;

import io.mars.cereal.model.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    @Query(value = "SELECT cart FROM Cart cart WHERE cart.totalPrice>=:minimum AND cart.totalPrice<=:maximum")
    List<Cart> findCartsWithPriceBetween(Double minimum, Double maximum);

}
