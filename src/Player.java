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

    // TODO: Create card array
  }
  
  // Hit: Add a card from the deck
  // Stand: Advance a card from the game
  // Bet: Add money to the pot
  // Bust: Go over 21 and lose

  /*
   * Turn ends if:
   *  Straight --> 21
   *  CHOICE (Hit or Stand)
   *  Bust --> Lose
   *  5 Cards --> 21
   * Do above for all players and dealer
   * Once dealer plays:
   *  If the dealer is under 17, then they take cards until 17
   *  Gets 21 --> Everybody loses
   */
}
