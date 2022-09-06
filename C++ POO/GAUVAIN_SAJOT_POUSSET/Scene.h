#ifndef SCENE_H_INCLUDED
#define SCENE_H_INCLUDED

#include "bloc.h"

class Scene
{
private:
    std::vector<Bloc*> m_vec_bloc;  //vecteur contenant tout les blocs de la scene
    std::vector<Bloc*> m_storage;

    std::vector<Bloc*> m_racine;

    bool m_grid,m_id;       //booléen pour afficher la grille et les ids des blocs
public :
    Scene();

    void showGrid();
    void hideGrid();
    void showId();
    void hideId();

    void store();
    void restore();
    bool storage();

    void creerBloc();
    void dessiner()const;
    void afficher()const;
    void translaterBloc(Bloc* c, double x, double y);
    void rotationBloc(Bloc* c, double ang);
    void vider_vec();

    Bloc* searchWithPath(std::string path);
    Bloc* chercher(std::string nom);

    void lecture_fichier(std::string nomFichier);
    void collision();

};
#endif // SCENE_H_INCLUDED
