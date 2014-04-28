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

public class MainMenuUI extends JFrame implements ActionListener{

    private GameUI gameUI;
    private JPanel mainMenuPanel;
    private JButton hostBTN;
    private JButton joinBTN;
    private JButton instructionsBTN;

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
        hostBTN      = new JButton("Host Game");
	joinBTN     = new JButton("Join Game");
	instructionsBTN = new JButton("Instructions");

        mainMenuPanel = new JPanel(new GridLayout(3,1));
        mainMenuPanel.add(hostBTN, BorderLayout.NORTH);
	mainMenuPanel.add(joinBTN, BorderLayout.CENTER);
	mainMenuPanel.add(instructionsBTN, BorderLayout.SOUTH);
        
	hostBTN.addActionListener(this);
	joinBTN.addActionListener(this);
	instructionsBTN.addActionListener(this);
        
        this.add(mainMenuPanel);
    } // initializeComponents

    public void actionPerformed(ActionEvent evt){
        Object obj = evt.getSource();
    
        if(obj == hostBTN){
            gameUI = new GameUI();
            this.dispose();
        } // if : hostBTN
        if(obj == joinBTN){
            JOptionPane.showMessageDialog(mainMenuPanel, "Joining game... [NON-FUNCTIONING]");
        } // if : joinBTN
        if(obj == instructionsBTN){
            JOptionPane.showMessageDialog(mainMenuPanel, "These are instructions.");
        } // if : instructionsBTN
    } // actionPerformed

} // MainMenuUI