#include <stdio.h>
#include <stdlib.h>
#include <allegro.h>
#include <math.h>
#define N 15     //nombre de type de ennemi
#define D 3     //nombre de type de tir
#define T 5     //nombre de type de bonus

//Structure pour un objet quelconque
typedef struct acteur
{
    int posx, posy;
    int tx,ty;
    int vitx,vity;
    int hp;     //0 = mort
    int etat;   //0 deplacement // 1 explosion
    int type;
    int rechargement;
    BITMAP *img;
    int cpt_img;
} t_acteur;

//structure pour la liste
typedef struct liste
{
    int taille_max; //taille max du tableau
    int nbre;       //nombre d'element utilisé
    t_acteur **tab; //pointeur sur le tableau
    int type;       //type 0 personnage //type 1 missile //type 2 ennemi //type 3 objets
    BITMAP *img[3];
} t_liste_acteur;

typedef struct interface
    {
        int score;
        int progression;
        int vitessee_defilement;
        int niveau;
        int jauge;
    } t_interface;
/***********************************************************************************/
void initialisation_allegro();

void creer_vaisseau(t_acteur* vaisseau);//creer le vaisseau
void creerListes(t_liste_acteur* liste_ennemi[N],t_liste_acteur* liste_missile[D],t_liste_acteur* liste_bonus[T], t_interface *jeu);
t_acteur * creerActeur(int x,int y,BITMAP* img,int type);
t_liste_acteur * creerListeActeur(int maxacteurs,int type);
void remplirActeur(t_acteur *nouv, int x, int y, int z, int k);
//reset les liste apres avoir finit ou perdu un niveau
void resetActeur(t_liste_acteur* liste_ennemi[N],t_liste_acteur* liste_missile[D],t_liste_acteur* liste_bonus[T], t_interface *jeu, t_acteur* vaisseau);

void ajouterActeur(t_liste_acteur *la,int x,int y); //ajouter un acteur au tableau
void enleverActeur(t_liste_acteur *la,int i);       //retirer un acteur au tableau

//fonctions pour actualiser les acteurs, les positions, leur etats
void actualiserActeur(t_acteur *acteur,BITMAP* buffer);
void actualiserListeActeurs_missile(t_liste_acteur *la[D],BITMAP *buffer);
void actualiserListeActeurs_ennemi(t_liste_acteur *la[N],BITMAP* buffer);
void actualiserVaisseau(t_acteur *acteur, t_liste_acteur *la[D]);
void actualiserListe_tir_ennemi(t_liste_acteur *liste_ennemi[N]);
void actualiserListeActeurs_bonus(t_liste_acteur *la[T],BITMAP* buffer);

//fonction pour dessiner les acteurs sur le buffer
void dessinerActeur(BITMAP *bmp,t_acteur *acteur,BITMAP* img_acteur);
void dessinerListeActeurs_missile(BITMAP *bmp,t_liste_acteur *la[D]);
void dessinerListeActeurs_ennemi(BITMAP *bmp,t_liste_acteur *la[N]);
void dessinerVaisseau(BITMAP* buffer, t_acteur *vaisseau);
void dessiner_explosion(t_acteur* acteur, BITMAP* buffer);
void dessinerListeActeurs_bonus(BITMAP *bmp,t_liste_acteur *la[D]);

//sous programme qui permet de detecter la collision entre 2 acteurs
//boollen retourne vrai si il y a un contact entre les deux
int collisionActeurs(t_acteur *a1, t_acteur *a2);
//parcours la liste de missile et de ennemi et verifie a chaque fois si il y a une collision entre deux acteurs grace au boolen precedent
void collisionListeActeurs(t_liste_acteur *le[N],t_liste_acteur *la[D],t_interface* jeu);
//sous programme qui verifie la collision entre le vaisseau, les bonus et les ennemis
void collision_vaisseau(t_acteur *vaisseau,t_liste_acteur *listeennemi[N], t_liste_acteur *liste_bonus[T], t_interface* jeu);

//sous programmes qui libèrentt la mémoire des tableaux de les listes
void eliminer_toute_liste_missile (t_liste_acteur *la[D]);
void eliminer_toute_liste_ennemi(t_liste_acteur *la[N]);
void eliminer_toute_liste_bonus(t_liste_acteur *la[T]);

//programmes qui générent les ennemis en fonction du niveau
void progression_niveau_1(t_liste_acteur *liste_bonus[T],t_liste_acteur *liste_ennemi[N], t_liste_acteur *liste_missile[D],t_interface *jeu,BITMAP* buffer);
void progression_niveau_2(t_liste_acteur *liste_bonus[T],t_liste_acteur *liste_ennemi[N], t_liste_acteur *liste_missile[D],t_interface *jeu,BITMAP* buffer);
void progression_niveau_3(t_liste_acteur *liste_bonus[T],t_liste_acteur *liste_ennemi[N], t_liste_acteur *liste_missile[D],t_interface *jeu,BITMAP* buffer);
/**************************************************************************************************************************************************/
/*SOUS-PROGRAMMES*/
/**************************************************************************************************************************************************/

//fonction de creation du joueur
void creer_vaisseau(t_acteur* vaisseau)
{
    vaisseau->img=load_bitmap("image//perso//vaisseau.bmp",NULL);

    if(!vaisseau->img)
        allegro_message("PRBLEME");

    vaisseau->posx=400-vaisseau->img->w/2;
    vaisseau->posy=300-vaisseau->img->h/2;
    vaisseau->vitx=6;
    vaisseau->vity=6;
    vaisseau->tx=vaisseau->img->w;
    vaisseau->ty=vaisseau->img->h;
    vaisseau->hp=3;
    vaisseau->type=0;
    vaisseau->etat=0;
}

