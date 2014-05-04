/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package istspeedgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintStream out = new PrintStream(server.getOutputStream());
            
            Client client = new Client(in, out);
            
            GameUI game = new GameUI(client);
        }
        catch(IOException e){
            System.err.println("IOException: "+e.getMessage());
            System.exit(-1);
        }
    }
    
}
