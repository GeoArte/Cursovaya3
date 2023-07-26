package ru.skypro.lessons.springboot.cursovaya3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.cursovaya3.AuctionService;
import ru.skypro.lessons.springboot.cursovaya3.LotDTO;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping("/lot")
    public ResponseEntity<LotDTO> createLot(@RequestBody LotDTO lotDTO) {
        LotDTO createdLot = auctionService.createLot(lotDTO);
        return ResponseEntity.ok(createdLot);
    }

    @PostMapping("/bid/{lotId}")
    public ResponseEntity<LotDTO> placeBid(@PathVariable Long lotId, @RequestParam double bidAmount) {
        LotDTO updatedLot = auctionService.placeBid(lotId, bidAmount);
        return ResponseEntity.ok(updatedLot);
    }

    @GetMapping("/lots")
    public ResponseEntity<List<LotDTO>> getAllLots() {
        List<LotDTO> lots = auctionService.getAllLots();
        return ResponseEntity.ok(lots);
    }

    @GetMapping("/lots-with-bids")
    public ResponseEntity<List<LotDTO>> getLotsWithBids() {
        List<LotDTO> lotsWithBids = auctionService.getLotsWithBids();
        return ResponseEntity.ok(lotsWithBids);
    }
}
