package com.mylocal.craftdemo.controller;
import com.mylocal.craftdemo.model.Player;
import com.mylocal.craftdemo.service.ListingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ListingControllerTest {
    private ListingController listingController;

    @Test
    public void testGetAllListings(){
        //given
        ListingService mockService = Mockito.mock(ListingService.class);
        listingController = new ListingController();
        listingController.setListingsService(mockService);
        Mockito.when(mockService.getListings()).thenReturn(getListings());

        //when
        List<Player> actualListings = listingController.getListings();

        //then
        Assert.assertEquals(actualListings.size(), getListings().size());
    }

    @Test
    public void testGetListingsByPlayerId(){
        //given
        ListingService mockService = Mockito.mock(ListingService.class);
        listingController = new ListingController();
        listingController.setListingsService(mockService);
        Mockito.when(mockService.getAllListingsByPlayerId(Mockito.any())).thenReturn(getListings());

        //when
        List<Player> actualListings = listingController.getListingsByPlayerId("89822");

        //then
        Assert.assertEquals(actualListings.size(), getListings().size());
    }


    private List<Player> getListings() {
        List<Player> listings = new ArrayList<>();
        listings.add(buildListing());
        return listings;
    }

    private Player buildListing() {
        Player player = new Player();
        player.setPlayerId("Player1");
        player.setBats("bats");
        return player;
    }


}
