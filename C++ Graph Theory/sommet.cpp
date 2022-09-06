#include "sommet.h"

Sommet::Sommet(std::string nom, double x, double y)
{
    m_nom = nom;
    m_coordonnees.set_x(x);
    m_coordonnees.set_y(y);
}

Sommet::Sommet()
{

}

Sommet::~Sommet()
{

}

void Sommet::ajouterAdj(Sommet* adjacent)
{
    m_adjacents.push_back(adjacent);
}

void Sommet::ajouterPoids(float pond)
{
    m_poids.push_back(pond);
}

void Sommet::afficherSommet()
{
    std::cout<<m_nom;
}

void Sommet::afficherAdjacents(std::vector<float> ponderations)
{
    for(size_t i=0; i<m_adjacents.size();++i)
    {
        m_adjacents[i]->afficherSommet();
        std::cout<<"("<<ponderations[m_poids[i]]<<") ";
    }
}

void Sommet::incremDeg()
{
    ++m_deg;
}

std::string Sommet::getNom()
{
    return m_nom;
}


float Sommet::getDeg()
{
    return m_deg;
}

double Sommet::getSomInd()
{
    return m_somInd;
}


double Sommet::get_x()const
{
    return m_coordonnees.get_x();
}
double Sommet::get_y()const
{
    return m_coordonnees.get_y();
}

void Sommet::dessinerData(Svgfile &svgout,int x,int y)const
{
    svgout.addText(x+10,y,m_nom);
    svgout.addText(x+60,y,m_deg);
    svgout.addText(x+100,y,m_deg_norm);
    svgout.addText(x+200,y,m_indice);
    svgout.addText(x+300,y,m_indice_proximite);
    svgout.addText(x+400,y,m_inter);
}

void Sommet::dessiner(Svgfile &svgout)const
{
    int deg=0;

    for(const auto& i : m_adjacents)//compte le degré du sommet
    {
        deg++;
    }

    svgout.addText(m_coordonnees.get_x()*100-5,m_coordonnees.get_y()*100-20,m_nom);

    switch(deg)//en fonction de son degré change la couleur de dessin
    {
        case 1 :    svgout.addDisk(m_coordonnees.get_x()*100,m_coordonnees.get_y()*100,7,"cyan");
        break;
        case 2 :    svgout.addDisk(m_coordonnees.get_x()*100,m_coordonnees.get_y()*100,7,"green");
        break;
        case 3 :    svgout.addDisk(m_coordonnees.get_x()*100,m_coordonnees.get_y()*100,7,"blue");
        break;
        case 4 :    svgout.addDisk(m_coordonnees.get_x()*100,m_coordonnees.get_y()*100,7,"red");
        break;
        default :   svgout.addDisk(m_coordonnees.get_x()*100,m_coordonnees.get_y()*100,7);
        break;
    }
}

void Sommet::setIndice(double val)
{
    m_indice = val;
}

void Sommet::somIndVoisin()
{
    double temp =0;
    for(size_t i=0; i<m_adjacents.size(); ++i)
    {
        temp += m_adjacents[i]->m_indice;
    }
    m_somInd = temp;
}

void Sommet::recalculInd(int lambda)
{
    m_indice = m_somInd/lambda;
}

void Sommet::afficherCentVecPropre()
{
    std::cout<< "Sommet "<< m_nom <<" : "<< m_indice;
}

void Sommet::visiter()
{
    m_visite = true;
}

void Sommet::explorer_adj(std::priority_queue<Sommet*, std::vector<Sommet*>, Comparaison> &file, std::vector<float> ponderations)
{

    for(unsigned int i=0; i<m_adjacents.size(); ++i) /// pour tous les sommets présent dans le vecteur des sommets adjacents
    {

        if(m_adjacents[i]->m_visite!=true) /// si le sommet n'est pas déjà visité
        {
            m_adjacents[i]->m_distance = m_distance+ponderations[m_poids[i]]; /// incrémentation de la distance
            file.push(m_adjacents[i]); /// ajout du nouveau sommet dans la file de priorité
        }

        if(m_adjacents[i]->m_visite == true) /// si le sommet est déjà visité
        {
            if(m_adjacents[i]->m_distance>m_distance+ponderations[m_poids[i]]) /// même traitement si la nouvelle distance est plus courte que la précédente, sinon rien
            {
                m_adjacents[i]->m_distance = m_distance+ponderations[m_poids[i]];
                file.push(m_adjacents[i]);

            }
        }
    }
}

