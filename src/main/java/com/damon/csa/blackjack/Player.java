package com.damon.csa.blackjack;

public class Player {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  public boolean isDealer;
  public String name;
  public int cash;
  public int bet;
  public Hand hand;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public Player(String name, boolean isDealer) {
    this.isDealer = isDealer;
    this.name = name;
    this.cash = Game.STARTING_CASH;
    this.reset();
  }

  public void reset() {
    this.bet = 0;
    this.hand = new Hand();
  }
}
