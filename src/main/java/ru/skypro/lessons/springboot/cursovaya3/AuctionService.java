package ru.skypro.lessons.springboot.cursovaya3;

import java.util.List;

public interface AuctionService {
    LotDTO createLot(LotDTO lotDTO);
    LotDTO placeBid(Long lotId, double bidAmount);
    List<LotDTO> getAllLots();
    List<LotDTO> getLotsWithBids();
}
