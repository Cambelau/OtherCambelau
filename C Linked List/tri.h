//On utilise le tri fusion qui  est stable pour trier la collection

void tri_fusion(t_maillon** ancre,  int choix);
//separe en deux la liste plusieurs fois par recursivit� et refusionne chaque partie
//recoit en param�tre la liste et le choix de tri

void tri_separation(t_maillon* liste,t_maillon** avant,  t_maillon** arriere);
//separe en deux partie la liste
//recoit en param�tre la liste, les adresses de la partie avant et arri�re
//retourne par adresse la partie avant et la partie arriere

t_maillon* tri_age(t_maillon* a,t_maillon* b);
//a et b sont les deux parties de la liste et sont fusionn�es en comparant a chaque fois lequel est plus grand que l'autre
//recoit en param�tre la partie a et b
//fonction recursive qui retourne a les nouvelles parties a et/ou b

t_maillon* tri_nom(t_maillon* a,t_maillon* b);
//idem que precedent

t_maillon* inversion(t_maillon* liste);
//recoit en param�tre la liste et retourne la nouvelle liste invers�
//utilisation du double chainage pour l'inversion de la liste on relie tout les maillons dans l'odre recu par le pointeur precedent
//puis on parcours la liste dans le sens inverse grace au pointeur precedent et on relie les maillons avec le pointeur suivant
