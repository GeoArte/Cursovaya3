package ru.skypro.lessons.springboot.cursovaya3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.cursovaya3.AuctionService;
import ru.skypro.lessons.springboot.cursovaya3.Bid;
import ru.skypro.lessons.springboot.cursovaya3.Lot;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping("/lot")
    public ResponseEntity<Lot> createLot(@RequestBody Lot lot) {
        Lot createdLot = auctionService.createLot(lot);
        return ResponseEntity.ok(createdLot);
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
    public ResponseEntity<List<Lot>> getAllLots() {
        List<Lot> lots = auctionService.getAllLots();
        return ResponseEntity.ok(lots);
    }

    @GetMapping("/lots/{id}")
    public ResponseEntity<Lot> getLotById(@PathVariable Long lotId) {
        Lot lot = auctionService.getLotById(lotId);
        return ResponseEntity.ok(lot);
    }

    @GetMapping("/lots/findByStatus")
    public ResponseEntity<List<Lot>> getLotsByStatus(@RequestParam String status) {
        List<Lot> lots = auctionService.getLotsByStatus(status);
        return ResponseEntity.ok(lots);
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
    @PostMapping("/lot/{id}/frequent")
    public ResponseEntity<String> getNameMaxBid(@RequestBody Long lotId) {
        String name = auctionService.getNameMaxBid(lotId);
        return ResponseEntity.ok(name);
    }
    @PostMapping("/lot/{id}/first")
    public ResponseEntity<String> getFirstBidder(@RequestBody Long lotId) {
        String name = auctionService.getFirstBidder(lotId);
        return ResponseEntity.ok(name);
    }
    @PostMapping("/lot/export")
    public ResponseEntity<String> exportLots(@RequestBody Long lotId) {
        String name = auctionService.exportLots();
        return ResponseEntity.ok(name);
    }
}
