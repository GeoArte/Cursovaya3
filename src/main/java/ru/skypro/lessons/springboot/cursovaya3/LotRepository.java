package ru.skypro.lessons.springboot.cursovaya3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LotRepository extends JpaRepository<Lot, Long> {
    List<Lot> findByStatus(String status);

    List<Lot> findByCurrentPriceGreaterThan(double minCurrentPrice);

    List<Lot> findByCurrentPriceBetween(double minCurrentPrice, double maxCurrentPrice);

    List<Lot> findByNameContainingIgnoreCase(String keyword);

    @Query(value = "SELECT COUNT(*) FROM Bid WHERE lot_id = :lotId", nativeQuery = true)
    int countBidsByLotId(@Param("lotId") Long lotId);
}
