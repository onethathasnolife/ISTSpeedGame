/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package istspeedgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.PrintStream;
import javax.swing.JFrame;

public class MainMenuUI extends JFrame implements ActionListener{

    private GameUI gameUI;
    private JPanel mainMenuPanel;
    private JButton hostBTN;
    private JButton joinBTN;
    private JButton instBTN;
    private JButton quitBTN;

    public MainMenuUI(PrintStream out, BufferedReader in){
	System.out.println("MainMenuUI - Building");
        
        this.initializeComponents();
	this.setVisible(true);
        this.setSize(450,300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        System.out.println("MainMenuUI - Running");
    } // MainMenuUI : Constructor
    
    public MainMenuUI(){
	System.out.println("MainMenuUI - Building");
        
        this.initializeComponents();
	this.setVisible(true);
        this.setSize(450,300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        System.out.println("MainMenuUI - Running");
    } // MainMenuUI : Constructor

    public void initializeComponents(){
        setTitle("<< SPEED >>");
        setLocationRelativeTo(null);
        hostBTN = new JButton("Host Game");
	joinBTN = new JButton("Join Game");
	instBTN = new JButton("Instructions");
        quitBTN = new JButton("Quit");

        mainMenuPanel = new JPanel(new GridLayout(4,1));
        mainMenuPanel.add(hostBTN);
	mainMenuPanel.add(joinBTN);
	mainMenuPanel.add(instBTN);
        mainMenuPanel.add(quitBTN);
        
	hostBTN.addActionListener(this);
	joinBTN.addActionListener(this);
	instBTN.addActionListener(this);
        quitBTN.addActionListener(this);
        
        this.add(mainMenuPanel);
    } // initializeComponents

    public void actionPerformed(ActionEvent evt){
        Object obj = evt.getSource();
    
        if(obj == hostBTN){
            JOptionPane.showMessageDialog(mainMenuPanel, "Starting Game Server... [NON-FUNCTIONING]");
            GameServer server = new GameServer();
            server.run();
        } // if : hostBTN
        if(obj == joinBTN){
            ClientConnection client = new ClientConnection();
            client.start();
            this.dispose();          
        } // if : joinBTN
        if(obj == instBTN){
            JOptionPane.showMessageDialog(mainMenuPanel, "These are instructions.");
        } // if : instructionsBTN
        if(obj == quitBTN){
            System.exit(0);
        }
    } // actionPerformed

} // MainMenuUI