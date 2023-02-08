public class Player {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  private boolean isDealer;
  private Card[] cards;
  private String name;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public Player(String name, boolean isDealer) {
    this.isDealer = isDealer;
    this.name = name;

    /*
     * Per house rules, the maximum number of cards we can take is 5 (considered a
     * blackjack).
     */
    cards = new Card[5];
  }

  /**
   * Calculates the value of the player's hand.
   * 
   * @return A number that is >= 0.
   */
  public int getHandValue() {
    int value = 0;

    // Add the value of all non-aces together
    for (Card card : cards) {
      if (card == null)
        continue;

      if (card.value() != -1) {
        value += card.value();
      }
    }

    // Now, we need to go through all the aces
    for (int i = 0; i < numAces(); i++) {
      /*
       * In blackjack, the value of an ace is 11 UNLESS that would cause the value to
       * be over 21.
       */
      if (value + 11 > 21) {
        // We have gone over 21, so it's worth 1
        value += 1;
      } else {
        // We won't go over 21, so add 11
        value += 11;
      }

      /*
       * In theory, we have already gone bust (say we have 3 Kings), but we don't care
       * now.
       */
    }

    // If we have 5 cards and haven't gone bust, then it's a blackhack
    /*
     * Because we are using an array, we will have null objects where we don't have
     * any cards (ie. we have only 3 cards, so there are two empty "null" spaces).
     */
    if (numCards() == 5 && value <= 21) {
      return 21;
    }

    return value;
  }

  public boolean isBust() {
    return getHandValue() > 21;
  }

  // TODO: Temporary function for testing
  public void setCard(int index, Card card) {
    cards[index] = card;
  }

  /**
   * Calculates the number of aces the player has.
   */
  private int numAces() {
    int acesFound = 0;

    /*
     * This new structure may look unfamiliar, but all we're doing is a for loop
     * of interval [0, cards.length), automatically getting "cards[i]" and
     * assigning it
     * to the local variable "card".
     * The reason I am using this is because I only care about the card itself,
     * not the index I can find the card at.
     */
    for (Card card : cards) {
      // Because our card array is filled with null, we need to check for it in our
      // for loop
      if (card == null)
        continue;

      /*
       * If we look at the Javadoc, we can see that "card.value()" will return -1 if
       * it's an ace.
       */
      if (card.value() == -1) {
        acesFound++;
      }
    }

    return acesFound;
  }

  /**
   * Calculates the actual number of cards in the player's hand.
   */
  private int numCards() {
    int num = 0;

    for (Card card : cards) {
      if (card != null)
        num++;
    }

    return num;
  }

  // Hit: Add a card from the deck
  // Stand: Advance a card from the game
  // Bet: Add money to the pot
  // Bust: Go over 21 and lose

  /*
   * Turn ends if:
   * Straight --> 21
   * CHOICE (Hit or Stand)
   * Bust --> Lose
   * 5 Cards --> 21
   * Do above for all players and dealer
   * Once dealer plays:
   * If the dealer is under 17, then they take cards until 17
   * Gets 21 --> Everybody loses
   */
}
