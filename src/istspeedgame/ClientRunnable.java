/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package istspeedgame;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author Bob
 */
public class ClientRunnable implements Runnable {
    
    private final Socket server;
    
    public ClientRunnable(Socket server){
        this.server = server;
    }
    
    public void run(){
        try{
           /* BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintStream out = new PrintStream(server.getOutputStream()); */ //I am not sure why buffered reader and print stream.
        	
            //Im going to copypasta my stuff from the old stuff into here.
        	OutputStream out = server.getOutputStream();          //Setup Output Stream In reality this doesnt need to be here.
    		ObjectOutput outs = new ObjectOutputStream(out);      //'Cast' to Object outpost stream.
        	InputStream in = server.getInputStream();
    		ObjectInput ins = new ObjectInputStream(server.getInputStream());
        	//Object a = null;
    		//outs.writeObject(a); //Sockets getting disconnected bc of things.
    		Deck a1 = (Deck) ins.readObject();
            Client client = new Client(in, out);
           
            
            GameUI game = new GameUI(client, a1); //Makes and throws the deck here, kinda makes client useless.
            System.out.println("Game Made");
            //game.initializeComponents();
        }
        catch(IOException | ClassNotFoundException e){
        	e.getStackTrace();
            //System.err.println("IOException: "+e.getMessage());
            System.exit(-1);
        }
    }
    
}