void creerListes(t_liste_acteur* liste_ennemi[N],t_liste_acteur* liste_missile[D],t_liste_acteur* liste_bonus[T], t_interface *jeu)
{
    int i;
    liste_missile[0]=creerListeActeur(20,1);
    liste_missile[1]=creerListeActeur(20,2);
    liste_missile[2]=creerListeActeur(1,3);

    liste_ennemi[0]=creerListeActeur(10,4);
    liste_ennemi[1]=creerListeActeur(1,6);
    liste_ennemi[2]=creerListeActeur(1,7);
    liste_ennemi[3]=creerListeActeur(10,5);
    liste_ennemi[4]=creerListeActeur(3,8);
    liste_ennemi[5]=creerListeActeur(1,9);
    liste_ennemi[6]=creerListeActeur(10,10);
    liste_ennemi[7]=creerListeActeur(10,17);
    liste_ennemi[8]=creerListeActeur(10,18);
    liste_ennemi[9]=creerListeActeur(1,19);
    liste_ennemi[10]=creerListeActeur(1,20);
    liste_ennemi[11]=creerListeActeur(1,21);
    liste_ennemi[12]=creerListeActeur(1,22);
    liste_ennemi[13]=creerListeActeur(1,23);
    liste_ennemi[14]=creerListeActeur(1,23);

    liste_bonus[0]=creerListeActeur(1,11);
    liste_bonus[1]=creerListeActeur(1,12);
    liste_bonus[2]=creerListeActeur(1,14);
    liste_bonus[3]=creerListeActeur(1,15);
    liste_bonus[4]=creerListeActeur(1,16);

    jeu->vitessee_defilement=4;
    jeu->niveau=0;
    jeu->progression=4;
    jeu->jauge=0;
    jeu->score=0;

    liste_missile[0]->img[0]=load_bitmap("image//objet//tir.bmp",NULL);
    liste_missile[1]->img[0]=load_bitmap("image//objet//tir_A.bmp",NULL);
    liste_missile[2]->img[0]=load_bitmap("image//objet//tir_B.bmp",NULL);

    liste_ennemi[0]->img[0]=load_bitmap("image//ennemi//ennemi_1.1.bmp",NULL);
    liste_ennemi[0]->img[1]=load_bitmap("image//ennemi//ennemi_1.2.bmp",NULL);
    liste_ennemi[0]->img[2]=load_bitmap("image//ennemi//ennemi_1.3.bmp",NULL);
    liste_ennemi[1]->img[0]=load_bitmap("image//ennemi//ennemi_2.1.bmp",NULL);
    liste_ennemi[1]->img[1]=load_bitmap("image//ennemi//ennemi_2.2.bmp",NULL);
    liste_ennemi[1]->img[2]=load_bitmap("image//ennemi//ennemi_2.3.bmp",NULL);
    liste_ennemi[2]->img[0]=load_bitmap("image//ennemi//ennemi_D.bmp",NULL);
    liste_ennemi[3]->img[0]=load_bitmap("image//objet//tir_C.bmp",NULL);
    liste_ennemi[4]->img[0]=load_bitmap("image//ennemi//ennemi_3.bmp",NULL);
    liste_ennemi[5]->img[0]=load_bitmap("image//ennemi//boss_1.bmp",NULL);
    liste_ennemi[6]->img[0]=load_bitmap("image//ennemi//tir_boss.bmp",NULL);
    liste_ennemi[7]->img[0]=load_bitmap("image//ennemi//Ennemi 5.2.bmp",NULL);
    liste_ennemi[8]->img[0]=load_bitmap("image//ennemi//Ennemi 6.2.bmp",NULL);
    liste_ennemi[9]->img[0]=load_bitmap("image//ennemi//boss_2.bmp",NULL);
    liste_ennemi[10]->img[0]=load_bitmap("image//ennemi//tir_boss.bmp",NULL);
    liste_ennemi[11]->img[0]=load_bitmap("image//ennemi//mini_boss.bmp",NULL);
    liste_ennemi[12]->img[0]=load_bitmap("image//ennemi//ennemi_boss.bmp",NULL);
    liste_ennemi[13]->img[0]=load_bitmap("image//ennemi//boss_final.bmp",NULL);
    liste_ennemi[14]->img[0]=load_bitmap("image//ennemi//boss_final.bmp",NULL);

    liste_bonus[0]->img[0]=load_bitmap("image//objet//bonus_1.bmp",NULL);
    liste_bonus[1]->img[0]=load_bitmap("image//objet//bonus_2.bmp",NULL);
    liste_bonus[2]->img[0]=load_bitmap("image//objet//bonus_3.bmp",NULL);
    liste_bonus[3]->img[0]=load_bitmap("image//objet//bonus_4.bmp",NULL);
    liste_bonus[4]->img[0]=load_bitmap("image//perso//vaisseau_2.bmp",NULL);

    for(i = 0; i < 3; i++)
    {
        if(!liste_missile[i]->img[0])
        {
            allegro_message("pas pu trouver images");
            exit(EXIT_FAILURE);
        }
    }
    for(i = 0; i < 14; i++)
    {
        if(!liste_ennemi[i]->img[0])
        {
            allegro_message("pas pu trouver images");
            exit(EXIT_FAILURE);
        }
    }
    for(i = 0; i < 5; i++)
    {
        if(!liste_bonus[i]->img[0])
        {
            allegro_message("pas pu trouver images");
            exit(EXIT_FAILURE);
        }
    }
}

