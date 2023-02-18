package com.damon.csa.blackjack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HandTests {
  /**
   * King
   * Queen
   * Expected: 20
   */
  @Test
  public void test1() {
    Hand hand = new Hand();

    hand.addCard(new Card(2, 12));
    hand.addCard(new Card(0, 11));

    assertEquals(20, hand.getValue());
    assertEquals(false, hand.isBust());
  }

  /**
   * King
   * Queen
   * Jack
   * Expected: 30 BUST
   */
  @Test
  public void test2() {
    Hand hand = new Hand();

    hand.addCard(new Card(3, 12));
    hand.addCard(new Card(4, 11));
    hand.addCard(new Card(0, 10));

    assertEquals(30, hand.getValue());
    assertEquals(true, hand.isBust());
  }

  /**
   * King
   * Queen
   * Ace
   * Expected: 21
   */
  @Test
  public void test3() {
    Hand hand = new Hand();

    hand.addCard(new Card(1, 12));
    hand.addCard(new Card(0, 11));
    hand.addCard(new Card(2, 0));

    assertEquals(21, hand.getValue());
    assertEquals(false, hand.isBust());
  }

  /**
   * King
   * Ace
   * Expected: 21
   */
  @Test
  public void test4() {
    Hand hand = new Hand();

    hand.addCard(new Card(3, 12));
    hand.addCard(new Card(4, 0));

    assertEquals(21, hand.getValue());
    assertEquals(false, hand.isBust());
  }

  /**
   * Two
   * Ace
   * Expected: 13
   */
  @Test
  public void test5() {
    Hand hand = new Hand();

    hand.addCard(new Card(1, 1));
    hand.addCard(new Card(2, 0));

    assertEquals(13, hand.getValue());
    assertEquals(false, hand.isBust());
  }

  /**
   * Three
   * Five
   * Two
   * Expected: 10
   */
  @Test
  public void test6() {
    Hand hand = new Hand();

    hand.addCard(new Card(0, 2));
    hand.addCard(new Card(2, 4));
    hand.addCard(new Card(4, 1));

    assertEquals(10, hand.getValue());
    assertEquals(false, hand.isBust());
  }

  /**
   * Ace
   * Ace
   * Queen
   * Expected: 12
   */
  @Test
  public void test7() {
    Hand hand = new Hand();

    hand.addCard(new Card(0, 0));
    hand.addCard(new Card(2, 0));
    hand.addCard(new Card(4, 11));

    assertEquals(12, hand.getValue());
    assertEquals(false, hand.isBust());
  }

  /**
   * Three
   * Five
   * Two
   * Four
   * Ace
   * Expected: 21
   */
  @Test
  public void test8() {
    Hand hand = new Hand();

    hand.addCard(new Card(0, 2));
    hand.addCard(new Card(2, 4));
    hand.addCard(new Card(4, 1));
    hand.addCard(new Card(3, 3));
    hand.addCard(new Card(1, 0));

    assertEquals(21, hand.getValue());
    assertEquals(false, hand.isBust());
  }
}
