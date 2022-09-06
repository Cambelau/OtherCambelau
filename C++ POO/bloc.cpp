#include "bloc.h"

Bloc::Bloc()
    :m_id{},m_parent{},m_id_parent{},m_child{},m_pos_abs{},m_pos_rel{},m_tl{},m_tr{},m_br{},m_bl{},m_color{}
{}

//les constructeurs recoivent une hauteur et une largeur et en déduisent les positions des 4 angles du rectangle par rapport a son centre
Bloc::Bloc(std::string id,double x,double y,double h,double w,std::string color, std::string id_parent)
    :m_id{id},m_parent{},m_id_parent{id_parent},m_child{},m_pos_abs{x,y},m_pos_rel{},m_tl{-h/2,-w/2},m_tr{-h/2,w/2},m_br{h/2,-w/2},m_bl{h/2,w/2},m_color{color}
{}

Bloc::Bloc(std::string id, std::string id_parent, double x, double y, double h,double w,std::string color,std::string r,std::string e)
    :m_id{id},m_id_parent{id_parent},m_pos_rel{x,y},m_tl{-h/2,-w/2},m_tr{-h/2,w/2},m_br{h/2,-w/2},m_bl{h/2,w/2},m_color{color},m_ref{r},m_end{e}
{}

//affichage des attributs d'un bloc (non utilisé dans le main actuel)
void Bloc::afficher()const
{
    std::cout << m_id ;
    std::cout << "\t";
    m_tl.afficher();
    std::cout << "\t";
    m_tr.afficher();
    std::cout << "\t";
    m_br.afficher();
    std::cout << "\t";
    m_bl.afficher();
    std::cout << "\t";
    m_pos_abs.afficher();
    std::cout << "\t";
    m_pos_rel.afficher();
    std::cout << "\t";
    m_ref_pos.afficher();
    std::cout << "\t";
    m_end_pos.afficher();
    std::cout << "\t";
    m_base_pos.afficher();
    std::cout << "\t";
    std ::cout << m_color;
    std::cout << "\t";
    if(m_parent!=nullptr)
        std::cout << "  " << m_id_parent << "  ";
    std::cout <<  std::endl;
}

Bloc* Bloc::getParent()
{
    return m_parent;
}

std::string Bloc::get_Parent()
{
    return m_id_parent;
}

std::vector<Bloc*> Bloc::getChilds()
{
    return m_child;
}

std::string Bloc::getID()
{
    return m_id;
}

double Bloc::getX_abs()
{
    return m_pos_abs.getX();
}
double Bloc::getY_abs()
{
    return m_pos_abs.getY();
}

double Bloc::getX_tl()
{
    return m_tl.getX();
}

double Bloc::getY_tl()
{
    return m_tl.getY();
}

double Bloc::getX_tr()
{
    return m_tr.getX();
}

double Bloc::getY_tr()
{
    return m_tr.getY();
}

double Bloc::getX_br()
{
    return m_br.getX();
}

double Bloc::getY_br()
{
    return m_br.getY();
}
double Bloc::getX_bl()
{
    return m_bl.getX();
}

double Bloc::getY_bl()
{
    return m_bl.getY();
}

//A la création d'un bloc fils, on utilise setParent pour lui donner tout ses attributs qui découlent du parent
void Bloc::setParent(Bloc* parent)
{
    m_parent=parent;        //adresse de son parent
    m_id_parent=parent->getID();    //nom de son parent
    setChild_abs();     //position absolu du fils (la somme de la position absolu du parent et de la position relative du fils)

    //pour chaque different cas on donne la position des position de references pour la translation
    if(m_ref=="tr")
    {
        m_ref_pos.setX(parent->getX_tr()+parent->getX_abs());
        m_ref_pos.setY(parent->getY_tr()+parent->getY_abs());
    }
    if(m_ref=="tl")
    {
        m_ref_pos.setX(parent->getX_tl()+parent->getX_abs());
        m_ref_pos.setY(parent->getY_tl()+parent->getY_abs());
    }
    if(m_ref=="bl")
    {
        m_ref_pos.setX(parent->getX_bl()+parent->getX_abs());
        m_ref_pos.setY(parent->getY_bl()+parent->getY_abs());
    }
    if(m_ref=="br")
    {
        m_ref_pos.setX(parent->getX_br()+parent->getX_abs());
        m_ref_pos.setY(parent->getY_br()+parent->getY_abs());
    }

    if(m_end=="tr")
    {
        m_end_pos.setX(parent->getX_tr()+parent->getX_abs());
        m_end_pos.setY(parent->getY_tr()+parent->getY_abs());
    }
    if(m_end=="tl")
    {
        m_end_pos.setX(parent->getX_tl()+parent->getX_abs());
        m_end_pos.setY(parent->getY_tl()+parent->getY_abs());
    }
    if(m_end=="bl")
    {
        m_end_pos.setX(parent->getX_bl()+parent->getX_abs());
        m_end_pos.setY(parent->getY_bl()+parent->getY_abs());
    }
    if(m_end=="br")
    {
        m_end_pos.setX(parent->getX_br()+parent->getX_abs());
        m_end_pos.setY(parent->getY_br()+parent->getY_abs());
    }

    m_base_pos.setX((m_ref_pos.getX()+m_end_pos.getX())/2);
    m_base_pos.setY((m_ref_pos.getY()+m_end_pos.getY())/2);
}

