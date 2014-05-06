/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package istspeedgame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;



import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameUI extends JFrame implements ActionListener{

    private JPanel gamePanel;
    private JPanel P1_Panel;
    private JPanel P2_Panel;
    private JPanel Table_Panel;
    private JPanel mainPanel;
    private JPanel optionsPanel;
    
    private Object lastAction;
    
    private MainMenuUI mainMenuUI;
    
    JButton[] P1_Hand;
    JButton[] P2_Hand;
    JButton[] Table_Mid;
    JButton P1_Deck;
    JButton P2_Deck;
    JButton mainMenuBTN;
    JButton quitBTN;
    JButton restartBTN;
    JLabel timer;
    Deck deck;
    Player P1;
    Player P2;
    Client client;
    
    Socket s;
    boolean gameRunning;
    ObjectOutput outs;
    ObjectInput ins;
    Timer tim;
    int seconds;
    int player;
    
    ImageIcon[] P1_Hand_Icon = new ImageIcon[5];
    ImageIcon[] P2_Hand_Icon = new ImageIcon[5];
    ImageIcon Mid_1, Mid_2;
    
    public GameUI(Client client, Deck a){
    	gameRunning = false;
    	System.out.println("GameUI - Building");
    	 this.client = client;
    	/*try {
			ObjectInput in = new ObjectInputStream(client.getIn());
			deck = (Deck) in.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	deck = a;
    	
       
        P1 = new Player(deck.P1, deck.P1_Hand);
        P2 = new Player(deck.P2, deck.P2_Hand);
        deck.updateHand(P1);
        deck.updateHand(P2);
       
        this.initializeComponents();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();
        tim = new Timer(1000,this);
        tim.addActionListener(this);
        deck.player = player;
        System.out.println("GameUI - Running");
        
        
        
        
      
    } // GameUI : Constructor

    public void initializeComponents(){
        setTitle("<< SPEED >>");
        
        optionsPanel = new JPanel(new GridLayout(1,7));
        P1_Panel = new JPanel(new GridLayout(1,7));
        P2_Panel = new JPanel(new GridLayout(1,7));
        Table_Panel = new JPanel(new GridLayout(1,7));
        
        P1_Panel.setBorder(new EmptyBorder(10,10,10,10));
        P2_Panel.setBorder(new EmptyBorder(10,10,10,10));
        Table_Panel.setBorder(new EmptyBorder(10,10,10,10));
        optionsPanel.setBorder(new EmptyBorder(10,10,10,10));
        
        P1_Hand = new JButton[5];
        P2_Hand = new JButton[5];
        Table_Mid = new JButton[5];
        
        P1_Deck = new JButton("P1 Library ("+P1.deck.size()+")");
        P2_Deck = new JButton("P2 Library ("+P2.deck.size()+")");

        Mid_1 = new ImageIcon("img/" + deck.tableMid.get(0) + ".png");
        Mid_2 = new ImageIcon("img/" + deck.tableMid.get(1) + ".png");
        
        Table_Mid[0] = new JButton("tableLeft ("+deck.tableLeft.size()+")");
        Table_Mid[1] = new JButton(Mid_1);
        Table_Mid[3] = new JButton(Mid_2);
        Table_Mid[4] = new JButton("tableRight ("+deck.tableRight.size()+")");
        
        Table_Mid[1].setOpaque(false);
        Table_Mid[1].setContentAreaFilled(false);
        Table_Mid[1].setBorderPainted(false);
        
        Table_Mid[3].setOpaque(false);
        Table_Mid[3].setContentAreaFilled(false);
        Table_Mid[3].setBorderPainted(false);
        
        for(int i = 0; i < 5; i++){
            
            P1_Hand_Icon[i] = new ImageIcon("img/"+deck.P1_Hand.get(i)+".png");
            P2_Hand_Icon[i] = new ImageIcon("img/"+deck.P2_Hand.get(i)+".png");
            
            P1_Hand[i] = new JButton(P1_Hand_Icon[i]);
            P2_Hand[i] = new JButton(P2_Hand_Icon[i]);
            
            P1_Panel.add(P1_Hand[i]);
            P2_Panel.add(P2_Hand[i]);
            
            P1_Hand[i].setOpaque(false);
            P1_Hand[i].setContentAreaFilled(false);
            P1_Hand[i].setBorderPainted(false);
            
            P2_Hand[i].setOpaque(false);
            P2_Hand[i].setContentAreaFilled(false);
            P2_Hand[i].setBorderPainted(false);
            
            if(i < 5 && i != 2){
                Table_Panel.add(Table_Mid[i]);
                Table_Mid[i].addActionListener(this);
            }
            else{
                Table_Panel.add(new JLabel());
            }
            
            P1_Hand[i].addActionListener(this);
            P2_Hand[i].addActionListener(this);
        }
        
        Table_Panel.add(new JLabel());
        Table_Panel.add(new JLabel());
        mainMenuBTN = new JButton("Main Menu");
        quitBTN = new JButton("Quit");
        restartBTN = new JButton("Restart");
        optionsPanel.add(mainMenuBTN);       
        optionsPanel.add(restartBTN);
        optionsPanel.add(quitBTN);
        timer = new JLabel("Time: 00:00");
        optionsPanel.add(timer);
        
        P1_Panel.add(new JLabel(""));
        P2_Panel.add(new JLabel(""));
        P1_Panel.add(P1_Deck);
        P2_Panel.add(P2_Deck);
        P1_Deck.addActionListener(this);
        P2_Deck.addActionListener(this);
        mainMenuBTN.addActionListener(this);
        quitBTN.addActionListener(this);
        restartBTN.addActionListener(this);
        
        mainPanel = new JPanel(new BorderLayout());
        gamePanel = new JPanel(new BorderLayout());
        
        gamePanel.add(P2_Panel, BorderLayout.NORTH);
        gamePanel.add(Table_Panel, BorderLayout.CENTER);
        gamePanel.add(P1_Panel, BorderLayout.SOUTH);
        mainPanel.add(optionsPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        this.add(mainPanel);
    } // initializeComponents

    public void actionPerformed(ActionEvent evt){
        Object obj = evt.getSource();
    
        // Swap cards
        System.out.println("Last Action: "+lastAction);
        if(obj == Table_Mid[1] || obj == Table_Mid[3]){
            
            //******************************************************************
            //                          PLAYER 1
            //******************************************************************
            if(lastAction == P1_Hand[0]){
                System.out.println("P1 - 0");
                if(obj == Table_Mid[1]){
                    if(deck.swapCard(P1.hand.get(0),deck.tableMid.get(0),P1)){
                        Update();
                    }
                }
                else if(obj == Table_Mid[3]){
                    if(deck.swapCard(P1.hand.get(0),deck.tableMid.get(1),P1)){
                        Update();
                    }
                }
            }
            if(lastAction == P1_Hand[1]){
                System.out.println("P1 - 1");
                if(obj == Table_Mid[1]){
                    if(deck.swapCard(P1.hand.get(1),deck.tableMid.get(0),P1)){
                        Update();
                    }
                }
                else if(obj == Table_Mid[3]){
                    if(deck.swapCard(P1.hand.get(1),deck.tableMid.get(1),P1)){
                        Update();
                    }
                }
            }
            if(lastAction == P1_Hand[2]){
                System.out.println("P1 - 2");
                if(obj == Table_Mid[1]){
                    if(deck.swapCard(P1.hand.get(2),deck.tableMid.get(0),P1)){
                        Update();
                    }
                }
                else if(obj == Table_Mid[3]){
                    if(deck.swapCard(P1.hand.get(2),deck.tableMid.get(1),P1)){
                        Update();
                    }
                }
            }
            if(lastAction == P1_Hand[3]){
                System.out.println("P1 - 3");
                if(obj == Table_Mid[1]){
                    if(deck.swapCard(P1.hand.get(3),deck.tableMid.get(0),P1)){
                        Update();
                    }
                }
                else if(obj == Table_Mid[3]){
                    if(deck.swapCard(P1.hand.get(3),deck.tableMid.get(1),P1)){
                        Update();
                    }
                }
            }
            if(lastAction == P1_Hand[4]){
                System.out.println("P1 - 4");
                if(obj == Table_Mid[1]){
                    if(deck.swapCard(P1.hand.get(4),deck.tableMid.get(0),P1)){
                        Update();
                    }
                }
                else if(obj == Table_Mid[3]){
                    if(deck.swapCard(P1.hand.get(4),deck.tableMid.get(1),P1)){
                        Update();
                    }
                }
            }
            
            //******************************************************************
            //                          PLAYER 2
            //******************************************************************
            if(lastAction == P2_Hand[0]){
                System.out.println("P2 - 0");
                if(obj == Table_Mid[1]){
                    if(deck.swapCard(P2.hand.get(0),deck.tableMid.get(0),P2)){
                        Update();
                    }
                }
                else if(obj == Table_Mid[3]){
                    if(deck.swapCard(P2.hand.get(0),deck.tableMid.get(1),P2)){
                        Update();
                    }
                }
            }
            if(lastAction == P2_Hand[1]){
                System.out.println("P2 - 1");
                if(obj == Table_Mid[1]){
                    if(deck.swapCard(P2.hand.get(1),deck.tableMid.get(0),P2)){
                        Update();
                    }
                }
                else if(obj == Table_Mid[3]){
                    if(deck.swapCard(P2.hand.get(1),deck.tableMid.get(1),P2)){
                        Update();
                    }
                }
            }
            if(lastAction == P2_Hand[2]){
                System.out.println("P2 - 2");
                if(obj == Table_Mid[1]){
                    if(deck.swapCard(P2.hand.get(2),deck.tableMid.get(0),P2)){
                        Update();
                    }
                }
                else if(obj == Table_Mid[3]){
                    if(deck.swapCard(P2.hand.get(2),deck.tableMid.get(1),P2)){
                        Update();
                    }
                }
            }
            if(lastAction == P2_Hand[3]){
                System.out.println("P2 - 3");
                if(obj == Table_Mid[1]){
                    if(deck.swapCard(P2.hand.get(3),deck.tableMid.get(0),P2)){
                        Update();
                    }
                }
                else if(obj == Table_Mid[3]){
                    if(deck.swapCard(P2.hand.get(3),deck.tableMid.get(1),P2)){
                        Update();
                    }
                }
            }
            if(lastAction == P2_Hand[4]){
                System.out.println("P2 - 4");
                if(obj == Table_Mid[1]){
                    if(deck.swapCard(P2.hand.get(4),deck.tableMid.get(0),P2)){
                        Update();
                    }
                }
                else if(obj == Table_Mid[3]){
                    if(deck.swapCard(P2.hand.get(4),deck.tableMid.get(1),P2)){
                        Update();
                    }
                }
            }
        }
        
        // Swap Middle Cards
        if(obj == Table_Mid[0]){
            deck.swapMid(0);
            Update();
        }
        if(obj == Table_Mid[4]){
            deck.swapMid(4);
            Update();
        }
        
        if(obj == restartBTN){
            this.dispose();
        }
        if(obj == P1_Deck || obj == P2_Deck){
        	try {
    			s = new Socket("localhost",5555);
    			OutputStream out = s.getOutputStream();
    	        InputStream in = s.getInputStream();//Setup Output Stream In reality this doesnt need to be here.
    			outs = new ObjectOutputStream(out);
    			ins = new ObjectInputStream(in);
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}      //'Cast' to Object outpost stream.
        	tim.start();
        	this.Update();
        }
        if(obj == tim){
        	
        	  try {	
        		s = new Socket("localhost",5555);
      			OutputStream out = s.getOutputStream();
      	        InputStream in = s.getInputStream();//Setup Output Stream In reality this doesnt need to be here.
      			outs = new ObjectOutputStream(out);
      			ins = new ObjectInputStream(in);
      			outs.writeObject(deck);
      			deck = (Deck) ins.readObject();
      			if(deck.player != player){
      				Object temp = deck.P1; 
      				deck.P1 = deck.P2;
      				deck.P2 = (ArrayList<Card>) temp;
      			}
      		} catch (IOException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		} catch (ClassNotFoundException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
        	seconds++;
        	timer.setText("Time: " + seconds);
        }
        // if : quit
        if(obj == quitBTN){
            System.exit(0);
        } else if (obj == mainMenuBTN) {
        	mainMenuUI = new MainMenuUI();
        	this.dispose();
        }
        
        lastAction = obj;
    }
    
    public void Update(){
        for(int i = 0; i < P1.hand.size(); i++){
            P1_Hand_Icon[i] = new ImageIcon("img/"+P1.hand.get(i)+".png");
            P1_Hand[i].setIcon(P1_Hand_Icon[i]);
        }
        for(int i = 0; i < P2.hand.size(); i++){
            P2_Hand_Icon[i] = new ImageIcon("img/"+P2.hand.get(i)+".png");
            P2_Hand[i].setIcon(P2_Hand_Icon[i]);
        }
        if(P1.hand.isEmpty()){
            for(int i = deck.P1_Hand.size(); i < 5; i++){
                P1_Hand_Icon[i] = new ImageIcon("img/x.png");
                P1_Hand[i].setIcon(P1_Hand_Icon[i]);
            }
        }
        if(P2.deck.isEmpty()){
            for(int i = deck.P2_Hand.size(); i < 5; i++){
                P2_Hand_Icon[i] = new ImageIcon("img/x.png");
                P2_Hand[i].setIcon(P2_Hand_Icon[i]);
            }
        }
        Mid_1 = new ImageIcon("img/" + deck.tableMid.get(0) + ".png");
        Mid_2 = new ImageIcon("img/" + deck.tableMid.get(1) + ".png");
        Table_Mid[1].setIcon(Mid_1);
        Table_Mid[3].setIcon(Mid_2);
        
        P1_Deck.setText("P1 Library ("+P1.deck.size()+")");
        P2_Deck.setText("P2 Library ("+P2.deck.size()+")");
        
        Table_Mid[0].setText("tableLeft ("+deck.tableLeft.size()+")");
        Table_Mid[4].setText("tableRight ("+deck.tableRight.size()+")");
        
        this.repaint();
        //Write
        try {
			s = new Socket("localhost",5555);
			OutputStream out = s.getOutputStream();
	        InputStream in = s.getInputStream();//Setup Output Stream In reality this doesnt need to be here.
			outs = new ObjectOutputStream(out);
			ins = new ObjectInputStream(in);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      //'Cast' to Object outpost stream.
        try {
			outs.writeObject(deck);
			deck = (Deck) ins.readObject();
  			if(deck.player != player){
  				deck.P1 = deck.P2;
  			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
    }

} // MainMenuUI