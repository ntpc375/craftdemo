package com.mylocal.craftdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="playerId")
    private String playerId;

    @Column(name="birthYear")
    private String birthYear;

    @Column(name="birthMonth")
    private String birthMonth;

    @Column(name="birthDay")
    private String birthDay;

    @Column(name="birthCountry")
    private String birthCountry;

    @Column(name="birthState")
    private String birthState;

    @Column(name="birthCity")
    private String birthCity;

    @Column(name="deathYear")
    private String deathYear;

    @Column(name="deathMonth")
    private String deathMonth;

    @Column(name="deathDay")
    private String deathDay;

    @Column(name="deathCountry")
    private String deathCountry;

    @Column(name="deathState")
    private String deathState;

    @Column(name="address")
    private String deathCity;

    @Column(name="nameFirst")
    private String nameFirst;

    @Column(name="nameLast")
    private String nameLast;

    @Column(name="nameGiven")
    private String nameGiven;

    @Column(name="weight")
    private String weight;

    @Column(name="height")
    private String height;

    @Column(name="bats")
    private String bats;

    @Column(name="throws")
    private String playerthrows;

    @Column(name="debut")
    private String debut;

    @Column(name="finalGame")
    private String finalGame;

    @Column(name="retroID")
    private String retroID;

    @Column(name="bbrefID")
    private String bbrefID;

}
