#ifndef BLOC_H_INCLUDED
#define BLOC_H_INCLUDED

#include"src/svg/svgfile.h"
#include "src/geometrie/coords.h"
#include "src/graphisme/couleur.h"
#include "src/geometrie/transformation.h"
#include <iostream>
#include <vector>

class Bloc
{
private :
    std::string m_id;       //nom du bloc
    Bloc* m_parent;         //Adresse sur le parent du bloc
    std::string m_id_parent;//nom du parent de ce bloc
    std::vector<Bloc*> m_child;//vecteur de pointeur sur tout les enfants de ce bloc

    Coords m_pos_abs,m_pos_rel; //la position absolu est le centre de ce bloc par rapport à l'entiereté du svgfile, la relative par rapport au bloc parent
    Coords m_tl,m_tr,m_br,m_bl;//les coordonnées des 4 coins du rectangle du bloc
    Coords m_ref_pos,m_end_pos,m_base_pos;//ref et end indiquent la ligne de translation possible du bloc, base la position sur cette ligne

    std::string m_color,m_ref,m_end;    //couleur et nom des points de reference sur le parent

public :
    Bloc();
    Bloc(std::string id,double h,double w,double x,double y,std::string color, std::string id_parent);//construcetur pour un parent (x/y pour les coords absolus)
    Bloc(std::string id, std::string id_parent, double x, double y, double w,double h,std::string color,std::string r,std::string e); //constructeur enfant (x/y pour les coords relatives)

    //Getteur
    std::string get_Parent();
    Bloc* getParent();
    std::vector <Bloc*> getChilds();
    std::string getID();
    double getX_abs();
    double getY_abs();
    double getX_tr();
    double getY_tr();
    double getX_tl();
    double getY_tl();
    double getX_br();
    double getY_br();
    double getX_bl();
    double getY_bl();

    //Setteur
    void setParent(Bloc* parent);
    void simpleSetParent(Bloc* parent);
    void addChild(Bloc* child);
    void clearChilds();
    void setChild_abs();

    //Méthodes
    void afficher()const;
    void saisir();
    void dessiner(Svgfile &svgout)const;
    void showId(Svgfile &svgout);
    void showTr(Svgfile &svgout);
    void translater(double x,double y);
    void translaterChild(double x,double y);
    bool testTranslation(double x,double y);
    void rotation(double ang);
    void rotationParent(double ang);
    void rotationEnfant(double ang);
    Bloc* chercherEnfant(std::string nom);
    bool testCollision(Bloc* a,Bloc*b);
};


#endif // BLOC_H_INCLUDED
