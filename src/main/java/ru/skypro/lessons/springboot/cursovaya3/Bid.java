package ru.skypro.lessons.springboot.cursovaya3;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bid")public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Lot lot;
    @Column(name = "bidamount")
    private double bidAmount;
    @Column(nullable = false)
    private LocalDateTime timestamp;
    @Column(nullable = false)
    private String bidderName;

    public Long getId() {
        return id;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public Lot getLot() {
        return lot;
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
}
