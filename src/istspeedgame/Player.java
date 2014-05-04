
package istspeedgame;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Player {
	private static final int PORT = 5555;//Port number
	Card a,b; //Simple test card A is card sent, b is card received
        
        ArrayList<Card> deck = new ArrayList<>();
        ArrayList<Card> hand = new ArrayList<>();
        
	public Player(ArrayList<Card> player_deck, ArrayList<Card> player_hand){
            
            this.deck = player_deck;
            this.hand = player_hand;
            
       
		try {
		Socket soc = new Socket("localhost", PORT);  	  //Socket Setup, Connects to server
		OutputStream os = soc.getOutputStream();  		//Setup Output Stream
		ObjectOutput obj = new ObjectOutputStream(os); //'Cast' to Object outpost stream.
		
		
		
		//a = new Card("c","10",10);
		
		obj.writeObject(deck.get(0));
		obj.flush(); //Something something writes A to output?
		//obj.close();		// Similar thing to get stuff FUCK THIS LINE OF CODE HONESTLY ITS PISSES ME OFF.
		ObjectInput objin = new ObjectInputStream(soc.getInputStream());
		Object temp = null;
		temp = objin.readObject();
		
		b = (Card) temp; //HEY WE RECIEVED SOMETHING FROM THE SERVER!!!!
		
		System.out.println("Client Here: " + b.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}//catch
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Client Here: " + b.toString());
	}//const
		
	public static void main(String[] args){ //FAST Test of Player.
		Deck a = new Deck();
		Player p = new Player(a.P1, a.P1_Hand);
	}
        
}// Player
