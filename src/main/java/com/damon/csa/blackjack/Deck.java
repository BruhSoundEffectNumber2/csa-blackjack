package com.damon.csa.blackjack;

import java.util.Random;

public class Deck {
  private Card[] cards;

  public Deck() {
    cards = new Card[52];

    for (int i = 0; i < cards.length; i++) {
      cards[i] = new Card(i);
    }
  }

  public void shuffle() {
    Random rand = new Random();

    for (int i = 0; i < cards.length; i++) {
      Card card = cards[i];
      int shuffleIndex = rand.nextInt(cards.length);
      Card temp = cards[shuffleIndex];

      cards[shuffleIndex] = card;
      cards[i] = temp;
    }
  }

  public Card deal() {
    // Iterate through the cards and find the first non-null card
    for (int i = 0; i < cards.length; i++) {
      Card current = cards[i];

      // Is there a card here?
      if (current != null) {
        Card chosen = cards[i];
        cards[i] = null;

        return chosen;
      }
    }

    // We have looked at every single card in the deck, but all of them have been
    // dealt
    // We can throw an error because we should not have gotten to this point in the
    // first place
    throw new Error("Deck::Every card in the deck has already been dealt.");
  }

  @Override
  public String toString() {
    String result = "";

    for (int i = 0; i < cards.length; i++) {
      result += cards[i].toString() + "\n";
    }

    return result;
  }
}
