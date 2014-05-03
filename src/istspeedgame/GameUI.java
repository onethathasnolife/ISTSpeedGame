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
    private JPanel hostPanel;
    private JPanel guestPanel;
    private JPanel activePanel;
    private JPanel mainPanel;
    private JPanel optionsPanel;
    
    private MainMenuUI mainMenuUI;
    
    JButton[] hostCards;
    JButton[] guestCards;
    JButton[] activeCards;
    JButton hostLibrary;
    JButton guestLibrary;
    JButton mainMenuBTN;
    JButton quitBTN;
    JButton restartBTN;
    JLabel timer;
    Deck deck;
    
    public GameUI(){
    	System.out.println("GameUI - Building");
        
        deck = new Deck();
        
        this.initializeComponents();
        this.setVisible(true);
        this.setSize(1200,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    
        System.out.println("GameUI - Running");
    } // GameUI : Constructor

    public void initializeComponents(){
        setTitle("<< SPEED | Hosting Game >>");
        setLocationRelativeTo(null);
        
        hostCards = new JButton[5];
        guestCards = new JButton[5];
        activeCards = new JButton[2];
        
        hostLibrary = new JButton("Host \nLibrary");
        guestLibrary = new JButton("Guest \nLibrary");
        
        // How to add images to buttons
        ImageIcon A1 = new ImageIcon("img/" + deck.tableMid.get(0) + ".png");
        ImageIcon A2 = new ImageIcon("img/" + deck.tableMid.get(1) + ".png");
        
        activeCards[0] = new JButton(A1);
        activeCards[1] = new JButton(A2);
        
        optionsPanel = new JPanel(new GridLayout(1,7));
        hostPanel = new JPanel(new GridLayout(1,7));
        guestPanel = new JPanel(new GridLayout(1,7));
        activePanel = new JPanel(new GridLayout(1,7));
        
        mainMenuBTN = new JButton("Main Menu");
        quitBTN = new JButton("Quit");
        restartBTN = new JButton("Restart");
        
        // Temporary Setup
        for(int i = 0; i < 5; i++){
            hostCards[i] = new JButton ("H"+(i+1));
            hostPanel.add(hostCards[i]);
            guestCards[i] = new JButton ("G"+(i+1));
            guestPanel.add(guestCards[i]);
            if(i == 1){
                activeCards[0].setMaximumSize(new Dimension (40, 80));
                activePanel.add(activeCards[0]);
                activeCards[0].addActionListener(this);
            }
            else if(i == 3){
                activeCards[0].setMaximumSize(new Dimension (40, 80));
                activePanel.add(activeCards[1]);
                activeCards[1].addActionListener(this);
            }
            else{
                activePanel.add(new JLabel(""));
            }
            hostCards[i].addActionListener(this);
            guestCards[i].addActionListener(this);
        }
        optionsPanel.add(mainMenuBTN);       
        optionsPanel.add(restartBTN);
        optionsPanel.add(quitBTN);
        timer = new JLabel("Time: 00:00");
        optionsPanel.add(timer);
        
        hostPanel.add(new JLabel(""));
        guestPanel.add(new JLabel(""));
        hostPanel.add(hostLibrary);
        guestPanel.add(guestLibrary);
        hostLibrary.addActionListener(this);
        guestLibrary.addActionListener(this);
        mainMenuBTN.addActionListener(this);
        quitBTN.addActionListener(this);
        restartBTN.addActionListener(this);
        
        mainPanel = new JPanel(new BorderLayout());
        gamePanel = new JPanel(new BorderLayout());
        
        gamePanel.add(guestPanel, BorderLayout.NORTH);
        gamePanel.add(activePanel, BorderLayout.CENTER);
        gamePanel.add(hostPanel, BorderLayout.SOUTH);
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

} // MainMenuUI