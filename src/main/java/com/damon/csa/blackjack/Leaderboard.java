package com.damon.csa.blackjack;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Leaderboard {
  ///////////////////////////////
  // Properties
  ///////////////////////////////

  private static final String FILE_PATH = "leaderboard.csv";

  private ArrayList<LeaderboardEntry> entries;

  ///////////////////////////////
  // Constructor
  ///////////////////////////////

  public Leaderboard() {
    entries = new ArrayList<>();
  }

  ///////////////////////////////
  // Methods
  ///////////////////////////////

  public LeaderboardEntry[] getEntries() {
    return entries.toArray(new LeaderboardEntry[entries.size()]);
  }

  /**
   * Loads all leaderboard entries that were saved into the Leaderboard.
   */
  public void load() {
    File file = new File(FILE_PATH);

    /*
     * This syntax will create a Scanner that will be looking
     * at the leaderboard.csv file. Now, scanners need to be
     * closed when they are done reading. We would normally
     * need to do this manually, but if there is an error somewhere,
     * we might not actually have the chance to close it. So we let
     * java create and close the scanner for us.
     */
    try (Scanner reader = new Scanner(file)) {
      // The first row of the CSV contains the names for the columns, which we skip
      reader.nextLine();

      while (reader.hasNextLine()) {
        // Split the line where a comma is seen
        String rawText = reader.nextLine();
        String[] rawValues = rawText.split(",");

        // Convert the strings into usable values
        String name = rawValues[0].trim();
        int wins = Integer.parseInt(rawValues[1].trim());
        int moneyWon = Integer.parseInt(rawValues[2].trim());

        entries.add(new LeaderboardEntry(name, wins, moneyWon));
      }
    } catch (Exception e) {
      System.err.println("Error loading leaderboard:");
      System.err.println(e.getMessage());

      entries = new ArrayList<>();
    }
  }

  /**
   * Writes all leaderboard entries to a file, overwriting any previous entries.
   */
  public void save() {
    File file = new File(FILE_PATH);

    try (FileWriter writer = new FileWriter(file, false)) {
      // Create the column names first
      writer.write("Name, Wins, MoneyWon\n");

      for (LeaderboardEntry entry : entries) {
        writer.write(entry.name + ", ");
        writer.write(entry.wins + ", ");
        writer.write(entry.moneyWon + "\n");
      }
    } catch (Exception e) {
      System.err.println("Error saving leaderboard:");
      System.err.println(e.getMessage());
    }
  }

  /**
   * Checks if the given name is already in the leaderboard.
   */
  public boolean isNameTaken(String name) {
    return findEntry(name) != null;
  }

  public void addNewEntry(String name) {
    entries.add(new LeaderboardEntry(name));
  }

  public void addEntryWin(String name) {
    findEntry(name).wins++;
  }

  public void addEntryMoney(String name, int amount) {
    findEntry(name).moneyWon += amount;
  }

  private LeaderboardEntry findEntry(String name) {
    for (LeaderboardEntry entry : entries) {
      if (entry.name.equals(name)) {
        return entry;
      }
    }

    return null;
  }
}
