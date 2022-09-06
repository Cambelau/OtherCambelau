#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "creation.h"
#include "save.h"
#include "maillons.h"

//Code pour la création d'un maillon

t_maillon* creer(t_maillon* liste)
{
    t_maillon *pt;
    char mot[100];
    int verif = 1;

    pt = (t_maillon*) malloc(sizeof(t_maillon));

    if(pt==NULL)
        return NULL;
    /****************************************************************************************************************************/
    struct tm *infos;
    t_date aujourdhui;
    time_t temps_act;
    time(&temps_act);   // Déterminer le temps actuel en secondes
    infos = localtime(&temps_act);

    aujourdhui.jour=infos->tm_mday;
    aujourdhui.mois=1+(infos->tm_mon);
    aujourdhui.annee=1900+(infos->tm_year);
    /*************************************************************************************************************************/
    fflush(stdin);      //Allocation dynamique du nom avec verification que le nom n'est pas deja attribué a une autre fiche
    do
    {
        printf("\nQuel est votre nom ?\t\t\t");
        fflush(stdin);
        gets(mot);
        verif=verification_nom(liste,mot);
    }
    while(verif==1);

    pt->nom = malloc(strlen(mot) +1);

    if(pt->nom == NULL)
        return NULL;
    strcpy(pt->nom,mot);
    /************************************************************/
    do          //blindage de l'entree de la date de naissance,
    {
        printf("\nQuel est votre date de naissance ?\t");
        scanf("%d %d %d",&(pt->date.jour),&(pt->date.mois),&(pt->date.annee));
    }
    while((pt->date.jour)<=0 || (pt->date.jour)>31 || (pt->date.mois)<=0 || (pt->date.mois)>12);

    pt->age=anniversaire(aujourdhui,pt->date);  //age est determiné en fonction de la date actuelle

    signeastro(&pt);

    printf("\nBonjour %s, vous avez %d ans et votre signe astrologique est le %s.\n",(pt->nom),(pt->age),(pt->signe));
    /**************************************************************************/
    pt->suivant =NULL;
    return pt;
}
/******************************************************************************************************************************************/
void signeastro(t_maillon **pt)
{
    if((((*pt)->date.mois)==1 && ((*pt)->date.jour)>=21) || (((*pt)->date.jour)<=19 && ((*pt)->date.mois)==2))
    {
        char chaine[] = "Verseau";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==12 && ((*pt)->date.jour)>=22) || (((*pt)->date.jour)<=20 && ((*pt)->date.mois)==1))
    {
        char chaine[] = "Capricorne";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==2 && ((*pt)->date.jour)>=20) || (((*pt)->date.jour)<=20 && ((*pt)->date.mois)==3))
    {
        char chaine[] = "Poisson";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==11 && ((*pt)->date.jour)>=23) || (((*pt)->date.jour)<=21 && ((*pt)->date.mois)==12))
    {
        char chaine[] = "Sagittaire";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==10 && ((*pt)->date.jour)>=23) || (((*pt)->date.jour)<=22 && ((*pt)->date.mois)==11))
    {
        char chaine[] = "Scorpion";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==9 && ((*pt)->date.jour)>=23) || (((*pt)->date.jour)<=22 && ((*pt)->date.mois)==10))
    {
        char chaine[] = "Balance";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==8 && ((*pt)->date.jour)>=23) || (((*pt)->date.jour)<=22 && ((*pt)->date.mois)==9))
    {
        char chaine[] = "Vierge";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==7 && ((*pt)->date.jour)>=23) || (((*pt)->date.jour)<=22 && ((*pt)->date.mois)==8))
    {
        char chaine[] = "Lion";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==6 && ((*pt)->date.jour)>=22) || (((*pt)->date.jour)<=22 && ((*pt)->date.mois)==7))
    {
        char chaine[] = "Cancer";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==05 && ((*pt)->date.jour)>=22) || (((*pt)->date.jour)<=21 && ((*pt)->date.mois)==06))
    {
        char chaine[] = "Gemaux";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==04 && ((*pt)->date.jour)>=21) || (((*pt)->date.jour)<=21 && ((*pt)->date.mois)==05))
    {
        char chaine[] = "Taureau";
        strcpy((*pt)->signe,chaine);
    }

    if((((*pt)->date.mois)==03 && ((*pt)->date.jour)>=21) || (((*pt)->date.jour)<=20 && ((*pt)->date.mois)==04))
    {
        char chaine[] = "Belier";
        strcpy((*pt)->signe,chaine);
    }
}
/*****************************************************************************************************************************************/
int anniversaire(t_date a,t_date n)
{
    int tmp;
    tmp = (a.annee)-(n.annee);
    if(((n.mois)*31 + (n.jour)) > ((a.mois)*31 + (a.jour)))
        tmp=tmp-1;
    return tmp;
}


int verification_nom(t_maillon* liste,char mot[100])
{
    t_maillon* courant;
    courant = liste;

    if(courant == NULL)
        return 0;
    else
    {
        while(courant!=NULL)
        {
            if(strcmp(mot,courant->nom)==0)
            {
                printf("\nCe nom existe deja !\n");
                return 1;
            }
            courant = courant->suivant;
        }
    }
}
