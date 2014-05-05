/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package istspeedgame;

import java.io.*;

/**
 *
 * @author Bob
 */
public class Client {
    
    /*private final BufferedReader in;
    private final PrintStream out;*/
	private final InputStream in;
	private final OutputStream out;
    
    public Client(/*BufferedReader in, PrintStream out*/InputStream in, OutputStream out){
        this.in = in;
        this.out = out;
    } // Constructor
    
    public InputStream getIn(){
    	return in;
    }
    public OutputStream getOut(){
    	return out;
    }
    /*public BufferedReader getIn(){
        return in;
    } // getIn
    
    public PrintStream getOut(){
        return out;
    } // getOut*/
    
} // Client
