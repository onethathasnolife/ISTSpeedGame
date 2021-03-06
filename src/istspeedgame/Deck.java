package istspeedgame;

import java.io.Serializable;
import java.util.*;

public class Deck implements Serializable{
	
	String [] suits  = {"s","c","h","d"};
	String [] values = {"2","3","4","5","6","7","8","9","10","j","q","k","a"};
	Card [] cards = new Card[52];
        
	ArrayList<Card> tableLeft = new ArrayList<>();
    ArrayList<Card> tableMid = new ArrayList<>();
    ArrayList<Card> tableRight = new ArrayList<>();
    ArrayList<Card> P1 = new ArrayList<>();
    ArrayList<Card> P2 = new ArrayList<>();
	ArrayList<Card> P1_Hand = new ArrayList<>();
    ArrayList<Card> P2_Hand = new ArrayList<>();
	LinkedList<Card> deck = new LinkedList<Card>();
	
	int player,changes = 0;
	boolean isGameFinished = false;
	boolean isClientConnected = true;
	
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
                
	}// Split
	
        public void printDeck(ArrayList<Card> deck){
            for(int i = 0; i < deck.size(); i++){
                System.out.println(deck.get(i).toString());
            }
        }
        
        public boolean updateHand(Player player){
            boolean success = false;
            
            // Check for null cards
            for(int i = 0; i < player.hand.size(); i++){
                if(player.hand.get(i) == null){
                    System.out.println("Hand size (before): "+player.hand.size());
                    player.hand.remove(i);
                    System.out.println("Hand size (after): "+player.hand.size());
                } // if : card is null, remove
            } // for : player hand
            
            // Check if hand is full
            while(player.hand.size() < 5 && !player.deck.isEmpty()){
                // Check if deck is empty
                if(!player.deck.isEmpty()){
                    player.hand.add(player.deck.get(0));
                    player.deck.remove(0);
                    success = true;
                } // if : deck not empty
            } // while : hand not full
            
            if(player.deck.isEmpty()){
                success = true;
            }
            
            return success;
        }
        
        public boolean swapCard(Card toSwap, Card toReplace, Player player){
            boolean success = false;
            
            // Possible replacements +/- 1 rank or Ace/2
            if(
                toSwap.getRank() - toReplace.getRank() == 1 || 
                toSwap.getRank() - toReplace.getRank() == -1 || 
                toSwap.getRank() - toReplace.getRank() == 12 ||  
                toSwap.getRank() - toReplace.getRank() == -12)
            {
                System.out.println("Swapping "+toReplace.toString()+" with "+toSwap.toString());
                for(int i = 0; i < player.hand.size(); i++){
                    if(player.hand.get(i) == toSwap){
                        player.hand.set(i, null);
                    }
                }
                if(toReplace == this.tableMid.get(0)){
                    this.tableMid.set(0, toSwap);
                }
                else if(toReplace == this.tableMid.get(1)){
                    this.tableMid.set(1, toSwap);
                }
                if(updateHand(player)){
                    success = true;
                }
            }
            
            return success;
        }
        
        public boolean swapMid(int index){
            boolean success = false;
            
            // Left
            if(index == 0){
                if(!this.tableLeft.isEmpty()){
                    this.tableMid.set(0, this.tableLeft.get(0));
                    this.tableLeft.remove(0);
                    success = true;
                }
            }
            
            // Right
            if(index == 4){
                if(!this.tableRight.isEmpty()){
                    this.tableMid.set(1, this.tableRight.get(0));
                    this.tableRight.remove(0);
                    success = true;
                }    
            }
            
            return success;
        }
}
