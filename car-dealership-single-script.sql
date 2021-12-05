CREATE DATABASE  IF NOT EXISTS `cardealership` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cardealership`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: cardealership
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `contact_message`
--

DROP TABLE IF EXISTS `contact_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_message` (
  `ContactMessageId` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Message` mediumtext NOT NULL,
  `Phone` char(10) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ContactMessageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_message`
--

LOCK TABLES `contact_message` WRITE;
/*!40000 ALTER TABLE `contact_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `make`
--

DROP TABLE IF EXISTS `make`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `make` (
  `MakeId` int NOT NULL,
  `MakeName` varchar(30) DEFAULT NULL,
  `DateAdded` date DEFAULT NULL,
  `UserId` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`MakeId`),
  KEY `FKMake_UserId_idx` (`UserId`),
  CONSTRAINT `FKMake_UserId` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `make`
--

LOCK TABLES `make` WRITE;
/*!40000 ALTER TABLE `make` DISABLE KEYS */;
/*!40000 ALTER TABLE `make` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model` (
  `ModelId` int NOT NULL,
  `MakeId` int NOT NULL,
  `ModelName` varchar(45) NOT NULL,
  `DateAdded` date NOT NULL,
  `UserId` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ModelId`),
  KEY `MakeId_idx` (`MakeId`),
  KEY `FKModel_UserId_idx` (`UserId`),
  CONSTRAINT `FKModel_MakeId` FOREIGN KEY (`MakeId`) REFERENCES `make` (`MakeId`),
  CONSTRAINT `FKModel_UserId` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `PurchaseId` int NOT NULL,
  `VIN` char(17) NOT NULL,
  `PurchaseType` varchar(13) NOT NULL,
  `PurchasePrice` decimal(10,2) NOT NULL,
  `Phone` char(10) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Street1` varchar(45) NOT NULL,
  `Street2` varchar(45) DEFAULT NULL,
  `City` varchar(45) NOT NULL,
  `State` varchar(25) NOT NULL,
  `Zip` char(5) NOT NULL,
  `DateOfPurchase` datetime NOT NULL,
  `SalesPersonId` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`PurchaseId`),
  KEY `FKPurchase_VIN_idx` (`VIN`),
  KEY `FKPurchase_SalesPersonId_idx` (`SalesPersonId`),
  CONSTRAINT `FKPurchase_SalesPersonId` FOREIGN KEY (`SalesPersonId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `FKPurchase_VIN` FOREIGN KEY (`VIN`) REFERENCES `vehicle` (`VIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specials`
--

DROP TABLE IF EXISTS `specials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specials` (
  `SpecialId` int NOT NULL,
  `Title` varchar(30) NOT NULL,
  `Description` text NOT NULL,
  PRIMARY KEY (`SpecialId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specials`
--

LOCK TABLES `specials` WRITE;
/*!40000 ALTER TABLE `specials` DISABLE KEYS */;
/*!40000 ALTER TABLE `specials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserId` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `PhoneNumber` char(10) NOT NULL,
  `Role` varchar(8) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'John','Smith','jsmith@gmail.com','1234567890','admin'),(2,'Travis','Car','tcar@gmail.com','2345678901','sales'),(3,'Mary','Berman','mberman@gmail.com','3456789012','disabled');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `VIN` char(17) NOT NULL,
  `MakeId` int NOT NULL,
  `ModelId` int NOT NULL,
  `Type` varchar(4) NOT NULL,
  `BodyStyle` varchar(30) NOT NULL,
  `Year` int NOT NULL,
  `Transmission` varchar(20) NOT NULL,
  `Color` varchar(20) NOT NULL,
  `Interior` varchar(45) NOT NULL,
  `Mileage` int NOT NULL,
  `SalePrice` decimal(10,2) NOT NULL,
  `MSRP` decimal(10,2) NOT NULL,
  `Description` text NOT NULL,
  `IsFeatured` tinyint NOT NULL,
  `IsSold` tinyint NOT NULL,
  `Picture` blob NOT NULL,
  PRIMARY KEY (`VIN`),
  KEY `MakeId_idx` (`MakeId`),
  KEY `ModelId_idx` (`ModelId`),
  CONSTRAINT `FK_MakeId` FOREIGN KEY (`MakeId`) REFERENCES `make` (`MakeId`),
  CONSTRAINT `FK_ModelId` FOREIGN KEY (`ModelId`) REFERENCES `model` (`ModelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-04 19:44:55
