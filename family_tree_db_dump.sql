-- MySQL dump 10.13  Distrib 8.0.40, for Linux (x86_64)
--
-- Host: localhost    Database: family_tree_db
-- ------------------------------------------------------
-- Server version	8.0.40-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (20,'MARRIAGE','2024-11-12','Marrige of Dave and Sandra'),(21,'BIRTH','1985-06-15','John Doe\'s Birth'),(22,'BIRTH','1987-02-20','Jane Doe\'s Birth'),(23,'BIRTH','1990-03-25','Mary Johnson\'s Birth'),(24,'BIRTH','1991-11-11','James Johnson\'s Birth'),(25,'BIRTH','1983-08-30','Robert Smith\'s Birth'),(26,'BIRTH','1994-09-22','Emily Smith\'s Birth'),(27,'BIRTH','1992-04-16','William Brown\'s Birth'),(28,'BIRTH','1985-01-30','Linda Brown\'s Birth'),(29,'BIRTH','1981-10-05','Michael Davis\'s Birth'),(30,'BIRTH','1993-12-12','Elizabeth Davis\'s Birth'),(31,'BIRTH','1995-07-18','David Miller\'s Birth'),(32,'BIRTH','1988-01-13','Sophia Miller\'s Birth'),(33,'BIRTH','1986-09-09','Joseph Wilson\'s Birth'),(34,'BIRTH','1992-11-01','Grace Wilson\'s Birth'),(35,'BIRTH','1994-03-04','Daniel Moore\'s Birth'),(36,'BIRTH','1990-06-20','Olivia Moore\'s Birth'),(37,'BIRTH','1989-12-30','Charles Taylor\'s Birth'),(38,'BIRTH','1993-07-12','Charlotte Taylor\'s Birth'),(39,'BIRTH','1991-05-02','Christopher Anderson\'s Birth'),(40,'BIRTH','1987-10-18','Amelia Anderson\'s Birth'),(41,'MARRIAGE','2015-05-10','John Doe and Jane Doe Marriage'),(42,'MARRIAGE','2020-07-22','Robert Smith and Emily Smith Marriage'),(43,'MARRIAGE','2023-04-14','Michael Davis and Elizabeth Davis Marriage'),(44,'DIVORCE','2022-06-30','John Doe and Jane Doe Divorce'),(45,'DIVORCE','2024-01-11','Robert Smith and Emily Smith Divorce'),(46,'DEATH','2060-02-28','John Doe\'s Death'),(47,'DEATH','2080-03-15','Jane Doe\'s Death'),(48,'DEATH','2075-07-09','Robert Smith\'s Death'),(49,'DEATH','2090-09-10','Emily Smith\'s Death'),(50,'BIRTH','1985-06-15','John Doe\'s Birth'),(51,'BIRTH','1987-02-20','Jane Doe\'s Birth'),(52,'BIRTH','1990-03-25','Mary Johnson\'s Birth'),(53,'BIRTH','1991-11-11','James Johnson\'s Birth'),(54,'BIRTH','1983-08-30','Robert Smith\'s Birth'),(55,'BIRTH','1994-09-22','Emily Smith\'s Birth'),(56,'BIRTH','1992-04-16','William Brown\'s Birth'),(57,'BIRTH','1985-01-30','Linda Brown\'s Birth'),(58,'BIRTH','1981-10-05','Michael Davis\'s Birth'),(59,'BIRTH','1993-12-12','Elizabeth Davis\'s Birth'),(60,'BIRTH','1995-07-18','David Miller\'s Birth'),(61,'BIRTH','1988-01-13','Sophia Miller\'s Birth'),(62,'BIRTH','1986-09-09','Joseph Wilson\'s Birth'),(63,'BIRTH','1992-11-01','Grace Wilson\'s Birth'),(64,'BIRTH','1994-03-04','Daniel Moore\'s Birth'),(65,'BIRTH','1990-06-20','Olivia Moore\'s Birth'),(66,'BIRTH','1989-12-30','Charles Taylor\'s Birth'),(67,'BIRTH','1993-07-12','Charlotte Taylor\'s Birth'),(68,'BIRTH','1991-05-02','Christopher Anderson\'s Birth'),(69,'BIRTH','1987-10-18','Amelia Anderson\'s Birth'),(70,'MARRIAGE','2015-05-10','John Doe and Jane Doe Marriage'),(71,'MARRIAGE','2020-07-22','Robert Smith and Emily Smith Marriage'),(72,'MARRIAGE','2023-04-14','Michael Davis and Elizabeth Davis Marriage'),(73,'DIVORCE','2022-06-30','John Doe and Jane Doe Divorce'),(74,'DIVORCE','2024-01-11','Robert Smith and Emily Smith Divorce'),(75,'DEATH','2060-02-28','John Doe\'s Death'),(76,'DEATH','2080-03-15','Jane Doe\'s Death'),(77,'DEATH','2075-07-09','Robert Smith\'s Death'),(78,'DEATH','2090-09-10','Emily Smith\'s Death');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family_tree`
--

DROP TABLE IF EXISTS `family_tree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `family_tree` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family_tree`
--

LOCK TABLES `family_tree` WRITE;
/*!40000 ALTER TABLE `family_tree` DISABLE KEYS */;
INSERT INTO `family_tree` VALUES (4,'Csf 2'),(5,'Csf 3');
/*!40000 ALTER TABLE `family_tree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_of_event`
--

DROP TABLE IF EXISTS `part_of_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part_of_event` (
  `event_id` int NOT NULL,
  `person_id` int NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`person_id`),
  KEY `person_id` (`person_id`),
  CONSTRAINT `part_of_event_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE CASCADE,
  CONSTRAINT `part_of_event_ibfk_2` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_of_event`
--

LOCK TABLES `part_of_event` WRITE;
/*!40000 ALTER TABLE `part_of_event` DISABLE KEYS */;
INSERT INTO `part_of_event` VALUES (21,16,'Self'),(22,17,'Self'),(23,18,'Self'),(24,19,'Self'),(25,20,'Self'),(26,21,'Self'),(27,22,'Self'),(28,23,'Self'),(29,24,'Self'),(30,25,'Self'),(31,26,'Self'),(32,27,'Self'),(33,28,'Self'),(34,29,'Self'),(35,30,'Self'),(36,31,'Self'),(37,32,'Self'),(38,33,'Self'),(39,34,'Self'),(40,35,'Self'),(41,16,'Spouse'),(41,17,'Spouse'),(42,20,'Spouse'),(42,21,'Spouse'),(43,24,'Spouse'),(43,25,'Spouse'),(44,16,'Ex-Spouse'),(44,17,'Ex-Spouse'),(45,20,'Ex-Spouse'),(45,21,'Ex-Spouse'),(46,16,'Deceased'),(47,17,'Deceased'),(48,20,'Deceased'),(49,21,'Deceased');
/*!40000 ALTER TABLE `part_of_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_of_family`
--

DROP TABLE IF EXISTS `part_of_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part_of_family` (
  `family_id` int NOT NULL,
  `person_id` int NOT NULL,
  PRIMARY KEY (`family_id`,`person_id`),
  KEY `person_id` (`person_id`),
  CONSTRAINT `part_of_family_ibfk_1` FOREIGN KEY (`family_id`) REFERENCES `family_tree` (`id`) ON DELETE CASCADE,
  CONSTRAINT `part_of_family_ibfk_2` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_of_family`
