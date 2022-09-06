#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "creation.h"
#include "save.h"
#include "maillons.h"
#include "tri.h"

int main()
{
    t_maillon *liste =NULL;     //pointeur qui represente la tete de liste
    t_maillon* nouv =NULL;      //pointeur pour un nouveau maillon
    char nomsup[100];
    int choix =0;
    int arret =1;
    int inv=0;

    liste=chargerliste();
    printf("\n[[[\tNOM\t\tSIGNE\t\tAGE\t\tDATE DE NAISSANCE]]]\n");
    affiche_liste(liste);

    do
    {
        printf("\n*******************************************************************************************************\n");
        printf("CHOIX :\n1.Ajouter fiche au debut\n2.Ajouter une fiche en fin\n3.Afficher toutes les fiches\n4.Recherche selon critere\n");
        printf("5.Supprimer une fiche\n6.Tri selon critere\n10.Quitter\n\n->");
        scanf("%d",&choix);
        switch(choix)
        {
        case 1 :
            nouv=creer(liste);
            if(nouv!=NULL)
                liste=ajoutTete(liste,nouv);
            break;
        case 2 :
            nouv=creer(liste);
            if(nouv!=NULL)
                liste=ajoutFin(liste,nouv);
            break;
        case 3 :
            printf("\n[[[\tNOM\t\tSIGNE\t\tAGE\t\tDATE DE NAISSANCE]]]\n");
            affiche_liste(liste);
            break;
        case 4 :
            affiche_recherche(liste);
            break;
        case 5 :
            printf("\nQuelle fiche voulez vous supprimer ?\n");
            fflush(stdin);
            scanf("%s",nomsup);
            supp_fiche(liste,nomsup);
            break;
        case 6 :
            tri_fusion(&liste,0);
            printf("\n\nMettre la liste dans l'odre decroissant du tri effectue ?\t 1 pour oui\t");
            scanf("%d",&inv);
            if(inv==1)
                liste=inversion(liste);
            break;
        case 10 :
            arret= 0;
            break;
        }
        printf("\n");
    }
    while(arret==1);

    saveliste(liste);
    return 0;
}
