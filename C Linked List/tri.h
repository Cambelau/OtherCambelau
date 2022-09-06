//On utilise le tri fusion qui  est stable pour trier la collection

void tri_fusion(t_maillon** ancre,  int choix);
//separe en deux la liste plusieurs fois par recursivité et refusionne chaque partie
//recoit en paramètre la liste et le choix de tri

void tri_separation(t_maillon* liste,t_maillon** avant,  t_maillon** arriere);
//separe en deux partie la liste
//recoit en paramètre la liste, les adresses de la partie avant et arrière
//retourne par adresse la partie avant et la partie arriere

t_maillon* tri_age(t_maillon* a,t_maillon* b);
//a et b sont les deux parties de la liste et sont fusionnées en comparant a chaque fois lequel est plus grand que l'autre
//recoit en paramètre la partie a et b
//fonction recursive qui retourne a les nouvelles parties a et/ou b

t_maillon* tri_nom(t_maillon* a,t_maillon* b);
//idem que precedent

t_maillon* inversion(t_maillon* liste);
//recoit en paramètre la liste et retourne la nouvelle liste inversé
//utilisation du double chainage pour l'inversion de la liste on relie tout les maillons dans l'odre recu par le pointeur precedent
//puis on parcours la liste dans le sens inverse grace au pointeur precedent et on relie les maillons avec le pointeur suivant
