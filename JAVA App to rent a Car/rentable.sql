-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 14 déc. 2020 à 19:29
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `rentable`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Adresse` varchar(255) NOT NULL,
  `Login` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Membre` tinyint(1) NOT NULL,
  `Coordonnees` varchar(10) NOT NULL,
  `Type` tinyint(1) NOT NULL,
  `Paiement` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`ID`, `Nom`, `Prenom`, `Adresse`, `Login`, `Password`, `Membre`, `Coordonnees`, `Type`, `Paiement`) VALUES
(1, 'Duchamps', 'Pierre', '3 rue ECE paris', 'dpierre', '22e64ab0afecf7cbccc9d7b01f622fc4', 1, '097775', 2, 'carte'),
(2, 'Whoha', 'Michel', 'Wuhan', 'M.Whoha', 'db1257dff2307ada2a2b7c7ad73e41a9', 1, '0619485621', 0, 'CB'),
(18, 'Filibert', 'Jacques', '15 rue Jacques', 'J.RUE', 'e230cc289ce112b5296293d0784ca386', 1, '0748165962', 1, 'CB'),
(19, 'Jaeger', 'Eren ', 'Paradis', 'Assaillant', 'caa8a5b7df461210d4f1347fa62a0284', 1, '0606060606', 0, 'Billet');

-- --------------------------------------------------------

--
-- Structure de la table `employer`
--

DROP TABLE IF EXISTS `employer`;
CREATE TABLE IF NOT EXISTS `employer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employer`
--

INSERT INTO `employer` (`id`, `nom`, `prenom`, `adresse`, `login`, `password`) VALUES
(1, 'Etienne', 'Lionnet', '25 rue eceparis', 'letienne', '2139c13e2addb52f69175bc00ac5dfce'),
(2, 'Matthieu', 'Sajot', '66 avenue JM', 'a', '0cc175b9c0f1b6a831c399e269772661'),
(3, 'Siennicki', 'Tom', 'Paname', 'tom', '34b7da764b21d298ef307d04d8152dc5');

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `IdClient` int(11) NOT NULL,
  `IdVoiture` int(11) NOT NULL,
  `DateDebut` date NOT NULL,
  `DateFin` date NOT NULL,
  `AdresseRecup` varchar(255) NOT NULL,
  `durée` int(11) NOT NULL,
  `PrixTotal` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`id`, `IdClient`, `IdVoiture`, `DateDebut`, `DateFin`, `AdresseRecup`, `durée`, `PrixTotal`) VALUES
(1, 1, 60, '2020-11-03', '2020-11-12', '20 rue eceparis', 10, 3000),
(2, 18, 61, '2020-12-10', '2020-10-14', 'Place de la République', 4, 280);

-- --------------------------------------------------------

--
-- Structure de la table `voitures`
--

DROP TABLE IF EXISTS `voitures`;
CREATE TABLE IF NOT EXISTS `voitures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Prix` int(11) NOT NULL,
  `NbrePortes` int(11) NOT NULL,
  `EmissionCO2` int(11) NOT NULL,
  `Disponiblité` tinyint(1) NOT NULL,
  `NbreBaggages` int(11) NOT NULL,
  `Poid` int(11) NOT NULL,
  `Volume` int(11) NOT NULL,
  `taille` int(11) NOT NULL,
  `options` varchar(255) NOT NULL,
  `NbrePassagers` int(11) NOT NULL,
  `BoiteVitesse` varchar(100) NOT NULL,
  `énergie` varchar(255) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `image` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `voitures`
--

INSERT INTO `voitures` (`id`, `Prix`, `NbrePortes`, `EmissionCO2`, `Disponiblité`, `NbreBaggages`, `Poid`, `Volume`, `taille`, `options`, `NbrePassagers`, `BoiteVitesse`, `énergie`, `Nom`, `image`) VALUES
(63, 70, 5, 10, 0, 4, 1000, 1000, 4, 'Large', 4, 'Manuelle', 'Essense', 'Utilitaire', 'A'),
(62, 30, 3, 5, 1, 2, 800, 800, 2, 'Pas cher et économique', 2, 'Manuelle', 'Essense', 'Citadine', 'C'),
(58, 70, 3, 5, 1, 2, 800, 800, 2, 'Assistance parking', 2, 'Automatique', 'Essense', 'Citadine', 'C'),
(59, 50, 3, 20, 0, 2, 1000, 1000, 4, 'ABS', 2, 'Manuelle', 'Essense', 'Sportive', 'D'),
(60, 30, 5, 20, 0, 6, 2000, 2000, 6, 'Caméra recul', 7, 'Manuelle', 'Essense', 'Familliale', 'B'),
(61, 60, 3, 20, 0, 2, 1000, 1000, 4, '  ', 2, 'Manuelle', 'Essense', 'Sportive', 'D');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
