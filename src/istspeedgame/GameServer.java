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
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }// const

        public void run() {
        	
        }// run
    }// Handler
}// GameServer
