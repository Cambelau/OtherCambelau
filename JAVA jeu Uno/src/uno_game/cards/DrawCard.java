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
public class DrawCard implements Card {
    
    private int colour;
    private final char symbol = 'd';
    
    public DrawCard(int c){
        colour=c;
    }
    
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
        return card.getColour()==colour || card.getSymbol()==symbol;
    }
    
    @Override
    public void setColour(int c){
        colour=c;
    }
}
