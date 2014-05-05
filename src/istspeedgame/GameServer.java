package istspeedgame;

import java.io.*;
import java.net.*;
/*
 * READING THIS? GOOD. I feel this is the best way to do this.. its going to open a stream just like a chat program that does it,
 * when it does it relays the card information. The logic is all client side and unsecure like stuff, we can probably double the 
 * we could double the logic but i do not see any point and considering this would be hacked together i don't think we should.
 * 
 */
public class GameServer implements Runnable {
	private static final int PORT = 5555;//Port number
	
	public void run(){
        System.out.println("The Game Server is running.");
        ServerSocket listener = null;
        Deck a = new Deck(); //Server controls the new deck.
        try {
            listener = new ServerSocket(PORT);
            while (true) {
                new Handler(listener.accept(), a).start(); //Starts the handler which does the work
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
        private Deck a;

        public Handler(Socket socket, Deck a) {
            this.socket = socket;
            this.a = a;
        }// const

    public void run() {
        	
        // Create character streams for the socket.
    	System.out.println("Made it here running");
        try {
            is = socket.getInputStream();
            oi = new ObjectInputStream(is); //Here for little reason but keep this
            //Object temp = oi.readObject(); // When we want to read the object sent
            outs = socket.getOutputStream();
            out = new ObjectOutputStream(outs);
            
        	if(a.P1 != null){ //First connection gets this. then removes the deck.
        		
        	}
        	else if(a.P2 != null){ //Second connection technically gets this sent. then removes the deck.
        		
        	}
        	else{
        		System.out.println("We Require more Decks to Continue, More than 2 Connections"); //Simple error log.
        	}
        	
        	
        	
            outs = socket.getOutputStream();
            out = new ObjectOutputStream(outs);
            out.writeObject(a); //sends object through output stream.
            out.flush();
            //out.close();
            while(true){
            	sleep(150);
            	//System.out.println("Made it here whiling");
            	
            }

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
        catch(IOException | InterruptedException e){ 
               e.printStackTrace();
        } //catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
    finally{
        	System.out.println("Made it here finally");
        }
        }// run
    }// Handler
    public static void main(String[] args){
		GameServer a = new GameServer();
		Thread thread = new Thread(a);
		thread.start();
	}
}// GameServer
