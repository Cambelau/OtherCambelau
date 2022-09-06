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
public class ReverseCard implements Card  {
    
    private int colour;
    private char symbol ='r';
    
    public ReverseCard(int c){
        colour=c;
    }
    
    public int getColour(){
        return colour;
    }
    
    public char getSymbol(){
        return symbol;
    }
    
    public boolean  canPlay()
    {
        return true;
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
