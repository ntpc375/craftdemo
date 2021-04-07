package com.mylocal.craftdemo.repository;


import com.mylocal.craftdemo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Player, Long> {

    Player findById(long id);

    List<Player> findByPlayerId(String playerId);
}