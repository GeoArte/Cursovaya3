package ru.skypro.lessons.springboot.cursovaya3.DTO;

import java.time.LocalDateTime;

public class CreateBidDTO {
    private double bidAmount;
    private String bidderName;

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }
}
