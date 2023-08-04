package ru.skypro.lessons.springboot.cursovaya3;

import jakarta.persistence.*;

@Entity
@Table(name = "lot")public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "startPrice")
    private double startPrice;
    @Column(name = "currentPrice")
    private double currentPrice;
    @Column(name = "status")
    private String status;

    public double getStartPrice() {
        return startPrice;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
