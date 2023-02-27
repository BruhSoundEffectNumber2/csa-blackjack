package com.damon.csa.blackjack;

import java.util.ArrayList;

public class Game {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  public static final int STARTING_CASH = 100;
  public static final int MIN_BET = 5;

  private InputManager input;
  private DisplayManager output;

  private Round currentRound;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public Game(int numPlayers) {
    input = new InputManager(System.in);
    output = new DisplayManager(System.out);

    output.clearScreen();

    output.printHeader("Blackjack");
    output.print("The", null);

    while (true) {

    }

    output.clearScreen();

    output.print("Thank You for Playing!");
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

  public void start() {
    dealPlayerCards();

    dealDealerHoleCard();

    dealDealerFaceCard();

    playPlayerHands();

    showDealerHoleCard();

    // TODO: If all player's are bust, do we really need to play the dealer's hand?

    playDealerHand();

    determineWinner();
  }

  private void dealPlayerCards() {
    for (int i = 0; i < players.length; i++) {
      players[i].addCard(deck.deal());
      players[i].addCard(deck.deal());
    }
  }

  private void dealDealerHoleCard() {
    Card card = deck.deal();
    // This is the only case where the card is face down, so set it here
    card.faceUp = false;

    dealer.addCard(card);
  }

  private void dealDealerFaceCard() {
    dealer.addCard(deck.deal());
  }

  private void playPlayerHands() {
    for (Player player : players) {
      playerHandDecision(player);
    }
  }

  private void playerHandDecision(Player player) {
    output.clearScreen();
    output.displayHand(dealer);
    output.newline();
    output.displayHand(player);

    int decision = input.getDecision(new String[] { "Hit", "Stand" });

    /*
     * This is an "enhanced" switch statement. The syntax is similar
     * to a regular switch statement, but allows the code to be blocked
     * into braces and removes the need for a break statement.
     * See https://www.vojtechruzicka.com/java-enhanced-switch/ for details.
     */
    switch (decision) {
      // Hit
      case 0 -> {
        player.addCard(deck.deal());

        if (player.isBust()) {
          output.clearScreen();
          output.displayHand(player);
          output.newline();

          output.print("Oh No! %s is bust and has lost $%d", player.name, player.bet);
          output.newline();

          input.waitForInput();
        } else {
          playerHandDecision(player);
        }
      }
    }
  }

  private void showDealerHoleCard() {
    // Because we aren't sure what card is face down, just set all cards to face up
    for (Card card : dealer.cards) {
      if (card != null)
        card.faceUp = true;
    }
  }

  private void playDealerHand() {
    output.clearScreen();

    output.print("The dealer will now play their hand.");
    input.waitForInput();

    while (dealer.getHandValue() < 17) {
      output.clearScreen();
      output.displayHand(dealer);
      output.newline();

      output.print("The dealer's hand is below 17. The dealer will hit.");
      input.waitForInput();

      dealer.addCard(deck.deal());
    }
  }

  private void determineWinner() {
    ArrayList<Player> busted = new ArrayList<>();
    ArrayList<Player> inPlay = new ArrayList<>();

    for (Player player : players) {
      if (player.getHandValue() > 21) {
        busted.add(player);
      } else {
        inPlay.add(player);
      }
    }

    output.clearScreen();

    int dealerHand = dealer.getHandValue();

    if (dealerHand > 21) {
      // Dealer went bust, everybody wins!
      output.printHeader("The dealer went bust! Everybody (who hasn't gone bust) wins!");
      output.newline();

      output.print("The following players won:");
      for (Player player : inPlay) {
        output.print("%s won $%d.", player.name, player.bet);
        player.cash += player.bet * 2;
      }

      if (busted.size() > 0) {
        output.newline();
        output.print("But these people sure didn't:");
        for (Player player : busted) {
          output.print("%s lost $%d", player.name, player.bet);
        }
      }
    } else {
      output.printHeader("The dealer had a hand worth %d", dealerHand);
      output.newline();

      output.print("The following people had a better hand than the dealer:");
      for (Player player : inPlay) {
        if (player.getHandValue() > dealerHand) {
          output.print("%s won $%d", player.name, player.bet);
          player.cash += player.bet * 2;
        }
      }
      output.newline();

      output.print("The following people had an equal hand to the dealer:");
      for (Player player : inPlay) {
        if (player.getHandValue() == dealerHand) {
          output.print("%s", player.name);
          player.cash += player.bet;
        }
      }
      output.newline();

      output.print("The following people had a worse hand than the dealer:");
      for (Player player : inPlay) {
        if (player.getHandValue() < dealerHand) {
          output.print("%s lost $%d", player.name, player.bet);
        }
      }
      output.newline();

      output.print("The following people went bust:");
      for (Player player : busted) {
        output.print("%s lost $%d", player.name, player.bet);
      }
    }
  }
}
