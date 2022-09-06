#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "creation.h"
#include "save.h"
#include "maillons.h"

//Code pour la sauvegarde et le chargement de la collection

void saveliste(t_maillon *liste)
{
    t_maillon *courant;
    courant=liste;

    if(courant==NULL)       //cas de la liste vide
    {
        printf("\nLa collection est vide\n");
        remove("fichier.txt");      //si la liste a été supprimer en memoire vive on ne sauvegarde rien en fichier donc on supprime le fichier
    }
    else        //ouverture et ecriture dans le fichier avecc creatio  du fichier si inexistant
    {
        FILE* fichier = NULL;
        fichier=fopen("fichier.txt","w");

        if(fichier ==NULL)
            printf("ERREUR Sauvegarde");

        else
        {
            while (courant!=NULL)
            {
                fprintf(fichier,"\n%s %s %d %d %d %d",courant->nom,courant->signe,courant->age,courant->date.jour,courant->date.mois,courant->date.annee);
                courant = courant->suivant;
            }
        }
        fclose(fichier);
    }
}

t_maillon* chargerliste()
{
    t_maillon* liste = NULL;
    t_maillon* preced;
    t_maillon* courant;
    int caractereActuel = 0;
    char chaine[100],chaine2[20];
    FILE* fichier = NULL;

    fichier=fopen("fichier.txt","r");   //ouverture

    if(fichier == NULL)
    {
        printf("PAS DE FICHER");
        return NULL;
    }
    else
    {
        do
        {
            t_maillon* pt =NULL;            //declaration d'un maillon comme pour une creation classique
            pt = (t_maillon*) malloc(sizeof(t_maillon));
            if(pt==NULL)
                return NULL;
            /*****************************************************************************************************************************/
            fscanf(fichier,"%s %s %d %d %d %d",chaine,chaine2,&(pt->age),&(pt->date.jour),&(pt->date.mois),&(pt->date.annee));
            pt->nom = malloc(strlen(chaine) +1);        //on recupere toute les donnees du maillon dans le fichier
            if(pt->nom== NULL)
                return NULL;
            strcpy(pt->nom,chaine);
            strcpy(pt->signe,chaine2);
            pt->precedent=NULL;
            pt->suivant=NULL;
            /*****************************************************************************************************************************/
            if(liste==NULL)     //pour le premier maillon il deviens la liste
                liste=pt;
            else
            {
                pt->precedent=liste;    //ajout en tete du maillon dans la liste avec chainage vers l'arriere
                liste=pt;
            }
        }
        while ((caractereActuel = fgetc(fichier)) != EOF);      //boucle tant qu'on arrive pas a la fin du fichier EOF

        courant=liste;

        if(courant->precedent==NULL)        //cas ou le maillon est la liste
        {
            courant->suivant=NULL;
        }
        else
        {
            while(courant->precedent!=NULL)     //on parcours la liste dans le sens orecedent en partant de la tete de liste
            {
                preced=courant->precedent;   //on relie les maillons par les pointeurs suivant pour obtenir la liste dans l'ordre dans lequel elle a été sauvegarder
                preced->suivant=courant;
                courant=courant->precedent;
            }
        }
        liste=courant;
        fclose(fichier);
        return liste;
    }
}