// Allouer et initialiser une liste (vide) de acteurs
t_liste_acteur *creerListeActeur(int maxacteurs, int type)
{
    t_liste_acteur *nouv;       //declare une nouvelle liste d'acteur
    int i;

    nouv=(t_liste_acteur *)malloc(1*sizeof(t_liste_acteur));        //alloue la memoire de la liste

    nouv->taille_max=maxacteurs;    //defini le nombre maximum d'acteur
    nouv->nbre=0;                      //pour l'instant on en utilise 0
    nouv->tab=(t_acteur **)malloc(maxacteurs*sizeof(t_acteur*));        //alloue la mémoire du tableau dynamique compris dans la structure liste

    for (i=0; i<maxacteurs; i++)    //initialise a 0
    {
        nouv->tab[i]=NULL;
    }
    nouv->type=type;
    return nouv;
}
/**************************************************************************************************************************************************/
void ajouterActeur(t_liste_acteur *la,int x,int y)
{
    int i =0;
    t_acteur *acteur;

    if (la->nbre >= la->taille_max)
        return NULL;

    acteur=creerActeur(x,y,la->img[0],la->type);

    while (la->tab[i]!=NULL && i<la->taille_max)
    {
        i++;
    }

    la->tab[i]=acteur;
    la->nbre++;
}
/**************************************************************************************************************************************************/
t_acteur * creerActeur(int x,int y,BITMAP* img,int type)
{
    t_acteur *nouv;

    nouv=(t_acteur *)malloc(1*sizeof(t_acteur));

    // Initialiser ...

    switch(type)
    {
    case 1 :                         //tir 1
        remplirActeur(nouv,1,0,10,rand()%4-2);
        break;
    case 2 :                         //tir 2
        remplirActeur(nouv,5,0,10,0);
        break;
    case 3 :                         //tir 3
        remplirActeur(nouv,10,0,1,0);                         //hp,etat,vitx,vity
        break;
    case 4 :                        //ennemi 1
        remplirActeur(nouv,1,0,5,0);
        break;
    case 5 :                       //tir ennemi 1
        remplirActeur(nouv,1,0,8,0);
        break;
    case 6 :                        //ennemi 2
        remplirActeur(nouv,2,0,5,0);
        break;
    case 7 :                     //ennemi 3 meteorite
        remplirActeur(nouv,1,0,rand()%2-1,10);
        break;
    case 8 :
        remplirActeur(nouv,1,0,rand()%15,5);
        break;
    case 9 :
        remplirActeur(nouv,100,0,0,3);
        break;
    case 10 :
        remplirActeur(nouv,1,0,7,0);
        break;
    case 11 :
        remplirActeur(nouv,1,0,4,0);
        break;
    case 12 :
        remplirActeur(nouv,1,0,4,0);
        break;
    case 14 :
        remplirActeur(nouv,1,0,4,0);
        break;
    case 15 :
        remplirActeur(nouv,1,0,4,0);
        break;
    case 16 :
        remplirActeur(nouv,1,0,0,0);
        break;
    case 17 :
        remplirActeur(nouv,1,0,5,0);
        break;
    case 18 :
        remplirActeur(nouv,1,0,5,0);
        break;
    case 19 :
        remplirActeur(nouv,150,0,0,3);
        break;
    case 20 :
        remplirActeur(nouv,1,0,5,rand()%2-1);
        break;
    case 21 :
        remplirActeur(nouv,1,0,5,0);
        break;
    case 23 :
        remplirActeur(nouv,200,0,0,0);
        break;
    case 22 :
        remplirActeur(nouv,1,0,5,rand()%2-1);
        break;
    }

    nouv->posx=x;
    nouv->posy=y;
    nouv->tx=img->w;
    nouv->ty=img->h;
    nouv->cpt_img=0;
    return nouv;
}

void remplirActeur(t_acteur *nouv, int x, int y, int z, int k)
{
    nouv->hp=x;        // ici ce qui est commun aux acteurs
    nouv->etat=y;
    nouv->vitx=z;
    nouv->vity=k;
    nouv->rechargement=0;
}
/**************************************************************************************************************************************************/
void actualiserActeur(t_acteur *acteur, BITMAP* buffer)
{
    if(acteur->etat==0)
    {
        switch(acteur->type)    // deplacement
        {
        case 1 :
            acteur->posx+=acteur->vitx;
            acteur->posy+=acteur->vity;
            break;
        case 2 :
            acteur->posx+=acteur->vitx;
            break;
        case 3 :
            acteur->posx+=acteur->vitx;
            if(acteur->vitx<8)
                acteur->vitx++;
            break;
        case 4 :
            acteur->posx-=acteur->vitx;
            break;
        case 5 :
            acteur->posx-=acteur->vitx;
            break;
        case 7 :
            acteur->posy+=acteur->vity;
            acteur->posx+=acteur->vitx;
            break;
        case 8 :
            acteur->posy-=acteur->vity;
            acteur->posx-=acteur->vitx;
            break;
        case 9 :
            if(acteur->posy<0)
                acteur->vity=-1*acteur->vity;
            if(acteur->posy+acteur->ty>=500)
                acteur->vity=-1*acteur->vity;
            acteur->posy+=acteur->vity;
            break;
        case 10 :
            acteur->posx-=acteur->vitx;
            break;
        case 11 :
            acteur->posx-=acteur->vitx;
            break;
        case 12 :
            acteur->posx-=acteur->vitx;
            break;
        case 14 :
            acteur->posx-=acteur->vitx;
            break;
        case 15 :
            acteur->posx-=acteur->vitx;
            break;
        case 17 :
            acteur->posx-=acteur->vitx;
            break;
        case 18 :
            acteur->posx-=acteur->vitx;
            break;
        case 19 :
            if(acteur->posy<0)
                acteur->vity=-1*acteur->vity;
            if(acteur->posy+acteur->ty>=500)
                acteur->vity=-1*acteur->vity;
            acteur->posy+=acteur->vity;
            break;
        case 20 :
            acteur->posy-=acteur->vity;
            acteur->posx-=acteur->vitx;
            break;
        case 21 :
            acteur->posy-=acteur->vity;
            acteur->posx-=acteur->vitx;
            break;
        case 22 :
            acteur->posy-=acteur->vity;
            acteur->posx-=acteur->vitx;
            break;
        }
    }

    if (acteur->hp==0)        //cas ou il explose
    {
        if(acteur->type==1 || acteur->type==2 || acteur->type==3 || acteur->type==5)
        {
            acteur->etat=15;        //ceux la n'explosent pas
        }

        if(acteur->etat<15)
        {
            acteur->etat++;
            dessiner_explosion(acteur,buffer);
        }
    }
// si dépasse le bord alors disparait
    if (acteur->posx+acteur->tx<0 || acteur->posx>SCREEN_W || acteur->posy+acteur->ty<0 || acteur->posy+acteur->ty>520 )
    {
        if(acteur->type != 7 && acteur->type !=9)
        {
            acteur->hp=0;
            acteur->etat=15;        //on ne veut pas d'explosion
        }
    }
    if(acteur->posy>=400 && acteur->type==7)
        acteur->hp=0;
}

