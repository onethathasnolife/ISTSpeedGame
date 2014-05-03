package istspeedgame;

import java.io.*;
import java.net.*;
/*
 * READING THIS? GOOD. I feel this is the best way to do this.. its going to open a stream just like a chat program that does it,
 * when it does it relays the card information. The logic is all client side and unsecure like stuff, we can probably double the 
 * we could double the logic but i do not see any point and considering this would be hacked together i don't think we should.
 * 
 */
public class GameServer {
	private static final int PORT = 5555;//Port number
	
	public static void main(String[] args) throws Exception {
        System.out.println("The Game Server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start(); //Starts the handler which does the work
            } // while : true
        } finally {
            listener.close();
        }//finally
    }// main

    private static class Handler extends Thread { //Handles Messages
        private String name;
        private Socket socket;
        /*private BufferedReader in; OLD READER/WRITER
        private PrintWriter out;*/
        private Card left; // Current cards on top of the deck.
        private Card right;
        private ObjectInput oi; //this has me thinking i need to send a packet of 
        private InputStream is;

        public Handler(Socket socket) {
            this.socket = socket;
        }// const

        public void run() {
        	
        	// Create character streams for the socket. 
            try {
            	is = socket.getInputStream();
            	oi = new ObjectInputStream(is);
            	
            	//real confusing stuff but a try/catch surrounded by a try catch.
            	
            	
            	try {
					left = (Card) oi.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	
            	
            /*	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true); */
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }// run
    }// Handler
}// GameServer
