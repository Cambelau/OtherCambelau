/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package uno_game;

import uno_game.gui.GameBoard;
import uno_game.gui.Menu;
import uno_game.gui.NamePlayer;
import java.util.ArrayList;
import uno_game.cards.*;

/**
 *
 * @author Matthieu Sajot
 * 
 * controls the entire game
 */
public class GameRun {
    
    private Deck deck = new Deck();
    private ArrayList<Player> players = new ArrayList();
    private ArrayList<Card> pile = new ArrayList<>();
    private int turn = 0;
    
    //method for the first step of the game, displays the menu to decide the number and names of the players.
    public void introGame(){
        
        Menu menu = new Menu();
        int n =0;  //nombe de joueur
        do{
            menu.setVisible(true);
            n= menu.getNumbPlayer();
            deck.shuffleDeck();
            
            for(int i=0;i<n;i++){
                Player newPlayer = new Player(deck);
                NamePlayer namePlayer = new NamePlayer();
                namePlayer.setVisible(true);
                
                do{
                    newPlayer.setName(namePlayer.getName());
                }while(newPlayer.getName()==null);
                
                players.add(newPlayer);
            }
        }while(n==0);//as long as you have not clicked on a player name button
        
        //Put the first card in the pile, it must be a number card.
        Card firstCard;
        do{
            firstCard=deck.getCard();
            pile.add(firstCard);
        }while(firstCard instanceof NumberCard == false);
    }
    
    //second part of the game with the 'board display
    public void displayGame(){
        
        GameBoard board = new GameBoard(players,deck,pile);
        board.displayGame();
    }
}
