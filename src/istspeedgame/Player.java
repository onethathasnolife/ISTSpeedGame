
package istspeedgame;

import java.io.*;
import java.net.*;

public class Player {
	private static final int PORT = 5555;//Port number
	Card a; //Simple test card
	public Player(){
		try {
		Socket soc = new Socket("localhost", PORT);  	  //Socket Setup, Connects to server
		OutputStream os = soc.getOutputStream();  		//Setup Output Stream
		ObjectOutput obj = new ObjectOutputStream(os); //'Cast' to Object outpost stream.
		
		a = new Card("c","10",10);
		
		obj.writeObject(a);
		obj.flush(); //Something something writes A to output?
		obj.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//catch
		
		System.out.println("Client Here: " + a.toString());
	}//const
	
	
	public static void main(String[] args){ //FAST Test of Player.
		Player p = new Player();
	}
}// Player
