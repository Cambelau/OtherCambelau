#include "graphe.h"
#include <iostream>
#include <istream>
#include <fstream>
#include <math.h>
#include <queue>


Graphe::Graphe(std::string &nomFichier)
{
    /// constructeur qui créé un graphe à partir d'un fichier
    /// creation des variables qui stockeront les valeurs lues dans le fichier de chargemet avant de les restituer aux objets concernés
    std::string nom;
    int test=0;
    int test2=0;

    double x,y;
    int id;

    do /// boucle do while pour s'assurer de la bonne ouverture du fichier, et proposer d'en ouvrir un autre ou de quitter le programme s'il y a un problème
    {
        std::ifstream ifs{nomFichier};

        if(ifs)
        {
            ifs >> m_orientation;
            ifs >> m_ordre;/// lecture des caractéristiques du graphe, oriente ou non, nombre de sommets

            for (int i=0; i<m_ordre; ++i)
            {
                ifs>>id>>nom>>x>>y;/// remplissage du vecteur contenant tout les sommets
                m_sommets.push_back( new Sommet{nom,x,y} );
            }

            ifs >> m_taille;/// recuperation de la taille du graphe

            int num1,num2;
            for (int i=0; i<m_taille; ++i)
            {
                ifs>>id>>num1>>num2; /// création des arrêtes en associant aux sommets leurs sommets adjacents
                m_sommets[num1]->ajouterAdj(m_sommets[num2]);
                m_sommets[num1]->ajouterPoids(i); ///rentre l'indice de la pondération correspondant à la liaison avec le sommet dans le vecteur de toutes les pond.
                m_sommets[num1]->incremDeg();

                if (m_orientation == 0) /// si le graphe n'est pas orienté on accroche réciproquement les sommets
                {
                    m_sommets[num2]->ajouterAdj(m_sommets[num1]);
                    m_sommets[num2]->ajouterPoids(i);
                    m_sommets[num2]->incremDeg();
                }

                m_arretes.push_back( new Arrete{m_sommets[num1],m_sommets[num2]}); /// on stocke les arrêtes dans un vecteur, pour pouvoir les supprimer plus tard
                m_ponderations.push_back(1);/// initialisation des pondérations à 1, si jamais l'utilisateur ne saisit pas de fichier de ponderation, ou s'il n'y a pas de ponderation
                test = 1;
            }
        }
        else
        {
            std::cout<<"ERREUR : Probleme lors de l'ouverture du fichier"<<std::endl;
            std::cout<<"Voulez-vous essayer avec un autre fichier?\n1 : Oui\n2 : Non\n";
            std::cin>>test2;
            if(test2==1)
            {
                std::cout<<"Resaisissez un nom de fichier valide :"; /// saisit d'un nouveau nom de fichier en cas d'echec
                std::cin>>nomFichier;
            }
            else
            {
                throw "Quitter le programme"; /// fin du programme en détruisant tout les objets, le throw ne rencontrera pas de catch
            }

        }
    }
    while(test == 0);
}