--

LOCK TABLES `part_of_family` WRITE;
/*!40000 ALTER TABLE `part_of_family` DISABLE KEYS */;
/*!40000 ALTER TABLE `part_of_family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mother_id` int DEFAULT NULL,
  `father_id` int DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mother_id` (`mother_id`),
  KEY `father_id` (`father_id`),
  CONSTRAINT `person_ibfk_1` FOREIGN KEY (`mother_id`) REFERENCES `person` (`id`) ON DELETE SET NULL,
  CONSTRAINT `person_ibfk_2` FOREIGN KEY (`father_id`) REFERENCES `person` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (16,NULL,NULL,'John','Doe','m','1985-06-15'),(17,NULL,NULL,'Jane','Doe','f','1987-02-20'),(18,NULL,NULL,'Mary','Johnson','f','1990-03-25'),(19,NULL,NULL,'James','Johnson','m','1991-11-11'),(20,18,17,'Robert','Smith','m','1983-08-30'),(21,18,17,'Emily','Smith','f','1994-09-22'),(22,24,23,'William','Brown','m','1992-04-16'),(23,24,23,'Linda','Brown','f','1985-01-30'),(24,22,21,'Michael','Davis','m','1981-10-05'),(25,22,21,'Elizabeth','Davis','f','1993-12-12'),(26,12,14,'David','Miller','m','1995-07-18'),(27,12,14,'Sophia','Miller','f','1988-01-13'),(28,22,24,'Joseph','Wilson','m','1986-09-09'),(29,22,24,'Grace','Wilson','f','1992-11-01'),(30,20,21,'Daniel','Moore','m','1994-03-04'),(31,20,21,'Olivia','Moore','f','1990-06-20'),(32,34,35,'Charles','Taylor','m','1989-12-30'),(33,34,35,'Charlotte','Taylor','f','1993-07-12'),(34,NULL,NULL,'Christopher','Anderson','m','1991-05-02'),(35,NULL,NULL,'Amelia','Anderson','f','1987-10-18');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','$2a$10$UjD16k70lJDYp/pfBcBr8OzmqQj6ALNarlpqAG3umkstel9nou1yq'),(2,'bodorcy','$2a$10$mnH6O97OAvv2R78DDWjeF.zDQwjP6jeLCrecGl6bogNALK.cpiqxO');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-24  1:56:38
