public class Card {
  private static char[] suites = {'S', 'H', 'D', 'C'};
  private static String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
  private static int[] values = {-1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

  // Spades, Hearts, Diamonds, Clubs
  int suite;
  // Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
  int rank;

  public Card(int index) {
    this.suite = index / 13;
    this.rank = index % 13;
  }

  public Card(int suit, int rank) {
    this.suite = suit;
    this.rank = rank;
  }

  /**
   * Calculates the value of the card.
   * If the card is an Ace, then it will return a value of -1 to indicate
   * that the value depends on the rest of the hand and should be calculated separately.
   */
  public int value() {
    return values[rank];
  }

  public String displayString() {
    return suites[suite] + ranks[rank];
  }

  @Override
  public String toString() {
    return "card: " + suite + " | " + rank;
  }
}
