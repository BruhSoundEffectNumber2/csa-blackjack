# AP Computer Science A - Blackjack

## Overview

This project is a demonstration of Blackjack. All code is written in Java, with an emphasis on topics and skills that are necessary in this class, and in general software engineering.

The code here is merely one way to complete this, and there are many other options that might be better for you.

**This should go without saying, but this is for reference only. Don't plagiarize.**

## Breakdown

The code is organized the following main classes:

- Main: Entry point for the program. Creates the initial game components, but does nothing else.

- Game: Controls the flow of the game, who is currently making a choice, etc.

- Player: A player, containing the concept of a hand of several cards, and money to bet. This object does not necessarily _perform_ the action of taking a card or making a bet, but it is affected by those actions.

- Deck: A deck of 52 cards that can be shuffled. Cards can be removed from the deck, and will not show up again.

- Card: A card of a certain suite and rank. Contains several helper classes to get the value of and display a card in blackjack.
