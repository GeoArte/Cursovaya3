package ru.skypro.lessons.springboot.cursovaya3;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    // Запрос для получения всех ставок по определенному лоту
    List<Bid> findByLot(Lot lot);

    // Запрос для получения всех ставок сумма которых больше указанной
    List<Bid> findByBidAmountGreaterThan(double minBidAmount);

    // Запрос для получения всех ставок сумма которых в диапазоне
    List<Bid> findByBidAmountBetween(double minBidAmount, double maxBidAmount);
}
