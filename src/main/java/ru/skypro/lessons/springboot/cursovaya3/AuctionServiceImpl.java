package ru.skypro.lessons.springboot.cursovaya3;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.skypro.lessons.springboot.cursovaya3.LotStatus.*;

@Service
public class AuctionServiceImpl implements AuctionService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;

    public AuctionServiceImpl(LotRepository lotRepository, BidRepository bidRepository) {
        this.lotRepository = lotRepository;
        this.bidRepository = bidRepository;
    }

    @Override
    public Lot createLot(Lot lot) {
        // Проверяем, что стартовая цена не отрицательная
        if (lot.getStartPrice() < 0) {
            throw new IllegalArgumentException("Стартовая цена должна быть положительной.");
        }

        // Устанавливаем статус "Создан" для нового лота
        lot.setStatus(CREATED);

        return lotRepository.save(lot);
    }

    @Override
    public Bid placeBid(Long lotId, Bid bid) {
        // Получаем лот по ID
        Lot lot = getLotById(lotId);

        // Проверяем, что ставка больше текущей цены лота
        Double currentPrice = lot.getCurrentPrice();
        if (currentPrice != null && bid.getBidAmount() <= 0) {
            throw new IllegalArgumentException("Ставка должна быть выше текущей цены лота.");
        }

        // Устанавливаем лот для ставки
        bid.setLot(lot);

        // Сохраняем ставку и обновляем текущую цену лота
        bidRepository.save(bid);
        lot.setCurrentPrice(bid.getBidAmount());

        return bid;
    }

    @Override
    public void startBidding(Long lotId) {
        // Получаем лот по ID
        Lot lot = getLotById(lotId);

        // Проверяем, что лот находится в статусе "Создан"
        if (!CREATED.equals(lot.getStatus())) {
            throw new IllegalStateException("Торги можно начать только для лота со статусом \"Создан\".");
        }

        // Устанавливаем статус "Запущены торги"
        lot.setStatus(STARTED);
    }

    @Override
    public void finishBidding(Long lotId) {
        // Получаем лот по ID
        Lot lot = getLotById(lotId);

        // Проверяем, что лот находится в статусе "Запущены торги"
        if (!STARTED.equals(lot.getStatus())) {
            throw new IllegalStateException("Торги можно завершить только для лота со статусом \"Запущены торги\".");
        }

        // Устанавливаем статус "Торги окончены"
        lot.setStatus(STOPPED);
    }

    @Override
    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    @Override
    public Lot getLotById(Long lotId) {
        return lotRepository.findById(lotId)
                .orElseThrow(() -> new IllegalArgumentException("Лот с ID " + lotId + " не найден."));
    }

    @Override
    public List<Lot> getLotsByStatus(String status) {
        return lotRepository.findAllByStatus(status);
    }

    @Override
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public Bid getBidById(Long bidId) {
        return bidRepository.findById(bidId)
                .orElseThrow(() -> new IllegalArgumentException("Ставка с ID " + bidId + " не найдена."));
    }
    @Override
    public String getNameMaxBid(Long lotId){
        List<Bid> Bids = lotRepository.findById(lotId).get().getBids();
        Map<String, Integer> nameCountMap = new HashMap<>();
        for (Bid bid : Bids){
            String name = bid.getBidderName();
            nameCountMap.put(name, nameCountMap.getOrDefault(name, 0) + 1);
        }
        String Bidder = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : nameCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                Bidder = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return Bidder;
    }
    @Override
    public String getFirstBidder(Long lotId){
        List<Bid> Bids = lotRepository.findById(lotId).get().getBids();
        String name = "Ставок ещё не было";
        LocalDateTime date = LocalDateTime.MAX;
        for (Bid bid : Bids){
            if(bid.getTimestamp().isBefore(date)){
                date = bid.getTimestamp();
                name = bid.getBidderName();
            }
        }
        return name;
    }
    @Override
    public String exportLots(){
        List<Lot> lots = lotRepository.findAll();
        String csvFileName = "lots_export.csv";
        LotCSVExporter.exportLotsToCSV(lots, csvFileName);
        return "Все лоты экспортированны";
    }
}

