package istspeedgame;

import java.util.*;

public class Deck {
	
	String [] suits  = {"s","c","h","d"};
	String [] values = {"2","3","4","5","6","7","8","9","10","j","q","k","a"};
	Card [] cards = new Card[52];
	
	LinkedList<String> deck = new LinkedList<String>();
	
	public Deck() {
		int counter = 0;
		
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 12; j++) {
				cards[counter] = new Card(suits[i], values[j], j+1);
				deck.add(cards[counter].getCard());
				counter++;
			}
		}
	}
	
	public void Shuffle() {
		Collections.shuffle(deck);
		
		for(String str: deck){
            System.out.println(str);
        }
	}
	
	public void Split() {
		
	}
	
	public String getCard(int index) {
		return deck.get(index);
	}


}
