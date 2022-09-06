#include "arrete.h"

Arrete::Arrete(Sommet* depart, Sommet* arrivee)
{
    m_depart = depart;
    m_arrivee = arrivee;
}

Arrete::~Arrete()
{

}

Sommet* Arrete::getDepart()
{
    return m_depart;
}

Sommet* Arrete::getArrivee()
{
    return m_arrivee;
}
