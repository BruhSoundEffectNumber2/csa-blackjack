# AP Computer Science A - Blackjack

## Overview

This project is a demonstration of Blackjack. All code is written in Java, with an emphasis on skills that are important for this both this class and software engineering in general.

The code here is merely one way to complete this, and there are many other options that might be better for you.

**This should go without saying, but this is for reference only. Don't plagiarize.**

## Breakdown

The code is organized the following main classes:

- `Main`: Entry point for the program. Creates the initial game components, but does nothing else.

- `Game`: Creates the initial players, and starts rounds of the game.

- `Round`: A round of blackjack, including bets, moves, and winning/losing.

- `Player`: A player, containing a hand, cash, and a bet.

- `Hand`: A hand of several cards that has some value.

- `Deck`: A deck of 52 cards that can be shuffled. Cards can be removed from the deck, and will not be dealt again.

- `Card`: A card of a certain suite and rank. Contains several helper classes to get the value of and display a card in blackjack.

- `InputManager`: Abstracts and validates user input, allowing game components to easily get player decisions.

- `DisplayManager`: Helper functions to display complex strings, cards, clear the screen, etc.

- `Leaderboard`: A leaderboard, containing methods to save and load itself, and an array of entries in the leaderboard.

- `LeaderboardEntry`: An entry in the leaderboard, containing their name and some other info.

## Replit

This project is developed using Visual Studio Code, but Replit will work out of the box, just press the Run button.

In order to run the unit tests in Replit, run `mvn test` in the console, and Maven will show the results.

## Documentation

I have worked to make the code clear, commented, and documented, but some sections may be better than others. If there is any section that is particularly confusing, feel free to talk to/email me and I will explain it and rewrite/add documentation to explain it more clear.

I have also created some flowcharts for certain aspects of the game. These are all simplified, but are a good starting point for anyone.

- [Game Flow](/docs/GameFlowchart.pdf)
- [Calculating Hand Value](/docs/HandValue.pdf)

## Unit Testing

Unit Tests are used for several functions, but mainly serve as convenient ways to make sure certain aspects "units" of the program are working correctly, both in the creation of the feature, and any changes afterward.

You will save a lot of time debugging if you begin to experiment with them. The Hand or Card is an excellent starting point for Unit Tests.
