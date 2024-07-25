package br.com.fiap.totem_express.infrastructure.order;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;

@Repository
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