Graphe::Graphe(std::string nomFichier,std::vector<int> m_id_supp)
{
/// constructeur très similaire à celui au dessus, prend en parametre en plus une liste d'arrete à effacer
    std::string nom;
    int test=0;
    int test2=0;

    double x,y;
    int id,var;
    int decalage = 0;

    std::ifstream ifs{nomFichier};

    if(ifs)
    {
        ifs >> m_orientation;
        ifs >> m_ordre;

        for (int i=0; i<m_ordre; ++i)
        {
            ifs>>id>>nom>>x>>y;/// remplissage du vecteur contenant tout les sommets
            m_sommets.push_back( new Sommet{nom,x,y} );
        }

        ifs >> m_taille;

        int num1,num2;
        for (int i=0; i<m_taille; ++i)
        {
            var=0;
            for(size_t j=0; j<m_id_supp.size(); ++j) /// test pour vérifier si l'arrete à créer appartient à la liste des arrêtes supprimées
            {
                if(i==m_id_supp[j])
                   {
                      var++;
                      decalage++;///incrémentation d'un décalage à chaque suppression d'arrete pour garder les indices des arretes et des ponderations alignés
                                /// ces derniers sont stockés dans des vecteurs différents, mais l'indice d'une arrete et de sa ponderation son les mêmes, il faut donc ce décalage pour s'en assurer
                   }
            }
            if(var==0)
            {
                ifs>>id>>num1>>num2; /// création des arrêtes en associant aux sommets leurs sommets adjacents
                m_sommets[num1]->ajouterAdj(m_sommets[num2]);
                m_sommets[num1]->ajouterPoids(i-decalage); ///rentre l'indice de la pondération correspondant à la liaison avec le sommet dans le vecteur de toutes les pond.
                m_sommets[num1]->incremDeg();               /// permet de changer la ponderation sans changer ce qu'il y a dans les sommets, en changeant la ponderation dans le vecteur de ponderation, l'indice reste le meme

                if (m_orientation == 0)
                {
                    m_sommets[num2]->ajouterAdj(m_sommets[num1]);
                    m_sommets[num2]->ajouterPoids(i-decalage);
                    m_sommets[num2]->incremDeg();
                }
                m_arretes.push_back( new Arrete{m_sommets[num1],m_sommets[num2]});
                m_ponderations.push_back(1);
            }
            else{ifs>>id>>num1>>num2;}
        }
            m_taille=m_arretes.size();
    }
}

Graphe::~Graphe()
{

}

void Graphe::afficher()
{
    /// simple affichage de la liste des sommets contenus dans le vecteur de sommets
    std::cout << "ordre = " << m_ordre << std::endl;
    std::cout << "taille = " << m_taille << std::endl;

    std::cout << "liste d'adjacence: " << std::endl;
    for(int i=0; i< m_ordre; ++i)
    {
        std::cout << "Sommet " << m_sommets[i]->getNom() << " : ";
        m_sommets[i]->afficherAdjacents(m_ponderations); /// affiche les sommets adjacents, et le poids de l'arrête entre parenthèses
        std::cout << std::endl;

    }
    std::cout << std::endl;
}


void Graphe::chargementPonderation(std::string &nomFichier)
{
    /// recois un nom de fichier contenant la ponderation de chaque arrete et remplis le vecteur avec ces ponderations
    m_ponderations.clear();/// reinitialisation du vecteur en cas de changement de ponderation

    std::ifstream ifsPoids{nomFichier};
    int id;
    float poids;

    if(ifsPoids)
    {
        for (int i=0; i<m_taille; ++i)
        {
            ifsPoids>>id>>poids;/// lecture dans le fichier
            m_ponderations.push_back(poids);
        }
        std::cout<<"Le systeme de ponderations a bien ete modifie.\n";
    }
    else
    {
        std::cout<<"ERREUR: Le fichier n'a pas pu etre ouvert ou n'esxiste pas\nLe systeme de ponderation n'a pas ete charge, vous pourrez reessayer depuis le menu\n"<<std::endl;
    }
}

void Graphe::copieChargementPonderation(std::string &nomFichier,std::vector<int> m_id_supp)
{
    /// comme pour le chargement du graphe, le chargement de la ponderation varie lorsque des arretes sont supprimées
    m_ponderations.clear();

    std::ifstream ifsPoids{nomFichier};
    int id;
    float poids;

    if(ifsPoids)
    {

        for (int i=0; i<m_taille+m_id_supp.size(); ++i)
        {
            ifsPoids>>id>>poids;
            if(id!=m_id_supp[i])/// verification que l'arrete qui s'apprete à etre push back, n'appartient pas à la liste des arretes supprimées
                m_ponderations.push_back(poids);
        }
        std::cout<<"Le systeme de ponderations a bien ete modifie.\n";
    }
    else
    {
        std::cout<<"ERREUR: Le fichier n'a pas pu etre ouvert ou n'esxiste pas\nLe systeme de ponderation n'a pas ete charge, vous pourrez reessayer depuis le menu\n"<<std::endl;
    }


}

