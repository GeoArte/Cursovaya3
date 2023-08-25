package ru.skypro.lessons.springboot.cursovaya3.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import ru.skypro.lessons.springboot.cursovaya3.Lot;

import java.time.LocalDateTime;

public class BidDTO {
    private Long id;
    private Lot lot;
    private double bidAmount;
    private LocalDateTime timestamp;
    private String bidderName;

    public Long getId() {
        return id;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
