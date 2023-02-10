public class Game {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  public static final int STARTING_CASH = 100;

  private int numPlayers;
  private Player[] players;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public Game(int numPlayers) {
    this.numPlayers = numPlayers;
    players = new Player[numPlayers];
  }
}
