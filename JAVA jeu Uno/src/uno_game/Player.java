/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package uno_game;

import java.util.ArrayList;
import uno_game.cards.*;

/**
 *
 * @author Matthieu Sajot
 * 
 * each game has a hand (its cards) and a name
 */
public class Player {
    
    private ArrayList<Card> playingHand = new ArrayList<>();
    private String name;
    
    public Player(Deck d){
        
        //give 7 fisrt cards to a player
        for(int i = 0;i<7;i++){
            playingHand.add(d.getCard());
        }
    }
    
    
    public int getHandSize(){
        return playingHand.size();
    }
    
    public Card getCard(int i){
        return playingHand.get(i);
    }
    
    
    public void setName(String n){
        name = n;
    }
    
    public void removeCard(int i){
        playingHand.remove(i);
    }
    
    //draw a card, limited to a maximum of 15 cards in the hand
    public void drawCard(Deck deck, int n){
        if(playingHand.size() + n <15)
            for(int i = 0;i<n;i++)
                playingHand.add(deck.getCard());
    }
    
    public boolean emptyHand(){
        return playingHand.isEmpty();
    }
    
    public String getName(){
        return name;
    }
}
