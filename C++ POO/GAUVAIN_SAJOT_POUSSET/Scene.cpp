#include "Scene.h"
#include <sstream>

Scene::Scene()
    :m_vec_bloc{},m_storage{},m_grid{false},m_id{false}
{}

void Scene::showGrid()
{
    m_grid = true;
}
void Scene::hideGrid()
{
    m_grid = false;
}
void Scene::showId()
{
    m_id = true;
}
void Scene::hideId()
{
    m_id = false;
}


void Scene::store()
{
    std::vector <Bloc*> childs;

    for (auto j: m_storage)
    {
        delete(j);
    }
    m_storage.clear();

    m_storage = m_vec_bloc; //copie la situation dans le stockage mais pas avec les bonnes adresses

    //parcours du vec sauvegardé
    for(size_t i = 0; i<m_storage.size(); ++i)
    {
        childs = m_vec_bloc[i]->getChilds();

        m_storage[i]->clearChilds();

        //parcours des enfants dans la situation pour raccorder la sauvegarde
        for (size_t k=0; k<childs.size(); ++k)
        {
            for(size_t l = 0; l<m_vec_bloc.size(); ++l)
            {
                if(childs[k]==m_vec_bloc[l])
                {
                    m_storage[i]->addChild(m_storage[l]);
                    break;
                }
            }
        }
        //ALLER AUSSI CHERCHER LES PARENTS T^T
        for (size_t k=0; k<m_vec_bloc.size(); ++k)
        {
            if(m_vec_bloc[k]==m_vec_bloc[i]->getParent())
            {
                m_storage[i]->simpleSetParent(m_storage[k]);
                break;
            }
        }
    }
}

void Scene::restore()
{
    std::vector <Bloc*> childs;
    for (auto i: m_vec_bloc)
    {
        delete(i);
    }
    m_vec_bloc.clear();
    m_racine.clear();

    m_vec_bloc = m_storage;

    for(size_t i = 0; i<m_vec_bloc.size(); ++i)  //parcours du vec sauvegardé
    {
        childs = m_storage[i]->getChilds();
        m_vec_bloc[i]->clearChilds();

        for (size_t k=0; k<childs.size(); ++k)  //parcours des enfants dans la situation pour raccorder la sauvegarde
        {
            for(size_t l = 0; l<m_storage.size(); ++l)
            {
                if(childs[k]==m_storage[l])
                {
                    m_vec_bloc[i]->addChild(m_vec_bloc[l]);
                    break;
                }
            }
        }
        m_vec_bloc[i]->simpleSetParent(NULL);
        for (size_t k=0; k<m_storage.size(); ++k)
        {
            if(m_storage[k]==m_storage[i]->getParent())
            {
                m_vec_bloc[i]->simpleSetParent(m_vec_bloc[k]);
                break;
            }
        }
        if(m_vec_bloc[i]->getParent()==NULL)
        {
            m_racine.push_back(m_vec_bloc[i]);
        }
    }
}

bool Scene::storage()
{
    if(m_storage.size()==0)
    {
        return false;
    }
    return true;
}

void Scene::dessiner()const
{
    Svgfile svgout;

    svgout.addRect(0,0,1000,0,0,800,1000,800,"#CEF6F5",30,"black");

    for(const auto& c : m_vec_bloc)
    {
        c->dessiner(svgout);
        if(m_id)
        {
            c->showId(svgout);
            c->showTr(svgout);
        }
    }
    if(m_grid)
        svgout.addGrid();
}

void Scene::afficher()const
{
    for(const auto& c : m_vec_bloc)
        c->afficher();
}

Bloc* Scene::chercher(std::string nom)
{

    bool skip=true;

    do
    {
        for(auto c : m_vec_bloc)
        {
            if(c->getID().compare(nom) == 0)
            {
                std::cout << "Le bloc " << nom << " a ete trouve" <<std::endl;
                return c;
            }
        }
        if(skip)
        {
            std::cout << "Le bloc " << nom << " n'a pas ete trouve" <<std::endl ;
            std::cout << "Voulez vous rechercher un nouveau bloc ou quitter ? 0 Quitter 1 Continuer\t";
            std::cin >> skip;
            if(!skip)

                return 0;
        }
    }
    while(skip);

    return nullptr;
}

Bloc* Scene::searchWithPath(std::string path)
{
    Bloc *current_bloc = NULL;
    //Bloc *precedent_bloc = NULL;

    std::string current_name;


    //on remplace les points par des espaces
    for (size_t i= 0; i<path.size(); ++i)
    {
        if(path[i]=='.')
        {
            path[i]=' ';
        }
    }

    //parcours de l'arbre via les générations
    std::istringstream ispath (path);
    ispath>>current_name;

    current_bloc = chercher(current_name);
    while (ispath>>current_name && current_bloc!= NULL)
    {
        current_bloc = current_bloc->chercherEnfant(current_name);
    }

    return current_bloc;
}


void Scene::translaterBloc (Bloc* c, double x, double y)
{
    if(c!=0)
    {
        if(c->testTranslation(x,y))
        {
            c->translater(x,y);
            c->translaterChild(x,y);
        }
    }
}

void Scene::rotationBloc(Bloc *c, double ang)
{
    if(c!=0)
    {
        c->rotationParent(ang);
        c->rotationEnfant(ang);
    }
}

void Scene::lecture_fichier(std::string nomFichier)
{
    std::ifstream ifs{nomFichier};
    if(!ifs)
    {
        std::cerr << "Erreur ouverture fichier: impossible d'ouvrir " + nomFichier <<std::endl;
        return;
    }
    std::string ligne;


    while(std::getline(ifs,ligne))
    {
        std::istringstream iss{ligne};
        std::string id, id_parent, color,refpos,endpos;
        double x, y,w,h;
        iss >> id;
        iss >> x;
        iss >> y;
        iss >> color;
        iss >> w;
        iss >> h;
        iss >> id_parent;
        iss >> refpos;
        iss >> endpos;

        if(id_parent=="na") //une racine (un bloc "sans parent")
        {
            Bloc*nouv=new Bloc(id,x,y,w,h,color,id_parent);
            m_racine.push_back(nouv);
            m_vec_bloc.push_back(nouv);
        }
        else
        {
            Bloc*nouv=new Bloc(id,id_parent,x,y,w,h,color,refpos,endpos);
            m_vec_bloc.push_back(nouv);
        }
    }

    for(size_t i=0; i<m_vec_bloc.size(); ++i)       //i pour l'enfant j pour le parent
    {
        for(size_t j=0; j<m_vec_bloc.size(); ++j)
        {
            if(m_vec_bloc[i]->get_Parent()==m_vec_bloc[j]->getID()) //id meme que le parent
            {
                m_vec_bloc[j]->addChild(m_vec_bloc[i]);
                m_vec_bloc[i]->setParent(m_vec_bloc[j]);
                //std::cout << m_vec_bloc[i]->getID() << " devient enfant de " << m_vec_bloc[j]->getID() << std::endl;
            }
        }
    }
}

void Scene::vider_vec()
{
    m_vec_bloc.clear();
    m_racine.clear();
}

void Scene::collision()
{
    for(size_t i=0; i<m_vec_bloc.size(); ++i)
    {
        for(size_t j=0; j<m_vec_bloc.size(); ++j)
        {
            if(m_vec_bloc[i]->testCollision(m_vec_bloc[i],m_vec_bloc[j]) && m_vec_bloc[i]!=m_vec_bloc[j])
            std::cout<<"Collision entre "<<m_vec_bloc[i]->getID()<<" et "<<m_vec_bloc[j]->getID()<<std::endl;
        }
    }
}