void dessiner_explosion(t_acteur* acteur, BITMAP* buffer)
{
    circlefill(buffer,acteur->posx,acteur->posy,100,makecol(255,255,0));
}
/*****************************************************************************************************************************************************/
void enleverActeur(t_liste_acteur *la,int i)        //retire un acteur du tab
{
    if (la->tab[i]!=NULL)           //si il n'est pas deja a null
    {
        free(la->tab[i]);       //free le tab[i]
        la->tab[i]=NULL;        //le remet a null
        la->nbre--;             //decremente de 1 le nombre d'element de la liste
    }
}
/*****************************************************************************************************************************************************/
// Gérer l'évolution de l'ensemble des acteurs missile
void actualiserListeActeurs_missile(t_liste_acteur *la[D],BITMAP* buffer)
{
    int i,d;

    // actualiser chaque acteur
    // si un acteur n'est plus vivant, l'enlever de la liste
    for(d=0; d<D; d++)
    {
        for (i=0; i<la[d]->taille_max; i++)
        {
            if (la[d]->tab[i]!=NULL)
            {
                la[d]->tab[i]->type=la[d]->type;
                actualiserActeur(la[d]->tab[i],buffer);
                if (la[d]->tab[i]->hp<=0)
                {
                    enleverActeur(la[d],i);
                }
            }
        }
    }

}

/*****************************************************************************************************************************************************/

void actualiserListeActeurs_ennemi(t_liste_acteur *la[N],BITMAP* buffer)
{
    int i,d;
    // actualiser chaque acteur
    // si un acteur n'est plus vivant, l'enlever de la liste
    for(d=0; d<N; d++)
    {
        for (i=0; i<la[d]->taille_max; i++)
        {
            if (la[d]->tab[i]!=NULL)
            {
                la[d]->tab[i]->type=la[d]->type;
                actualiserActeur(la[d]->tab[i],buffer);
                if (la[d]->tab[i]->hp<=0)
                {
                    enleverActeur(la[d],i);
                }
            }
        }
    }

}

void actualiserListeActeurs_bonus(t_liste_acteur *la[T],BITMAP* buffer)
{
    int i,d;
    // actualiser chaque acteur
    // si un acteur n'est plus vivant, l'enlever de la liste
    for(d=0; d<T; d++)
    {
        for (i=0; i<la[d]->taille_max; i++)
        {
            if (la[d]->tab[i]!=NULL)
            {
                la[d]->tab[i]->type=la[d]->type;
                actualiserActeur(la[d]->tab[i],buffer);
                if (la[d]->tab[i]->hp<=0)
                {
                    enleverActeur(la[d],i);
                }
            }
        }
    }
}

void progression_niveau_1(t_liste_acteur *liste_bonus[T],t_liste_acteur *liste_ennemi[N], t_liste_acteur *liste_missile[D],t_interface *jeu,BITMAP* buffer)
{
    BITMAP* attention;
    attention=load_bitmap("image//objet//signal.bmp",NULL);

    if(attention == NULL)
        allegro_message("Probleme image");

    textprintf_ex(buffer,font,50,525,makecol(255,255,255),-1,"Score :%d",jeu->score);
    if(jeu->progression<=12000)
    {
        if(jeu->progression%3000==0)    //bonus  bouclier
            ajouterActeur(liste_bonus[0],rand()%(SCREEN_W-550+1)+550,rand()%500);

        if(jeu->progression%5000==0)   //bonus coeur
            ajouterActeur(liste_bonus[2],rand()%(SCREEN_W-550+1)+550,rand()%500);

        if(jeu->progression==7000)  //bonus nouveau tir
            ajouterActeur(liste_bonus[1],rand()%(SCREEN_W-550+1)+550,rand()%500);

        if(jeu->progression==8500)  //vitesse augmentée
            ajouterActeur(liste_bonus[3],rand()%(SCREEN_W-550+1)+550,rand()%500);

        if(jeu->progression%400==0 && jeu->niveau==1)
            ajouterActeur(liste_ennemi[0],SCREEN_W,rand()%400);

        if(jeu->progression%2000==0 && jeu->niveau==1)
            ajouterActeur(liste_ennemi[1],650,400);

        if(jeu->progression==3500)  //bonus nouveau tir
            ajouterActeur(liste_bonus[1],rand()%(SCREEN_W-550+1)+550,rand()%500);

        if((jeu->progression%1200)>=900)
            ajouterActeur(liste_ennemi[2],rand()%SCREEN_W,-1000);

        if((jeu->progression%1200)>=900 && (jeu->progression%1200)<=1200 && liste_ennemi[2]->tab[0]!=NULL)
            draw_sprite(buffer,attention,liste_ennemi[2]->tab[0]->posx,50);

        if(jeu->progression==12000)
            ajouterActeur(liste_ennemi[5],400,0);
    }
    if(jeu->progression>12000 && liste_ennemi[5]->tab[0]!= NULL)
    {
        textprintf_ex(buffer,font, 600, 555, makecol(255, 255, 255), -1, "MEGAZORUS");
        rectfill(buffer,500,520,790,540,makecol(0,0,0));
        rectfill(buffer,500,520,500+jeu->jauge,540,makecol(255,0,0));

        if(liste_ennemi[5]->tab[0]->hp>=90)
            jeu->jauge++;
        else
            jeu->jauge=liste_ennemi[5]->tab[0]->hp*3;
        if(jeu->jauge > 290)
            jeu->jauge = 290;

        if(liste_ennemi[5]->tab[0]->hp==0)      //recompense boss
            ajouterActeur(liste_bonus[4],600,220);
    }
}
/*****************************************************************************/
void progression_niveau_2(t_liste_acteur *liste_bonus[T],t_liste_acteur *liste_ennemi[N], t_liste_acteur *liste_missile[D],t_interface *jeu,BITMAP* buffer)
{
    textprintf_ex(buffer,font,50,525,makecol(255,255,255),-1,"Score :%d",jeu->score);
    if(jeu->progression<8000)
    {
        if(jeu->progression%292==0)
            ajouterActeur(liste_ennemi[7],SCREEN_W,rand()%400);

        if(jeu->progression%420==0)
            ajouterActeur(liste_ennemi[8],SCREEN_W,rand()%400);

        if(jeu->progression%2000==0)    //bonus  bouclier
            ajouterActeur(liste_bonus[0],rand()%(SCREEN_W-550+1)+550,rand()%500);

        if(jeu->progression%5000==0)   //bonus coeur
            ajouterActeur(liste_bonus[2],rand()%(SCREEN_W-550+1)+550,rand()%500);
    }

    if(jeu->progression==8000)
    {
        ajouterActeur(liste_ennemi[9],350,200);
        jeu->vitessee_defilement=0;
    }

    if(jeu->progression>8000 && liste_ennemi[9]->tab[0]!= NULL)
    {
        textprintf_ex(buffer,font, 600, 555, makecol(255, 255, 255), -1, "VEZORUS");
        rectfill(buffer,500,520,790,540,makecol(0,0,0));
        rectfill(buffer,500,520,500+jeu->jauge,540,makecol(255,0,0));

        if(liste_ennemi[9]->tab[0]->hp>=150)
            jeu->jauge++;
        else
            jeu->jauge=liste_ennemi[9]->tab[0]->hp*2;
        if(jeu->jauge > 290)
            jeu->jauge = 290;

        if(liste_ennemi[9]->tab[0]->hp==0)
        {
            rest(1000);
            jeu->niveau=-1;
        }

    }
}

