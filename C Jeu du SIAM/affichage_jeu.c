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


void affichage_grille()
{
        int i,j;
        Color(4,0);
        gotoligcol(10,40);  printf("%c",201);   for(i=0;i<4;i++){ligne();printf("%c",203);} ligne();printf("%c",187); //ligne du haut du plateau
        gotoligcol(9,42);   printf("1    2    3    4    5");                                                       //affiche 12345
        for(i=0;i<5;i++)    {gotoligcol(11+2*i,38);   printf("%c",65+i);}                                         //affiche ABCDE

    for(j=0;j<4;j++)                                                                                              //boucle centre du plateau
   {    gotoligcol(11+2*j,40);
        for(i=0;i<4;i++) {printf("%c",186);printf("    ");}    printf("%c",186);printf("    ");printf("%c",186);
        printf("\n");
        gotoligcol(12+2*j,40);
        printf("%c",204);      for(i=0;i<4;i++) {ligne();printf("%c",206);}           ligne();printf("%c",185);
        printf("\n");
    }
        gotoligcol(19,40);                                                                                        //ligne du bas plateau
        for(i=0;i<4;i++) {printf("%c",186);printf("    ");}    printf("%c",186);printf("    ");printf("%c",186);
        gotoligcol(20,40);
        printf("%c",200);   for(i=0;i<4;i++){ligne();printf("%c",202);}     ligne();printf("%c",188);

        gotoligcol(10,30);  printf("%c",201);ligne();printf("%c",187);                                            //affiche la colonne de gauche
        for(i=0;i<5;i++)    {gotoligcol(11+2*i,30); printf("%c",186);printf("    ");printf("%c",186);}
        for(i=0;i<4;i++)    {gotoligcol(12+2*i,30);printf("%c",204);ligne();printf("%c",185);}
        gotoligcol(20,30);  printf("%c",200);ligne();printf("%c",188);

        gotoligcol(10,70);  printf("%c",201);ligne();printf("%c",187);                                            //affiche la colonne de droite
        for(i=0;i<5;i++)    {gotoligcol(11+2*i,70); printf("%c",186);printf("    ");printf("%c",186);}
        for(i=0;i<4;i++)    {gotoligcol(12+2*i,70);printf("%c",204);ligne();printf("%c",185);}
        gotoligcol(20,70);  printf("%c",200);ligne();printf("%c",188);

        Color(6,0);
        for(j=0;j<3;j++)                                                                          //lettre S
        {gotoligcol((1+2*j),33);  for(i=0;i<10;i++)    {printf("%c",219);}}
        gotoligcol(2,33);printf("%c%c",219,219);    gotoligcol(4,41);printf("%c%c",219,219);

        for(i=0;i<5;i++)    {gotoligcol(1+i,46);printf("%c%c%c",219,219,219);}                    //lettreo I

        for(i=0;i<5;i++)    {gotoligcol(1+i,52);printf("%c%c%c",219,219,219);}                    //lettre A
        for(i=0;i<5;i++)    {gotoligcol(1+i,58);printf("%c%c%c",219,219,219);}
        gotoligcol(1,55);   printf("%c%c%c",219,219,219);
        gotoligcol(3,55);   printf("%c%c%c",219,219,219);

        for(i=0;i<5;i++)    {gotoligcol(1+i,64);printf("%c%c%c",219,219,219);}                    //lettre M
        for(i=0;i<5;i++)    {gotoligcol(1+i,72);printf("%c%c%c",219,219,219);}
        gotoligcol(1,67);   printf("%c%c %c%c",219,219,219,219);
        gotoligcol(2,68);   printf("%c%c%c",219,219,219);
        gotoligcol(3,69);   printf("%c",219);
        gotoligcol(25,0);

        Color(6,0);
}
void ligne()
{
    printf("%c%c%c%c",205,205,205,205);                                          //fonction pour raccourcir le code des longues lignes
}


void affichage_pion(int tab[5][5])          //affiche les pions
{
    Color(15,0);
    int i,j;

    for (i=0;i<5;i++)
    {
        for(j=0;j<5;j++)
        {
            gotoligcol(11+2*i,42+5*j);
            switch(tab[i][j])
            {
                case MONTAGNE : printf("M");
                break;
                case ELEPHANT : printf("E");
                break;
                case RHINOCEROS : printf("R");
                break;
                default : printf(" ");
                break;
            }


        }
    }
    nbr_pion(tab);

}

void affichage_orientation(char ori[5][5])       //affiche l'orientaion des pions
{
    int i,j;

    for (i=0;i<5;i++)
    {
        for(j=0;j<5;j++)
        {
            gotoligcol(11+2*i,43+5*j);
            printf("%c",ori[i][j]);
        }
    }
    Color(6,0);
}

void initialisiation_jeu(int tab[5][5], char ori[5][5])  //initialise l'orientation à 0
{
    int i,j;

    for (i=0;i<5;i++)
    {
        for(j=0;j<5;j++)
        {
            tab[i][j]=-1;
            ori[i][j]=' ';
        }
    }
    tab[2][2]=MONTAGNE;tab[3][2]=MONTAGNE;tab[1][2]=MONTAGNE;
}

void nbr_pion(int tab[5][5])
{
    int i,j;
    int elephant_nbr =0;
    int rhino_nbr =0;
    Color(6,0);

    for (i=0;i<5;i++)                       //Compte le nombre de pions dans le tableau
    {
        for(j=0;j<5;j++)
        {
            if(tab[i][j]==ELEPHANT){elephant_nbr++;}
            if(tab[i][j]==RHINOCEROS){rhino_nbr++;}
        }
    }

    for (i=0;i<5;i++)                         //affiche les 5 elephants initiaux
    {
        gotoligcol(11+2*i,72);  printf("R");
        gotoligcol(11+2*i,32);  printf("E");

    }
    for (i=0;i<rhino_nbr;i++)            //retire un elephant a chaque fois quon en place un sur le plateau
    {
        gotoligcol(11+2*i,72);  printf(" ");
    }

    for (i=0;i<elephant_nbr;i++)               //retire un rhinoceros a chaque fois quon en place un sur le plateau
    {
      gotoligcol(11+2*i,32);  printf(" ");
    }
}

void efface_ligne()     //efface lez differentes instructions
{
    int i,j;
    gotoligcol(24,0);
    for(i=0;i<10;i++)
    {
        for(j=0;j<120;j++)
        {
            printf(" ");
        }
        printf("\n");
    }
}

void gotoligcol( int lig, int col )
{

COORD mycoord;

mycoord.X = col;
mycoord.Y = lig;
SetConsoleCursorPosition( GetStdHandle( STD_OUTPUT_HANDLE ), mycoord );
}

void Color(int couleurDuTexte,int couleurDeFond) // fonction d'affichage de couleurs
{
        HANDLE H=GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(H,couleurDeFond*16+couleurDuTexte);
}

void regle()
{
printf("Le but du jeu de SIAM est de sortir une montagne du plateau.5 actions sont possible :\n");
printf("\t1.Placer un pion, sur une case libre alors on peut choisie son orientation ou\n");
printf("\tsur une case occupé alors le pion sera poussee (uniquement sur les contours du plateau.\n\t2.Deplacer son pion de facon\n");
printf("\torthogonale dans le sens de son orientation 3.Changer dorientation \n");
printf("\t4.Retirer un pion uniquement sur les contours du plateau\n\t5.Faire un deplacement avec poussee d'autres pions");
printf("\n\tLes animaux: force de 1 et resistance de 1. Montagne resistance de 0.9\n");
system("pause");
}
