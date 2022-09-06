/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package uno_game.gui;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.*;
import uno_game.Deck;
import uno_game.Player;
import uno_game.cards.*;
import uno_game.exception.*;

/**
 *
 * @author Matthieu Sajot
 * jframe to display the game board
 */
public class GameBoard extends javax.swing.JFrame {
    
    //all the buttons are grouped in arraylists
    private ArrayList<JButton> cardBtnP1 = new  ArrayList();
    private ArrayList<JButton> cardBtnP2 = new  ArrayList();
    private ArrayList<JButton> cardBtnP3= new  ArrayList();
    private ArrayList<JButton> cardBtnP4= new  ArrayList();
    
    private ArrayList<Player> players;
    private Deck deck;
    private ArrayList<Card> pile;
    
    //the action variable is used to retrieve which button the user presses
    private int action = -1;
    //number of the sister whose turn it is to play
    private int turn = 0;
    //direction of play rotation
    private boolean rotate = true;
    //current colour to play
    private int currentColor=0;
    
    //we retrieve the joeurs, the deck and the Game Run 
    public GameBoard(ArrayList<Player> p, Deck d, ArrayList<Card> l ) {
        initComponents();
        setTitle("UNO GAME by Matthieu Sajot");
        setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon("src/image/icone.png");
        setIconImage(image.getImage());
        
        players=p;
        deck=d;
        pile=l;
        /*********************************************************************************************************************************************************************************************************************************************/
        //hides all pannels and components, they will be re-displayed depending on the number of players.
        namePannel1.setVisible(false);
        namePannel2.setVisible(false);
        namePannel3.setVisible(false);
        namePannel4.setVisible(false);
        scrollPannelP1.setVisible(false);
        scrollPannelP2.setVisible(false);
        scrollPannelP3.setVisible(false);
        scrollPannelP4.setVisible(false);
        
        for(int i=0;i<players.size();i++){
            switch(i){
                case 0 : nameP1.setText(players.get(i).getName());
                namePannel1.setVisible(true);
                scrollPannelP1.setVisible(true);
                break;
                case 1 :  nameP2.setText(players.get(i).getName());
                namePannel2.setVisible(true);
                scrollPannelP2.setVisible(true);
                break;
                case 2 : nameP3.setText(players.get(i).getName());
                namePannel3.setVisible(true);
                scrollPannelP3.setVisible(true);
                break;
                case 3 :  nameP4.setText(players.get(i).getName());
                namePannel4.setVisible(true);
                scrollPannelP4.setVisible(true);
                break;
            }
        }
        
        cardBtnP1.add(cardButton0);cardBtnP1.add(cardButton1);cardBtnP1.add(cardButton2);cardBtnP1.add(cardButton3);
        cardBtnP1.add(cardButton4);cardBtnP1.add(cardButton5);cardBtnP1.add(cardButton6);cardBtnP1.add(cardButton7);
        cardBtnP1.add(cardButton8);cardBtnP1.add(cardButton9);cardBtnP1.add(cardButton10);cardBtnP1.add(cardButton11);
        cardBtnP1.add(cardButton12);cardBtnP1.add(cardButton13);cardBtnP1.add(cardButton14);cardBtnP1.add(cardButton15);
        for(JButton b : cardBtnP1){
            b.setVisible(false);
        }
        cardBtnP2.add(cardButton16);cardBtnP2.add(cardButton17);cardBtnP2.add(cardButton18);cardBtnP2.add(cardButton19);
        cardBtnP2.add(cardButton20);cardBtnP2.add(cardButton21);cardBtnP2.add(cardButton22);cardBtnP2.add(cardButton23);
        cardBtnP2.add(cardButton24);cardBtnP2.add(cardButton25);cardBtnP2.add(cardButton26);cardBtnP2.add(cardButton27);
        cardBtnP2.add(cardButton28);cardBtnP2.add(cardButton29);cardBtnP2.add(cardButton30);cardBtnP2.add(cardButton31);
        for(JButton b : cardBtnP2){
            b.setVisible(false);
        }
        cardBtnP3.add(cardButton32);cardBtnP3.add(cardButton33);cardBtnP3.add(cardButton34);cardBtnP3.add(cardButton35);
        cardBtnP3.add(cardButton36);cardBtnP3.add(cardButton37);cardBtnP3.add(cardButton38);cardBtnP3.add(cardButton39);
        cardBtnP3.add(cardButton40);cardBtnP3.add(cardButton41);cardBtnP3.add(cardButton42);cardBtnP3.add(cardButton43);
        cardBtnP3.add(cardButton44);cardBtnP3.add(cardButton45);cardBtnP3.add(cardButton46);cardBtnP3.add(cardButton47);
        for(JButton b : cardBtnP3){
            b.setVisible(false);
        }
        cardBtnP4.add(cardButton48);cardBtnP4.add(cardButton49);cardBtnP4.add(cardButton50);cardBtnP4.add(cardButton51);
        cardBtnP4.add(cardButton52);cardBtnP4.add(cardButton53);cardBtnP4.add(cardButton54);cardBtnP4.add(cardButton55);
        cardBtnP4.add(cardButton56);cardBtnP4.add(cardButton57);cardBtnP4.add(cardButton58);cardBtnP4.add(cardButton59);
        cardBtnP4.add(cardButton60);cardBtnP4.add(cardButton61);cardBtnP4.add(cardButton62);cardBtnP4.add(cardButton63);
        for(JButton b : cardBtnP4){
            b.setVisible(false);
        }
        /*********************************************************************************************************************************************************************************************************************************************/
        
        //ddisplays the cards of all players
        for(int i =0;i<players.size();i++)
                displayCards(i);
        //displays the back of the cards
        hideCard();
        //displays the cards of the player whose turn it is to play
        displayCards(turn);
        //displays the name of the player whose turn it is to play
        turnName.setText(players.get(turn).getName());
    }
    
    //displays the game board
    public void displayGame() {
        setVisible(true);
        
        //as long as the game is not finished.
        while(endGame()){
            
            //small delay to allow time to update action, we update the board
            try {        Thread.sleep(50);        } catch (InterruptedException ie) {}
            
            //when a button is activated
            if(action!=-1){
                //reset action to be able to detect a new change
                resetAction();
                //hides all the cards and shows only those of the player who has to play.
                hideCard();
                displayCards(turn);
                //updates the name of the turn player
                turnName.setText(players.get(turn).getName());
                setVisible(true);
            }
        }
        
        //hides the board because the game is over
        setVisible(false);
    }
    