void progression_niveau_3(t_liste_acteur *liste_bonus[T],t_liste_acteur *liste_ennemi[N], t_liste_acteur *liste_missile[D],t_interface *jeu,BITMAP* buffer)
{
    textprintf_ex(buffer,font,50,525,makecol(255,255,255),-1,"Score :%d",jeu->score);
    if(jeu->progression<8000)
    {
        if(jeu->progression%200==0)
            ajouterActeur(liste_ennemi[11],SCREEN_W,rand()%400);

        if(jeu->progression%340==0)
            ajouterActeur(liste_ennemi[12],SCREEN_W,rand()%400);

        if(jeu->progression==3500)  //bonus nouveau tir
            ajouterActeur(liste_bonus[1],rand()%(SCREEN_W-550+1)+550,rand()%500);

        if(jeu->progression==8000)
        {
            ajouterActeur(liste_ennemi[14],550,0);
            jeu->vitessee_defilement=0;
        }

        if(jeu->progression%2000==0)    //bonus  bouclier
            ajouterActeur(liste_bonus[0],rand()%(SCREEN_W-550+1)+550,rand()%500);

        if(jeu->progression%5000==0)   //bonus coeur
            ajouterActeur(liste_bonus[2],rand()%(SCREEN_W-550+1)+550,rand()%500);
    }

    if(jeu->progression>8000 && liste_ennemi[14]->tab[0]!= NULL)
    {
        textprintf_ex(buffer,font, 600, 555, makecol(255, 255, 255), -1, "BAKORUS");
        rectfill(buffer,500,520,790,540,makecol(0,0,0));
        rectfill(buffer,500,520,500+jeu->jauge,540,makecol(255,0,0));

        if(liste_ennemi[14]->tab[0]->hp>=150)
            jeu->jauge++;
        else
            jeu->jauge=liste_ennemi[14]->tab[0]->hp*2;
        if(jeu->jauge > 290)
            jeu->jauge = 290;

        if(liste_ennemi[14]->tab[0]->hp==0)
        {
            rest(1000);
            jeu->niveau=-1;
        }

    }
}
/************************************************************************************************************************/
void actualiserListe_tir_ennemi(t_liste_acteur *liste_ennemi[N])
{
    int i,k,j;
    for(k=0; k<5; k++)
    {
        if(k==0)
            j=0;
        if(k==1)
            j=7;
        if(k==2)
            j=8;
        if(k==3)
            j=11;
        if(k==4)
            j=12;
        if(liste_ennemi[j]->nbre!=0)
        {
            for(i=0; i<liste_ennemi[j]->taille_max; i++)
            {
                if(liste_ennemi[j]->tab[i]!=NULL && liste_ennemi[j]->tab[i]->etat==0)
                {
                    liste_ennemi[j]->tab[i]->rechargement++;

                    if(liste_ennemi[j]->tab[i]->rechargement>=80)
                    {
                        ajouterActeur(liste_ennemi[3],liste_ennemi[j]->tab[i]->posx-80,liste_ennemi[j]->tab[i]->posy);
                        liste_ennemi[j]->tab[i]->rechargement=0;
                    }
                }
            }
        }
    }


    if(liste_ennemi[1]->nbre!=0)
    {
        for(i=0; i<liste_ennemi[1]->taille_max; i++)
        {
            if(liste_ennemi[1]->tab[i]!=NULL && liste_ennemi[1]->tab[i]->etat==0)
            {
                liste_ennemi[1]->tab[i]->rechargement++;

                if(liste_ennemi[1]->tab[i]->rechargement>=200)
                {
                    ajouterActeur(liste_ennemi[4],liste_ennemi[1]->tab[i]->posx,liste_ennemi[1]->tab[i]->posy);
                    liste_ennemi[1]->tab[i]->rechargement=0;
                    liste_ennemi[1]->tab[i]->cpt_img++;
                    if(liste_ennemi[1]->tab[i]->cpt_img ==3)
                        liste_ennemi[1]->tab[i]->cpt_img=0;
                }
            }
        }
    }

    if(liste_ennemi[5]->nbre!=0)        //tir du boss
    {
        for(i=0; i<liste_ennemi[5]->taille_max; i++)
        {
            if(liste_ennemi[5]->tab[i]!=NULL && liste_ennemi[5]->tab[i]->etat==0)
            {
                liste_ennemi[5]->tab[i]->rechargement++;

                if(liste_ennemi[5]->tab[i]->rechargement>=160)
                {
                    ajouterActeur(liste_ennemi[6],liste_ennemi[5]->tab[i]->posx,liste_ennemi[5]->tab[i]->posy);
                    liste_ennemi[5]->tab[i]->rechargement=0;
                }
            }
        }
    }

    if(liste_ennemi[9]->nbre!=0)        //tir du boss
    {
        for(i=0; i<liste_ennemi[9]->taille_max; i++)
        {
            if(liste_ennemi[9]->tab[i]!=NULL && liste_ennemi[9]->tab[i]->etat==0)
            {
                liste_ennemi[9]->tab[i]->rechargement++;

                if(liste_ennemi[9]->tab[i]->rechargement>=160)
                {
                    ajouterActeur(liste_ennemi[10],liste_ennemi[9]->tab[i]->posx+200,liste_ennemi[9]->tab[i]->posy+40);
                    liste_ennemi[9]->tab[i]->rechargement=0;
                }
            }
        }
    }
     if(liste_ennemi[14]->nbre!=0)        //tir du boss
    {
        for(i=0; i<liste_ennemi[14]->taille_max; i++)
        {
            if(liste_ennemi[14]->tab[i]!=NULL && liste_ennemi[14]->tab[i]->etat==0)
            {
                liste_ennemi[14]->tab[i]->rechargement++;

                if(liste_ennemi[14]->tab[i]->rechargement>=50)
                {
                    ajouterActeur(liste_ennemi[10],liste_ennemi[14]->tab[i]->posx,rand()%SCREEN_H);
                    liste_ennemi[14]->tab[i]->rechargement=0;
                }
            }
        }
    }
}
/*****************************************************************************************************************************************************/
void dessinerListeActeurs_missile(BITMAP *bmp,t_liste_acteur *la[D])
{
    int i,d;
    for(d=0; d<D; d++)
    {
        for (i=0; i<la[d]->taille_max; i++)
        {
            if (la[d]->tab[i]!=NULL)
                dessinerActeur(bmp,la[d]->tab[i],la[d]->img[0]);
        }
    }
}
void dessinerListeActeurs_bonus(BITMAP *bmp,t_liste_acteur *la[D])
{
    int i,d;
    for(d=0; d<T; d++)
    {
        for (i=0; i<la[d]->taille_max; i++)
        {
            if (la[d]->tab[i]!=NULL)
                dessinerActeur(bmp,la[d]->tab[i],la[d]->img[0]);
        }
    }
}

