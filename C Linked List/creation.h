typedef struct date
{
    int jour;
    int mois;
    int annee;
}t_date;

typedef struct  maillon
{
        char *nom;
        char signe[20];
        int age;
        t_date date;
        struct maillon *suivant;
        struct maillon *precedent;
}t_maillon;

t_maillon* creer(t_maillon* liste);
//Algorithme de creation d'un maillon
//recoit en paramètre la liste pour verifier le doublon de nom
//retourne le nouveau maillon
//Algorithme pour déterminer l'age exact en fontion de la date du jour
// https://codes-sources.commentcamarche.net/source/17654-calcul-d-age

void signeastro(t_maillon **pt);
//algorithme pour determiner le signe astrologique en fonction de la date de naissance
//recoit en paramètre le nouveau maillon
//ecrit directement le signe astrologique dans le maillon grace au passage par adresse

int anniversaire(t_date a,t_date n);
//fonction qui retourne l'age en fonction de la date de naissance et la date du jour
//recoit la date du  jour et la date de naissance
//retourne l'age

int verification_nom(t_maillon* liste,char mot[100]);
//fontion booleenne qui verifie que le nom n'est pas deja attribué
//recoit en paramètre la liste et le nouveau nom
//parcours la liste et verifie si le nom existe deja
//retourne 0 ou 1
