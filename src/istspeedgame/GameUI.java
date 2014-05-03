/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package istspeedgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class GameUI extends JFrame implements ActionListener{

    private JPanel gamePanel;
    private JPanel P1_Panel;
    private JPanel P2_Panel;
    private JPanel Table_Panel;
    private JPanel mainPanel;
    private JPanel optionsPanel;
    
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
    
    ImageIcon[] P1_Hand_Icon = new ImageIcon[5];
    ImageIcon[] P2_Hand_Icon = new ImageIcon[5];
    
    public GameUI(){
    	System.out.println("GameUI - Building");
        
        deck = new Deck();
        deck.updateHand(deck.P1, deck.P1_Hand);
        deck.updateHand(deck.P2, deck.P2_Hand);
        
        this.initializeComponents();
        this.setVisible(true);
        this.setSize(1200,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    
        System.out.println("GameUI - Running");
      
    } // GameUI : Constructor

    public void initializeComponents(){
        setTitle("<< SPEED >>");
        setLocationRelativeTo(null);
          
        optionsPanel = new JPanel(new GridLayout(1,7));
        P1_Panel = new JPanel(new GridLayout(1,7));
        P2_Panel = new JPanel(new GridLayout(1,7));
        Table_Panel = new JPanel(new GridLayout(1,7));
        P1_Hand = new JButton[5];
        P2_Hand = new JButton[5];
        Table_Mid = new JButton[2];
        
        P1_Deck = new JButton("P1 Library ("+deck.P1.size()+")");
        P2_Deck = new JButton("P2 Library ("+deck.P2.size()+")");

        ImageIcon Mid_1 = new ImageIcon("img/" + deck.tableMid.get(0) + ".png");
        ImageIcon Mid_2 = new ImageIcon("img/" + deck.tableMid.get(1) + ".png");
        Table_Mid[0] = new JButton(Mid_1);
        Table_Mid[1] = new JButton(Mid_2);
        
        for(int i = 0; i < 5; i++){
            P1_Hand_Icon[i] = new ImageIcon("img/"+deck.P1_Hand.get(i)+".png");
            P2_Hand_Icon[i] = new ImageIcon("img/"+deck.P2_Hand.get(i)+".png");
            
            P1_Hand[i] = new JButton(P1_Hand_Icon[i]);
            P2_Hand[i] = new JButton(P2_Hand_Icon[i]);
            
            P1_Panel.add(P1_Hand[i]);
            P2_Panel.add(P2_Hand[i]);
            
            if(i == 1){
                Table_Panel.add(Table_Mid[0]);
                Table_Mid[0].addActionListener(this);
            }
            if(i == 3){
                Table_Panel.add(Table_Mid[1]);
                Table_Mid[1].addActionListener(this);
            }
            
            P1_Hand[i].addActionListener(this);
            P2_Hand[i].addActionListener(this);
        }
  
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
        
        // if : quit
        if(obj == quitBTN){
            System.exit(0);
        } else if (obj == mainMenuBTN) {
        	mainMenuUI = new MainMenuUI();
        	this.dispose();
        }
    }
    
    public void Update(){
        
    }

} // MainMenuUI