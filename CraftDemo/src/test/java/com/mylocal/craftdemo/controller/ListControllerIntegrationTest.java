package com.mylocal.craftdemo.controller;

import com.mylocal.craftdemo.model.Player;
import com.mylocal.craftdemo.service.ListingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ListingController.class)
public class ListControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ListingService listingsService;


    @Test
    public void testGetCallStatus() throws Exception {
        Mockito.when(listingsService.getListings()).thenReturn(getListings());

        mockMvc.perform(get("/v1/listings")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(listingsService).getListings();
    }

    @Test
    public void testIfServiceCallisGettingCalledForGetListingsByPlayerId() throws Exception {
        Mockito.when(listingsService.getAllListingsByPlayerId(Mockito.any(String.class))).thenReturn(getListZip());

        mockMvc.perform(get("/v1/listings/getByPlayerId?playerId="+ "12334")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(listingsService).getAllListingsByPlayerId("12334");
    }


    private List<Player> getListings() {
        List<Player> listings = new ArrayList<>();
        listings.add(new Player());
        listings.add(new Player());
        return listings;
    }

    private List<Player> getListZip() {
        List<Player> listings = new ArrayList<>();
        Player player = new Player();
        player.setPlayerId("12334");
        listings.add(player);
        return listings;
    }


}
