#ifndef GRAPHE_H_INCLUDED
#define GRAPHE_H_INCLUDED
#include <vector>
#include "sommet.h"
#include "arrete.h"
#include "svgfile.h"

class Graphe
{
private:
    float m_ordre;
    int m_taille;
    int m_orientation;

    std::vector<Sommet*> m_sommets;
    std::vector<Arrete*> m_arretes;
    std::vector<float> m_ponderations;

public:
    Graphe(std::string &nomFichier);
    Graphe(std::string nomFichier,std::vector<int> m_id_supp);
    ~Graphe();
    void afficher();
    void chargementPonderation(std::string &nomFichier);
    void copieChargementPonderation(std::string &nomFichier,std::vector<int> m_id_supp);
    void centraliteDegre();
    void centraliteVecteur();
    void dessiner()const;
    void centraliteProximite();
    void centraliteIntermediarite();
    void ecritureData();
    void testConnexite();
    void comparaison(Graphe original);

};


#endif // GRAPHE_H_INCLUDED
