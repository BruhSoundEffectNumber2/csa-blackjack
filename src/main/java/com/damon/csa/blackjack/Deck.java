package com.damon.csa.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
  private ArrayList<Card> cards;

  public Deck() {
    cards = new ArrayList<>(52);

    for (int i = 0; i < cards.size(); i++) {
      cards.set(i, new Card(i));
    }
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }

  public Card deal() {
    if (cards.isEmpty()) {
      // There are no cards left to deal. This shouldn't happen, so throw an error
      throw new Error("Deck::Every card in the deck has already been dealt.");
    }

    return cards.remove(0);
  }

  @Override
  public String toString() {
    String result = "";

    for (int i = 0; i < cards.size(); i++) {
      result += cards.get(i).toString() + "\n";
    }

    return result;
  }
}
