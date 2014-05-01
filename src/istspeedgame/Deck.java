package istspeedgame;

import java.util.*;

public class Deck {
	
	String [] suits  = {"s","c","h","d"};
	String [] values = {"2","3","4","5","6","7","8","9","10","j","q","k","a"};
	Card [] cards = new Card[52];
	Card [] tabledeckLeft= new Card[5];
	Card [] tabledeckRight = new Card[5];
	Card [] playerDeckA = new Card[21];
	Card [] playerDeckB = new Card[21];
	
	LinkedList<Card> deck = new LinkedList<Card>();
	
	public Deck() {
		int counter = 0;
		
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 12; j++) {
				cards[counter] = new Card(suits[i], values[j], j+2);
				deck.add(cards[counter]);
				counter++;
			}
		}
	}
	
	public void Shuffle() {
		Collections.shuffle(deck);
	}
	
	public void Split() {
		
	}
	
	public Card getCard(int index) {
		return deck.get(index);
	}


}
