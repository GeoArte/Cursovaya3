package ru.skypro.lessons.springboot.cursovaya3;

public class LotDTO {
    private Long id;
    private String name;
    private double startPrice;
    private int bidCount;
    private double currentPrice;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public int getBidCount() {
        return bidCount;
    }

    public void setBidCount(int bidCount) {
        this.bidCount = bidCount;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
