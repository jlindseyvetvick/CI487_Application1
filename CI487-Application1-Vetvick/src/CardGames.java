import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.w3c.dom.Element;

public class CardGames {

	public static void shuffle(SinglyLinkedList<Card> deck) {

		Random rand = new Random();

		for (int i = 0; i < 300; i++) {
			Card tmpCard = deck.removeNodeAtPos(rand.nextInt(deck.size())).data;
			deck.addNodeAtPos(rand.nextInt(deck.size()), tmpCard);
		}
	}

	public static int compareCards(Card currCard, Card nextCard) {
		Scanner sc = new Scanner(System.in);
		int earnedScore = 0;

		System.out.println("");
		System.out.println("Guess high or low: ");
		String playerGuess = sc.nextLine();
		playerGuess = playerGuess.toLowerCase();

		/*if (!playerGuess.equals("high") || !playerGuess.equals("low")) {
			System.out.println("");
			System.out.println("ERROR: User input is invalid. Please type high or low.");
		}*/

		if (currCard.getCardValue().equals(nextCard.getCardValue())) {
			System.out.println("");
			System.out.println("It's a tie! Your card is: " + currCard + " and the newly drawn card is: " + nextCard);
			earnedScore = 0;
		}

		else if (playerGuess.equals("high") && Card.CARD_VALUES.indexOf(currCard.getCardValue()) < Card.CARD_VALUES
				.indexOf(nextCard.getCardValue())) {
			System.out.println("");
			System.out.println("Correct!The card, " + nextCard + " was higher!");

			earnedScore = 1;
		}

		else if (playerGuess.equals("low") && Card.CARD_VALUES.indexOf(currCard.getCardValue()) > Card.CARD_VALUES
				.indexOf(nextCard.getCardValue())) {
			System.out.println("");
			System.out.println("Correct!The card, " + nextCard + " was lower!");
			earnedScore = 1;

		}

		else if (playerGuess.equals("low") && Card.CARD_VALUES.indexOf(currCard.getCardValue()) < Card.CARD_VALUES
				.indexOf(nextCard.getCardValue())) {
			System.out.println("");
			System.out.println("Incorrect!The card, " + nextCard + " was higher!");
			earnedScore = 0;
		}

		else if (playerGuess.equals("high") && Card.CARD_VALUES.indexOf(currCard.getCardValue()) > Card.CARD_VALUES
				.indexOf(nextCard.getCardValue())) {
			System.out.println("");
			System.out.println("Incorrect!The card, " + nextCard + " was lower!");
			earnedScore = 0;
		}

		else {
			System.out.println("");
			System.out.println("ERROR: User input is invalid. Please type high or low.");
		}
		return earnedScore;
	}

	public static int guessHigherOrLower(SinglyLinkedList<Card> deck) {

		Scanner sc = new Scanner(System.in);
		int score = 0;
		Card card = deck.removeFromEnd().data;
		System.out.println("");
		System.out.println("Your first card is: " + card + ". Let the game begin!");

		String keepGoing = "yes";

		while (keepGoing.equals("yes")) {
			System.out.println("");
			System.out.println("Enter one of the following commands: shuffle or get card");

			String playerChoice = sc.nextLine();
			playerChoice = playerChoice.toLowerCase();

			if (playerChoice.equals("shuffle")) {
				shuffle(deck);
				System.out.println("Shuffle complete.");
				keepGoing = "yes";
			}

			else if (playerChoice.equals("get card")) {
				keepGoing = "no";
				System.out.println("");
				System.out.println("Select from the top or bottom: ");
				String drawChoice = sc.nextLine();
				drawChoice = drawChoice.toLowerCase();

				if (drawChoice.equals("top")) {
					Card playerDraw = deck.removeFromFront().data;
					score = score + compareCards(card, playerDraw);
				}

				else if (drawChoice.equals("bottom")) {
					Card playerDraw = deck.removeFromFront().data;
					score = score + compareCards(card, playerDraw);
				}

				else {
					System.out.println("ERROR: User input is invalid. Please type top or bottom.");
					keepGoing = "yes";
				}

			}

			else {
				keepGoing = "yes";
				System.out.println("ERROR: User input is invalid. Please type shuffle or get card.");
			}
		}
		return score;
	}

	public static void main(String[] args) {

		/* Step 1: Start the selection process */
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"\n--Guess Higher Or Lower: A Card Game--\n" + "This is a simple card game. In a given move you can\n"
						+ "choose to shuffle the deck or select a card. If you select a card\n"
						+ "it will be selected from the top of the deck and compared to the\n"
						+ "previously selected card. If the user guessed that the card is low\n"
						+ "and the card has a lower value or if you guessed high and the card\n"
						+ "had a higher value then your score will be incremented. If you do not\n"
						+ "then the game will end and your final score is reported.\n");

		String keepPlaying = "yes";
		int score = 0;

		while (keepPlaying.equals("yes")) {

			/* Step 2: Build and fill the deck */
			SinglyLinkedList<Card> deck = new SinglyLinkedList<>();

			for (int i = 0; i < Card.SUITES.size(); i++) {
				String cardSuite = Card.SUITES.get(i);

				for (int j = 0; j < Card.CARD_VALUES.size(); j++) {
					String cardValue = Card.CARD_VALUES.get(j);
					Card newCard = new Card(cardSuite, cardValue);
					deck.addToEnd(newCard);
				}
			}

			/* Step 3: Shuffle the deck and start the game */
			shuffle(deck);
			score = score + guessHigherOrLower(deck);

			/* Step 4: Ask the user if they want to quit or continue */
			System.out.println("");
			System.out.println("Would you like to continue or quit?");
			String playerChoice = sc.nextLine();
			playerChoice = playerChoice.toLowerCase();

			if (playerChoice.equals("continue")) {
				keepPlaying = "yes";
			}

			else if (playerChoice.equals("quit")) {
				break;
			}

			else {
				keepPlaying = "yes";
			}

		}
		System.out.println("");
		System.out.println("Thank you for playing! Player Score: " + score);
	}

}
