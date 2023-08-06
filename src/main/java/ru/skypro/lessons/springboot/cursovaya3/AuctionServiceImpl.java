package ru.skypro.lessons.springboot.cursovaya3;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
        lot.setStatus("Создан");

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
        if (!"Создан".equals(lot.getStatus())) {
            throw new IllegalStateException("Торги можно начать только для лота со статусом \"Создан\".");
        }

        // Устанавливаем статус "Запущены торги"
        lot.setStatus("Запущены торги");
    }

    @Override
    public void finishBidding(Long lotId) {
        // Получаем лот по ID
        Lot lot = getLotById(lotId);

        // Проверяем, что лот находится в статусе "Запущены торги"
        if (!"Запущены торги".equals(lot.getStatus())) {
            throw new IllegalStateException("Торги можно завершить только для лота со статусом \"Запущены торги\".");
        }

        // Устанавливаем статус "Торги окончены"
        lot.setStatus("Торги окончены");
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
}

