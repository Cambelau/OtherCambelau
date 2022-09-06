/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package uno_game.cards;

/**
 *
 * @author Owner
 */
public interface  Card {
    
    public boolean  canPlay(Card card, int currentColor);
    
    public int getColour();
    
    public char getSymbol();
    
    public void setColour(int c);
}
