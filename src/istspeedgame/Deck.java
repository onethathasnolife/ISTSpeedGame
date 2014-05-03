package istspeedgame;

import java.util.*;

public class Deck {
	
	String [] suits  = {"s","c","h","d"};
	String [] values = {"2","3","4","5","6","7","8","9","10","j","q","k","a"};
	Card [] cards = new Card[52];
        
	ArrayList<Card> tableLeft = new ArrayList<>();
        ArrayList<Card> tableMid = new ArrayList<>();
        ArrayList<Card> tableRight = new ArrayList<>();
        ArrayList<Card> P1 = new ArrayList<>();
        ArrayList<Card> P2 = new ArrayList<>();
	
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
                
                this.Shuffle();
                this.Split();
	}
	
	public void Shuffle() {
		Collections.shuffle(deck);
	}
	
	public void Split() {
            int count = 0;
            
            for(int i = 0; i < 20; i++){
                P1.add(deck.get(count));
                count++;
            }
            for(int i = 0; i < 20; i++){
                P2.add(deck.get(count));
                count++;
            }
            for(int i = 0; i < 5; i++){
                tableLeft.add(deck.get(count));
                count++;
            }
            for(int i = 0; i < 2; i++){
                tableMid.add(deck.get(count));
                count++;
            }
            for(int i = 0; i < 5; i++){
                tableRight.add(deck.get(count));
                count++;
            }
            
            System.out.println("Cards split: "+count);
            System.out.println("Table Left: ");
            printDeck(tableLeft);
            System.out.println("Table Mid: ");
            printDeck(tableMid);
            System.out.println("Table Right: ");
            printDeck(tableRight);
            System.out.println("Player 1 Deck: ");
            printDeck(P1);
            System.out.println("Player 2 Deck: ");
            printDeck(P2);
                
	}// Split
	
        public void printDeck(ArrayList<Card> deck){
            for(int i = 0; i < deck.size(); i++){
                System.out.println(deck.get(i).toString());
            }
        }
}
