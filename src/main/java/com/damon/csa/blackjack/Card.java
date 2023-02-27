package com.damon.csa.blackjack;

public class Card {
  private static int[] values = { -1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

  // Spades, Hearts, Diamonds, Clubs
  int suit;
  // Ace, Two, Three, etc.
  int rank;

  // In almost every case, the card is face up, but the dealers hole card starts
  // face down
  boolean faceUp;

  public Card(int index) {
    this.suit = index / 13;
    this.rank = index % 13;
    this.faceUp = true;
  }

  public Card(int suit, int rank) {
    this.suit = suit;
    this.rank = rank;
    this.faceUp = true;
  }

  /**
   * Calculates the value of the card.
   * If the card is an Ace, then it will return a value of -1 to indicate
   * that the value depends on the rest of the hand and should be calculated
   * separately.
   */
  public int value() {
    return values[rank];
  }

  @Override
  public String toString() {
    return "card: " + suit + " | " + rank;
  }
}
