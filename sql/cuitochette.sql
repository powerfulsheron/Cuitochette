-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cuitochette
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  `restaurant` int(11) DEFAULT NULL,
  `taable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_COMMANDE_RESTAURANT_idx` (`restaurant`),
  CONSTRAINT `FK_COMMANDE_RESTAURANT` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande`
--

LOCK TABLES `commande` WRITE;
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
INSERT INTO `commande` VALUES (1,'commande',1,1),(2,'entreeEnvoye',1,1),(3,'platEnvoye',1,1),(4,'dessertEnvoye',1,1),(10,'commande',2,2),(11,'commande',2,2),(12,'commande',2,2),(13,'commande',2,2);
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commande_plat`
--

DROP TABLE IF EXISTS `commande_plat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commande_plat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commande` int(11) DEFAULT NULL,
  `plat` int(11) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_COMPLAT_COMMANDE_idx` (`commande`),
  KEY `FK_COMPLAT_PLAT_idx` (`plat`),
  CONSTRAINT `FK_COMPLAT_COMMANDE` FOREIGN KEY (`commande`) REFERENCES `commande` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_COMPLAT_PLAT` FOREIGN KEY (`plat`) REFERENCES `plat` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande_plat`
--

LOCK TABLES `commande_plat` WRITE;
/*!40000 ALTER TABLE `commande_plat` DISABLE KEYS */;
INSERT INTO `commande_plat` VALUES (1,1,2,3),(2,1,3,3),(3,2,1,2),(4,2,2,2),(5,3,5,3),(6,3,2,1),(7,3,3,4),(8,4,1,1),(9,4,2,1);
/*!40000 ALTER TABLE `commande_plat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plat`
--

DROP TABLE IF EXISTS `plat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `restaurant` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PLAT_RESTAURANT_idx` (`restaurant`),
  CONSTRAINT `FK_PLAT_RESTAURANT` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plat`
--

LOCK TABLES `plat` WRITE;
/*!40000 ALTER TABLE `plat` DISABLE KEYS */;
INSERT INTO `plat` VALUES (1,'Salade de Crevettes','Une salade fraiche du marché avec des crevettes du poissonnier','entree',1),(2,'Bourguignon de Boeuf','Sauté de boeuf mijoté pendant 5 heures','plat',1),(3,'Mousse au Chocolat','75% de chocolat','dessert',1),(4,'Nems','Les vrais nems du Vietnam','entree',1),(5,'Columbo de Porc','La vraie recette Antillaise','plat',1),(6,'Gateau à l\'Ananas','Fait maison le matin même','dessert',1),(7,'Fanta','Canette de 33cl','boisson',1),(8,'Coca','Canette de 33cl','boisson',1),(9,'Part de Pizza','La vraie pizza de naples','entree',2),(10,'Soupe à l\'Oignon','Faite par les chevaliers oignons de Final Fantasy','entree',2),(11,'Souris d\'Agneau','Servi avec des pâtes fraiches','plat',2),(12,'Rocher Coco','Rocher Coco de la maison Ferrerro','dessert',2),(13,'Verre de Vin','Chateau corbillac','boisson',2);
/*!40000 ALTER TABLE `plat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `propriétaire` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_RESTAURANT_UTILISATEUR_idx` (`propriétaire`),
  CONSTRAINT `FK_RESTAURANT_UTILISATEUR` FOREIGN KEY (`propriétaire`) REFERENCES `utilisateur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (1,'La Fourchette d\'Or',NULL),(2,'Le Blanc Bec',NULL);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) DEFAULT NULL,
  `mdp` varchar(100) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `restaurant` int(11) DEFAULT NULL,
  `numeroTable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_UTILISATEUR_RESTAURANT_idx` (`restaurant`),
  CONSTRAINT `FK_UTILISATEUR_RESTAURANT` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (1,'table1','12345','table',1,1),(2,'chef','12345','chef',NULL,NULL);
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-12 16:21:52
