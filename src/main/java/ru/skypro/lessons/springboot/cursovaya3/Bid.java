package ru.skypro.lessons.springboot.cursovaya3;

import jakarta.persistence.*;

@Entity
@Table(name = "bid")public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Lot lot;

    @Column(name = "bidAmount")
    private double bidAmount;

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
}
