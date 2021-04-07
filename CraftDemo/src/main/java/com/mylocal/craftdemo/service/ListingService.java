package com.mylocal.craftdemo.service;

import com.mylocal.craftdemo.model.Player;
import com.mylocal.craftdemo.repository.ListingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
public class ListingService implements Comparator<Player> {
    @Autowired
    ListingRepository listingRepository;

    public List<Player> getListings() {
        log.info("Inside getListings method of ListingService");
        List<Player> allPlayersList =  listingRepository.findAll();
        sortRecordsByFirstName(allPlayersList);
        return allPlayersList;

    }

    private void sortRecordsByFirstName(List<Player> allPlayersList) {
        ListingService listingService = new ListingService();
        Collections.sort(allPlayersList, listingService);
    }


    public Page<Player> getAllListings(Pageable pageable) {
        return listingRepository.findAll(pageable);
    }

    public List<Player> getAllListingsByPlayerId(String zip) {
        log.info("In getAllListingsByPlayerId method() in ListingsService");
        return listingRepository.findByPlayerId(zip);
    }


    public void saveAllCSVData(Set<Player> allCSVListings) {
        log.info("In saveAllCSVData method() in ListingsService");
        listingRepository.saveAll(allCSVListings);
    }

    public void setListingRepository(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @Override
    public int compare(Player a1, Player a2) {
         char firstNameCharacter = a1.getNameFirst().charAt(0);
         char secondNameCharacter = a2.getNameFirst().charAt(0);
         return Character.compare(firstNameCharacter,secondNameCharacter);
    }
}


