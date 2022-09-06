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



void placement_pion(int tab[5][5],char ori[5][5],int *ppion, int* pptour)              //Placement du pion
{


    char abscisse; int ordonnee;
    int choix_orientation =' ';
    int retour =0;
    int i,j,elephant_nbr,rhino_nbr;

    for (i=0;i<5;i++)                                                                  //Compte le nombre de pions dans le tableau
    {
        for(j=0;j<5;j++)
        {
            if(tab[i][j]==ELEPHANT){elephant_nbr++;}
            if(tab[i][j]==RHINOCEROS){rhino_nbr++;}
        }
    }

    if(elephant_nbr==6 || rhino_nbr==5)                                                //Blindage pour ne pas pouvoir de placer plus de 5 pions sur le plateau
    {
        retour=1;
        printf("ATTENTION :Vous avez place tout vos pions\n");
        system("pause");
    }

    do
        {
            printf("Placer les coordonnees du pion : (ex :A1, F1 pour revenir) "); //Demande les coordonnees les stockes puis place un pion à ces coordonees
            fflush(stdin);                                                          //Pour revenir R1 conditions if pour ne rien changer si on rentre R1
            scanf ("%c",&abscisse);
            scanf("%d",&ordonnee);
            if(abscisse=='F' && ordonnee ==1)
                retour=1;
        }while((abscisse!='A' && abscisse!='E' && abscisse!='B' && abscisse!='D' && abscisse!='C' && abscisse!='F') || ( ordonnee!=5 && ordonnee!=1 && abscisse!='A' && (ordonnee!=2 || ordonnee!=3 ||ordonnee!=4) && abscisse!='E' && (ordonnee!=2 || ordonnee!=3 ||ordonnee!=4) ));

/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
    if(retour==0 && (tab[abscisse-65][ordonnee-1]==MONTAGNE || tab[abscisse-65][ordonnee-1]==ELEPHANT || tab[abscisse-65][ordonnee-1]==RHINOCEROS))
    {
        int pion_x=abscisse-65;                                                    //Il y a quatre fois la meme if else pour chaque orientation.
        int pion_y=ordonnee-1;                                                     //Cest pour le cas ou l'on veut rentrer avec poussee. Dabord on verifie si la poussee est possible
       /*------------------------------------------------------------------*/       //Puis on fait une poussée pour liberer la case exterieur où est ensuite placer le pion.
        if(ordonnee-1==0 && ori[pion_x][pion_y]!='g')
        {
            int autorisation_poussee=verification_poussee(tab,ori,&pion_x,&pion_y);

            if(autorisation_poussee==1)
                {
                    char temp=ori[pion_x][pion_y];
                    ori[pion_x][pion_y]='d';
                    printf("Poussee autorisee");
                    deplacement_poussee(tab,ori,&pion_x,&pion_y);
                    tab[pion_x][pion_y]=(*ppion);
                    ori[pion_x][pion_y]='d';
                    ori[pion_x][pion_y+1]=temp;
                    *pptour=(*pptour)+1;
                }
            else
                {
                printf("POUSSEE IMPOSSIBLE");
                }

        }
 /*-----------------------------------------------------------------------------------------*/
        if(ordonnee-1==4 && ori[pion_x][pion_y]!='d')
        {
            int autorisation_poussee=verification_poussee(tab,ori,&pion_x,&pion_y);

            if(autorisation_poussee==1)
                {
                    char temp=ori[pion_x][pion_y];
                    ori[pion_x][pion_y]='g';
                    printf("Poussee autorisee");
                    deplacement_poussee(tab,ori,&pion_x,&pion_y);
                    tab[pion_x][pion_y]=(*ppion);
                    ori[pion_x][pion_y]='g';
                    ori[pion_x][pion_y+1]=temp;
                    *pptour=(*pptour)+1;
                }
            else
                {
                printf("POUSSEE IMPOSSIBLE");
                }
        system("pause");
        }

/*-----------------------------------------------------------------------------------------------*/
        if((abscisse-65==0 && (ordonnee-1==2 || ordonnee-1==3 || ordonnee-1==1)) && ori[pion_x][pion_y]!='h')
        {
            int autorisation_poussee=verification_poussee(tab,ori,&pion_x,&pion_y);

            if(autorisation_poussee==1)
                {
                    char temp=ori[pion_x][pion_y];
                    ori[pion_x][pion_y]='b';
                    printf("Poussee autorisee");
                    deplacement_poussee(tab,ori,&pion_x,&pion_y);
                    tab[pion_x][pion_y]=(*ppion);
                    ori[pion_x][pion_y]='b';
                    ori[pion_x+1][pion_y]=temp;
                    *pptour=(*pptour)+1;
                }
            else
                {
                printf("POUSSEE IMPOSSIBLE");
                }
        system("pause");
        }
/*--------------------------------------------------------------------------------------------------------*/
        if((abscisse-65==4 && ordonnee-1==2 || ordonnee-1==3 || ordonnee-1==1) && ori[pion_x][pion_y]!='b')
        {
            int autorisation_poussee=verification_poussee(tab,ori,&pion_x,&pion_y);

            if(autorisation_poussee==1)
                {
                    char temp=ori[pion_x][pion_y];
                    ori[pion_x][pion_y]='h';
                    printf("Poussee autorisee");
                    deplacement_poussee(tab,ori,&pion_x,&pion_y);
                    tab[pion_x][pion_y]=(*ppion);
                    ori[pion_x][pion_y]='h';
                    ori[pion_x-1][pion_y]=temp;
                    *pptour=(*pptour)+1;
                }
            else
                {
                printf("POUSSEE IMPOSSIBLE");
                }
        system("pause");
        }

    }
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    if(retour==0 && tab[abscisse-65][ordonnee-1]==-1)       //Dans le cas ou la case est libre le jeu demande a l'utlisateur l'orientation pour le pion placé.
    {
        tab[abscisse-65][ordonnee-1]=(*ppion);
        *pptour=(*pptour)+1;
        printf("\n\t\t\t     Quelle est l'orientation du pion ?(g/d/b/h) ");    //demande l'orientation puis la place au coordonnees precedemment recues
        fflush(stdin);
        do{
            scanf ("%c",&choix_orientation);
        }while(choix_orientation!='g' && choix_orientation!='d' & choix_orientation!='b' && choix_orientation!='h');

        ori[abscisse-65][ordonnee-1]=choix_orientation;
    }


//A chaque fin de fonction on actualise l'affiche des pions et de leur orientation
//On elimine aussi le dialogue sous le tableau



    efface_ligne();
    affichage_pion(tab);
    affichage_orientation(ori);
}

