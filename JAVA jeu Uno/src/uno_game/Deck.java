/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package uno_game;

import java.util.ArrayList;
import java.util.Collections;
import   uno_game.cards.*;

/**
 *
 * @author Matthieu Sajot
 */

public class Deck {
    
    //contains the 108 cards of the uno deck
    private ArrayList<Card> cards = new ArrayList<>();
    
    //Deck builder, fills the 108 fixed cards
    public Deck(){
        Boolean firstRound=true;
        
        //add the number cards (the deck contains 4 cards numbered 0 and 2 for each other number).
        for(int i=0;i<10;i++){
            for(int j=1;j<5;j++){
                Card numCard= new NumberCard(j,i);
                cards.add(numCard);
            }
            if(i==9 && firstRound){
                firstRound=false;
                i=0;
            }
        }
        
        for(int i=1;i<5;i++){
            for(int j=0 ;j<2;j++){
                Card drawCard= new DrawCard(i);
                cards.add(drawCard);
                Card revCard= new ReverseCard(i);
                cards.add(revCard);
                Card skiCard= new SkipCard(i);
                cards.add(skiCard);
            }
            Card wCard= new WildCard();
            cards.add(wCard);
            Card wdCard= new WildDrawCard();
            cards.add(wdCard);
        }
    }
    
    //take a card and remove it from the deck
    public Card getCard(){
        Card cardTaken = cards.get(0);
        cards.remove(0);
        return cardTaken;
    }
    
    //shuffle the deck
    public void shuffleDeck(){
        Collections.shuffle(cards);
    }
    
}
