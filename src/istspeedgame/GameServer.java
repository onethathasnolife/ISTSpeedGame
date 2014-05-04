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
        try {
            listener = new ServerSocket(PORT);
            while (true) {
                new Handler(listener.accept()).start(); //Starts the handler which does the work
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
        private String name;
        private Socket socket;
        /*private BufferedReader in; OLD READER/WRITER
        private PrintWriter out;*/
        private Card left; // Current cards on top of the deck.
        private Card right;
        private ObjectInput oi; //this has me thinking i need to send a packet of 
        private InputStream is;
        private ObjectOutputStream out;
        private OutputStream outs;

        public Handler(Socket socket) {
            this.socket = socket;
        }// const

    public void run() {
        	
        // Create character streams for the socket. 
        try {
            is = socket.getInputStream();
            oi = new ObjectInputStream(is);
            	
            outs = socket.getOutputStream();
            out = new ObjectOutputStream(outs);          	
            	
            try { 	
                Object temp = null;
                temp = oi.readObject();
                left = (Card) temp; //reads and sets object
                right = left;
                out.writeObject(temp); //sends object through output stream.
                out.flush();
                out.close();
            } 
            catch(IOException e){ 
                e.printStackTrace();
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
	}
        catch(IOException e){ 
                e.printStackTrace();
        }
        finally{
            System.out.println("Server Here:" + left.toString()); //Test to see what happens here.
            /*try {
            //out.flush();
            //out.close();
            } catch (IOException e){
            e.printStackTrace();
            }*/
	}         	
            /*	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true); */
							
        }// run
    }// Handler
}// GameServer