void Graphe::centraliteDegre()
{
    std::cout<<"Centralites de degre non normalises :"<<std::endl;
    for (int i=0; i<m_ordre; ++i)
    {
        std::cout<<"Sommet "<<m_sommets[i]->getNom()<<" : "<<m_sommets[i]->getDeg()<<std::endl; /// recuperation et affichage des degrés de tout les sommets

    }
    std::cout<<std::endl;

    std::cout<<"Centralites de degre normalises :"<<std::endl;
    for (int i=0; i<m_ordre; ++i)
    {
        m_sommets[i]->setDegNorm((m_sommets[i]->getDeg())/(m_ordre-1));
        std::cout<<"Sommet "<<m_sommets[i]->getNom()<<" : "<<(m_sommets[i]->getDeg())/(m_ordre-1)<<std::endl;
        /// normalisation de tout les degrés en divisant par le nombre total de sommets-1
    }
}

void Graphe::dessiner()const
{
    Svgfile svgout; /// creation dun fichier svgout

    svgout.addRect(0,0,1000,0,0,800,1000,800,"#EFF2FB",10,"black"); /// affichage des indices de centralités calculés en bas à droite de l'ecran
    svgout.addGrid();

    svgout.addText(670,498,"Valeurs des indices :");
    svgout.addText(500,530,"Nom / Degre / Degre normalise / I. vect propre / I. Prox / I. centralite inter");
    svgout.addLine(500,500,1000,500);
    svgout.addLine(500,500,500,500+40*m_ordre);
    svgout.addLine(500,500+40*m_ordre,1000,500+40*m_ordre);

    for(int i=0; i< m_ordre; ++i)
    {
        m_sommets[i]->dessiner(svgout); /// affichage des sommets sous forme de points
        m_sommets[i]->dessinerData(svgout,500,550+30*i);
    }

    for(int i=0; i< m_taille; ++i)
    {
        svgout.addLine(m_arretes[i]->getDepart()->get_x()*100, /// affichage des arrêtes entre les sommets
                       m_arretes[i]->getDepart()->get_y()*100,
                       m_arretes[i]->getArrivee()->get_x()*100,
                       m_arretes[i]->getArrivee()->get_y()*100);

        std::ostringstream oss;
        oss<<i<<":"<<m_ponderations[i];
        std::string m =oss.str(); /// affichage des ponderations sur les arretes

        svgout.addText((m_arretes[i]->getDepart()->get_x()*100+m_arretes[i]->getArrivee()->get_x()*100)/2,
                       (m_arretes[i]->getDepart()->get_y()*100+m_arretes[i]->getArrivee()->get_y()*100)/2,m);
    }
}

void Graphe::centraliteVecteur()
{
    int lambda = 1;
    int lambdaPrec = 1;
    double somme = 0;


    for(size_t i=0; i<m_ordre; ++i)
    {
        m_sommets[i]->setIndice(1); /// reinitialisation des indices de vecteur propre à 1
    }

    do
    {
        lambdaPrec = lambda;
        for(size_t i=0; i<m_ordre; ++i)
        {
            m_sommets[i]->somIndVoisin(); /// utilisation d'une variable pour sommer les indices des sommets adjacents, sans modifier l'indice du sommet
        }                                 /// pour éviter de fausser les calculs en additionant des indices ayant la mauvaise valeur

        for(size_t i=0; i<m_ordre; ++i)
        {
            somme += (m_sommets[i]->getSomInd()*m_sommets[i]->getSomInd());/// calcul de lambda
        }
        lambda = sqrt(somme);

        for(size_t i=0; i<m_ordre; ++i)
        {
            m_sommets[i]->recalculInd(lambda); ///calcul du nouvel indice à partir de lambda
        }


    }
    while((lambda/lambdaPrec)!=1); /// tant que lambda varie trop


    std::cout<<std::endl;

    std::cout<<"Centralites de vecteur propre :"<<std::endl;
    std::cout<<"\nLambda : "<<lambda<<std::endl;

    for(size_t i=0; i<m_ordre; ++i)
    {
        m_sommets[i]->afficherCentVecPropre(); /// affichage des indices de vecteur propre
        std::cout<<std::endl;
    }

}

