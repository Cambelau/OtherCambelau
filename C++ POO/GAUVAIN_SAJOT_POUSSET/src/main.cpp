//
//  main.cpp
//  Projet_S3
//
//  Created by Magdeleine GAUVAIN on 22/11/2019.
//  Copyright © 2019 Magdeleine GAUVAIN. All rights reserved.
//

#include <iostream>
#include <vector>
#include <sstream>
#include <windows.h>

#include "../bloc.h"
#include "../Scene.h"

int main(int argc, const char * argv[])
{

    //Déclarations
    Scene scene;

    Bloc *current_bloc = NULL;
    bool run = true;

    std::string input;
    std::string input2;
    double x,y;

    //Récupération des éléments depuis le fichier
    scene.lecture_fichier("scene_1.txt");


    do
    {
        //Affichage
        scene.dessiner();

        //Traitement
        if (current_bloc!=NULL)
        {
            std::cout<<"@"<<current_bloc->getID();
        }
        std::cout<<"\n>";

        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),10);
        std::cin>>input;
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),15);

        //bouger un élément d'un robot
        if (input[0]=='@')
        {
            std::cin>>input2;
            input.erase(input.begin());

            current_bloc = scene.searchWithPath(input);

            if (current_bloc == NULL)
            {
                std::cout<<"Bloc not found\n";
            }

            else if (input2 == "move")
            {
                std::cin>>x>>y;
                //aller bouger le robot avec x et y comme translation
                scene.translaterBloc(current_bloc, x, y);
            }
            else if (input2 == "rotate")
            {
                std::cin>>x;
                //pareil mais en rotation
                scene.rotationBloc(current_bloc, x);
            }
        }
        else if (input == "move" && current_bloc != NULL)
        {
            std::cin>>x>>y;
            //aller bouger le robot avec x et y comme translation
            scene.translaterBloc(current_bloc, x, y);

        }

        else if (input == "rotate" && current_bloc != NULL)
        {
            std::cin>>x;
            //pareil mais en rotation
            scene.rotationBloc(current_bloc, x);
        }

        //quitter
        else if(input =="exit")
        {
            run = !run;
        }

        //charger un fichier .rom
        else if(input == "load")
        {
            std::getline(std::cin,input2);
            scene.vider_vec();
            input2.erase(input2.begin());
            //charger le fichier dont le nom est input2
            scene.lecture_fichier(input2);
            current_bloc = NULL; //ou sinon aller chercher le nouveau
        }

        //afficher les ids
        else if(input == "ids")
        {
            scene.showId();
        }

        //chacher les ids
        else if(input == "noids")
        {
            scene.hideId();
        }

        //afficher la grille
        else if(input == "rulers")
        {
            scene.showGrid();
        }

        //retirer la grille
        else if(input == "norulers")
        {
            scene.hideGrid();
        }

        //sauvegarder la situation dans le vecteur
        else if (input == "store")
        {
            scene.store();
        }
        //cherche les collision de bloc
        else if (input == "collision")
        {
            SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),10);
            scene.collision();
            SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),15);
        }

        //restauder la sauvegarde depuis le vecteur
        else if (input == "restore")
        {
            if (scene.storage())
            {
                scene.restore();
            }
            else
            {
                std::cout<<"nothing to store...\n";
            }
        }

        else
        {
            std::cout<<"invalid command, please restart\n";
        }


    }
    while (run);

    return 0;
}

