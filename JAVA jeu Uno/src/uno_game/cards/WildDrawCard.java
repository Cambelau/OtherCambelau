/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package uno_game.cards;
import javax.swing.*;

/**
 *
 * @author Owner
 */
public class WildDrawCard implements Card {
    
    private int colour = 0;
    private char symbol = 'p';
    
    @Override
    public int getColour(){
        return colour;
    }
    @Override
    public char getSymbol(){
        return symbol;
    }
    
    @Override
    public boolean  canPlay(Card card, int currentColor)
    {
        return true;
    }
    @Override
    public void setColour(int c){
        colour=c;
    }
}