    //displays the back of the cards to all the cards
    //then it is necessary to display the cards on the right side only for the player whose turn it is.
    public void hideCard(){
        for(JButton b : cardBtnP1)
            b.setVisible(false);
        
        for(int i=0;i<players.get(0).getHandSize();i++){
            cardBtnP1.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png")));
            cardBtnP1.get(i).setVisible(true);
        }
        for(JButton b : cardBtnP2)
            b.setVisible(false);
        for(int i=0;i<players.get(1).getHandSize();i++){
            cardBtnP2.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png")));
            cardBtnP2.get(i).setVisible(true);
        }
        if(players.size()>2){
            for(JButton b : cardBtnP3)
                b.setVisible(false);
            
            for(int i=0;i<players.get(2).getHandSize();i++){
                cardBtnP3.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png")));
                cardBtnP3.get(i).setVisible(true);
            }}
        if(players.size()>3){
            for(JButton b : cardBtnP4)
                b.setVisible(false);
            for(int i=0;i<players.get(3).getHandSize();i++){
                cardBtnP4.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png")));
                cardBtnP4.get(i).setVisible(true);
            }}
    }
    
    //displays the cards of the player whose name is passed in parameter
    public void displayCards(int playerNumb){
        
        ArrayList<JButton> cardBut = new  ArrayList();
        switch(playerNumb){
            case 0 -> cardBut=cardBtnP1;
            case 1 -> cardBut=cardBtnP2;
            case 2 -> cardBut=cardBtnP3;
            case 3 -> cardBut=cardBtnP4;
        }
        
        for(int i=0;i<players.get(playerNumb).getHandSize();i++){
            cardBut.get(i).setVisible(true);
            
            if(players.get(playerNumb).getCard(i) instanceof WildCard){
                cardBut.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/w.png")));
            }else{
                if(players.get(playerNumb).getCard(i) instanceof WildDrawCard){
                    cardBut.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/p.png")));
                }else{
                    cardBut.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/"+ players.get(playerNumb).getCard(i).getColour() +"_" + players.get(playerNumb).getCard(i).getSymbol() + ".png")));
                }
            }
        }
        switch(playerNumb){
            case 0 -> cardBtnP1=cardBut;
            case 1 -> cardBtnP2=cardBut;
            case 2 -> cardBtnP3=cardBut;
            case 3 -> cardBtnP4=cardBut;
        }
        
        Card actualPile=pile.get(pile.size()-1);
        if(actualPile instanceof WildCard){
            pileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/w.png")));
        }else{
            if(actualPile instanceof WildDrawCard){
                pileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/p.png")));
            }else{
                pileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/"+ actualPile.getColour() +"_" + actualPile.getSymbol() + ".png")));
            }
        }
        
    }
    
    public void resetAction(){
        action=-1;
    }
    
    //at a click on a button we do the following algorithm
    public void  requestButton(int n){
        action = n;
        try{
            //we check if the button that was clicked belonged to the player whose turn it is to play
            canPlayTurn(action,turn);
            //we check if the button that has been clicked allows to play a card that is playable
            canPlayCard(action,players,currentColor,pile.get(pile.size()-1));
            //if everything is good we play the card
            makeAction(action);
        }catch(Exception ex ){
            //displays an error message if this card cannot be played
            setMessage(ex.getMessage());
            setVisible(true);
        }
        
    }   
    
    //plays the card that has been selected
    public void makeAction(int action){
        
        messageText.setText("");
        ///action=100 is the same as pressing the deck button. 
        if(action==100)
            players.get(turn).drawCard(deck,1);
        else{
            
            int i =-1,j = 0;
            if(action<=15 && action >=0)
                i=0;
            if(action<=31 && action >=16)
                i=1;
            if(action<=46 && action >=31)
                i=2;
            if(action<=63 && action >=46)
                i=3;
            
            j=action%16;
            //the variables i and j make it possible to identify which player wants to play which card
            
            //effect of each card on the game
            if(players.get(i).getCard(j) instanceof DrawCard){
                players.get(getNextPlayer()).drawCard(deck,2);
                nextTurn();
            }
            if(players.get(i).getCard(j) instanceof ReverseCard){
                rotate = !rotate;
                if(players.size()==2)
                    nextTurn();
            }
            if(players.get(i).getCard(j) instanceof SkipCard){
                nextTurn();
            }
            if(players.get(i).getCard(j) instanceof WildDrawCard || players.get(i).getCard(j) instanceof WildCard){
                WildPannel choice = new WildPannel();
                choice.setVisible(true);
                players.get(i).getCard(j).setColour(currentColor);
            }
            if(players.get(i).getCard(j) instanceof WildDrawCard){
                players.get(getNextPlayer()).drawCard(deck,4);
                nextTurn();
            }
            
            //playing the card: updates the current colour, adds the card to the pile and removes it from a player's hand
            currentColor=players.get(i).getCard(j).getColour();
            pile.add(players.get(i).getCard(j));
            players.get(i).removeCard(j);
        }
        //change the round
        nextTurn();
    }
    
    //refuses the move if the button and the turn do not match
    static void canPlayTurn(int action, int turn) throws WrongPlayer{
        if((action<=15 && action >=0) && turn!=0)
            throw new WrongPlayer("It's not your turn to play");
        if((action<=31 && action >=16) && turn!=1)
            throw new WrongPlayer("It's not your turn to play");
        if((action<=47 && action >=32) && turn !=2)
            throw new WrongPlayer("It's not your turn to play");
        if((action<=63 && action >=48) && turn !=3)
            throw new WrongPlayer("It's not your turn to play");
    }
    
    //refuses the move if the card is not playable
    static void canPlayCard(int action, ArrayList<Player> players, int currentColor, Card pile) throws WrongPlayer{
        
        if(action>=0 && action<=63){
            int i =-1,j = 0;
            j=action%16;
            if(action<=15 && action >=0)
                i=0;
            if(action<=31 && action >=16)
                i=1;
            if(action<=46 && action >=31)
                i=2;
            if(action<=63 && action >=46)
                i=3;
            
            if(!players.get(i).getCard(j).canPlay(pile,currentColor))
                throw new WrongPlayer("You can't play this card");
        }
    }
    
