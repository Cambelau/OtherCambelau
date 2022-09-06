/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package uno_game;
/**
 *
 * @author Matthieu Sajot
 * Code du gradient color : https://github.com/k33ptoo/KControls
 * DECK NOMENCLATURE :
 * SYMBOL NUMBER 0 to 9
 * COLOUR RED=1 BLUE =2 GREEN=3 YELLOW=4 BLACK=0
 */
public class UNO_GAME {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameRun uno = new GameRun();
        uno.introGame();
        uno.displayGame();
    }
    
}
