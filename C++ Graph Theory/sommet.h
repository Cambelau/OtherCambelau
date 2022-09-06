#ifndef SOMMET_H_INCLUDED
#define SOMMET_H_INCLUDED

#include <iostream>
#include <vector>
#include <queue>
#include "coord.h"
#include "svgfile.h"
#include <sstream>

class Comparaison;

class Sommet
{
private:
    double m_indice;    //indice de vecteur propre
    double m_somInd;    //somme des indices de vecteur propre des sommets adjacetns
    int m_ncpp = 0;     //nombre de plus cours chemin
    std::string m_nom;  //nom du sommet
    std::vector <Sommet*> m_adjacents;  //sommets adjacents au sommet
    std::vector <float> m_poids;    //position dans le vecteur de ponderation des arretes adjacentes du sommet
    std::vector <float> m_tdistances;//distance par rapport a tout les autres sommets
    std::vector <float> m_tncpps;   //nombre de plus court chemin entre chaque sommet
    Coord m_coordonnees;
    float m_deg = 0;    //degré du sommet
    float m_deg_norm = 0;   //degré normalisé
    float m_indice_proximite = 0;   //indice de proximité
    float m_inter = 0;  //indice d'intermedialité
    bool m_visite;      //pour le calcul d'intermedialité et proximité
    bool m_vu;          //pour le calcul d'intermedialité et proximité
    float m_distance = 0;//pour le calcul d'intermedialité et proximité
    int m_couleur = 0;  //pour calcul de connexité
public:
    Sommet(std::string nom, double x, double y);
    Sommet();
    ~Sommet();
    double get_x()const;
    double get_y()const;
    void ajouterAdj(Sommet* adjacent);
    void ajouterPoids(float pond);
    void afficherSommet();
    void afficherAdjacents(std::vector<float> ponderations);
    void incremDeg();
    std::string getNom();
    float getDeg();
    double getSomInd();
    void dessiner(Svgfile &svgout)const;
    void dessinerData(Svgfile &svgout,int x,int y)const;
    void setIndice(double val);
    void somIndVoisin();
    void recalculInd(int lambda);
    void afficherCentVecPropre();
    void visiter();
    void explorer_adj(std::priority_queue<Sommet*, std::vector<Sommet*>, Comparaison> &file, std::vector<float> ponderations);
    void explorer_adj_inter(std::priority_queue<Sommet*, std::vector<Sommet*>, Comparaison> &file, std::vector<float> ponderations);
    float getDistance();
    void reinit();
    void saveSommet();
    void setDegNorm(float val);
    void setIndiceProx(float val);

    int getNcpp();
    void setNcpp(int val);
    void remplirNpccs(int val);
    void remplirDist(float val);
    float getTdistance(int id);
    float getTncpp(int id);
    void setInter(float val);
    void clearDist();

    void parcours_DFS(int couleur);
    int  getCouleur();
    void setCouleur(int i);

    double getVec();
    float getProxi();
    float getInter();


};


class Comparaison /// création de la classe Comparaison pour pouvoir comparer deux sommets en fct de leur distance pour le tri de la priority queue
/// https://stackoverflow.com/questions/16111337/declaring-a-priority-queue-in-c-with-a-custom-comparator
{
public:
    bool operator() (Sommet* som1, Sommet* som2)
    {

        return som1->getDistance() > som2->getDistance();
    }
};


#endif // SOMMET_H_INCLUDED
