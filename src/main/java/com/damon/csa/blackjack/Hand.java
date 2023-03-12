package com.damon.csa.blackjack;

import java.util.ArrayList;

/**
 * A set of cards that are owned by only one player.
 */
public class Hand {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  private ArrayList<Card> cards;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public Hand() {
    this.cards = new ArrayList<>();
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

  public Card[] getCards() {
    // If we're trying to get the cards, we want it as a list because we won't
    // modify them
    return cards.toArray(new Card[cards.size()]);
  }

  public void addCard(Card card) {
    cards.add(card);
  }

  public boolean hasFaceDownCard() {
    for (Card card : cards) {
      if (card.faceUp == false) {
        return true;
      }
    }

    return false;
  }

  /**
   * Makes all cards in the hand face up.
   */
  public void makeCardsFaceUp() {
    for (Card card : cards) {
      card.faceUp = true;
    }
  }

  /**
   * Calculates the value of the player's hand.
   * 
   * @return An integer score that is at least 0.
   */
  public int getValue() {
    int baseValue = 0;
    int numAces = 0;
    int aceValue = 0;

    // Sum the values of all non-aces, keeping track of how many aces there are
    for (Card card : cards) {
      if (card.value() == -1) {
        numAces++;
      } else {
        baseValue += card.value();
      }
    }

    // Every ace starts at its full value of 11
    aceValue = numAces * 11;

    /*
     * If an ace at a value of 11 would put us over 21, then it becomes worth 1.
     * Start with the first ace, and make it 1 if we are over 21. After that, go to
     * the next ace and repeat.
     */
    for (int i = 0; i < numAces; i++) {
      if (baseValue + aceValue > 21) {
        aceValue -= 10;
      }
    }

    // If there are 5 cards in a hand that hasn't gone bust, the hand is worth 21
    if (cards.size() >= 5 && baseValue + aceValue <= 21) {
      return 21;
    }

    return baseValue + aceValue;
  }

  /**
   * Returns whether the hand has gone bust (a value over 21).
   */
  public boolean isBust() {
    return getValue() > 21;
  }
}
