package istspeedgame;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
/*
 * READING THIS? GOOD. I feel this is the best way to do this.. its going to open a stream just like a chat program that does it,
 * when it does it relays the card information. The logic is all client side and unsecure like stuff, we can probably double the 
 * we could double the logic but i do not see any point and considering this would be hacked together i don't think we should.
 * 
 */
public class GameServer implements Runnable {
	private static final int PORT = 5555;//Port number
	public static int currentConnections =0;
	private static Object obj;
	private static Object obj2;
	private static Deck deck, P1, P2;
        public static boolean running = false;
        
	public void run(){
        System.out.println("The Game Server is running.");
       
        ServerSocket listener = null;
        deck = new Deck(); //Server controls the new deck.
        try {
            listener = new ServerSocket(PORT);
            while (true) {
                running = true;
                new Handler(listener.accept(), deck, currentConnections).start(); //Starts the handler which does the work
            } // while : true
        }
        catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                listener.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }//finally
        }// run

    private static class Handler extends Thread { //Handles Messages
        private Socket socket;
        /*private BufferedReader in; OLD READER/WRITER
        private PrintWriter out;*/
        private ObjectInput oi; //this has me thinking i need to send a packet of 
        private InputStream is;
        private ObjectOutputStream out;
        private OutputStream outs;
        private Deck a, tempDeck;
        private int connectionNumber;
        

        public Handler(Socket socket, Deck a, int b) {
            this.socket = socket;
            this.a = a;
            this.connectionNumber = b;           
        }// const

    public void run() {
        	
        // Create character streams for the socket.
    	System.out.println("Made it here running");
        try {
        	//GameObjectHandler game = new GameObjectHandler(a);

            is = socket.getInputStream();
            oi = new ObjectInputStream(is); //Here for little reason but keep this
            GameServer.currentConnections++;
            
            
            
            
            
            
            outs = socket.getOutputStream();
            out = new ObjectOutputStream(outs);
            
            
            /*
             * Dont doubt the logic here, for some reason on first connect it numerates by a few.
             */
        	if(connectionNumber <= 2){ //First connection gets this.
        		System.out.println("3 or less");
        		GameServer.obj = a.P2; //Takes snapshot of the deck
        		GameServer.obj2 = a.P1;
        		a.player=1; //sets player based on what came first
        		GameServer.P1 = a;
        		out.writeObject(a); //sends object through output stream.
        		       		 
        	}
        	else if(connectionNumber >= 3 && connectionNumber < 5){ //Switches the deck.
        		a.P1 = (ArrayList<Card>) GameServer.obj; //Switches the deck. 
        		a.P2 = (ArrayList<Card>) GameServer.obj2;
        		System.out.println("6 or more");
        		a.player=2; //sets player based on what came first
        		GameServer.P2 = a;
        		out.writeObject(a); //sends object through output stream.
        	}
        	else{
        		Object temp = oi.readObject(); // When we want to read the object sent
                tempDeck = (Deck) temp;
                if(tempDeck.changes > deck.changes){ //if more changes have occurred.
                	
                		deck.tableLeft = tempDeck.tableLeft;
	                	deck.tableRight = tempDeck.tableRight; //switch the stuff in the middle.
	                	deck.tableMid = tempDeck.tableMid;
	                	
	                	deck.changes = tempDeck.changes; // Update change counter.
	                	
	                	
	                	out.writeObject(tempDeck); //send it back
	                	
	                	}
                if(deck.changes > tempDeck.changes){ //If the deck is currently ahead of the deck inside
                	tempDeck.tableLeft = deck.tableLeft;
                	tempDeck.tableRight = deck.tableRight;   //switch the stuff in the middle.
                	tempDeck.tableMid = deck.tableMid;
                	
                	tempDeck.changes = deck.changes;
                	
                	out.writeObject(tempDeck);
                	}
                else{ //if equal do nothing just write the deck.
                	out.writeObject(tempDeck);
                }
                	
                }
               
        	
        	
        	
            
            outs = socket.getOutputStream();
            out = new ObjectOutputStream(outs);
           
            out.flush();
            //out.close();
            /*while(true){
            	sleep(50);
            }*/

            /*try { 
            	while(true){
	            	while(oi.available() > 0){ //Only if something is being pushed through the stream....
	                Object temp = null;
	                temp = oi.readObject();
	    
	                Deck tempa = (Deck) temp;
	                                     
	                //out.close();            	
	            	}//while oi.available()
	            	
	            	out.writeObject(a); //sends object through output stream.
	                out.flush();
            	}//while true
            } //try
            catch(IOException e){ 
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }*/
            
        
        }
        catch(IOException e){ 
               e.printStackTrace();
        } catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    finally{
        	System.out.println("Made it here finally"); //shouldent happen but if something didnt go working and still or the loop doesnt propigate.
        }
        }// run
    }// Handler
    public static void main(String[] args){
		GameServer a = new GameServer();
		Thread thread = new Thread(a);
		thread.start();
	}
}// GameServer
