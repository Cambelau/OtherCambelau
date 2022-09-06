#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "creation.h"
#include "save.h"
#include "maillons.h"
#include "tri.h"

//Code pour le tri de la collection

void tri_fusion(t_maillon** ancre,  int choix)
{
    t_maillon* liste;
    t_maillon* a, *b;

    if(choix==0)        //blindage pour ne demander qu'une fois le choix car la fonction est récursive
    {
        printf("\nComment voulez trier ?\t1.Ordre croissant noms 2.Ordre croissant ages\t");
        scanf("%d",&choix);
        if(choix==1)
            printf("La collection va etre trie dans l'ordre croissant alphabetique des noms");
        if(choix==2)
            printf("La collection va etre trie dans l'ordre croissant des ages");
    }

    switch (choix)
    {
    case 1 :
        liste= *ancre;

        if ((liste == NULL) || (liste->suivant == NULL))        //cas de la liste vide ou avec une fiche
            return NULL;

        tri_separation(liste, &a, &b);      //on separe la liste en deux parties

        tri_fusion(&a,choix);       //on refait cette algorithme avec la partie a de la liste
        tri_fusion(&b,choix);       //idem avec la partie b

        *ancre = tri_nom(a, b);    //on fusionne a et b
        break;
    case 2 :
        liste= *ancre;

        if ((liste == NULL) || (liste->suivant == NULL))
            return NULL;

        tri_separation(liste, &a, &b);

        tri_fusion(&a,choix);
        tri_fusion(&b,choix);

        *ancre = tri_age(a, b);
        break;
    }
}


t_maillon* tri_age(t_maillon* a, t_maillon* b)
{
    t_maillon* result = NULL;

    if (a == NULL)      // cas où la partie a ou b est nulle  donc on retourne la partie opposée
        return b;
    else
    {
        if (b == NULL)
            return a;
    }

    if (a->age <= b->age)       //comparaison pour l'age
    {
        result = a;             //si oui on place a avant b
        result->suivant = tri_age(a->suivant, b);
    }
    else
    {
        result = b;             //sinon on place b avant a
        result->suivant = tri_age(a, b->suivant);
    }
    return result;
}

t_maillon* tri_nom(t_maillon* a, t_maillon* b)
{
    t_maillon* result = NULL;

    if (a == NULL)
        return b;
    else
    {
        if (b == NULL)
            return a;
    }

    if ( strcmp(a->nom,b->nom)<0)    //comparaison pour le nom
    {
        result = a;
        result->suivant = tri_nom(a->suivant, b);
    }
    else
    {
        result = b;
        result->suivant = tri_nom(a, b->suivant);
    }
    return result;
}


void tri_separation(t_maillon* liste,t_maillon** avant,  t_maillon** arriere)
{
    t_maillon* courant;
    t_maillon* prec;
    prec = liste;
    courant = liste->suivant;

    while (courant != NULL)
    {
        courant = courant->suivant;
        if (courant != NULL)
        {
            prec = prec->suivant;
            courant = courant->suivant;
        }
    }

    *avant = liste;
    *arriere = prec->suivant;
    prec->suivant = NULL;
}

t_maillon* inversion(t_maillon* liste)
{
    t_maillon* courant,* prec,*fin;
    courant=liste;

    if(courant->suivant==NULL)
        return liste;

    else
    {
        while(courant!=NULL)
        {
            if(courant==liste)
            {
                prec=courant;
                courant->precedent=NULL;
                courant=courant->suivant;
            }

            else
            {
                if(courant->suivant==NULL)
                {
                    printf("fin de liste");
                    fin=courant;
                    courant->precedent=prec;
                    courant=courant->suivant;
                }
                else
                {
                    courant->precedent=prec;
                    prec=courant;
                    courant=courant->suivant;
                }
            }
        }

        courant=fin;    //on repart de la fin

        while(courant!=NULL)
        {
            if(courant==fin)
            {
                prec=courant;
                liste=courant;
                courant=courant->precedent;
            }
            else
            {
                prec->suivant=courant;
                prec=courant;
                courant=courant->precedent;
            }
        }
    }
    prec->suivant=NULL;
    return liste;
}