/*--------------------------------------------------------------------------------------------------------*/
void deplacer_pion(int tab[5][5],char ori[5][5],int *ppion, int *pptour)
{

    char abscisse;int ordonnee;
    int retour =0;
    do
    {
        printf("Quel pion voulez vous deplacer ?\t (F1 pour retour)");
        fflush(stdin);
        scanf ("%c",&abscisse);
        scanf("%d",&ordonnee);
        if(abscisse=='F' && ordonnee ==1){retour=1;}
    }while(tab[abscisse-65][ordonnee-1]!=(*ppion) && abscisse!='F' );


    if(retour==0) //retour si on a entré "F1"
    {   //Encore une fois 4 fois la condition pour chaque orientation. On verifie aussi que la case est libre pour le deplacement
        if(ori[abscisse-65][ordonnee-1]=='d' && tab[abscisse-65][ordonnee]==-1)
        {
            tab[abscisse-65][ordonnee]=(*ppion);
            ori[abscisse-65][ordonnee]='d';
            tab[abscisse-65][ordonnee-1]=-1;
            ori[abscisse-65][ordonnee-1]=' ';
            *pptour=(*pptour)+1;
        }

        if(ori[abscisse-65][ordonnee-1]=='g' && tab[abscisse-65][ordonnee-2]==-1)
        {
            tab[abscisse-65][ordonnee-2]=(*ppion);
            ori[abscisse-65][ordonnee-2]='g';
            tab[abscisse-65][ordonnee-1]=-1;
            ori[abscisse-65][ordonnee-1]=' ';
            *pptour=(*pptour)+1;
        }

    if(ori[abscisse-65][ordonnee-1]=='h' && tab[abscisse-66][ordonnee-1]==-1)
        {
            tab[abscisse-66][ordonnee-1]=(*ppion);
            ori[abscisse-66][ordonnee-1]='h';
            tab[abscisse-65][ordonnee-1]=-1;
            ori[abscisse-65][ordonnee-1]=' ';
            *pptour=(*pptour)+1;
        }

        if(ori[abscisse-65][ordonnee-1]=='b' && tab[abscisse-64][ordonnee-1]==-1)
        {
            tab[abscisse-64][ordonnee-1]=(*ppion);
            ori[abscisse-64][ordonnee-1]='b';
            tab[abscisse-65][ordonnee-1]=-1;
            ori[abscisse-65][ordonnee-1]=' ';
            *pptour=(*pptour)+1;
        }

    }


        efface_ligne();
        affichage_pion(tab);
        affichage_orientation(ori);


}

