package ru.skypro.lessons.springboot.cursovaya3;

import ru.skypro.lessons.springboot.cursovaya3.DTO.CreateLotDto;
import ru.skypro.lessons.springboot.cursovaya3.DTO.LotDTO;

import java.util.List;

public interface AuctionService {
    Lot createLot(CreateLotDto lot);
    Bid placeBid(Long lotId, Bid bid);
    void startBidding(Long lotId);
    void finishBidding(Long lotId);
    List<Lot> getAllLots();
    Lot getLotById(Long lotId);
    List<Lot> getLotsByStatus(String status);
    List<Bid> getAllBids();
    Bid getBidById(Long bidId);
    String getNameMaxBid(Long lotId);
    String getFirstBidder(Long lotId);
    String exportLots();
}
