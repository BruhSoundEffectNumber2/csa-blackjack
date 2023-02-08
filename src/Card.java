public class Card {
  private static char[] suites = {'S', 'H', 'D', 'C'};
  private static String[] ranks = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

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

  public String displayString() {
    return suites[suite] + ranks[rank];
  }

  @Override
  public String toString() {
    return "card: " + suite + " | " + rank;
  }
}
