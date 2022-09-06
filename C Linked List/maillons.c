#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "creation.h"
#include "save.h"
#include "maillons.h"

//Code pour les manipulations générales de la liste : ajout, affichage, recherche, suppression

void affiche_nom(t_maillon *ptmaillon)
{
    printf("\t[%s]   ",ptmaillon->nom);
}
void affiche_signe(t_maillon *ptmaillon)
{
    printf("\t[%s]  ",ptmaillon->signe);
}
void affiche_age(t_maillon *ptmaillon)
{
    printf("\t[%d ans] ",ptmaillon->age);
}
void affiche_date(t_maillon *ptmaillon)
{
    printf("\t[%d/%d/%d]",ptmaillon->date.jour,ptmaillon->date.mois,ptmaillon->date.annee);
}

void affiche_liste(t_maillon *liste)
{
    t_maillon *courant;
    courant=liste;

    if(courant==NULL)
    {   }
    else
    {
        printf("\n");
        courant->precedent=NULL;
        affiche_nom(courant);
        affiche_signe(courant);
        affiche_age(courant);
        affiche_date(courant);
        affiche_liste(courant->suivant);
    }
}

t_maillon* ajoutTete(t_maillon* liste,t_maillon* N)
{
    if(liste==NULL)
        return N;
    else
    {
        N->suivant=liste;
        return N;
    }
}

t_maillon *ajoutFin(t_maillon* liste,t_maillon* nouv)
{
    t_maillon* courant,*precedent;
    courant=liste;
    precedent=courant;

    if(courant == NULL)
        return nouv;
    else
    {
        while(courant!=NULL)
        {
            precedent=courant;
            courant = courant->suivant;
        }
        precedent->suivant=nouv;
        return liste;
    }
}


void affiche_recherche(t_maillon *liste)
{
    t_maillon *courant;
    int choix,choid;//choix est le choix du menu, choid le choix pour la recherche de l'age, chois le choix pour la recherche du signe
    char chois[100];
    courant=liste;
    do          //blindage pour le choix de recherche
    {
        printf("Quel champ voulez vous chercher ? 1 pour nom, 2 pour signe, 3 pour age\n");
        scanf("%d",&choix);
    }while(choix!=1 && choix!=2 && choix!=3);

    switch(choix)
    {
        case 1 :
            printf("Quel nom voulez-vous chercher ?\t");
            fflush(stdin);
            gets(chois);

            while(courant!=NULL)//on parcours la liste est quand le nom recherché est trouvé on affiche le maillon
            {
                if(strcmp(chois,courant->nom)==0)
                {
                    printf("\n");
                    affiche_nom(courant);
                    affiche_signe(courant);
                    affiche_age(courant);
                    affiche_date(courant);
                }
                courant=courant->suivant;
            }
            break;
        case 2 :
            printf("Quel signe voulez-vous chercher ?\t");
            fflush(stdin);
            gets(chois);

            while(courant!=NULL)//on parcours la liste est quand le signe recherché est trouvé on affiche le maillon
            {
                if(strcmp(chois,courant->signe)==0)
                {
                    printf("\n");
                    affiche_nom(courant);
                    affiche_signe(courant);
                    affiche_age(courant);
                    affiche_date(courant);
                }
                courant=courant->suivant;
            }
            break;
        case 3 :

            printf("Quel age voulez-vous chercher ?\t");    //meme fonctionnement que la recherche de signe mais avec un entier
            fflush(stdin);
            scanf("%d",&choid);

            while(courant!=NULL)
            {
                if(choid==courant->age)
                {
                    printf("\n");
                    affiche_nom(courant);
                    affiche_signe(courant);
                    affiche_age(courant);
                    affiche_date(courant);
                }
                courant=courant->suivant;
            }
            break;
        }
}

t_maillon* supp_fiche(t_maillon* liste,char nom_sup[100])
{
    if(liste == NULL)       //cas de la liste vide
        return NULL;

    if(strcmp(liste->nom,nom_sup)==0)
    {
        t_maillon* prec;
        prec = liste->suivant;
        free(liste->nom);
        free(liste->age);
        free(liste);

        prec = supp_fiche(prec, nom_sup);
        return prec;
    }
    else
    {
        liste->suivant = supp_fiche(liste->suivant, nom_sup);
        return liste;
    }
}
