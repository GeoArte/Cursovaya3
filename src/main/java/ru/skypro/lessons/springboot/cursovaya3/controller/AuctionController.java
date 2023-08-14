package ru.skypro.lessons.springboot.cursovaya3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.cursovaya3.AuctionService;
import ru.skypro.lessons.springboot.cursovaya3.Bid;
import ru.skypro.lessons.springboot.cursovaya3.DTO.LotDTO;
import ru.skypro.lessons.springboot.cursovaya3.Lot;
import ru.skypro.lessons.springboot.cursovaya3.LotRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionService auctionService;
    private final LotRepository lotRepository;

    public AuctionController(AuctionService auctionService, LotRepository lotRepository) {
        this.auctionService = auctionService;
        this.lotRepository = lotRepository;
    }

    @PostMapping("/lot")
    public ResponseEntity<LotDTO> createLot(@RequestBody Lot lot) {
        Lot createdLot = auctionService.createLot(lot);
        LotDTO lotDTO = convertToDTO(createdLot);
        return ResponseEntity.ok(lotDTO);
    }

    @PostMapping("/lots/{lotId}/bid")
    public ResponseEntity<Bid> placeBid(@PathVariable Long lotId, @RequestBody Bid bid) {
        Bid placedBid = auctionService.placeBid(lotId, bid);
        return ResponseEntity.ok(placedBid);
    }

    @PutMapping("/lots/{lotId}/start")
    public ResponseEntity<Void> startBidding(@PathVariable Long lotId) {
        auctionService.startBidding(lotId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/lots/{lotId}/stop")
    public ResponseEntity<Void> finishBidding(@PathVariable Long lotId) {
        auctionService.finishBidding(lotId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lot")
    public ResponseEntity<List<LotDTO>> getAllLots() {
        List<Lot> lots = auctionService.getAllLots();
        List<LotDTO> lotDTOs = lots.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lotDTOs);
    }

    @GetMapping("/lots/{id}")
    public ResponseEntity<LotDTO> getLotById(@PathVariable Long id) {
        Lot lot = auctionService.getLotById(id);
        LotDTO lotDTO = convertToDTO(lot);
        return ResponseEntity.ok(lotDTO);
    }

    @GetMapping("/lots/findByStatus")
    public ResponseEntity<List<LotDTO>> getLotsByStatus(@RequestParam String status) {
        List<Lot> lots = auctionService.getLotsByStatus(status);
        List<LotDTO> lotDTOs = lots.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lotDTOs);
    }

    @GetMapping("/bids")
    public ResponseEntity<List<Bid>> getAllBids() {
        List<Bid> bids = auctionService.getAllBids();
        return ResponseEntity.ok(bids);
    }

    @GetMapping("/bids/{bidId}")
    public ResponseEntity<Bid> getBidById(@PathVariable Long bidId) {
        Bid bid = auctionService.getBidById(bidId);
        return ResponseEntity.ok(bid);
    }

    @PostMapping("/lots/{id}/frequent")
    public ResponseEntity<String> getNameMaxBid(@PathVariable Long id) {
        String name = auctionService.getNameMaxBid(id);
        return ResponseEntity.ok(name);
    }

    @PostMapping("/lots/{id}/first")
    public ResponseEntity<String> getFirstBidder(@PathVariable Long id) {
        String name = auctionService.getFirstBidder(id);
        return ResponseEntity.ok(name);
    }

    @PostMapping("/lot/export")
    public ResponseEntity<String> exportLots() {
        String message = auctionService.exportLots();
        return ResponseEntity.ok(message);
    }

    private LotDTO convertToDTO(Lot lot) {
        LotDTO dto = new LotDTO();
        dto.setId(lot.getId());
        dto.setTitle(lot.getTitle());
        dto.setStartPrice(lot.getStartPrice());
        dto.setCurrentPrice(lot.getCurrentPrice());
        dto.setStatus(lot.getStatus());
        dto.setBidCount(lotRepository.countBidsByLotId(lot.getId()));
        return dto;
    }
}