Bloc* Bloc::chercherEnfant(std::string nom)
{
    for (auto i:m_child)
    {
        if(i->getID()==nom)
        {
            return i;
        }
    }
    return NULL;
}

void Bloc::simpleSetParent(Bloc* parent)
{
    m_parent = parent;  //simplement donner l'adresse du parent un son fils
}

//ajouter un fils dans le vecteur d'adresse du parent
void Bloc::addChild(Bloc* child)
{
    m_child.push_back(child);
}

//supprimer les fils dans le vecteur d'adresse du parent
void Bloc::clearChilds()
{
    m_child.clear();
}

void Bloc::dessiner(Svgfile &svgout)const
{
    svgout.addRect(m_pos_abs.getX()+m_tl.getX(),
                   m_pos_abs.getY()+m_tl.getY(),
                   m_pos_abs.getX()+m_tr.getX(),
                   m_pos_abs.getY()+m_tr.getY(),
                   m_pos_abs.getX()+m_br.getX(),
                   m_pos_abs.getY()+m_br.getY(),
                   m_pos_abs.getX()+m_bl.getX(),
                   m_pos_abs.getY()+m_bl.getY(),
                   m_color,3,"black");
}

void Bloc::showId(Svgfile &svgout)
{
    svgout.addText(m_pos_abs.getX(), m_pos_abs.getY(), m_id,"white");
}

void Bloc::showTr(Svgfile &svgout)
{
    svgout.addLine(m_ref_pos.getX(),m_ref_pos.getY(),m_end_pos.getX(),m_end_pos.getY(),"red");
    svgout.addTriangle(m_end_pos.getX(),m_end_pos.getY(),m_end_pos.getX()-3,m_end_pos.getY()-3,m_end_pos.getX()-3,m_end_pos.getY()+3,"red");
}

//translatio à l'aide du code fourni par M.Fercoq et des fichiers matrice et transformation
void Bloc::translater(double x,double y)
{
    Coords vecteur{x, y};
    Transformation transfo{ Translation{vecteur} };
    transfo.appliquer(m_pos_abs);
}

//méthode récursive pour la translation des enfants des enfants
void Bloc::translaterChild(double x,double y)
{
    for(auto c : m_child)
    {
        c->translater(x,y);
        c->translaterChild(x, y);
    }
}

//vérification de la possibilité de la translation
bool Bloc::testTranslation(double x,double y)
{
    //premier if pour verifier que lon sortira pas de la zone de dessin
    if(m_pos_abs.getX()+m_br.getX()+x>1000 || m_pos_abs.getX()+m_tl.getX()+x<0 || m_pos_abs.getY()+m_tl.getY()+y<0 || m_pos_abs.getY()+m_bl.getY()+y>800)
    {
        std::cout<<"Erreur move"<<std::endl;
        return false ;
    }
    else
    {
        //second if pour les enfants uniquement, on vérifie que la translation est en accord avec l'axe de translation du bloc
        if(m_id_parent!="na")
        {
            if(m_base_pos.getX()+x>m_end_pos.getX() || m_base_pos.getX()+x<m_ref_pos.getX() || m_base_pos.getY()+y>m_end_pos.getY() || m_base_pos.getY()+y<m_ref_pos.getY())
            {
                std::cout<<"Erreur move"<<std::endl;
                return false;
            }
            else
            {
                m_base_pos.setX(m_base_pos.getX()+x);
                m_base_pos.setY(m_base_pos.getY()+y);
                return true;
            }
        }
        else            //cas d'un bloc parent initial
            return true;
    }
}

//rotation également à partir du code de M.Fercoq
void Bloc::rotation(double ang)
{
    Coords centre{0,0};
    Transformation transfo{ Rotation{centre, ang } };
    transfo.appliquer(m_tl);
    transfo.appliquer(m_tr);
    transfo.appliquer(m_br);
    transfo.appliquer(m_bl);
    transfo.appliquer(m_pos_rel);       //on applqie également sur le vecteur relatif d'un bloc fils
}

void Bloc::rotationParent(double ang)
{
    Coords centre{0,0};
    Transformation transfo{ Rotation{centre, ang } };
    transfo.appliquer(m_tl);
    transfo.appliquer(m_tr);
    transfo.appliquer(m_br);
    transfo.appliquer(m_bl);
}

void Bloc::rotationEnfant(double ang)
{
    for(auto c : m_child)
    {
        c->rotation(ang);
        c->setChild_abs();
        c->rotationEnfant(ang);
    }
}

void Bloc::setChild_abs()
{
    m_pos_abs.setX(m_parent->getX_abs() + m_pos_rel.getX());
    m_pos_abs.setY(m_parent->getY_abs() + m_pos_rel.getY());

}

bool Bloc::testCollision(Bloc* a,Bloc*b)
{
    for(auto c : a->getChilds())
    {
        if(c==b)
            return false;
    }
    for(auto c : b->getChilds())
    {
        if(c==a)
            return false;
    }

    if(a->getX_abs()+a->getX_tl() < b->getX_abs()+b->getX_br() &&
       b->getX_abs()+b->getX_tl() < a->getX_abs()+a->getX_br() &&
       a->getY_abs()+a->getY_tl() < b->getY_abs()+b->getY_tr() &&
       b->getY_abs()+b->getY_tl() < a->getY_abs()+a->getY_tr()
        )
        return true;
    else return false;

}
