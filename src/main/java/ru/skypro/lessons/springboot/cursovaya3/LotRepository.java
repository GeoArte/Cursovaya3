package ru.skypro.lessons.springboot.cursovaya3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {
    List<Lot> findAll();

    List<Lot> findAllByStatus(String status);

    Lot findById(long id);

    @Query(value = "SELECT COUNT(*) FROM bid WHERE lot_id = :lotId", nativeQuery = true)
    int countBidsByLotId(@Param("lotId") Long lotId);
}