/*--------------------------------------------------------------------------------------------------------*/

void orientiation_pion(int tab[5][5],char ori[5][5],int *ppion, int* pptour)    //Fonction pour changer l'orientation d'un pion
{
    char abscisse; int ordonnee;
    char choix_orientation=0;
    int retour =0;
    int verif_pion=0;

   do  {

        printf("\t  Quels sont les coordonnees du pion ?\t(F1 pour retour)");   //On verifie qu'il y a un pion a l'emplacement
            fflush(stdin);
            scanf("%c",&abscisse);
            scanf("%d",&ordonnee);
            if(abscisse=='F' && ordonnee ==1)
            {
                 retour=1;
            }

            if(tab[abscisse-65][ordonnee-1]!=(*ppion))
            {
                verif_pion=1;
            }

        }while(verif_pion!=0 && retour==0);



    if(retour==0)   //On demande la nouvelle orientation du pion
    {
        printf("Comment voulez-vous orienter votre pion ?\ng:gauche\td:droite\th:haut\tb:bas");
    do{
        fflush(stdin);
        scanf("%c",&choix_orientation);
    }while (choix_orientation!='g' && choix_orientation!='d' && choix_orientation!='b' && choix_orientation!='h');

    ori[abscisse-65][ordonnee-1]=choix_orientation; //On change l'orientation
    *pptour=(*pptour)+1;
    }

    efface_ligne();
    affichage_pion(tab);
    affichage_orientation(ori);

}

/*--------------------------------------------------------------------------------------------------------*/
void sortie_pion (int tab[5][5],char ori[5][5],int *ppion, int* pptour)
{
    fflush(stdin);
    char abscisse;int ordonnee;
    int retour =0;
   do
    {   printf("Quel pion voulez vous retirer ?\t(F1 pour revenir)");

        fflush(stdin);
        scanf ("%c",&abscisse);
        scanf("%d",&ordonnee);
        if(abscisse=='F' && ordonnee ==1) {retour=1;}   //Le while verifie que le pion est sur une cases exterieure
    }while((abscisse!='A' && abscisse!='E' && ordonnee!=5 && ordonnee!=1 && abscisse!='F') && (tab[abscisse-65][ordonnee-1]!=(*ppion)));

    if(retour==0)   //Sortie du pion on "reset" la case ou le pion est situé
    {
        tab[abscisse-65][ordonnee-1]=-1;
        ori[abscisse-65][ordonnee-1]=' ';
        *pptour=(*pptour)+1;
    }

    efface_ligne();
    affichage_pion(tab);
    affichage_orientation(ori);

}


