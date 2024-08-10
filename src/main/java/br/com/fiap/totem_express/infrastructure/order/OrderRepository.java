package br.com.fiap.totem_express.infrastructure.order;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT order FROM orders order")
    @EntityGraph(attributePaths = { "items", "user", "items.product" })
    List<OrderEntity> findAllWithDeleteProducts();
}