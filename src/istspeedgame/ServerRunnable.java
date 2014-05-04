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
class ServerRunnable implements Runnable{
    
    private Socket server;
    
    public ServerRunnable(Socket server){
        this.server = server;
    } // Constructor
    
    public void run(){
        try{
            PrintStream out = new PrintStream(server.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            
            System.out.println("Connecting to server...");
            new MainMenuUI(out, in);
        } // try
        catch(IOException e){
            e.printStackTrace();
        } // catch
    } // run
    
} // ServerRunnable
