/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package istspeedgame;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Bob
 */
public class ClientConnection {
    
    private static final String HOST = "localhost";
    private static final int PORT = 5555;
    private static Socket clientSocket;
    
    public void start(){
        connect();
    } // start
    
    public static void connect(){
        try{
            clientSocket = new Socket(HOST, PORT);
            ClientRunnable runnable = new ClientRunnable(clientSocket);
            
            Thread thread = new Thread(runnable);
            thread.start();
        } // try
        catch(UnknownHostException e){
            System.err.println("UnknownHostException: "+e.getMessage());
            System.exit(-1);
        } // catch
        catch(ConnectException e){
            System.out.println("Server disconnected.");
            System.exit(0);
        }
        catch(SocketException e){
            System.out.println("Server disconnected.");
            System.exit(0);
        }
        catch(IOException e){
            System.err.println("IOException: "+e.getMessage());
            System.exit(-1);
        } // catch
    } // connect
    
    public static void disconnect(){
        try{
            if(clientSocket != null){
                clientSocket.close();
            } // if
        } // try
        catch(IOException e){
            System.err.println("IOException: "+e.getMessage());
        } // catch
    } // disconnect
    
} // ClientConnection
