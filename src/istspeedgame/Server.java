/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package istspeedgame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Bob
 */
public class Server {
    
    private static final int PORT = 5555;
    private static boolean start = false;
    
    public void start(){
        
        ServerSocket listener;
        
        try{
            listener = new ServerSocket(getPort());
            Socket server;
            
            start = true;
            while(start){
                server = listener.accept();
                ServerRunnable run = new ServerRunnable(server);
                Thread thread = new Thread(run);
                thread.start();
            } // while
        } // try
        catch(IOException e){
            e.printStackTrace();
        } // catch     
    } // start
    
    public static int getPort(){
            return PORT;
    } // getPort
    
} // Server
