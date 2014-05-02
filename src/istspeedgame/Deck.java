package istspeedgame;

import java.util.*;

public class Deck {
	
	String [] suits  = {"s","c","h","d"};
	String [] values = {"2","3","4","5","6","7","8","9","10","j","q","k","a"};
	Card [] cards = new Card[52];
	Card [] tableDeckLeft= new Card[5];
	Card [] tableDeckRight = new Card[5];
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
		int j = 1; //Deck A or Deck B
		int k = 0; //right
		int l = 0; //playerDeckA
		int m = 0; //playerDeckB
		for(int i = 0;i<52;i++){
			if(i <5){
				tableDeckLeft[i] = deck.get(i);
			}// i < 5
			if(i >=5 && i < 10){
				tableDeckRight[k] = deck.get(i);
				k++;
			}// i >= 5 && i < 10
			if(i >= 10){
				if(j ==1){
					playerDeckA[l] = deck.get(i);
					l++;
					j--;
				}//A
				else{
					playerDeckA[m] = deck.get(i);
					m++;
					j++;
				}//B
			}//i >= 10
		}// for : i
	}// Split
	
	public Card getCard(int index) {
		return deck.get(index);
	}


}
