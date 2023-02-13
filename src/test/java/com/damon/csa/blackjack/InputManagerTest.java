package com.damon.csa.blackjack;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class InputManagerTest {
  /**
   * " test phrase_ \n"
   * Expected: "test phrase_"
   */
  @Test
  public void getTrimmedTextTest1() {
    ByteArrayInputStream input = new ByteArrayInputStream(" test phrase_ \n".getBytes());
    InputManager manager = new InputManager(input);

    assertEquals("test phrase_", manager.getTrimmedText());
  }

  /**
   * " a set of words \n "
   * Expected: "a set of words"
   */
  @Test
  public void getTrimmedTextTest2() {
    ByteArrayInputStream input = new ByteArrayInputStream(" a set of words \n ".getBytes());
    InputManager manager = new InputManager(input);

    assertEquals("a set of words", manager.getTrimmedText());
  }

  /**
   * "\n this is on another line "
   * Expected: "this is on another line"
   */
  @Test
  public void getTrimmedTextTest3() {
    ByteArrayInputStream input = new ByteArrayInputStream("\n this is on another line ".getBytes());
    InputManager manager = new InputManager(input);

    assertEquals("", manager.getTrimmedText());
  }

  /**
   * test1, test2, test3; "1"
   * Expected: 0
   */
  @Test
  public void getDecisionTest1() {
    ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
    InputManager manager = new InputManager(input);

    String[] options = { "test1", "test2", "test3" };

    assertEquals(0, manager.getDecision(options));
  }

  /**
   * test1, test2, test3; "0", " test2"
   * Expected: 1
   */
  @Test
  public void getDecisionTest2() {
    ByteArrayInputStream input = new ByteArrayInputStream("0 \n test2".getBytes());
    InputManager manager = new InputManager(input);

    String[] options = { "test1", "test2", "test3" };

    assertEquals(1, manager.getDecision(options));
  }

  /**
   * test1, test2, test3; "4", " TeRst2", " TEST3 "
   * Expected: 2
   */
  @Test
  public void getDecisionTest3() {
    ByteArrayInputStream input = new ByteArrayInputStream("4\n TeRst2\n TEST3 \n".getBytes());
    InputManager manager = new InputManager(input);

    String[] options = { "test1", "test2", "test3" };

    assertEquals(2, manager.getDecision(options));
  }

  /**
   * " -1 \n"
   * Expected:
   */
  @Test
  public void getNumberTest1() {
    ByteArrayInputStream input = new ByteArrayInputStream(" -1 \n".getBytes());
    InputManager manager = new InputManager(input);

    assertEquals(-1, manager.getNumber());
  }

  /**
   * " 0415901 \n"
   * Expected: 415901
   */
  @Test
  public void getNumberTest2() {
    ByteArrayInputStream input = new ByteArrayInputStream(" 0415901 \n".getBytes());
    InputManager manager = new InputManager(input);

    assertEquals(415901, manager.getNumber());
  }

  /**
   * Min: 0, Max: 10; "-1", "10", "5"
   * Expected: 5
   */
  @Test
  public void getNumberTest3() {
    ByteArrayInputStream input = new ByteArrayInputStream("-1\n10\n5".getBytes());
    InputManager manager = new InputManager(input);

    assertEquals(5, manager.getNumber(0, 10));
  }

  /**
   * Min: -10, Max: 6; "-1414", "10556", "5", "7"
   * Expected: 5
   */
  @Test
  public void getNumberTest4() {
    ByteArrayInputStream input = new ByteArrayInputStream("-1414\n10556\n5\n7".getBytes());
    InputManager manager = new InputManager(input);

    assertEquals(5, manager.getNumber(-10, 6));
  }
}
