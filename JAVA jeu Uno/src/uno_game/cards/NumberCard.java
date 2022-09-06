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
public class NumberCard implements Card {
    
    private int colour;
    private char symbol;
    
    public NumberCard(int c,int n){
        colour=c;
        symbol= (char) (n + 48);
    }
    
    public int getColour(){
        return colour;
    }
    
    public char getSymbol(){
        return symbol;
    }
    
    public boolean  canPlay()
    {
        return false;
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
