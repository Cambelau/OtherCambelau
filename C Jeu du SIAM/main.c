#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

#define MONTAGNE 0
#define ELEPHANT 1
#define RHINOCEROS 2

#include "action_jeu.h"
#include "affichage_jeu.h"
#include "JcJ.h"
#include "poussee.h"

int main()
{
    int tab[5][5] ={-1};    //tableau pour les pions
    char ori[5][5];         //tableau pour l'orientation des pions
    int tour = 0;           //variable qui compte le nombre de tour
    int fin_de_partie=0;    //variable boolenne pour la fin de partie

    Color(6,0);
    initialisiation_jeu(tab,ori);                       //Initialise le tableau
    affichage_grille(tab);                              //Affiche la Grille de jeu

    affichage_pion(tab);                                //affiche les pions
    affichage_orientation(ori);                         //affiche l'orientation

gotoligcol(25,15);
do{

        gotoligcol(8,47);
        printf(" Tour [%d]",tour+1);    //affiche le nombre de tour

        gotoligcol(24,30);

            if((tour%2)==0) //A chaque fois que un joueur joue, on incremente le nombre de tour.
            {
                tour_joueurA(tab,ori,&tour);efface_ligne();
            }
            else
            {

                tour_joueurB(tab,ori,&tour);efface_ligne();
            }

    fin_de_partie=condition_victoire(tab);

    }while(fin_de_partie==0 && tour!=-1);   //on fait une boucle entre les tours des deux joueurs jusqu'à que la condition de fin soit validé (=1)

    gotoligcol(25,0);
    return 0;
}
