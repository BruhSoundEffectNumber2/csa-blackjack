package com.damon.csa.blackjack;

public class LeaderboardEntry {

  ///////////////////////////////
  // Properties
  ///////////////////////////////

  public String name;
  public int wins;
  public int moneyWon;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public LeaderboardEntry(String name) {
    this.name = name;
    this.wins = 0;
    this.moneyWon = 0;
  }

  public LeaderboardEntry(String name, int wins, int moneyWon) {
    this.name = name;
    this.wins = wins;
    this.moneyWon = moneyWon;
  }
}
