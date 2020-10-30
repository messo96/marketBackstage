CREATE DATABASE  IF NOT EXISTS `MarketBackstage` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `MarketBackstage`;
-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: MarketBackstage
-- ------------------------------------------------------
-- Server version	5.7.32-0ubuntu0.18.04.1

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
-- Table structure for table `CASSA`
--

DROP TABLE IF EXISTS `CASSA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CASSA` (
  `idCassa` int(2) NOT NULL,
  `isOccupata` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idCassa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CASSA`
--

LOCK TABLES `CASSA` WRITE;
/*!40000 ALTER TABLE `CASSA` DISABLE KEYS */;
INSERT INTO `CASSA` VALUES (1,0),(2,0),(3,0),(4,0),(5,0),(6,0);
/*!40000 ALTER TABLE `CASSA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CLIENTE`
--

DROP TABLE IF EXISTS `CLIENTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CLIENTE` (
  `codiceFiscale` char(16) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `totalePunti` int(10) DEFAULT '0',
  `dataDiNascita` date NOT NULL,
  PRIMARY KEY (`codiceFiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CLIENTE`
--

LOCK TABLES `CLIENTE` WRITE;
/*!40000 ALTER TABLE `CLIENTE` DISABLE KEYS */;
INSERT INTO `CLIENTE` VALUES ('AAAAAAAAAAAAAAAA','aaa','AAA',0,'2020-06-06'),('FCXTMN94B62Z729J','Brisa','Sipes',583,'2003-08-12'),('JVFQWN60E05F594X','Alvera','West',266,'1993-10-20'),('NNDQDM70E55C623P','Einar','Bernier',146,'1990-05-30'),('PHLLBN50R69E235H','Maye','Cronin',158,'1981-04-05'),('PRIPLC70M56F739F','Era','Wilkinson',186,'2002-05-16'),('XGAMNL88P55C984H','Joseph','Morissette',73,'2017-05-19');
/*!40000 ALTER TABLE `CLIENTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DIPENDENTE`
--

DROP TABLE IF EXISTS `DIPENDENTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DIPENDENTE` (
  `idDipendente` int(4) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `tipo` varchar(12) NOT NULL,
  `codiceFiscale` char(16) NOT NULL,
  `telefono` varchar(18) NOT NULL,
  PRIMARY KEY (`idDipendente`),
  UNIQUE KEY `IDDIPENDENTE_1` (`codiceFiscale`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DIPENDENTE`
--

LOCK TABLES `DIPENDENTE` WRITE;
/*!40000 ALTER TABLE `DIPENDENTE` DISABLE KEYS */;
INSERT INTO `DIPENDENTE` VALUES (1,'Mario','Rossi','Commesso','MRSSI34L32M3230P','0543224876'),(2,'Gino','Bianchi','Responsabile','GBNCI24S42G9830F','0547384920'),(3,'Maria','Neri','Responsabile','MRNRI64S22G3232A','0547359582'),(4,'Giorgio','Verdi','Commesso','GVRD59M42G98546F','0547090820'),(5,'Caterina','Rossi','Responsabile','CTRSSI45S42F9239','0543384970'),(6,'Viola','Neri','Commesso','VLNRI99S21G3732M','0547929592'),(7,'Monica','Bianchi','Commesso','MNCB59M42M9854AF','0547868890'),(8,'adffadv','sadasd','Responsabile','ahajskeodleoeirk','j33333');
/*!40000 ALTER TABLE `DIPENDENTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FORNITORE`
--

DROP TABLE IF EXISTS `FORNITORE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FORNITORE` (
  `PIVA` char(11) NOT NULL,
  `nomeAzienda` varchar(255) NOT NULL,
  `indirizzo` varchar(100) NOT NULL,
  `città` varchar(50) NOT NULL,
  `nazione` varchar(30) NOT NULL,
  `telefono` varchar(18) DEFAULT NULL,
  `fax` char(18) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `sitoWeb` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PIVA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FORNITORE`
--

LOCK TABLES `FORNITORE` WRITE;
/*!40000 ALTER TABLE `FORNITORE` DISABLE KEYS */;
INSERT INTO `FORNITORE` VALUES ('02569874521','Panificio F.lli Rossi','via anatre 28','Bergamo(BG)','Italy','+05 415689745',NULL,'panificio.rossi@libero.it','www.panificiorossi.it'),('03442800136','Forteron Drink s.r.l','Via dei campi 4','Roma(RM)','Italy','+39 0805569858','+39 0805569859','forteron@segreteria.it','www.fort.drink.it'),('31596784512','Big Industry','Via Nazionale 45','Roma(RO)','Italy','+39 056984742','+39 056984743','big-industry.segreteria@aruba.com','www.big-industry.com'),('32658974512','Break and Fast','5800 Ambrey Road.','London','United Kingdom','+08 255689745',NULL,'break-fast.office@gmail.com','www.break-fast.uk'),('33495813704','Pizza da Totò','Via ascoli 7','Napoli(NA)','Italy','+39 0815823555','+39 0815823554','totopizza@gmail.com',NULL),('93415791131','Dal Campo s.r.l. ','Piazza Gramsci 8','Firenze(FI)','Italy','+39 0540221839','+39 0540221840','dalcampo@uffici.it','www.fort.drink.it'),('98432206831','New England Seafood Cannery','2100 Paul Revere Blvd.','Manchester(MN)','England','+04 75553389',NULL,'cannery@seafood.en','www.seafood-england.en');
/*!40000 ALTER TABLE `FORNITORE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LAVORA`
--

DROP TABLE IF EXISTS `LAVORA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LAVORA` (
  `data` date NOT NULL,
  `oraInizio` time NOT NULL,
  `idCassa` int(3) NOT NULL,
  `oraFine` time DEFAULT NULL,
  `idDipendente` int(3) NOT NULL,
  PRIMARY KEY (`data`,`oraInizio`,`idDipendente`,`idCassa`),
  KEY `FKLAV_DIP` (`idDipendente`),
  KEY `FKLAV_CAS_FK` (`idCassa`),
  CONSTRAINT `FKLAV_CAS_FK` FOREIGN KEY (`idCassa`) REFERENCES `CASSA` (`idCassa`),
  CONSTRAINT `FKLAV_DIP` FOREIGN KEY (`idDipendente`) REFERENCES `DIPENDENTE` (`idDipendente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LAVORA`
--

LOCK TABLES `LAVORA` WRITE;
/*!40000 ALTER TABLE `LAVORA` DISABLE KEYS */;
INSERT INTO `LAVORA` VALUES ('2020-02-19','08:10:00',1,'10:10:00',1),('2020-02-19','08:10:00',3,'10:10:00',2),('2020-02-19','08:10:00',2,'10:10:00',3),('2020-02-19','10:10:00',2,'13:10:00',3),('2020-02-20','08:10:00',3,'10:10:00',2),('2020-06-11','11:19:00',2,'11:19:00',3),('2020-06-11','11:41:00',2,'11:41:00',2),('2020-06-23','14:02:00',3,'14:02:00',3),('2020-07-14','11:32:00',3,'11:32:00',3),('2020-07-14','11:35:00',2,'11:35:00',2);
/*!40000 ALTER TABLE `LAVORA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OFFERTA`
--

DROP TABLE IF EXISTS `OFFERTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OFFERTA` (
  `id_offerta` int(3) NOT NULL,
  `dataInizio` date NOT NULL,
  `dataFine` date NOT NULL,
  `sconto` int(2) NOT NULL,
  PRIMARY KEY (`id_offerta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OFFERTA`
--

LOCK TABLES `OFFERTA` WRITE;
/*!40000 ALTER TABLE `OFFERTA` DISABLE KEYS */;
INSERT INTO `OFFERTA` VALUES (1,'2020-01-19','2020-06-19',10),(2,'2019-12-02','2020-07-31',5),(3,'2020-05-29','2020-08-10',20);
/*!40000 ALTER TABLE `OFFERTA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODOTTO`
--

DROP TABLE IF EXISTS `PRODOTTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODOTTO` (
  `idProdotto` int(3) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `descrizione` text NOT NULL,
  `reparto` varchar(30) NOT NULL,
  `quantità` int(3) NOT NULL,
  `prezzo` double(4,2) NOT NULL,
  `PIVA` char(11) NOT NULL,
  `id_offerta` int(3) DEFAULT NULL,
  PRIMARY KEY (`idProdotto`),
  KEY `FKVENDITA` (`PIVA`),
  KEY `FKCONTIENE` (`id_offerta`),
  CONSTRAINT `FKCONTIENE` FOREIGN KEY (`id_offerta`) REFERENCES `OFFERTA` (`id_offerta`),
  CONSTRAINT `FKVENDITA` FOREIGN KEY (`PIVA`) REFERENCES `FORNITORE` (`PIVA`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODOTTO`
--

LOCK TABLES `PRODOTTO` WRITE;
/*!40000 ALTER TABLE `PRODOTTO` DISABLE KEYS */;
INSERT INTO `PRODOTTO` VALUES (1,'Sugo pronto di calamari','Sugo pronto surgelato di calamari freschi made in England','Surgelati',146,3.50,'98432206831',1),(2,'Aranciata','Aranciata da 1,5L','Bevande',497,1.50,'03442800136',NULL),(3,'Chinotto','Chinotto da 1,5L','Bevande',495,1.00,'03442800136',1),(4,'Gamberetti surgelati','Gamberetti surgelati made in England','Surgelati',97,5.50,'98432206831',3),(5,'Patè di salmone','Patè di salmone da 400g','Pesce',597,2.00,'98432206831',NULL),(6,'Pizza Margherita surgelata','Pizza surgelata al gusto Margherita','Surgelati',599,1.80,'33495813704',NULL),(7,'Pizza Americana surgelata','Pizza surgelata al gusto Wurstel e Patatine ','Surgelati',598,1.80,'33495813704',NULL),(8,'Tortellini','Tortellini dalla tradizione 250g ','Pasta',350,3.00,'02569874521',2),(9,'Gnocchetti','Gnocchetti sardi fatti a mano 300g ','Pasta',350,4.50,'02569874521',NULL),(10,'Cornetti al cioccolato','Cornetti al cioccolato preconfezionati ','Colazione',800,2.50,'02569874521',1),(11,'Patatine fritte','Patatine prefritte 1000g','Surgelati',800,2.00,'31596784512',1),(12,'Banane','Casco di banane preconfezionate da 500g','Frutta e Verdura',300,4.00,'31596784512',3),(13,'Vino gourmet','Vino DOC provenienza Firenze 1L','Vini',200,6.50,'93415791131',NULL),(14,'Patatine fritte','Patatine prefritte 1000g','Surgelati',800,2.00,'31596784512',1),(15,'AAA','Prova nuovo prodotto','Pesce',222,2.30,'32658974512',NULL);
/*!40000 ALTER TABLE `PRODOTTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SCONTRINO`
--

DROP TABLE IF EXISTS `SCONTRINO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SCONTRINO` (
  `idScontrino` int(3) NOT NULL AUTO_INCREMENT,
  `idCassa` int(3) NOT NULL,
  `dataEmissione` date NOT NULL,
  `orarioEmissione` time NOT NULL,
  `totale` double(6,2) NOT NULL,
  `codiceFiscale` char(16) DEFAULT NULL,
  PRIMARY KEY (`idScontrino`),
  UNIQUE KEY `IDSCONTRINO_1` (`idCassa`,`dataEmissione`,`orarioEmissione`),
  KEY `FKINTESTATO` (`codiceFiscale`),
  CONSTRAINT `FKINTESTATO` FOREIGN KEY (`codiceFiscale`) REFERENCES `CLIENTE` (`codiceFiscale`),
  CONSTRAINT `FKPRODUCE` FOREIGN KEY (`idCassa`) REFERENCES `CASSA` (`idCassa`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SCONTRINO`
--

LOCK TABLES `SCONTRINO` WRITE;
/*!40000 ALTER TABLE `SCONTRINO` DISABLE KEYS */;
INSERT INTO `SCONTRINO` VALUES (1,1,'2020-12-02','10:10:00',55.70,NULL),(2,1,'2020-12-02','10:12:00',25.10,NULL),(3,2,'2020-02-19','08:10:00',505.70,NULL),(4,3,'2020-02-20','09:10:00',5.10,'XGAMNL88P55C984H'),(6,2,'2020-12-02','10:12:00',25.10,NULL),(7,2,'2020-06-11','11:19:00',5.55,NULL),(8,2,'2020-06-11','11:41:00',10.25,NULL),(9,3,'2020-06-23','14:02:00',7.40,NULL),(10,3,'2020-07-14','11:32:00',8.00,NULL),(11,2,'2020-07-14','11:35:00',16.00,NULL);
/*!40000 ALTER TABLE `SCONTRINO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TURNO`
--

DROP TABLE IF EXISTS `TURNO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TURNO` (
  `data` date NOT NULL,
  `idDipendente` int(4) NOT NULL,
  `oraInizio` time NOT NULL,
  `oraFine` time NOT NULL,
  UNIQUE KEY `IDTURNO_1` (`data`,`idDipendente`,`oraInizio`),
  KEY `FKAPPARTIENE` (`idDipendente`),
  CONSTRAINT `FKAPPARTIENE` FOREIGN KEY (`idDipendente`) REFERENCES `DIPENDENTE` (`idDipendente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TURNO`
--

LOCK TABLES `TURNO` WRITE;
/*!40000 ALTER TABLE `TURNO` DISABLE KEYS */;
INSERT INTO `TURNO` VALUES ('2020-05-26',1,'08:10:00','13:10:00'),('2020-05-26',2,'08:10:00','13:10:00'),('2020-05-26',3,'08:10:00','13:10:00'),('2020-05-26',4,'13:10:00','18:10:00'),('2020-05-26',5,'13:10:00','18:10:00'),('2020-05-26',6,'13:10:00','18:10:00'),('2020-06-11',8,'10:10:00','13:10:00'),('2020-07-14',5,'10:10:00','13:10:00');
/*!40000 ALTER TABLE `TURNO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-30 11:44:26
