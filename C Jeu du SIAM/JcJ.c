#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

#include "action_jeu.h"
#include "affichage_jeu.h"
#include "JcJ.h"
#include "poussee.h"

#define MONTAGNE 0
#define ELEPHANT 1
#define RHINOCEROS 2

void tour_joueurA(int tab[5][5],char ori[5][5],int *ptour)  //pour le joueur A et B le fonctionnement est le meme
{
    int choix =0;  //le choix d'action pour le joueur A
    int pion_jA =ELEPHANT;

    gotoligcol(7,40);
    printf("TOUR JOUEUR A Elephant   ");

    gotoligcol(24,30);
    Color(9,0);
    printf("C'est le tour du joueur A : Que veux tu faire ?\n\n\t\t\t\t\t");
    printf("1.Entrer un pion sur la plateau\n\t\t\t\t\t\t2.Deplacer pion\n\t\t\t\t\t3.Changer orientation du pion\n\t\t\t\t  4.Sortir pion (seulement cases exterieures)\n\t\t\t\t5.Deplacement avec poussee d'autre(s) piece(s)\n\t\t\t\t\t\t6.Regles du jeu\n\t\t\t\t\t\t  7.Quitter\n");

    do  {
            scanf("%d",&choix);
            fflush(stdin);
        }while(choix >= 7 && choix < 0 );   //On demande au joueur le choix de son action

    efface_ligne();
    gotoligcol(25,30);

            switch(choix)       //5 actions possibles par le joueur
     {
        case 1 : placement_pion(tab,ori,&pion_jA,&(*ptour));
        break;
        case 2 : deplacer_pion(tab,ori,&pion_jA,&(*ptour));
        break;
        case 3 : orientiation_pion(tab,ori,&pion_jA,&(*ptour));
        break;
        case 4 : sortie_pion(tab,ori,&pion_jA,&(*ptour));
        break;
        case 5 : poussee_globale(tab,ori,&pion_jA,&(*ptour));
        break;
        case 6 : regle();
        break;
        case 7 : (*ptour)=-1;
        break;
    }
    Color(6,0);

    nbr_pion(tab);
}

void tour_joueurB(int tab[5][5],char ori[5][5],int *ptour)
{
    int choix=0;
    int pion_jB =RHINOCEROS;

    gotoligcol(7,40);
    printf("TOUR JOUEUR B Rhinoceros");

    gotoligcol(24,30);
    Color(11,0);
    printf("C'est le tour du joueur B : Que veux tu faire ?\n\n\t\t\t\t\t");
    printf("1.Entrer un pion sur la plateau\n\t\t\t\t\t\t2.Deplacer pion\n\t\t\t\t\t3.Changer orientation du pion\n\t\t\t\t  4.Sortir pion (seulement cases exterieures)\n\t\t\t\t5.Deplacement avec poussee d'autre(s) piece(s)\n\t\t\t\t\t\t6.Regles du jeu\n\t\t\t\t\t\t  7.Quitter\n");

    do  {
            fflush(stdin);
            scanf("%d",&choix);

        }while(choix >= 6 && choix < 0 );

    efface_ligne();
    gotoligcol(25,30);

    switch(choix)
    {
        case 1 :placement_pion(tab,ori,&pion_jB,&(*ptour));
        break;
        case 2 : deplacer_pion(tab,ori,&pion_jB,&(*ptour));
        break;
        case 3 : orientiation_pion(tab,ori,&pion_jB,&(*ptour));
        break;
        case 4 : sortie_pion(tab,ori,&pion_jB,&(*ptour));
        break;
        case 5 : poussee_globale(tab,ori,&pion_jB,&(*ptour));
        break;
        case 6 : regle();
        break;
        case 7 : (*ptour)=-1;
        break;
    }
    Color(6,0);
  nbr_pion(tab);
}

int condition_victoire(int tab[5][5])       //le jeu s'arrete quand il y a moins de 3 montagnes sur le plateau.
{
   int victoire=0;
   int i,j;
   int compt =0;
   for(i=0;i<5;i++)

   {
       for(j=0;j<5;j++)
       {
           if(tab[i][j]==MONTAGNE)  compt++;
       }
   }
   if(compt<3) victoire=1;
   return victoire;
}