void dessinerVaisseau(BITMAP* buffer, t_acteur *vaisseau)
{
    if(vaisseau->etat%4==0)
        draw_sprite(buffer,vaisseau->img,vaisseau->posx,vaisseau->posy);
}
/*****************************************************************************************************************************************************/
void dessinerListeActeurs_ennemi(BITMAP *bmp,t_liste_acteur *la[N])
{
    int i,d,m;
    for(d=0; d<N; d++)
    {
        for (i=0; i<la[d]->taille_max; i++)
        {
            if (la[d]->tab[i]!=NULL)
            {
                switch(d)
                {
                case 0 :
                    m=la[d]->tab[i]->cpt_img;
                    if((rand()%500)%50==0)
                        la[d]->tab[i]->cpt_img++;
                    if(la[d]->tab[i]->cpt_img > 2)
                        la[d]->tab[i]->cpt_img=0;
                    dessinerActeur(bmp,la[d]->tab[i],la[d]->img[m]);
                    break;
                case 1 :
                    m=la[d]->tab[i]->cpt_img;
                    dessinerActeur(bmp,la[d]->tab[i],la[d]->img[m]);
                    break;
                default :
                    dessinerActeur(bmp,la[d]->tab[i],la[d]->img[0]);
                    break;
                }
            }
        }
    }
}
/*****************************************************************************************************************************************************/
void dessinerActeur(BITMAP *bmp,t_acteur *acteur,BITMAP* img_acteur)
{
    // Si vivant
    if (acteur->etat==0) //deplacement donc on le dessine
    {
        if(acteur->type==8)
            rotate_sprite(bmp,img_acteur,acteur->posx,acteur->posy,ftofix(((M_PI/1000)-7*(rand()%5))*128/M_PI));
        else
            draw_sprite(bmp,img_acteur,acteur->posx,acteur->posy);

    }
}
/*****************************************************************************************************************************************************/
void collisionListeActeurs(t_liste_acteur *le[N],t_liste_acteur *la[D],t_interface* jeu)
{

    int i,j,d,n,res=0;
    for(d=0; d<D; d++)
    {
        for(n=0; n<N; n++)
        {
            for (i=0; i<la[d]->taille_max; i++)
            {
                for(j=0; j<le[n]->taille_max; j++)
                {
                    if (la[d]->tab[i]!=NULL && le[n]->tab[j]!= NULL)
                    {
                        res=collisionActeurs(le[n]->tab[j],la[d]->tab[i]);
                        if(res==1 && n!=3 && n!=2 && n!=4 && n!=6)
                        {
                            la[d]->tab[i]->hp--;
                            le[n]->tab[j]->hp--;
                            jeu->score++;
                        }
                    }
                }
            }
        }
    }
}

