import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerTest {
  /*
   * King
   * Queen
   * Expected: 20
   */
  @Test
  public void test1() {
    Player player = new Player("Damon", false);

    player.setCard(0, new Card(0, 12));
    player.setCard(1, new Card(0, 11));

    assertEquals(player.getHandValue(), 20);
    assertEquals(player.isBust(), false);
  }

  /*
   * King
   * Queen
   * Jack
   * Expected: 30 BUST
   */
  @Test
  public void test2() {
    Player player = new Player("Damon", false);

    player.setCard(0, new Card(0, 12));
    player.setCard(1, new Card(0, 11));
    player.setCard(2, new Card(0, 10));

    assertEquals(player.getHandValue(), 30);
    assertEquals(player.isBust(), true);
  }

  /*
   * King
   * Queen
   * Ace
   * Expected: 21
   */
  @Test
  public void test3() {
    Player player = new Player("Damon", false);

    player.setCard(0, new Card(0, 12));
    player.setCard(1, new Card(0, 11));
    player.setCard(2, new Card(0, 0));

    assertEquals(player.getHandValue(), 21);
    assertEquals(player.isBust(), false);
  }

  /*
   * King
   * Ace
   * Expected: 21
   */
  @Test
  public void test4() {
    Player player = new Player("Damon", false);

    player.setCard(0, new Card(0, 12));
    player.setCard(1, new Card(0, 0));

    assertEquals(player.getHandValue(), 21);
    assertEquals(player.isBust(), false);
  }
}