void Graphe::centraliteProximite()
{
    float cent;

    for(size_t i=0; i<m_sommets.size(); ++i)
    {
        m_sommets[i]->reinit(); /// initialisation des variables permettant de vérifier si un sommet a été visité, de connaitre la distance entre deux sommets, à 0
    }

    std::priority_queue<Sommet*, std::vector<Sommet*>, Comparaison> file;

    for(size_t i=0; i<m_sommets.size(); ++i)
    {
        cent = 0;
        m_sommets[i]->visiter();
        file.push(m_sommets[i]);

        while(!file.empty()) /// tant qu'il reste un sommet dans la file, on prend le premier sommet de la file (fct .top() ), celui qui se trouve le moins loin du point de départ
        {
            file.top()->explorer_adj(file,m_ponderations);
            file.top()->visiter();
            file.pop(); /// permet de sortir le sommet qui vient d'être exploré

        }


        for(size_t j=0; j<m_sommets.size(); ++j)
        {
            cent+=m_sommets[j]->getDistance();
            //std::cout<<"Sommet "<<m_sommets[j]->getNom()<<" : "<<m_sommets[j]->getDistance()<<std::endl;
            m_sommets[j]->reinit();
        }
        if(cent!=0)
        {
            cent = (m_ordre - 1)/cent;
        }
        std::cout<<"Centralites de proximite :"<<std::endl;

        m_sommets[i]->setIndiceProx(cent);
        std::cout<<"Sommet "<<m_sommets[i]->getNom()<<" :"<<cent<<std::endl;

    }
    /// affichage du parcours le plus rapide en remontant la liste des prédecesseurs associés par l'algorithme de dijkstra

}

void Graphe::centraliteIntermediarite()
{
    float npccjki =0;
    float jk,ji,ik;



    std::priority_queue<Sommet*, std::vector<Sommet*>, Comparaison> file;
    for(size_t i=0; i<m_sommets.size(); ++i)
    {
        m_sommets[i]->reinit();
        m_sommets[i]->clearDist();
    }


    for(size_t i=0; i<m_sommets.size(); ++i)
    {

        m_sommets[i]->setNcpp(1);
        //std::cout<<"dijkstra sur "<<m_sommets[i]->getNom()<<std::endl;
        m_sommets[i]->visiter();
        file.push(m_sommets[i]);

        while(!file.empty()) /// tant qu'il reste un sommet dans la file, on prend le premier sommet de la file (fct .top() ), celui qui se trouve le moins loin du point de départ
        {
            file.top()->explorer_adj_inter(file,m_ponderations);
            file.top()->visiter();
            file.pop(); /// permet de sortir le sommet qui vient d'être exploré

        }

        m_sommets[i]->setNcpp(0);

        for(size_t j=0; j<m_sommets.size(); ++j)
        {
            m_sommets[i]->remplirNpccs(m_sommets[j]->getNcpp());
            m_sommets[i]->remplirDist(m_sommets[j]->getDistance());

            m_sommets[j]->reinit();
        }


    }
    std::cout<<"Centralites d'intemediarite :"<<std::endl;
    for(size_t i=0; i<m_sommets.size(); ++i)
    {

        npccjki = 0;


        for(size_t j=0; j<m_sommets.size(); ++j)
        {



            for(size_t k=j+1; k<m_sommets.size(); ++k)
            {
                if(j!=i && k!=i)
                {
                    if(m_sommets[j]->getTdistance(k) == (m_sommets[j]->getTdistance(i)+m_sommets[i]->getTdistance(k)))
                    {
                        ji = m_sommets[j]->getTncpp(i);
                        ik = m_sommets[i]->getTncpp(k);
                        jk = m_sommets[j]->getTncpp(k);
                        npccjki += ji*ik/jk;

                    }
                }
            }
        }
        npccjki = 2*npccjki;
        npccjki = npccjki/(m_ordre*m_ordre - 3*m_ordre +2);
        m_sommets[i]->setInter(npccjki);
        std::cout<<"Sommet "<<m_sommets[i]->getNom()<<" :"<<npccjki<<std::endl;
    }

}

