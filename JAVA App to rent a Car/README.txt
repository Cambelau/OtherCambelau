README Projet SIENNICKI LIONNET SAJOT

Pour le bon fonctionnement du logiciel : 

- Récupérer la bdd rentable.sql

- Vérifier dans le fichier DAOProperties.txt que les informations correpondent à la location de la bdd. 
	Nous utilisons WampServer3.2.3.

- Une librairie mysql-connector-java-8.0.22.jar est disponible dans le fichier lib

- inclure les librairies JavaFx disponibles dans le fichier Lib 

- Ecrire dans la partie "Run" des properties du projet : 
	--module-path "C:\Program Files\Java\javafx-sdk-15.0.1\lib" --add-modules=javafx.controls,javafx.fxml