void collision_vaisseau(t_acteur *vaisseau,t_liste_acteur *listeennemi[N], t_liste_acteur *liste_bonus[T], t_interface *jeu)
{
    int i,res=0,n;

    for(n=0; n<N; n++)
    {
        for(i=0; i<listeennemi[n]->taille_max; i++)
        {
            if(listeennemi[n]->tab[i]!=NULL)
            {
                res=collisionActeurs(listeennemi[n]->tab[i],vaisseau);
                if(res==1 && vaisseau->etat==0)
                {
                    vaisseau->hp--;
                    vaisseau->etat=150;
                    listeennemi[n]->tab[i]->hp--;
                }
            }
        }
    }
    for(n=0; n<T; n++)
    {
        for(i=0; i<liste_bonus[n]->taille_max; i++)
        {
            if(liste_bonus[n]->tab[i]!=NULL)
            {
                res=collisionActeurs(liste_bonus[n]->tab[i],vaisseau);
                if(res==1 )
                {
                    if(liste_bonus[n]->type==11)
                    {
                        vaisseau->etat=250;
                        liste_bonus[n]->tab[i]->hp=0;
                    }
                    if(liste_bonus[n]->type==12)
                    {
                        vaisseau->type=2;
                        liste_bonus[n]->tab[i]->hp=0;
                    }
                    if(liste_bonus[n]->type==14)
                    {
                        vaisseau->hp++;
                        liste_bonus[n]->tab[i]->hp=0;
                    }
                    if(liste_bonus[n]->type==15)
                    {
                        jeu->vitessee_defilement=10;
                        if(jeu->progression>=11000)
                            jeu->vitessee_defilement=4;
                        liste_bonus[n]->tab[i]->hp=0;
                    }
                    if(liste_bonus[n]->type==16)
                    {
                        vaisseau->img=liste_bonus[n]->img[0];
                        liste_bonus[n]->tab[i]->hp=0;
                        rest(100);
                        jeu->niveau=-1;
                    }
                }
            }
        }
    }
}

//Fonction de déplacement d'un joueur, recoit en paramètre la structure d'un joueur
void actualiserVaisseau(t_acteur *acteur, t_liste_acteur *la[D])
{
    acteur->rechargement++;
    if(acteur->etat>0)
        acteur->etat--;

    if(key[KEY_S] && acteur->posy<480-acteur->ty)
        acteur->posy += acteur->vity;

    if(key[KEY_W] && acteur->posy>10)
        acteur->posy -= acteur->vity;

    if(key[KEY_A] && acteur->posx>0)
        acteur->posx -= acteur->vitx;

    if(key[KEY_D] && acteur->posx<SCREEN_W-acteur->tx)
        acteur->posx += acteur->vitx;

    if (mouse_b&1 && acteur->rechargement>=35)
    {
        ajouterActeur(la[0],acteur->posx+acteur->tx/2,acteur->posy-5+acteur->ty/2);
        acteur->rechargement=0;
    }
    if (key[KEY_SPACE] && acteur->rechargement>=50)
    {
        ajouterActeur(la[1],acteur->posx+acteur->tx/2,acteur->posy-5+acteur->ty/2);
        acteur->rechargement=0;
        if(acteur->posx-50>=0)
            acteur->posx-=50;
    }
    if(key[KEY_Q] && acteur->rechargement>=80 && acteur->type==2)
    {
        ajouterActeur(la[2],acteur->posx+acteur->tx/2,acteur->posy-5+acteur->ty/2);
        acteur->rechargement=0;
    }
}


//fonction de test de collision
int collisionActeurs(t_acteur *a1, t_acteur *a2)
{
    int retour=0;
    // Si collision mettre valeur retour à 1
    if (    a1->posx <= a2->posx + a2->tx  &&  a2->posx <= a1->posx + a1->tx  &&
            a1->posy <= a2->posy + a2->ty  &&  a2->posy <= a1->posy + a1->ty    )
        retour=1;

    return retour;
}

void resetActeur(t_liste_acteur* liste_ennemi[N],t_liste_acteur* liste_missile[D],t_liste_acteur* liste_bonus[T], t_interface *jeu, t_acteur* vaisseau)
{
    vaisseau->hp=3;
    vaisseau->etat=0;
    vaisseau->rechargement=0;
    jeu->niveau=0;
    jeu->jauge=0;
    jeu->score=0;
    jeu->progression=4;
    jeu->vitessee_defilement=4;
    eliminer_toute_liste_missile(liste_missile);
    eliminer_toute_liste_ennemi(liste_ennemi);
    eliminer_toute_liste_bonus(liste_bonus);
}

void eliminer_toute_liste_missile(t_liste_acteur *la[D])
{
    int d,i;

    for(d=0; d<D; d++)
    {
        for(i=0; i<la[d]->taille_max; i++)
        {
            if(la[d]->tab[i]!=NULL)
            {
                la[d]->tab[i]->hp=0;
                la[d]->tab[i]->etat=15;
            }
        }
    }
}

void eliminer_toute_liste_ennemi(t_liste_acteur *la[N])
{
    int d,i;

    for(d=0; d<N; d++)
    {
        for(i=0; i<la[d]->taille_max; i++)
        {
            if(la[d]->tab[i]!=NULL)
            {
                la[d]->tab[i]->hp=0;
                la[d]->tab[i]->etat=15;
            }
        }
    }
}
void eliminer_toute_liste_bonus(t_liste_acteur *la[T])
{
    int d,i;

    for(d=0; d<T; d++)
    {
        for(i=0; i<la[d]->taille_max; i++)
        {
            if(la[d]->tab[i]!=NULL)
            {
                la[d]->tab[i]->hp=0;
                la[d]->tab[i]->etat=15;
            }
        }
    }
}