void Graphe::ecritureData()
{
    std::string const nomFichier("dataSommet.txt");
    std::ofstream monFlux(nomFichier.c_str());

    if(monFlux)
    {
        monFlux << "//FICHIER DE SAVE DES SOMMETS\\" << std::endl;
        monFlux << "Nom\t\t/Degré/Degré normalisé /Indice vect propre /Indice proximite /Indice centralite d'intermediarite" << std::endl;

    }
    else
    {
        std::cout << "ERREUR: Impossible d'ouvrir le fichier" << std::endl;
    }

    for(size_t i=0; i<m_ordre; ++i)
        m_sommets[i]->saveSommet();
}

void Graphe::testConnexite()
{
    int connexe = 0;
    int nombre_composante = 1;

    for(size_t i=0; i<m_ordre; ++i) //colorie tout les sommets a partir d'un parcours DFS pour que chaque composante connexe est une couleur
    {
        if(m_sommets[i]->getCouleur()==0)
           {
                m_sommets[i]->parcours_DFS(nombre_composante);
                nombre_composante++;
           }
    }

    for(size_t i=0; i<m_ordre; ++i) //compte le nombre de sommets de couleur 1
    {
        if(m_sommets[i]->getCouleur()==1)
            connexe++;
    }

    if(connexe==m_ordre)        //si tout les sommets sont colorés 1 alors le graphe est connexe
        std::cout<<"Le Graphe est connexe"   << std::endl;
    else        //sinon il n'est pas connexe et le nombre de composante est affiché
    {
        std::cout<<"Le Graphe n'est pas connexe. Il y a "  << nombre_composante-1 << "composantes connexes." << std::endl;
        for(size_t i=0; i<nombre_composante+1; ++i) //affichage des composantes connexes
        {
            for(size_t j=0; j<m_ordre; ++j)
            {
                if(m_sommets[j]->getCouleur()==i-1)
                std::cout << m_sommets[j]->getNom() << "/";
            }
            std::cout<< std::endl;
            std::cout<< std::endl;
        }
    }

    for(size_t i=0; i<m_ordre; ++i) //reset de toute les couleurs
    {
        m_sommets[i]->setCouleur(0);
    }

}


void Graphe::comparaison(Graphe original)
{
    std::cout<<"Difference entre les indices avant/apres suppression des arretes"<<std::endl;
    std::cout<<"Difference degre :\n";

    for(size_t i=0; i<m_sommets.size(); ++i)
    {
        std::cout<<original.m_sommets[i]->getNom()<<" : "<<original.m_sommets[i]->getDeg()-m_sommets[i]->getDeg()<<std::endl;
    }

    std::cout<<"\nDifference degre normalise:\n";

    for(size_t i=0; i<m_sommets.size(); ++i)
    {
        std::cout<<original.m_sommets[i]->getNom()<<" : "<<(original.m_sommets[i]->getDeg()/(original.m_ordre-1))-(m_sommets[i]->getDeg()/(m_ordre-1))<<std::endl;
    }
        std::cout<<"\nDifference vecteur propre :\n";

    for(size_t i=0; i<m_sommets.size(); ++i)
    {
        std::cout<<original.m_sommets[i]->getNom()<<" : "<<original.m_sommets[i]->getVec()-m_sommets[i]->getVec()<<std::endl;
    }
        std::cout<<"\nDifference proximite :\n";

    for(size_t i=0; i<m_sommets.size(); ++i)
    {
        std::cout<<original.m_sommets[i]->getNom()<<" : "<<original.m_sommets[i]->getProxi()-m_sommets[i]->getProxi()<<std::endl;
    }
        std::cout<<"\nDifference intermediarite :\n";

    for(size_t i=0; i<m_sommets.size(); ++i)
    {
        std::cout<<original.m_sommets[i]->getNom()<<" : "<<original.m_sommets[i]->getInter()-m_sommets[i]->getInter()<<std::endl;
    }
}
