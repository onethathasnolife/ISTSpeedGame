/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package istspeedgame;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 *
 * @author Bob
 */
public class Client {
    
    private final BufferedReader in;
    private final PrintStream out;
    
    public Client(BufferedReader in, PrintStream out){
        this.in = in;
        this.out = out;
    } // Constructor
    
    public BufferedReader getIn(){
        return in;
    } // getIn
    
    public PrintStream getOut(){
        return out;
    } // getOut
    
} // Client
