package ru.skypro.lessons.springboot.cursovaya3;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuctionServiceImpl implements AuctionService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;

    public AuctionServiceImpl(LotRepository lotRepository, BidRepository bidRepository) {
        this.lotRepository = lotRepository;
        this.bidRepository = bidRepository;
    }

    @Override
    public LotDTO createLot(LotDTO lotDTO) {
        Lot lot = new Lot();
        lot.setName(lotDTO.getName());
        lot.setStartPrice(lotDTO.getStartPrice());

        Lot savedLot = lotRepository.save(lot);

        return mapToLotDTO(savedLot);
    }

    @Override
    public LotDTO placeBid(Long lotId, double bidAmount) {
        Lot lot = lotRepository.findById(lotId)
                .orElseThrow(() -> {
                    return new NotFoundException("Lot not found with id: " + lotId);
                });

        Bid bid = new Bid();
        bid.setLot(lot);
        bid.setBidAmount(bidAmount);

        bidRepository.save(bid);

        List<Bid> bids = bidRepository.findByLot(lot);
        double currentPrice = bids.stream().mapToDouble(Bid::getBidAmount).sum() + lot.getStartPrice();
        lot.setCurrentPrice(currentPrice);
        lotRepository.save(lot);

        return mapToLotDTO(lot);
    }

    @Override
    public List<LotDTO> getAllLots() {
        List<Lot> lots = lotRepository.findAll();
        return lots.stream().map(this::mapToLotDTO).collect(Collectors.toList());
    }

    @Override
    public List<LotDTO> getLotsWithBids() {
        List<Lot> lotsWithBids = lotRepository.findAll().stream()
                .filter(lot -> !bidRepository.findByLot(lot).isEmpty())
                .collect(Collectors.toList());
        return lotsWithBids.stream().map(this::mapToLotDTO).collect(Collectors.toList());
    }
    private LotDTO mapToLotDTO(Lot lot) {
        LotDTO lotDTO = new LotDTO();
        lotDTO.setId(lot.getId());
        lotDTO.setName(lot.getName());
        lotDTO.setStartPrice(lot.getStartPrice());
        lotDTO.setBidCount(bidRepository.findByLot(lot).size());
        lotDTO.setCurrentPrice(lot.getCurrentPrice());
        return lotDTO;
    }
}

