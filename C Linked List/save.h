void saveliste(t_maillon *liste);
//sauvegarde en fichier de la liste
//recoit en paramètre la liste
//parcours la liste et ecrit dans le fichier pour chaque maillon

t_maillon* chargerliste();
//chargement de la liste qui utilise un double chainage
//retourne la liste
//Lis le fichier et ecrit en memoire vive la liste a partir du chainage vers le precedent*
//Parcours la liste a l'envers grace au chainage precedent et relie la chaine avec le pointeur suivant dans le bon ordre*
//le double chainage permet de charger la liste dans le meme ordre que celui qui a été sauvegarder
