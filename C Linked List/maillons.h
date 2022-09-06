// fonctions qui affichent chaque data de la structure
void affiche_nom(t_maillon *ptmaillon);
void affiche_signe(t_maillon *ptmaillon);
void affiche_date(t_maillon *ptmaillon);
void affiche_age(t_maillon *ptmaillon);
//recoit en param�tre un maillon

void affiche_liste(t_maillon *liste);
//affiche la liste dans son int�gralit�
//recoit la liste en param�tre

t_maillon* ajoutTete(t_maillon* liste,t_maillon* N);
//Ajoute un maillon en tete de liste
//recoit en param�tre la liste et le nouveau maillon
//retourne la nouvelle liste

t_maillon *ajoutFin(t_maillon* liste,t_maillon* nouv);
//Ajoute un maillon en fin de liste
//recoit en param�tre la liste et le nouveau maillon
//retourne la nouvelle liste

void affiche_recherche(t_maillon *liste);
//Recherche selon un crit�re
//recoit en param�tre la liste
//demande d'abord le crit�re a rechercher puis le mot ou age recherch�
//en fonction du crit�re on parcours la liste dans son int�gralit� et affiche des que le mot est trouv�

t_maillon* supp_fiche(t_maillon* liste,char nom_sup[100]);
//algorithme r�cursif de suppression d'un maillon en fonction d'un nom saisie au prealable
//recoit en param�tre la lisste et le nom a supprimer

