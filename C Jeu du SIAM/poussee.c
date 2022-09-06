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

void poussee_globale(int tab[5][5],char ori[5][5],int *ppion, int* pptour)  //la pousse se fait en deux parties : d'abord on verifie si la poussee est possible puis on deplace les pions
{
    char abscisse;
    int ordonnee;
    int retour =0;
    int autorisation_poussee=0;
    int verif_pion=0;

    do
    {
        printf("Quel pion déplacer par poussee : (F1 pour revenir)\t");
        fflush(stdin);
        scanf ("%c",&abscisse);
        scanf("%d",&ordonnee);
        if(abscisse=='F' && ordonnee==1)
            {
                 retour=1;
            }

        if(tab[abscisse-65][ordonnee-1]==(*ppion))
            {

                verif_pion=1;
            }


    }while(verif_pion==0 && retour==0) ;        //on demande et verifie quel est la case du pion que le joueur veut déplacer



    int pion_x=abscisse-65;
    int pion_y=ordonnee-1;

    if(retour==0)
    {
        autorisation_poussee=verification_poussee(tab,ori,&pion_x,&pion_y);

        if(autorisation_poussee==1)
            {
                printf("Poussee autorisee\n");
                deplacement_poussee(tab,ori,&pion_x,&pion_y);
                *pptour=(*pptour)+1;
            }
        else
            {
            printf("POUSSEE IMPOSSIBLE");
            }
    system("pause");

    }


    efface_ligne();
    affichage_pion(tab);
    affichage_orientation(ori);

}
/*-----------------------------------------------------------------------------------------------------------------------------*/
int verification_poussee(int tab[5][5],char ori[5][5],int *ppion_x, int* ppion_y)
{
    int bool_poussee =1;    //bool_pousse = 1 signifie que la poussee est autorisée
    float somme_poussee=1;  //somme_poussee représente la somme des resitances des pions que l'on va poussee + la force de base de l'animal qui est de 1
    int i;
    int S=0;
/*------------------------------------------------------------------*/
    if(ori[(*ppion_x)][(*ppion_y)]=='d')                               //4 fois la meme conditions pour chaque orientation.
    {
        i=(*ppion_y)+1;
        while(tab[(*ppion_x)][i]==RHINOCEROS    ||  tab[(*ppion_x)][i]==ELEPHANT || tab[(*ppion_x)][i]==MONTAGNE)
        {S++;i++;}  //on calcule le nombre de pions qui suit le pion selectionner

        for(i=((*ppion_y)+1);i<=((*ppion_y)+1+S);i++)
        {
            if( tab[(*ppion_x)][i]==RHINOCEROS    ||  tab[(*ppion_x)][i]==ELEPHANT  )
            {

                if(ori[(*ppion_x)][i]=='g')
                {
                    somme_poussee=somme_poussee-1;  //si cest un elephant ou un rhino dans le sens opposé on fait -1
                }
                if(ori[(*ppion_x)][i]=='d')         //si cest un elephant ou un rhino dans le sens opposé on fait +1
                {
                    somme_poussee++;
                }
            }
                if( tab[(*ppion_x)][i]==MONTAGNE)  //si on veut deplacer une montagne cest -0.9
                {
                somme_poussee=somme_poussee-0.9;
                }
        }
    }

/*---------------------------------------------------------------*/
    if(ori[(*ppion_x)][(*ppion_y)]=='g')
    {
        i=(*ppion_y)-1;
        while(tab[(*ppion_x)][i]==RHINOCEROS    ||  tab[(*ppion_x)][i]==ELEPHANT || tab[(*ppion_x)][i]==MONTAGNE)
        {S++;i--;}

        for(i=((*ppion_y)-1);i>=((*ppion_y)-1-S);i--)
        {
            if( tab[(*ppion_x)][i]==RHINOCEROS    ||  tab[(*ppion_x)][i]==ELEPHANT  )
            {

                if(ori[(*ppion_x)][i]=='d')
                {
                    somme_poussee=somme_poussee-1;
                }
                if(ori[(*ppion_x)][i]=='g')
                {
                    somme_poussee++;
                }
            }
                if( tab[(*ppion_x)][i]==MONTAGNE)
                {
                somme_poussee=somme_poussee-0.9;
                }
        }
    }
/*-------------------------------------------------------------------------------------------------------------------------***/
    if(ori[(*ppion_x)][(*ppion_y)]=='b')
    {
        i=(*ppion_x)+1;
        while(tab[i][(*ppion_y)]==RHINOCEROS    ||  tab[i][(*ppion_y)]==ELEPHANT || tab[i][(*ppion_y)]==MONTAGNE)
        {S++;i++;}

        for(i=((*ppion_x)+1);i<=((*ppion_x)+1+S);i++)
        {
            if( tab[i][(*ppion_y)]==RHINOCEROS    ||  tab[i][(*ppion_y)]==ELEPHANT  )
            {

                if(ori[i][(*ppion_y)]=='h')
                {
                    somme_poussee=somme_poussee-1;
                }
                if(ori[i][(*ppion_y)]=='b')
                {
                    somme_poussee++;
                }
            }
                if( tab[i][(*ppion_y)]==MONTAGNE)
                {
                somme_poussee=somme_poussee-0.9;
                }
        }
    }

/*---------------------------------------------------------------*/
    if(ori[(*ppion_x)][(*ppion_y)]=='h')
    {
        i=(*ppion_x)-1;
        while(tab[i][(*ppion_y)]==RHINOCEROS    ||  tab[i][(*ppion_y)]==ELEPHANT || tab[i][(*ppion_y)]==MONTAGNE)
        {S++;i--;}

        for(i=((*ppion_x)-1);i>=((*ppion_x)-1-S);i--)
        {
            if( tab[i][(*ppion_y)]==RHINOCEROS    ||  tab[i][(*ppion_y)]==ELEPHANT  )
            {

                if(ori[i][(*ppion_y)]=='b')
                {
                    somme_poussee=somme_poussee-1;
                }
                if(ori[i][(*ppion_y)]=='h')
                {
                    somme_poussee++;
                }
            }
                if( tab[i][(*ppion_y)]==MONTAGNE)
                {
                somme_poussee=somme_poussee-0.9;
                }
        }
    }
/*-------------------------------------------------------------------*/
//Si la somme des resistance + la force de base est inferieure ou égale à 0 la poussee est refusée, bool_poussee prend la valeur 0
//Au contraire si elle est strictemetn supérieure à 0 cest possible et bool_poussee garde la valeur 1



    if(somme_poussee<=0)
        bool_poussee=0;

    printf("La somme des pions fait %f.\t\t",somme_poussee);
    return bool_poussee;
}
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
void deplacement_poussee(int tab[5][5],char ori[5][5],int *ppion_x, int* ppion_y)
{
    int temp,i,S=0;
    char pemt;
    int stop=44;
/*--------------------------------------------------------------*/
    if (ori[(*ppion_x)][(*ppion_y)]=='d')   //4 fois la meme conditions pour chaque orientation

        {
                i=(*ppion_y)+1;
                while(tab[(*ppion_x)][i]==RHINOCEROS    ||  tab[(*ppion_x)][i]==ELEPHANT || tab[(*ppion_x)][i]==MONTAGNE)
                {S++;i++;}  //on compte le nombre de pion qui suit le pion selectionne

                temp=tab[(*ppion_x)][(*ppion_y)+S];//on garde en mémoire le type du pion à l'extremité car il sera écrasé
                for(i=0;i<=S;i++)
                {tab[(*ppion_x)][(*ppion_y)+S-i]=tab[(*ppion_x)][(*ppion_y)+S-1-i];} //cette boucle décale de un vers la droite chaque pion



                tab[(*ppion_x)][(*ppion_y)]=-1; //on reset la case de départ car plus aucun pion ne s'y trouve (si on ne le fait pas un pion est dupliqué)
                tab[(*ppion_x)][(*ppion_y)+S+1]=temp;   //on replace le pion a l'extrémité

                if(tab[(*ppion_x)][(*ppion_y)+S+1]==MONTAGNE && (*ppion_y)+S+1==5)  //condition pour vérifier quel joueur a gagné
                    {
                        printf("\nFIN DU JEU\n");
                        for(i=0;i<stop;i++)
                            if(ori[(*ppion_x)][(*ppion_y)+S-i]=='d')    //on verifie si le pion pres de la montagne sortie est dans le sens de la pousse
                            {                                              //si ce n'est pas le cas on prend le pion qui le precede jusqu'a trouver le bon
                            if(tab[(*ppion_x)][(*ppion_y)+S-i]==1)      //si ce pion a la valeur 1 les rhinoceros ont gagné sinon c'est les elephants
                               {
                                    Color(6,0);
                                    printf("\n\t\t\tLa partie est finie, les elephants ont gagne ! Bravo!\n");
                                    system("pause");
                                    i=stop;
                               }

                            if(tab[(*ppion_x)][(*ppion_y)+S-i]==2)
                                {
                                    Color(6,0);
                                    printf("\n\t\t\tLa partie est finie, les rhinceros ont gagne  ! Bravo!\n");
                                    system("pause");
                                    i=stop;
                                }
                            }

                    }

                if((*ppion_y)+S+1==5)tab[(*ppion_x)][(*ppion_y)+S+1]=-1;

                pemt=ori[(*ppion_x)][(*ppion_y)+S];     //on effecue le meme deplacement pour les orientations que pour les pions
                for(i=0;i<=S;i++)
                {ori[(*ppion_x)][(*ppion_y)+S-i]=ori[(*ppion_x)][(*ppion_y)+S-1-i];}

                ori[(*ppion_x)][(*ppion_y)]=-1;
                ori[(*ppion_x)][(*ppion_y)+S+1]=pemt;
                if((*ppion_y)+S+1==5)ori[(*ppion_x)][(*ppion_y)+S+1]=' ';
        }
/*-------------------------------------------------------------------------------------------------------------------------------*/
if (ori[(*ppion_x)][(*ppion_y)]=='g')

        {
                i=(*ppion_y)-1;
                while(tab[(*ppion_x)][i]==RHINOCEROS    ||  tab[(*ppion_x)][i]==ELEPHANT || tab[(*ppion_x)][i]==MONTAGNE)
                {S++;i++;}

                temp=tab[(*ppion_x)][(*ppion_y)-S];
                for(i=0;i<=S;i++)
                {tab[(*ppion_x)][(*ppion_y)-S+i]=tab[(*ppion_x)][(*ppion_y)-S+1+i];}

                tab[(*ppion_x)][(*ppion_y)]=-1;
                tab[(*ppion_x)][(*ppion_y)-S-1]=temp;

                if((*ppion_y)-S-1==-1)tab[(*ppion_x)][(*ppion_y)-S-1]=-1;

                pemt=ori[(*ppion_x)][(*ppion_y)-S];
                for(i=0;i<=S;i++)
                {ori[(*ppion_x)][(*ppion_y)-S+i]=ori[(*ppion_x)][(*ppion_y)-S+1+i];}

                ori[(*ppion_x)][(*ppion_y)]=-1;
                ori[(*ppion_x)][(*ppion_y)-S-1]=pemt;
                if((*ppion_y)-S-1==5)ori[(*ppion_x)][(*ppion_y)-S-1]=' ';
        }
/*----------------------------------------------------------------------------------------------------------------------*/

if (ori[(*ppion_x)][(*ppion_y)]=='b')

        {
                i=(*ppion_x)+1;
                while(tab[i][(*ppion_y)]==RHINOCEROS    ||  tab[i][(*ppion_y)]==ELEPHANT || tab[i][(*ppion_y)]==MONTAGNE)
                {S++;i++;}

                temp=tab[(*ppion_x)+S][(*ppion_y)];
                for(i=0;i<=S;i++)
                {tab[(*ppion_x)+S-i][(*ppion_y)]=tab[(*ppion_x)+S-1-i][(*ppion_y)];}

                tab[(*ppion_x)][(*ppion_y)]=-1;
                tab[(*ppion_x)+S+1][(*ppion_y)]=temp;
                if((*ppion_x)+S+1==5)tab[(*ppion_x)+S+1][(*ppion_y)]=-1;

                pemt=ori[(*ppion_x)+S][(*ppion_y)];
                for(i=0;i<=S;i++)
                {ori[(*ppion_x)+S-i][(*ppion_y)]=ori[(*ppion_x)+S-1-i][(*ppion_y)];}

                ori[(*ppion_x)][(*ppion_y)]=-1;
                ori[(*ppion_x)+S+1][(*ppion_y)]=pemt;
                if((*ppion_x)+S+1==5)ori[(*ppion_x)+S+1][(*ppion_y)]=' ';
        }
/*-----------------------------------------------------------------------------------------------------------------------------*/
if (ori[(*ppion_x)][(*ppion_y)]=='h')

        {
                i=(*ppion_x)-1;
                while(tab[i][(*ppion_y)]==RHINOCEROS    ||  tab[i][(*ppion_y)]==ELEPHANT || tab[i][(*ppion_y)]==MONTAGNE)
                {S++;i++;}

                temp=tab[(*ppion_x)-S][(*ppion_y)];
                for(i=0;i<=S;i++)
                {tab[(*ppion_x)-S+i][(*ppion_y)]=tab[(*ppion_x)-S+1+i][(*ppion_y)];}

                tab[(*ppion_x)][(*ppion_y)]=-1;
                tab[(*ppion_x)-S-1][(*ppion_y)]=temp;
                if((*ppion_x)-S-1==5)tab[(*ppion_x)-S-1][(*ppion_y)]=-1;

                pemt=ori[(*ppion_x)-S][(*ppion_y)];
                for(i=0;i<=S;i++)
                {ori[(*ppion_x)-S+i][(*ppion_y)]=ori[(*ppion_x)-S+1+i][(*ppion_y)];}

                ori[(*ppion_x)][(*ppion_y)]=-1;
                ori[(*ppion_x)-S-1][(*ppion_y)]=pemt;
                if((*ppion_x)-S-1==-1)ori[(*ppion_x)-S-1][(*ppion_y)]=' ';
        }



}

