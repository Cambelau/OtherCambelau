#ifndef ARRETE_H_INCLUDED
#define ARRETE_H_INCLUDED
#include "ponderation.h"
#include "sommet.h"

class Arrete
{
private:
    /// int indice? Comme pour les sommets, l'indice correspondrait à sa place dans le vecteur d'arretes du graphe

    Sommet* m_depart;
    Sommet* m_arrivee;

public:
    Arrete(Sommet* depart, Sommet* arrivee);
    ~Arrete();
    Sommet* getDepart();
    Sommet* getArrivee();

};
#endif // ARRETE_H_INCLUDED
