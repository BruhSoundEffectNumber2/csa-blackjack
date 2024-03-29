package com.damon.csa.blackjack;

import java.util.ArrayList;

public class Round {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  private ArrayList<Player> players;

  private ArrayList<Player> activePlayers;

  private Player dealer;
  private Deck deck;

  private InputManager input;
  private DisplayManager output;

  private Leaderboard leaderboard;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  // TODO: Can we reduce the amount of stuff we pass into the constructor?
  public Round(
      ArrayList<Player> players,
      InputManager input,
      DisplayManager output,
      Leaderboard leaderboard) {
    this.input = input;
    this.output = output;

    this.dealer = new Player("Gus 'Dealer' Reiber", true);

    this.players = new ArrayList<>(players);
    this.activePlayers = new ArrayList<>(players);

    this.deck = new Deck();

    this.leaderboard = leaderboard;
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

  public void play() {
    getBets();

    deck.shuffle();

    dealCards();

    /*
     * The method 'playerMove' removes players from activePlayers when they
     * go bust. When that happens, we A) mutate the ArrayList itself and
     * B) shift all elements after the removed element down by 1,
     * filling the gap left by the now-removed element. An enhanced for loop
     * would error when A occurs, and a normal for loop would skip over the
     * next element when B occurs, as the current index is now the next element.
     * 
     * To compensate for this, whenever we remove a player from the array,
     * we first decrement i by 1 before the for loop automatically increments i.
     * This effectively causes no change in the index, which is what we want.
     * 
     * We could also start at the end of the array and go to the front of the array,
     * but this would cause us to go in the order of Player 4, 3, 2, and 1, when we
     * normally go in order of Player 1, 2, 3, and 4.
     */
    for (int i = 0; i < activePlayers.size(); i++) {
      if (playerMove(activePlayers.get(i))) {
        i--;
      }
    }

    dealerMove();

    decideWinners();

    resetPlayers();
  }

  /**
   * Returns all players who cannot afford to make the minimum bet.
   */
  public Player[] getDonePlayers() {
    ArrayList<Player> list = new ArrayList<>();

    for (Player player : players) {
      if (player.cash < Game.MIN_BET) {
        list.add(player);
      }
    }

    return list.toArray(new Player[] {});
  }

  private void resetPlayers() {
    for (Player player : players) {
      player.reset();
    }
  }

  private void getBets() {
    for (Player player : activePlayers) {
      output.clearScreen();

      output.printHeader("Getting Player Info");

      output.newline();

      output.print("Hello %s, please place your bet.", player.name);
      output.print("The minimum bet is $%d. You have $%d.", Game.MIN_BET, player.cash);

      int bet = input.getNumber(Game.MIN_BET, player.cash);
      player.bet = bet;
    }
  }

  private void removePlayer(Player player) {
    int index = activePlayers.indexOf(player);

    activePlayers.remove(index);
  }

  private void dealCards() {
    for (Player player : activePlayers) {
      player.hand.addCard(deck.deal());
      player.hand.addCard(deck.deal());
    }

    // Because this is the only time we deal a card facing down, we just
    // store the card in a temp variable and set it to be face down
    Card faceDownCard = deck.deal();
    faceDownCard.faceUp = false;
    dealer.hand.addCard(faceDownCard);

    dealer.hand.addCard(deck.deal());
  }

  /**
   * Does the move for a player.
   * 
   * @param player The player that is playing.
   * @return Has the player gone bust during the move?
   */
  private boolean playerMove(Player player) {
    output.clearScreen();

    output.printHeader("%s's Turn", player.name);
    output.displayHand(player);
    output.newline();
    output.displayHand(dealer);
    output.newline();

    if (player.hand.isBust()) {
      output.print("Oh No! %s went bust!", player.name);
      input.waitForInput();
      removePlayer(player);
      return true;
    }

    int choice = input.getDecision(new String[] { "Hit", "Stand" });
    output.newline();

    switch (choice) {
      case 0 -> {
        // Hit
        player.hand.addCard(deck.deal());

        /*
         * This is an example of recursion. The player has the ability
         * to take as many cards as they want, and nothing changes
         * each time they do it. When the player eventully stops
         * (or goes bust), that result is cascaded through the call
         * stack until the first call, which is used by the while loop.
         */

        return playerMove(player);
      }
      case 1 -> {
        // Stand, so we just need to move on
      }
      default -> throw new Error("Choice was invalid.");
    }

    return false;
  }

  private void dealerMove() {
    // Flip over the dealer card
    dealer.hand.makeCardsFaceUp();

    output.clearScreen();

    output.printHeader("All players have completed their turn. The dealer will now play");

    input.waitForInput();

    output.clearScreen();

    output.printHeader("%s's Turn", dealer.name);
    output.displayHand(dealer);
    output.newline();

    delay(1000);

    while (dealer.hand.getValue() < 17) {
      output.print("The dealer will hit.");

      dealer.hand.addCard(deck.deal());

      delay(1000);

      output.clearScreen();

      output.printHeader("%s's Turn", dealer.name);
      output.displayHand(dealer);
      output.newline();

      delay(1000);
    }

    if (dealer.hand.isBust()) {
      output.print("The dealer went bust!");
    } else {
      output.print("The dealer is over 17 and will stand.");
    }

    input.waitForInput();
  }

  private void decideWinners() {
    output.clearScreen();

    int dealerHand = dealer.hand.getValue();

    /*
     * There are a few possibilities at this point:
     * - The dealer went bust:
     * - So the player wins
     * 
     * - The dealer hasn't gone bust:
     * -- The dealer has a hand higher than the player:
     * -- So the player loses
     * 
     * -- The dealer has the same hand as the player:
     * -- So the player doesn't win or lose any money
     * 
     * -- The dealer has a worse hand than the player:
     * -- So the player wins
     */

    if (dealerHand > 21) {
      output.printHeader("The dealer is bust!");
      output.newline();
    }

    showPlayerResults(dealerHand);

    input.waitForInput();
  }

  private void showPlayerResults(int dealerHand) {
    ArrayList<Player> winning = new ArrayList<>();
    ArrayList<Player> drawing = new ArrayList<>();
    ArrayList<Player> losing = new ArrayList<>();

    for (Player player : players) {
      // People who go bust always lose
      if (activePlayers.contains(player) == false) {
        losing.add(player);
        continue;
      }

      if (dealerHand > 21) {
        winning.add(player);
      } else {
        if (player.hand.getValue() > dealerHand) {
          winning.add(player);
        } else if (player.hand.getValue() == dealerHand) {
          drawing.add(player);
        } else {
          losing.add(player);
        }
      }
    }

    if (winning.size() > 0) {
      output.printHeader("%d players won:", winning.size());

      for (Player player : winning) {
        delay(200);

        player.cash += player.bet;
        leaderboard.addEntryWin(player.name);
        leaderboard.addEntryMoney(player.name, player.bet);
        output.print("%s won $%d and has $%d", player.name, player.bet, player.cash);
      }

      output.newline();
      delay(250);
    }

    if (drawing.size() > 0) {
      output.printHeader("%d players didn't win, but didn't lose:", drawing.size());

      for (Player player : drawing) {
        delay(200);

        output.print("%s has $%d", player.name, player.cash);
      }

      output.newline();
      delay(250);
    }

    if (losing.size() > 0) {
      output.printHeader("%d players lost:", losing.size());

      for (Player player : losing) {
        delay(200);

        player.cash -= player.bet;
        leaderboard.addEntryMoney(player.name, -player.bet);
        output.print("%s lost $%d and has $%d", player.name, player.bet, player.cash);
      }

      output.newline();
      delay(250);
    }
  }

  /**
   * Delays the program by the milliseconds passed in.
   */
  private void delay(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      System.err.println("Thread was interrupted.");
    }
  }
}
