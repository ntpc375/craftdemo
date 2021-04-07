package com.mylocal.craftdemo.service;

import com.mylocal.craftdemo.model.Player;
import com.mylocal.craftdemo.repository.ListingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ListingsServiceTest {
    private ListingService listingsService;

    @Test
    public void testGetAllListings(){
        //given
        ListingRepository mockRepo = Mockito.mock(ListingRepository.class);
        listingsService = new ListingService();
        listingsService.setListingRepository(mockRepo);
        Mockito.when(mockRepo.findAll()).thenReturn(populateListings());

        //when
        List<Player> actualListings = listingsService.getListings();

        //then
        Assert.assertEquals(actualListings.size(), populateListings().size());
    }

    @Test
    public void testGetAllListingsByPlayerId(){
        //given
        ListingRepository mockRepo = Mockito.mock(ListingRepository.class);
        listingsService = new ListingService();
        listingsService.setListingRepository(mockRepo);
        Mockito.when(mockRepo.findByPlayerId(Mockito.any())).thenReturn(populateListings());

        //when
        List<Player> actualListings = listingsService.listingRepository.findByPlayerId("89822");

        //then
        Assert.assertEquals(actualListings.size(), populateListings().size());
    }


    private List<Player> populateListings() {
        List<Player> listings = new ArrayList<>();
        listings.add(buildPlayer());
        listings.add(buildPlayer());
        listings.add(buildPlayer());
        return listings;
    }

    private Player buildPlayer() {
       return new Player();

    }
}
