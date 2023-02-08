public class Main {
  public static void main(String[] args) {
    Deck deck = new Deck();

    System.out.println("Starting Deck");
    System.out.println(deck);

    deck.shuffle();

    System.out.println("Shuffled Deck");
    System.out.println(deck);

    System.out.println("Dealing Cards");
    for (int i = 0; i < 52; i++) {
      System.out.println(deck.deal().displayString());
    }

    // We have already dealt every card in the deck. But what happens when we try to deal another card?
    System.out.println(deck.deal());
  }
}