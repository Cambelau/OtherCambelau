#include <iostream>
#include "graphe.h"


int main()
{

    std::string nomGraphe;
    std::string nomPond =" ";
    int choix = 0;
    int test;
    std::vector<int> m_id_supp;

    std::cout<< "Bienvenue dans ce calculateur d'indices de centralite. \n";
    std::cout<< "Choisissez le graphe que vous souhaitez charger en saisissant le nom du fichier, par exemple 'graphe.txt'.\n";
    std::cin>>nomGraphe;
    Graphe graphe(nomGraphe);
    std::cout<<"Parfait, votre fichier "<<nomGraphe<<" a ete charge, pour le moment, votre graphe n'est pas pondere.\nIl sera toujours possible de charger ou de changer la ponderation plus tard.\n";
    std::cout<<"Soughaitez-vous charger un fichier de ponderations?\n1 : Oui\n2 : Plus tard\n";
    std::cin>>choix;
    if(choix == 1)
    {
        std::cout<< "Choisissez la ponderation que vous souhaitez charger en saisissant le nom du fichier, par exemple 'ponderation.txt'.\n";
        std::cin>>nomPond;
        graphe.chargementPonderation(nomPond);
        std::cout<<"Voici le menu :\n";
    }
    else
    {
        std::cout<<"Voici le menu, les calculs se feront pour un graphe non pondere :\n";
    }



    do
    {
        std::cout<<"\nMenu :\n1 : Calcul, affichage et sauvegarde des indices de centralite\n";
        std::cout<<"2 : Changement de ponderations\n3 : Suppression d'arrete\n10 : Sortir du programme\n";
        std::cin>>choix;

        switch(choix)
        {
            case 1:
            {
                std::cout<<"\n";
                graphe.centraliteDegre();
                std::cout<<"\n";
                graphe.centraliteVecteur();
                std::cout<<"\n";
                graphe.centraliteProximite();
                std::cout<<"\n";
                graphe.centraliteIntermediarite();

                graphe.ecritureData();
                std::cout<<"Les valeurs des indices ont bien ete enregistrees dans le fichier dataSommet.txt.\n";

                graphe.dessiner();
                std::cout<<"Vous pouvez visualiser votre graphe depuis le fichier output.svg.\n";
                break;
            }
            case 2:
            {
                std::cout<<"Pour changer la ponderation que vous souhaitez,saisissez le nom du fichier, par exemple 'ponderation.txt'.\n";
                std::cin>>nomPond;
                graphe.chargementPonderation(nomPond);
                break;
            }
            case 3:
            {
                test=0;
                m_id_supp.clear();
                std::cout<<"Pour supprimer une arrete\nUne fois toutes les arretes selectionnees, saisissez '-1'\n";
                while(test!=-1)
                {
                    std::cout<<"Arrete :";
                    std::cin>>test;
                    if(test!=-1)
                        m_id_supp.push_back(test);
                }

                Graphe copieGraphe(nomGraphe,m_id_supp);

                if(nomPond!=" ")
                    copieGraphe.copieChargementPonderation(nomPond,m_id_supp);

                std::cout<<"Affichage des nouveux indices"<<std::endl;
                std::cout<<"\n";

                copieGraphe.centraliteDegre();
                std::cout<<"\n";
                copieGraphe.centraliteVecteur();
                std::cout<<"\n";
                copieGraphe.centraliteProximite();
                std::cout<<"\n";
                copieGraphe.centraliteIntermediarite();
                std::cout<<"\n";
                copieGraphe.comparaison(graphe);

                copieGraphe.dessiner();

                copieGraphe.testConnexite();
                break;
            }
        }

    }while(choix!=10);

    return 0;
}
