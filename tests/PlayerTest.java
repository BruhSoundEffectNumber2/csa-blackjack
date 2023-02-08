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

    player.setCard(0, new Card(2, 12));
    player.setCard(1, new Card(0, 11));

    System.out.println(player.getHandValue());
    assertEquals(20, player.getHandValue());
    assertEquals(false, player.isBust());
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

    player.setCard(0, new Card(3, 12));
    player.setCard(1, new Card(4, 11));
    player.setCard(2, new Card(0, 10));

    assertEquals(30, player.getHandValue());
    assertEquals(true, player.isBust());
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

    player.setCard(0, new Card(1, 12));
    player.setCard(1, new Card(0, 11));
    player.setCard(2, new Card(2, 0));

    assertEquals(21, player.getHandValue());
    assertEquals(false, player.isBust());
  }

  /*
   * King
   * Ace
   * Expected: 21
   */
  @Test
  public void test4() {
    Player player = new Player("Damon", false);

    player.setCard(0, new Card(3, 12));
    player.setCard(1, new Card(4, 0));

    assertEquals(21, player.getHandValue());
    assertEquals(false, player.isBust());
  }

  /*
   * Two
   * Ace
   * Expected: 13
   */
  @Test
  public void test5() {
    Player player = new Player("Damon", false);

    player.setCard(0, new Card(1, 1));
    player.setCard(1, new Card(2, 0));

    assertEquals(13, player.getHandValue());
    assertEquals(false, player.isBust());
  }

  /*
   * Three
   * Five
   * Two
   * Expected: 10
   */
  @Test
  public void test6() {
    Player player = new Player("Damon", false);

    player.setCard(0, new Card(0, 2));
    player.setCard(1, new Card(2, 4));
    player.setCard(2, new Card(4, 1));

    assertEquals(10, player.getHandValue());
    assertEquals(false, player.isBust());
  }
}
