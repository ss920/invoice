package com.systena.invoice.repository;

import com.systena.invoice.entity.OrderEntity;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface OrderRepository.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    /**
     * Find by client no is and create datetime between.
     *
     * @param clientNo the client no
     * @param findStartTime the find start time
     * @param findEndTime the find end time
     * @return the list
     */
    List<OrderEntity> findByClientNoIsAndCreateDatetimeBetween(
            final int clientNo, final Timestamp findStartTime, final Timestamp findEndTime);
}