void Sommet::explorer_adj_inter(std::priority_queue<Sommet*, std::vector<Sommet*>, Comparaison> &file, std::vector<float> ponderations)
{
    for(unsigned int i=0; i<m_adjacents.size(); ++i) /// pour tous les sommets présent dans le vecteur des sommets adjacents
    {

        if(m_adjacents[i]->m_visite!=true) /// si le sommet n'est pas déjà visité
        {
            m_adjacents[i]->m_distance = m_distance+ponderations[m_poids[i]]; /// incrémentation de la distance
            m_adjacents[i]->m_ncpp+=this->m_ncpp;

            if(m_adjacents[i]->m_vu!=true)
            {
                file.push(m_adjacents[i]); /// ajout du nouveau sommet dans la file de priorité
                m_adjacents[i]->m_vu = true;
            }


        }

        if(m_adjacents[i]->m_visite == true) /// si le sommet est déjà visité
        {
            if(m_adjacents[i]->m_distance>m_distance+ponderations[m_poids[i]]) /// même traitement si la nouvelle distance est plus courte que la précédente, sinon rien
            {
                m_adjacents[i]->m_distance = m_distance+ponderations[m_poids[i]];
                m_adjacents[i]->m_ncpp=0;
                m_adjacents[i]->m_ncpp+=this->m_ncpp;
                file.push(m_adjacents[i]);

            }

            if(m_adjacents[i]->m_distance == m_distance+ponderations[m_poids[i]]) /// même traitement si la nouvelle distance est plus courte que la précédente, sinon rien
            {
                m_adjacents[i]->m_ncpp+=this->m_ncpp;
            }


        }


    }

    }


float Sommet::getDistance()
{
    return m_distance;
}

void Sommet::reinit()
{
    m_distance = 0;
    m_visite = false;
    m_vu = false;
    m_ncpp = 0;
}

void Sommet::saveSommet()
{
    std::string const nomFichier("dataSommet.txt");
    std::ofstream monFlux(nomFichier.c_str(), std::ios::app);

    if(monFlux)
    {
        monFlux <<  m_nom << "\t" << m_deg << "\t" << m_deg_norm << "\t" << m_indice << "\t\t  " << m_indice_proximite  << "\t\t  " << m_inter << std::endl;

    }
    else
    {
        std::cout << "ERREUR: Impossible d'ouvrir le fichier" << std::endl;
    }
}

void Sommet::setDegNorm(float val)
{
    m_deg_norm = val;
}

void Sommet::setIndiceProx(float val)
{
    m_indice_proximite = val;
}


int Sommet::getNcpp()
{
    return m_ncpp;
}

void Sommet::setNcpp(int val)
{
    m_ncpp = val;
}

void Sommet::remplirNpccs(int val)
{
    m_tncpps.push_back(val);
}

void Sommet::remplirDist(float val)
{
    m_tdistances.push_back(val);
}

float Sommet::getTdistance(int id)
{
    return m_tdistances[id];
}

float Sommet::getTncpp(int id)
{
    return m_tncpps[id];
}

void Sommet::setInter(float val)
{
    m_inter = val;
}

void Sommet::parcours_DFS(int couleur) //parcours recursif de tout les sommets liés
{
    m_couleur=couleur;

    for(unsigned int i=0; i<m_adjacents.size();++i) //colorie de la meme couleur du sommet i tout ses sommets adjacents
    {
        if(m_adjacents[i]->m_couleur==0)
        {
            m_adjacents[i]->parcours_DFS(couleur);
        }
    }
}

void Sommet::setCouleur(int i)
{
    m_couleur=i;
}
int Sommet::getCouleur()
{
    return m_couleur;
}

void Sommet::clearDist()
{
    m_tdistances.clear();
    m_tncpps.clear();

}

double Sommet::getVec()
{
    return m_indice;
}

float Sommet::getProxi()
{
    return m_indice_proximite;
}

float Sommet::getInter()
{
    return m_inter;
}
