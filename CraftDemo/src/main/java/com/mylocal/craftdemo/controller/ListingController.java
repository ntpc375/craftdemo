package com.mylocal.craftdemo.controller;


import com.mylocal.craftdemo.model.Player;
import com.mylocal.craftdemo.service.ListingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequestMapping("/v1/listings")
@RestController
@Slf4j
public class ListingController {


    @Autowired
    ListingService listingsService;

    @GetMapping()
    public List<Player> getListings(){
        log.info("Inside getAllListings method of ListingController");
        List<Player> allListings = listingsService.getListings();
        return allListings;
    }

    @GetMapping("/getByPlayerId")
    public List<Player> getListingsByPlayerId(@RequestParam(required = true, name = "playerId") String playerId){
        log.info("Inside getListingsByPlayerId method of ListingController");
        return listingsService.getAllListingsByPlayerId(playerId);
    }

    @GetMapping("/getWithPagination")
    public ResponseEntity<Page<Player>> getAllListings(final Pageable pageable, @RequestAttribute("traceId") final String traceId){
        log.info("Inside getAllListings method of ListingController, traceId {}", traceId);
        Page<Player> allListings = listingsService.getAllListings(pageable);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("trace-id", traceId);
        return new ResponseEntity<>(allListings, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/migrateCSVData")
    public String migrateCSV() {
        String path = "classpath:People1.csv";
        String line = "";
        Set<Player> allCSVListings = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(ResourceUtils.getFile(path)));
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");

                Player player = new Player();
                player.setPlayerId(values[0]);
                player.setBirthYear(values[1]);
                player.setBirthMonth(values[2]);
                player.setBirthDay(values[3]);
                player.setBirthCountry(values[4]);

                player.setBirthState(values[5]);
                player.setBirthCity(values[6]);
                player.setDeathYear(values[7]);
                player.setDeathMonth(values[8]);
                player.setDeathDay(values[9]);
                player.setDeathCountry(values[10]);
                player.setDeathState(values[11]);
                player.setDeathCity(values[12]);


                player.setNameFirst(values[13]);
                player.setNameLast(values[14]);
                player.setNameGiven(values[15]);

                player.setWeight(values[16]);
                player.setHeight(values[17]);
                player.setBats(values[18]);
                player.setPlayerthrows(values[19]);

                player.setDebut(values[20]);
                player.setFinalGame(values[21]);
                player.setRetroID(values[22]);
                player.setBbrefID(values[23]);

                allCSVListings.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //saveListings
        listingsService.saveAllCSVData(allCSVListings);
        log.info("Inside migrateCSV method of ListingController");

        return "File successfully imported";
    }

    public void setListingsService(ListingService listingsService) {
        this.listingsService = listingsService;
    }


}
