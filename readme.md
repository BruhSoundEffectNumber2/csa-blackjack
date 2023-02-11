# AP Computer Science A - Blackjack

## Overview

This project is a demonstration of Blackjack. All code is written in Java, with an emphasis on skills that are important for this both this class and software engineering in general.

The code here is merely one way to complete this, and there are many other options that might be better for you.

**This should go without saying, but this is for reference only. Don't plagiarize.**

## Breakdown

The code is organized the following main classes:

- Main: Entry point for the program. Creates the initial game components, but does nothing else.

- Game: Controls the flow of the game, who is currently making a choice, wins or loses, etc.

- Player: A player, containing the concept of a hand of several cards, and money to bet. This object does not necessarily _perform_ the action of taking a card or making a bet, but it is affected by those actions (user input).

- Deck: A deck of 52 cards that can be shuffled. Cards can be removed from the deck, and will not be dealt again.

- Card: A card of a certain suite and rank. Contains several helper classes to get the value of and display a card in blackjack.

## Replit

This project is developed using Visual Studio Code, and compatibility with Replit for running or modifying this is likely, but not guaranteed. Unit Tests may be more complicated, but should work with some tweaking.

In the future, I will probably test if Replit will work out of the box, and if it doesn't, make a guide on how to make it work.

## Documentation

I have worked to make the code clearly commented and documented, but some sections may be better than others. If there is any section that is particularly confusing, feel free to talk to/email me and I will explain it and rewrite/add documentation to explain it more clearly

I have also created some flowcharts for certain aspects of the game. These are all simplified, but are a good starting point for anyone.

- [Game Flow](/docs/GameFlowchart.pdf)
- [Calculating Hand Value](/docs/HandValue.pdf)

## Unit Testing

Unit Tests are used for several functions, but mainly serve as convenient ways to make sure certain aspects "units" of the program are working correctly, both in the creation of the feature, and any changes afterward.

You will save a lot of time debugging if you begin to experiment with them. The Player or Card is an excellent starting point for Unit Tests.