void initialisation_allegro()
{
    allegro_init();
    install_keyboard();
    install_mouse();
    install_sound(DIGI_AUTODETECT, MIDI_AUTODETECT, NULL);
    set_color_depth(desktop_color_depth());
    if(set_gfx_mode(GFX_AUTODETECT_WINDOWED,800,600,0,0)!= 0)
    {
        allegro_message("prb gfx mode");
        allegro_exit();
        exit(EXIT_FAILURE);
    }
}
/********************************************************************************************************************************************************/
/*MAIN*/
/********************************************************************************************************************************************************/
int main()
{
    initialisation_allegro();
    /************************************************************************************************************************/
    BITMAP * buffer, *fond, *fond_2, *fond_3, *fond_menu, *img_hp;
    SAMPLE * sample_1,* sample_2, *sample_3;
    int xdecl =1;//decalage du slide
    int i;
    t_interface jeu;
    t_acteur vaisseau;
    t_liste_acteur *liste_missile[D];
    t_liste_acteur *liste_ennemi[N];
    t_liste_acteur *liste_bonus[T];
    /****************************************************************************************************************************/
    buffer=create_bitmap(SCREEN_W,SCREEN_H);
    fond_menu=load_bitmap("image//fond//fond_menu.bmp",NULL);
    fond=load_bitmap("image//fond//back_1.bmp",NULL);
    fond_3=load_bitmap("image//fond//back_2.bmp",NULL);
    fond_2=load_bitmap("image//fond//back_3.bmp",NULL);
    img_hp=load_bitmap("image//objet//coeur.bmp",NULL);
    sample_1=load_sample("image//objet//son_1.wav");
    sample_2=load_sample("image//objet//son_2.wav");
    sample_3=load_sample("image//objet//son_3.wav");

    creer_vaisseau(&vaisseau);
    creerListes(liste_ennemi, liste_missile, liste_bonus,&jeu);

    if (!fond || !vaisseau.img ||!fond_menu ||! img_hp ||! liste_ennemi[0]->img[1] ||! liste_missile[2]->img[0] ||! liste_bonus[4]->img[0])
    {
        allegro_message("pas pu trouver images");
        exit(EXIT_FAILURE);
    }

    show_mouse(screen);
    /*******************************************************************************************************/
    while (!key[KEY_ESC])
    {
        clear(buffer);

        if(jeu.niveau==0)
        {
            blit(fond_menu,buffer,0,0,0,0,fond->w,fond->h);
            stop_sample(sample_1);
            stop_sample(sample_2);
            stop_sample(sample_3);


            if (mouse_x<240 && mouse_x>40 && mouse_y<450 && mouse_y>220)
            {
                textprintf_ex(buffer,font, 280, 400, makecol(255, 255, 255), -1, "Niveau 1, cliquer pour jouer");
                if(mouse_b & 1)
                {
                    jeu.niveau=1;
                    play_sample(sample_1,255,0,1000,1);
                }
            }
            if (mouse_x<480 && mouse_x>300 && mouse_y<280 && mouse_y>60)
            {
                textprintf_ex(buffer,font, 280, 400, makecol(255, 255, 255), -1, "Niveau 2, cliquer pour jouer");
                if(mouse_b & 1)
                {
                    jeu.niveau=2;
                    play_sample(sample_2,255,0,1000,1);
                }
            }
            if (mouse_x<730 && mouse_x>500 && mouse_y<450 && mouse_y>220)
            {
                textprintf_ex(buffer,font, 280, 400, makecol(255, 255, 255), -1, "Niveau 3, cliquer pour jouer");
                if(mouse_b & 1)
                {
                    jeu.niveau=3;
                    play_sample(sample_3,255,0,1000,1);
                }
            }
            if (mouse_b & 1 && mouse_x<500 && mouse_x>320 && mouse_y<460 && mouse_y>420)    //quitter le jeu depuis le menu
                return 0;
        }
        /************************************************************************************************************************************************/
        if(jeu.niveau== 1 || jeu.niveau==2 || jeu.niveau==3)
        {
            if(jeu.niveau==1)
            {
                blit(fond,buffer,0,0,-xdecl,0,fond->w,fond->h);
                blit(fond,buffer,0,0,-xdecl+fond->w,0,fond->w,fond->h);
                progression_niveau_1(liste_bonus,liste_ennemi,liste_missile,&jeu,buffer);
            }
            if(jeu.niveau==2)
            {
                blit(fond_2,buffer,0,0,-xdecl,0,fond_2->w,fond_2->h);
                blit(fond_2,buffer,0,0,-xdecl+fond_2->w,0,fond_2->w,fond_2->h);
                progression_niveau_2(liste_bonus,liste_ennemi,liste_missile,&jeu,buffer);
            }
            if(jeu.niveau==3)
            {
                blit(fond_3,buffer,0,0,-xdecl,0,fond_2->w,fond_2->h);
                blit(fond_3,buffer,0,0,-xdecl+fond_2->w,0,fond_2->w,fond_2->h);
                progression_niveau_3(liste_bonus,liste_ennemi,liste_missile,&jeu,buffer);
            }
            /*********************************/
            xdecl+=jeu.vitessee_defilement;
            if(xdecl>=800)
                xdecl=1;
            if(jeu.progression >12000)
            {
                jeu.vitessee_defilement=0;
                jeu.progression=12000;
            }
            for(i=0; i<vaisseau.hp; i++)
            {
                masked_blit(img_hp,buffer,0,0,200+(60*i),540,img_hp->w,img_hp->h);
            }
            jeu.progression+=4;
            /*************************/

            actualiserListeActeurs_missile(liste_missile,buffer);      //mettre a jour la liste des tirs
            actualiserListeActeurs_ennemi(liste_ennemi,buffer);
            actualiserListe_tir_ennemi(liste_ennemi);
            actualiserVaisseau(&vaisseau,liste_missile);
            actualiserListeActeurs_bonus(liste_bonus,buffer);
            collision_vaisseau(&vaisseau,liste_ennemi,liste_bonus,&jeu);
            collisionListeActeurs(liste_ennemi,liste_missile,&jeu);

            dessinerListeActeurs_missile(buffer,liste_missile);
            dessinerListeActeurs_ennemi(buffer,liste_ennemi);
            dessinerListeActeurs_bonus(buffer,liste_bonus);
            dessinerVaisseau(buffer,&vaisseau);

            /******************************/
            if(key[KEY_P] || vaisseau.hp<=0 ||jeu.progression==-1 || jeu.niveau==-1)
            {
                resetActeur(liste_ennemi, liste_missile, liste_bonus,&jeu,&vaisseau);
                clear(buffer);
                rest(50);
            }

        }
        blit(buffer,screen,0,0,0,0,SCREEN_W,SCREEN_H);
        rest(12);
    }

    eliminer_toute_liste_bonus(liste_bonus);
    eliminer_toute_liste_missile(liste_missile);
    eliminer_toute_liste_ennemi(liste_ennemi);
    return 0;
}
END_OF_MAIN();