    //if a player has no more cards in hand, stops the game and indicates the winning player
    public boolean endGame(){
        for(int i =0;i<players.size();i++){
            if(players.get(i).emptyHand()){
                EndPannel end = new EndPannel(players.get(i).getName());
                end.setVisible(true);
                return false;
            }
        }
        return true;
    }
    
    public void setMessage(String s){
        messageText.setText(s);
    }
    
    public void nextTurn(){
        turn = getNextPlayer();
    }
    
    //calculates the next game based on turn and rotation
    public int getNextPlayer(){
        int  next = turn;
        if(rotate)
            next++;
        else
            next--;
        
        if(next==players.size())
            next=0;
        
        if( next==-1)
            next=players.size()-1;
        
        return next;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        boardPannel = new javax.swing.JPanel();
        namePannel1 = new javax.swing.JPanel();
        nameP1 = new javax.swing.JLabel();
        arrowP1 = new javax.swing.JLabel();
        scrollPannelP1 = new javax.swing.JScrollPane();
        cardPannelP1 = new javax.swing.JPanel();
        cardButton8 = new javax.swing.JButton();
        cardButton0 = new javax.swing.JButton();
        cardButton1 = new javax.swing.JButton();
        cardButton2 = new javax.swing.JButton();
        cardButton3 = new javax.swing.JButton();
        cardButton5 = new javax.swing.JButton();
        cardButton4 = new javax.swing.JButton();
        cardButton6 = new javax.swing.JButton();
        cardButton7 = new javax.swing.JButton();
        cardButton9 = new javax.swing.JButton();
        cardButton10 = new javax.swing.JButton();
        cardButton11 = new javax.swing.JButton();
        cardButton12 = new javax.swing.JButton();
        cardButton13 = new javax.swing.JButton();
        cardButton14 = new javax.swing.JButton();
        cardButton15 = new javax.swing.JButton();
        namePannel2 = new javax.swing.JPanel();
        nameP2 = new javax.swing.JLabel();
        arrowP3 = new javax.swing.JLabel();
        namePannel3 = new javax.swing.JPanel();
        nameP3 = new javax.swing.JLabel();
        arrowP4 = new javax.swing.JLabel();
        namePannel4 = new javax.swing.JPanel();
        nameP4 = new javax.swing.JLabel();
        arrowP2 = new javax.swing.JLabel();
        scrollPannelP2 = new javax.swing.JScrollPane();
        cardPannelP2 = new javax.swing.JPanel();
        cardButton16 = new javax.swing.JButton();
        cardButton17 = new javax.swing.JButton();
        cardButton18 = new javax.swing.JButton();
        cardButton19 = new javax.swing.JButton();
        cardButton20 = new javax.swing.JButton();
        cardButton21 = new javax.swing.JButton();
        cardButton22 = new javax.swing.JButton();
        cardButton23 = new javax.swing.JButton();
        cardButton24 = new javax.swing.JButton();
        cardButton25 = new javax.swing.JButton();
        cardButton26 = new javax.swing.JButton();
        cardButton27 = new javax.swing.JButton();
        cardButton28 = new javax.swing.JButton();
        cardButton29 = new javax.swing.JButton();
        cardButton30 = new javax.swing.JButton();
        cardButton31 = new javax.swing.JButton();
        scrollPannelP3 = new javax.swing.JScrollPane();
        cardPannelP3 = new javax.swing.JPanel();
        cardButton32 = new javax.swing.JButton();
        cardButton33 = new javax.swing.JButton();
        cardButton34 = new javax.swing.JButton();
        cardButton35 = new javax.swing.JButton();
        cardButton36 = new javax.swing.JButton();
        cardButton37 = new javax.swing.JButton();
        cardButton38 = new javax.swing.JButton();
        cardButton39 = new javax.swing.JButton();
        cardButton40 = new javax.swing.JButton();
        cardButton41 = new javax.swing.JButton();
        cardButton42 = new javax.swing.JButton();
        cardButton43 = new javax.swing.JButton();
        cardButton44 = new javax.swing.JButton();
        cardButton45 = new javax.swing.JButton();
        cardButton46 = new javax.swing.JButton();
        cardButton47 = new javax.swing.JButton();
        scrollPannelP4 = new javax.swing.JScrollPane();
        cardPannelP4 = new javax.swing.JPanel();
        cardButton48 = new javax.swing.JButton();
        cardButton49 = new javax.swing.JButton();
        cardButton50 = new javax.swing.JButton();
        cardButton51 = new javax.swing.JButton();
        cardButton52 = new javax.swing.JButton();
        cardButton53 = new javax.swing.JButton();
        cardButton54 = new javax.swing.JButton();
        cardButton55 = new javax.swing.JButton();
        cardButton56 = new javax.swing.JButton();
        cardButton57 = new javax.swing.JButton();
        cardButton58 = new javax.swing.JButton();
        cardButton59 = new javax.swing.JButton();
        cardButton60 = new javax.swing.JButton();
        cardButton61 = new javax.swing.JButton();
        cardButton62 = new javax.swing.JButton();
        cardButton63 = new javax.swing.JButton();
        centerPannel = new javax.swing.JPanel();
        deckButton = new javax.swing.JButton();
        messagePannel = new javax.swing.JPanel();
        messageText = new javax.swing.JLabel();
        turnName = new javax.swing.JLabel();
        turnText = new javax.swing.JLabel();
        pileButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        kGradientPanel1.setkEndColor(new java.awt.Color(163, 3, 3));
        kGradientPanel1.setkStartColor(new java.awt.Color(110, 36, 36));

        boardPannel.setBackground(new java.awt.Color(153, 153, 153));
        boardPannel.setOpaque(false);

        namePannel1.setBackground(new java.awt.Color(0, 153, 153));
        namePannel1.setOpaque(false);

        nameP1.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 12)); // NOI18N
        nameP1.setForeground(new java.awt.Color(0, 0, 0));
        nameP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameP1.setText("Player 1");

        arrowP1.setBackground(new java.awt.Color(153, 153, 255));
        arrowP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow/arrow_right.png"))); // NOI18N

        javax.swing.GroupLayout namePannel1Layout = new javax.swing.GroupLayout(namePannel1);
        namePannel1.setLayout(namePannel1Layout);
        namePannel1Layout.setHorizontalGroup(
            namePannel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePannel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameP1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(namePannel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(arrowP1)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        namePannel1Layout.setVerticalGroup(
            namePannel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePannel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameP1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(arrowP1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        scrollPannelP1.setBackground(new java.awt.Color(153, 0, 153));
        scrollPannelP1.setForeground(new java.awt.Color(204, 0, 51));
        scrollPannelP1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPannelP1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        scrollPannelP1.setOpaque(false);

        cardPannelP1.setBackground(new java.awt.Color(153, 255, 153));
        cardPannelP1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cardPannelP1.setOpaque(false);

        cardButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton8.setToolTipText("");
        cardButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton8ActionPerformed(evt);
            }
        });

        cardButton0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton0.setToolTipText("");
        cardButton0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton0ActionPerformed(evt);
            }
        });

        cardButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton1.setToolTipText("");
        cardButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton1ActionPerformed(evt);
            }
        });

        cardButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton2.setToolTipText("");
        cardButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton2ActionPerformed(evt);
            }
        });

        cardButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton3.setToolTipText("");
        cardButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton3ActionPerformed(evt);
            }
        });

        cardButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton5.setToolTipText("");
        cardButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton5ActionPerformed(evt);
            }
        });

        cardButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton4.setToolTipText("");
        cardButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton4ActionPerformed(evt);
            }
        });

        cardButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton6.setToolTipText("");
        cardButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton6ActionPerformed(evt);
            }
        });

        cardButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton7.setToolTipText("");
        cardButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton7ActionPerformed(evt);
            }
        });

        cardButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton9.setToolTipText("");
        cardButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton9ActionPerformed(evt);
            }
        });

        cardButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton10.setToolTipText("");
        cardButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton10ActionPerformed(evt);
            }
        });

        cardButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton11.setToolTipText("");
        cardButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton11ActionPerformed(evt);
            }
        });

        cardButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton12.setToolTipText("");
        cardButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton12ActionPerformed(evt);
            }
        });

        cardButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton13.setToolTipText("");
        cardButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton13ActionPerformed(evt);
            }
        });

        cardButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton14.setToolTipText("");
        cardButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton14ActionPerformed(evt);
            }
        });

        cardButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton15.setToolTipText("");
        cardButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cardPannelP1Layout = new javax.swing.GroupLayout(cardPannelP1);
        cardPannelP1.setLayout(cardPannelP1Layout);
        cardPannelP1Layout.setHorizontalGroup(
            cardPannelP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPannelP1Layout.createSequentialGroup()
                .addComponent(cardButton0, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cardButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardPannelP1Layout.setVerticalGroup(
            cardPannelP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPannelP1Layout.createSequentialGroup()
                .addGroup(cardPannelP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton0, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPannelP1.setViewportView(cardPannelP1);

        namePannel2.setBackground(new java.awt.Color(0, 204, 51));
        namePannel2.setOpaque(false);

        nameP2.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 12)); // NOI18N
        nameP2.setForeground(new java.awt.Color(0, 0, 0));
        nameP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameP2.setText("Player 2");

        arrowP3.setBackground(new java.awt.Color(153, 153, 255));
        arrowP3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow/arrow_left.png"))); // NOI18N

        javax.swing.GroupLayout namePannel2Layout = new javax.swing.GroupLayout(namePannel2);
        namePannel2.setLayout(namePannel2Layout);
        namePannel2Layout.setHorizontalGroup(
            namePannel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePannel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameP2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(namePannel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(arrowP3)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        namePannel2Layout.setVerticalGroup(
            namePannel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, namePannel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(arrowP3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameP2)
                .addGap(27, 27, 27))
        );

        namePannel3.setBackground(new java.awt.Color(204, 204, 0));
        namePannel3.setOpaque(false);

        nameP3.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 12)); // NOI18N
        nameP3.setForeground(new java.awt.Color(0, 0, 0));
        nameP3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameP3.setText("Player 3");

        arrowP4.setBackground(new java.awt.Color(153, 153, 255));
        arrowP4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow/arrow_top.png"))); // NOI18N

        javax.swing.GroupLayout namePannel3Layout = new javax.swing.GroupLayout(namePannel3);
        namePannel3.setLayout(namePannel3Layout);
        namePannel3Layout.setHorizontalGroup(
            namePannel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePannel3Layout.createSequentialGroup()
                .addComponent(nameP3, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(namePannel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(arrowP4)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        namePannel3Layout.setVerticalGroup(
            namePannel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePannel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(arrowP4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameP3)
                .addGap(26, 26, 26))
        );

        namePannel4.setBackground(new java.awt.Color(102, 255, 153));
        namePannel4.setOpaque(false);

        nameP4.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 12)); // NOI18N
        nameP4.setForeground(new java.awt.Color(0, 0, 0));
        nameP4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameP4.setText("Player 3");

        arrowP2.setBackground(new java.awt.Color(153, 153, 255));
        arrowP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/arrow/arrow_bot.png"))); // NOI18N

        javax.swing.GroupLayout namePannel4Layout = new javax.swing.GroupLayout(namePannel4);
        namePannel4.setLayout(namePannel4Layout);
        namePannel4Layout.setHorizontalGroup(
            namePannel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameP4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(namePannel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(arrowP2)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        namePannel4Layout.setVerticalGroup(
            namePannel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePannel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(nameP4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arrowP2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPannelP2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPannelP2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cardPannelP2.setBackground(new java.awt.Color(153, 255, 153));
        cardPannelP2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cardPannelP2.setOpaque(false);

        cardButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton16.setToolTipText("");
        cardButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton16ActionPerformed(evt);
            }
        });

        cardButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton17.setToolTipText("");
        cardButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton17ActionPerformed(evt);
            }
        });

        cardButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton18.setToolTipText("");
        cardButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton18ActionPerformed(evt);
            }
        });

        cardButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton19.setToolTipText("");
        cardButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton19ActionPerformed(evt);
            }
        });

        cardButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton20.setToolTipText("");
        cardButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton20ActionPerformed(evt);
            }
        });

        cardButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton21.setToolTipText("");
        cardButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton21ActionPerformed(evt);
            }
        });

        cardButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton22.setToolTipText("");
        cardButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton22ActionPerformed(evt);
            }
        });

        cardButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton23.setToolTipText("");
        cardButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton23ActionPerformed(evt);
            }
        });

        cardButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton24.setToolTipText("");
        cardButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton24ActionPerformed(evt);
            }
        });

        cardButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton25.setToolTipText("");
        cardButton25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton25ActionPerformed(evt);
            }
        });

        cardButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton26.setToolTipText("");
        cardButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton26ActionPerformed(evt);
            }
        });

        cardButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton27.setToolTipText("");
        cardButton27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton27ActionPerformed(evt);
            }
        });

        cardButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton28.setToolTipText("");
        cardButton28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton28ActionPerformed(evt);
            }
        });

        cardButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton29.setToolTipText("");
        cardButton29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton29ActionPerformed(evt);
            }
        });

        cardButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton30.setToolTipText("");
        cardButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton30ActionPerformed(evt);
            }
        });

        cardButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton31.setToolTipText("");
        cardButton31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cardPannelP2Layout = new javax.swing.GroupLayout(cardPannelP2);
        cardPannelP2.setLayout(cardPannelP2Layout);
        cardPannelP2Layout.setHorizontalGroup(
            cardPannelP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPannelP2Layout.createSequentialGroup()
                .addComponent(cardButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cardButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cardButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cardPannelP2Layout.setVerticalGroup(
            cardPannelP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cardButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        scrollPannelP2.setViewportView(cardPannelP2);

        scrollPannelP3.setBackground(new java.awt.Color(0, 0, 0));
        scrollPannelP3.setForeground(new java.awt.Color(0, 0, 0));
        scrollPannelP3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPannelP3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cardPannelP3.setBackground(new java.awt.Color(0, 0, 0));
        cardPannelP3.setForeground(new java.awt.Color(0, 0, 0));
        cardPannelP3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cardPannelP3.setOpaque(false);

        cardButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton32.setToolTipText("");
        cardButton32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton32ActionPerformed(evt);
            }
        });

        cardButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton33.setToolTipText("");
        cardButton33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton33ActionPerformed(evt);
            }
        });

        cardButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton34.setToolTipText("");
        cardButton34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton34ActionPerformed(evt);
            }
        });

        cardButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton35.setToolTipText("");
        cardButton35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton35ActionPerformed(evt);
            }
        });

        cardButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton36.setToolTipText("");
        cardButton36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton36ActionPerformed(evt);
            }
        });

        cardButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton37.setToolTipText("");
        cardButton37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton37ActionPerformed(evt);
            }
        });

        cardButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton38.setToolTipText("");
        cardButton38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton38ActionPerformed(evt);
            }
        });

        cardButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton39.setToolTipText("");
        cardButton39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton39ActionPerformed(evt);
            }
        });

        cardButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton40.setToolTipText("");
        cardButton40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton40ActionPerformed(evt);
            }
        });

        cardButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton41.setToolTipText("");
        cardButton41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton41ActionPerformed(evt);
            }
        });

        cardButton42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton42.setToolTipText("");
        cardButton42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton42ActionPerformed(evt);
            }
        });

        cardButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton43.setToolTipText("");
        cardButton43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton43ActionPerformed(evt);
            }
        });

        cardButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton44.setToolTipText("");
        cardButton44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton44ActionPerformed(evt);
            }
        });

        cardButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton45.setToolTipText("");
        cardButton45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton45ActionPerformed(evt);
            }
        });

        cardButton46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton46.setToolTipText("");
        cardButton46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton46ActionPerformed(evt);
            }
        });

        cardButton47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton47.setToolTipText("");
        cardButton47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton47ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cardPannelP3Layout = new javax.swing.GroupLayout(cardPannelP3);
        cardPannelP3.setLayout(cardPannelP3Layout);
        cardPannelP3Layout.setHorizontalGroup(
            cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPannelP3Layout.createSequentialGroup()
                .addComponent(cardButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(cardPannelP3Layout.createSequentialGroup()
                .addComponent(cardButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(cardPannelP3Layout.createSequentialGroup()
                .addComponent(cardButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(cardPannelP3Layout.createSequentialGroup()
                .addComponent(cardButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(cardPannelP3Layout.createSequentialGroup()
                .addComponent(cardButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(cardPannelP3Layout.createSequentialGroup()
                .addGroup(cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cardButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        cardPannelP3Layout.setVerticalGroup(
            cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPannelP3Layout.createSequentialGroup()
                .addGroup(cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        scrollPannelP3.setViewportView(cardPannelP3);

        scrollPannelP4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPannelP4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        scrollPannelP4.setOpaque(false);

        cardPannelP4.setBackground(new java.awt.Color(153, 255, 153));
        cardPannelP4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cardPannelP4.setOpaque(false);

        cardButton48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton48.setToolTipText("");
        cardButton48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton48ActionPerformed(evt);
            }
        });

        cardButton49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton49.setToolTipText("");
        cardButton49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton49ActionPerformed(evt);
            }
        });

        cardButton50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton50.setToolTipText("");
        cardButton50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton50ActionPerformed(evt);
            }
        });

        cardButton51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton51.setToolTipText("");
        cardButton51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton51ActionPerformed(evt);
            }
        });

        cardButton52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton52.setToolTipText("");
        cardButton52.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton52ActionPerformed(evt);
            }
        });

        cardButton53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton53.setToolTipText("");
        cardButton53.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton53ActionPerformed(evt);
            }
        });

        cardButton54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton54.setToolTipText("");
        cardButton54.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton54ActionPerformed(evt);
            }
        });

        cardButton55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton55.setToolTipText("");
        cardButton55.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton55ActionPerformed(evt);
            }
        });

        cardButton56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton56.setToolTipText("");
        cardButton56.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton56ActionPerformed(evt);
            }
        });

        cardButton57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton57.setToolTipText("");
        cardButton57.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton57ActionPerformed(evt);
            }
        });

        cardButton58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton58.setToolTipText("");
        cardButton58.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton58ActionPerformed(evt);
            }
        });

        cardButton59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton59.setToolTipText("");
        cardButton59.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton59ActionPerformed(evt);
            }
        });

        cardButton60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton60.setToolTipText("");
        cardButton60.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton60ActionPerformed(evt);
            }
        });

        cardButton61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton61.setToolTipText("");
        cardButton61.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton61ActionPerformed(evt);
            }
        });

        cardButton62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton62.setToolTipText("");
        cardButton62.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton62ActionPerformed(evt);
            }
        });

        cardButton63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cards/1_0.png"))); // NOI18N
        cardButton63.setToolTipText("");
        cardButton63.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardButton63ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cardPannelP4Layout = new javax.swing.GroupLayout(cardPannelP4);
        cardPannelP4.setLayout(cardPannelP4Layout);
        cardPannelP4Layout.setHorizontalGroup(
            cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPannelP4Layout.createSequentialGroup()
                .addGroup(cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardPannelP4Layout.createSequentialGroup()
                        .addComponent(cardButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardPannelP4Layout.createSequentialGroup()
                        .addComponent(cardButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardPannelP4Layout.createSequentialGroup()
                        .addComponent(cardButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardPannelP4Layout.createSequentialGroup()
                        .addComponent(cardButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardPannelP4Layout.createSequentialGroup()
                        .addComponent(cardButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardPannelP4Layout.createSequentialGroup()
                        .addComponent(cardButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardPannelP4Layout.createSequentialGroup()
                        .addComponent(cardButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardPannelP4Layout.createSequentialGroup()
                        .addComponent(cardButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        cardPannelP4Layout.setVerticalGroup(
            cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPannelP4Layout.createSequentialGroup()
                .addGroup(cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cardButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cardButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(cardPannelP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cardButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        scrollPannelP4.setViewportView(cardPannelP4);

        centerPannel.setOpaque(false);

        deckButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png")));
        deckButton.setToolTipText("");
        deckButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deckButtonActionPerformed(evt);
            }
        });

        messagePannel.setOpaque(false);

        messageText.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        messageText.setForeground(new java.awt.Color(0, 0, 0));
        messageText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        messageText.setText("Let's Play !");

        turnName.setFont(new java.awt.Font("Lucida Sans Typewriter", 1, 14)); // NOI18N
        turnName.setForeground(new java.awt.Color(0, 0, 0));
        turnName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        turnText.setFont(new java.awt.Font("Lucida Sans Typewriter", 1, 14)); // NOI18N
        turnText.setForeground(new java.awt.Color(0, 0, 0));
        turnText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        turnText.setText("Turn of : ");

        javax.swing.GroupLayout messagePannelLayout = new javax.swing.GroupLayout(messagePannel);
        messagePannel.setLayout(messagePannelLayout);
        messagePannelLayout.setHorizontalGroup(
            messagePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagePannelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(messagePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(messagePannelLayout.createSequentialGroup()
                        .addComponent(turnText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(turnName, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(messageText, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        messagePannelLayout.setVerticalGroup(
            messagePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, messagePannelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(messagePannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(turnText, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(turnName, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageText, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png")));
        pileButton.setToolTipText("");
        pileButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout centerPannelLayout = new javax.swing.GroupLayout(centerPannel);
        centerPannel.setLayout(centerPannelLayout);
        centerPannelLayout.setHorizontalGroup(
            centerPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPannelLayout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(deckButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(pileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messagePannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        centerPannelLayout.setVerticalGroup(
            centerPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPannelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(centerPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deckButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(169, 169, 169))
            .addGroup(centerPannelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(messagePannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );

        javax.swing.GroupLayout boardPannelLayout = new javax.swing.GroupLayout(boardPannel);
        boardPannel.setLayout(boardPannelLayout);
        boardPannelLayout.setHorizontalGroup(
            boardPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boardPannelLayout.createSequentialGroup()
                .addGroup(boardPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(namePannel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPannelP3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namePannel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(boardPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, boardPannelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(scrollPannelP2, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(namePannel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(boardPannelLayout.createSequentialGroup()
                        .addGroup(boardPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPannelP1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(boardPannelLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(centerPannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(boardPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrollPannelP4, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(namePannel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        boardPannelLayout.setVerticalGroup(
            boardPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boardPannelLayout.createSequentialGroup()
                .addGroup(boardPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(namePannel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(namePannel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPannelP1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(boardPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(boardPannelLayout.createSequentialGroup()
                        .addComponent(scrollPannelP3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namePannel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(boardPannelLayout.createSequentialGroup()
                        .addGroup(boardPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrollPannelP4, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(centerPannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(boardPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(boardPannelLayout.createSequentialGroup()
                                .addComponent(scrollPannelP2)
                                .addGap(5, 5, 5))
                            .addComponent(namePannel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(boardPannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boardPannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void cardButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton8ActionPerformed
        requestButton(8);
    }//GEN-LAST:event_cardButton8ActionPerformed
    
    private void cardButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton0ActionPerformed
        requestButton(0);
    }//GEN-LAST:event_cardButton0ActionPerformed
    
    private void cardButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton1ActionPerformed
        requestButton(1);
    }//GEN-LAST:event_cardButton1ActionPerformed
    
    private void cardButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton2ActionPerformed
        requestButton(2);
    }//GEN-LAST:event_cardButton2ActionPerformed
    
    private void cardButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton3ActionPerformed
        requestButton(3);
    }//GEN-LAST:event_cardButton3ActionPerformed
    
    private void cardButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton5ActionPerformed
        requestButton(5);
    }//GEN-LAST:event_cardButton5ActionPerformed
    
    private void cardButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton4ActionPerformed
        requestButton(4);
    }//GEN-LAST:event_cardButton4ActionPerformed
    
    private void cardButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton6ActionPerformed
        requestButton(6);
    }//GEN-LAST:event_cardButton6ActionPerformed
    
    private void cardButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton7ActionPerformed
        requestButton(7);
    }//GEN-LAST:event_cardButton7ActionPerformed
    
    private void cardButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton9ActionPerformed
        requestButton(9);
    }//GEN-LAST:event_cardButton9ActionPerformed
    
    private void cardButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton10ActionPerformed
        requestButton(10);
    }//GEN-LAST:event_cardButton10ActionPerformed
    
    private void cardButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton11ActionPerformed
        requestButton(11);
    }//GEN-LAST:event_cardButton11ActionPerformed
    
    private void cardButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton12ActionPerformed
        requestButton(12);
    }//GEN-LAST:event_cardButton12ActionPerformed
    
    private void cardButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton13ActionPerformed
        requestButton(13);
    }//GEN-LAST:event_cardButton13ActionPerformed
    
    private void cardButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton14ActionPerformed
        requestButton(14);
    }//GEN-LAST:event_cardButton14ActionPerformed
    
    private void cardButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton15ActionPerformed
        requestButton(15);
    }//GEN-LAST:event_cardButton15ActionPerformed
    
    private void deckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deckButtonActionPerformed
        requestButton(100);
    }//GEN-LAST:event_deckButtonActionPerformed
    
    private void cardButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton16ActionPerformed
        requestButton(16);
    }//GEN-LAST:event_cardButton16ActionPerformed
    
    private void cardButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton17ActionPerformed
        requestButton(17);
    }//GEN-LAST:event_cardButton17ActionPerformed
    
    private void cardButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton18ActionPerformed
        requestButton(18);
    }//GEN-LAST:event_cardButton18ActionPerformed
    
    private void cardButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton19ActionPerformed
        requestButton(19);
    }//GEN-LAST:event_cardButton19ActionPerformed
    
    private void cardButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton20ActionPerformed
        requestButton(20);
    }//GEN-LAST:event_cardButton20ActionPerformed
    
    private void cardButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton21ActionPerformed
        requestButton(21);
    }//GEN-LAST:event_cardButton21ActionPerformed
    
    private void cardButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton22ActionPerformed
        requestButton(22);
    }//GEN-LAST:event_cardButton22ActionPerformed
    
    private void cardButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton23ActionPerformed
        requestButton(23);
    }//GEN-LAST:event_cardButton23ActionPerformed
    
    private void cardButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton24ActionPerformed
        requestButton(24);
    }//GEN-LAST:event_cardButton24ActionPerformed
    
    private void cardButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton25ActionPerformed
        requestButton(25);
    }//GEN-LAST:event_cardButton25ActionPerformed
    
    private void cardButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton26ActionPerformed
        requestButton(26);
    }//GEN-LAST:event_cardButton26ActionPerformed
    
    private void cardButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton27ActionPerformed
        requestButton(27);
    }//GEN-LAST:event_cardButton27ActionPerformed
    
    private void cardButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton28ActionPerformed
        requestButton(28);
    }//GEN-LAST:event_cardButton28ActionPerformed
    
    private void cardButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton29ActionPerformed
        requestButton(29);
    }//GEN-LAST:event_cardButton29ActionPerformed
    
    private void cardButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton30ActionPerformed
        requestButton(30);
    }//GEN-LAST:event_cardButton30ActionPerformed
    
    private void cardButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton31ActionPerformed
        requestButton(31);
    }//GEN-LAST:event_cardButton31ActionPerformed
    
    private void cardButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton48ActionPerformed
        requestButton(48);
    }//GEN-LAST:event_cardButton48ActionPerformed
    
    private void cardButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton49ActionPerformed
        requestButton(49);
    }//GEN-LAST:event_cardButton49ActionPerformed
    
    private void cardButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton50ActionPerformed
        requestButton(50);
    }//GEN-LAST:event_cardButton50ActionPerformed
    
    private void cardButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton51ActionPerformed
        requestButton(51);
    }//GEN-LAST:event_cardButton51ActionPerformed
    
    private void cardButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton52ActionPerformed
        requestButton(52);
    }//GEN-LAST:event_cardButton52ActionPerformed
    
    private void cardButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton53ActionPerformed
        requestButton(53);
    }//GEN-LAST:event_cardButton53ActionPerformed
    
    private void cardButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton54ActionPerformed
        requestButton(54);
    }//GEN-LAST:event_cardButton54ActionPerformed
    
    private void cardButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton55ActionPerformed
        requestButton(55);
    }//GEN-LAST:event_cardButton55ActionPerformed
    
    private void cardButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton56ActionPerformed
        requestButton(56);
    }//GEN-LAST:event_cardButton56ActionPerformed
    
    private void cardButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton57ActionPerformed
        requestButton(57);
    }//GEN-LAST:event_cardButton57ActionPerformed
    
    private void cardButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton58ActionPerformed
        requestButton(58);
    }//GEN-LAST:event_cardButton58ActionPerformed
    
    private void cardButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton59ActionPerformed
        requestButton(59);
    }//GEN-LAST:event_cardButton59ActionPerformed
    
    private void cardButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton60ActionPerformed
        requestButton(60);
    }//GEN-LAST:event_cardButton60ActionPerformed
    
    private void cardButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton61ActionPerformed
        requestButton(61);
    }//GEN-LAST:event_cardButton61ActionPerformed
    
    private void cardButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton62ActionPerformed
        requestButton(62);
    }//GEN-LAST:event_cardButton62ActionPerformed
    
    private void cardButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton63ActionPerformed
        requestButton(63);
    }//GEN-LAST:event_cardButton63ActionPerformed
    
    private void cardButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton47ActionPerformed
        requestButton(47);
    }//GEN-LAST:event_cardButton47ActionPerformed
    
    private void cardButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton46ActionPerformed
        requestButton(46);
    }//GEN-LAST:event_cardButton46ActionPerformed
    
    private void cardButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton45ActionPerformed
        requestButton(45);
    }//GEN-LAST:event_cardButton45ActionPerformed
    
    private void cardButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton44ActionPerformed
        requestButton(44);
    }//GEN-LAST:event_cardButton44ActionPerformed
    
    private void cardButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton43ActionPerformed
        requestButton(43);
    }//GEN-LAST:event_cardButton43ActionPerformed
    
    private void cardButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton42ActionPerformed
        requestButton(42);
    }//GEN-LAST:event_cardButton42ActionPerformed
    
    private void cardButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton41ActionPerformed
        requestButton(41);
    }//GEN-LAST:event_cardButton41ActionPerformed
    
    private void cardButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton40ActionPerformed
        requestButton(40);
    }//GEN-LAST:event_cardButton40ActionPerformed
    
    private void cardButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton39ActionPerformed
        requestButton(39);
    }//GEN-LAST:event_cardButton39ActionPerformed
    
    private void cardButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton38ActionPerformed
        requestButton(38);
    }//GEN-LAST:event_cardButton38ActionPerformed
    
    private void cardButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton37ActionPerformed
        requestButton(37);
    }//GEN-LAST:event_cardButton37ActionPerformed
    
    private void cardButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton36ActionPerformed
        requestButton(36);
    }//GEN-LAST:event_cardButton36ActionPerformed
    
    private void cardButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton35ActionPerformed
        requestButton(35);
    }//GEN-LAST:event_cardButton35ActionPerformed
    
    private void cardButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton34ActionPerformed
        requestButton(34);
    }//GEN-LAST:event_cardButton34ActionPerformed
    
    private void cardButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton33ActionPerformed
        requestButton(33);
    }//GEN-LAST:event_cardButton33ActionPerformed
    
    private void cardButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardButton32ActionPerformed
        requestButton(32);
    }//GEN-LAST:event_cardButton32ActionPerformed
    
    private void pileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pileButtonActionPerformed
    }//GEN-LAST:event_pileButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arrowP1;
    private javax.swing.JLabel arrowP2;
    private javax.swing.JLabel arrowP3;
    private javax.swing.JLabel arrowP4;
    private javax.swing.JPanel boardPannel;
    private javax.swing.JButton cardButton0;
    private javax.swing.JButton cardButton1;
    private javax.swing.JButton cardButton10;
    private javax.swing.JButton cardButton11;
    private javax.swing.JButton cardButton12;
    private javax.swing.JButton cardButton13;
    private javax.swing.JButton cardButton14;
    private javax.swing.JButton cardButton15;
    private javax.swing.JButton cardButton16;
    private javax.swing.JButton cardButton17;
    private javax.swing.JButton cardButton18;
    private javax.swing.JButton cardButton19;
    private javax.swing.JButton cardButton2;
    private javax.swing.JButton cardButton20;
    private javax.swing.JButton cardButton21;
    private javax.swing.JButton cardButton22;
    private javax.swing.JButton cardButton23;
    private javax.swing.JButton cardButton24;
    private javax.swing.JButton cardButton25;
    private javax.swing.JButton cardButton26;
    private javax.swing.JButton cardButton27;
    private javax.swing.JButton cardButton28;
    private javax.swing.JButton cardButton29;
    private javax.swing.JButton cardButton3;
    private javax.swing.JButton cardButton30;
    private javax.swing.JButton cardButton31;
    private javax.swing.JButton cardButton32;
    private javax.swing.JButton cardButton33;
    private javax.swing.JButton cardButton34;
    private javax.swing.JButton cardButton35;
    private javax.swing.JButton cardButton36;
    private javax.swing.JButton cardButton37;
    private javax.swing.JButton cardButton38;
    private javax.swing.JButton cardButton39;
    private javax.swing.JButton cardButton4;
    private javax.swing.JButton cardButton40;
    private javax.swing.JButton cardButton41;
    private javax.swing.JButton cardButton42;
    private javax.swing.JButton cardButton43;
    private javax.swing.JButton cardButton44;
    private javax.swing.JButton cardButton45;
    private javax.swing.JButton cardButton46;
    private javax.swing.JButton cardButton47;
    private javax.swing.JButton cardButton48;
    private javax.swing.JButton cardButton49;
    private javax.swing.JButton cardButton5;
    private javax.swing.JButton cardButton50;
    private javax.swing.JButton cardButton51;
    private javax.swing.JButton cardButton52;
    private javax.swing.JButton cardButton53;
    private javax.swing.JButton cardButton54;
    private javax.swing.JButton cardButton55;
    private javax.swing.JButton cardButton56;
    private javax.swing.JButton cardButton57;
    private javax.swing.JButton cardButton58;
    private javax.swing.JButton cardButton59;
    private javax.swing.JButton cardButton6;
    private javax.swing.JButton cardButton60;
    private javax.swing.JButton cardButton61;
    private javax.swing.JButton cardButton62;
    private javax.swing.JButton cardButton63;
    private javax.swing.JButton cardButton7;
    private javax.swing.JButton cardButton8;
    private javax.swing.JButton cardButton9;
    private javax.swing.JPanel cardPannelP1;
    private javax.swing.JPanel cardPannelP2;
    private javax.swing.JPanel cardPannelP3;
    private javax.swing.JPanel cardPannelP4;
    private javax.swing.JPanel centerPannel;
    private javax.swing.JButton deckButton;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JPanel messagePannel;
    private javax.swing.JLabel messageText;
    private javax.swing.JLabel nameP1;
    private javax.swing.JLabel nameP2;
    private javax.swing.JLabel nameP3;
    private javax.swing.JLabel nameP4;
    private javax.swing.JPanel namePannel1;
    private javax.swing.JPanel namePannel2;
    private javax.swing.JPanel namePannel3;
    private javax.swing.JPanel namePannel4;
    private javax.swing.JButton pileButton;
    private javax.swing.JScrollPane scrollPannelP1;
    private javax.swing.JScrollPane scrollPannelP2;
    private javax.swing.JScrollPane scrollPannelP3;
    private javax.swing.JScrollPane scrollPannelP4;
    private javax.swing.JLabel turnName;
    private javax.swing.JLabel turnText;
    // End of variables declaration//GEN-END:variables
}
