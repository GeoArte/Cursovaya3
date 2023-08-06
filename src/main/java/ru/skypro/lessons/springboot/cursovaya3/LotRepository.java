package ru.skypro.lessons.springboot.cursovaya3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LotRepository extends JpaRepository<Lot, Long> {
    List<Lot> findAll();

    // Метод для получения лотов по статусу
    List<Lot> findAllByStatus(String status);

    // Метод для получения лота по ID
    Lot findById(long id);

    @Query(value = "SELECT COUNT(*) FROM Bid WHERE lot_id = :lotId", nativeQuery = true)
    int countBidsByLotId(@Param("lotId") Long lotId);
}
