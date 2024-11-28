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
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (86,'BIRTH','2012-09-20','Athena kipattan Zeusz homlokából'),(87,'MARRIAGE','2024-11-01','Zeusz és Demeter törvényen kívüli házassága'),(88,'DEATH','2006-10-10','Zeusz feldarabolja Kronost, és a Tartarusba dobja'),(89,'BIRTH','2009-07-27','Aphrodité kiemelkedik meztelen a vízből'),(90,'BIRTH','2009-06-18','Megszületik Hermes, Zeusz és Maia törvények kívüli gyereke'),(91,'DIVORCE','1987-10-06','Kronos és Rhea válása, mert kronos folyton meztelen'),(92,'MARRIAGE','2024-11-10','Zeusz és Héra házassága, ha Zeusz nem lenne házas épp'),(93,'MARRIAGE','2024-10-27','Hades és Persephone házassága'),(94,'MARRIAGE','2024-10-06','Aphrodite és Ares házassága');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family_tree`
--

LOCK TABLES `family_tree` WRITE;
/*!40000 ALTER TABLE `family_tree` DISABLE KEYS */;
INSERT INTO `family_tree` VALUES (7,'Zeusz és Demeter családja'),(8,'Zeusz és Héra családfája'),(9,'Zeusz mindenféle fura kalandja'),(10,'Dionysus egyik családja');
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
INSERT INTO `part_of_event` VALUES (86,46,NULL),(86,56,NULL),(87,45,NULL),(87,46,NULL),(88,43,NULL),(88,44,NULL),(89,57,NULL),(90,46,NULL),(90,59,NULL),(91,43,NULL),(91,44,NULL),(92,47,NULL),(93,49,NULL),(93,51,NULL),(94,52,NULL),(94,57,NULL);
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
INSERT INTO `part_of_family` VALUES (7,45),(7,46),(8,46),(9,46),(8,47),(7,51),(8,52),(8,54),(8,55),(10,60);
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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (43,NULL,NULL,'Kronos','Titan','m','1901-12-31'),(44,NULL,NULL,'Rhea','Titan','f','1900-11-01'),(45,44,43,'Demeter','Olympian','f','1950-12-01'),(46,44,43,'Zeus','Olympian','m','1952-11-11'),(47,44,43,'Hera','Olympian','f','1995-10-18'),(48,44,43,'Poseidon','Olympian','m','1958-01-07'),(49,44,43,'Hades','Olympian','m','1967-09-20'),(50,44,43,'Hestia','Olympian','f','1990-04-07'),(51,45,46,'Persephone','Decendentolympian','f','1999-01-05'),(52,47,46,'Ares','Decendentolympian','m','1994-10-04'),(54,47,46,'Eileithyia','Decendentolympian','f','1995-03-21'),(55,47,46,'Hebe','Decendentolympian','f','2000-07-18'),(56,NULL,46,'Athena','Zeusismyfather','f','2012-09-20'),(57,NULL,46,'Aphrodite','Zeusismyfather','f','2009-07-27'),(58,NULL,46,'Artemis','Zeusismyfather','f','2004-05-20'),(59,NULL,46,'Hermes','Zeusismyfather','m','2009-06-18'),(60,NULL,46,'Dionysus','Zeusismyfather','m','2004-09-23'),(61,NULL,46,'Apollo','Zeusismyfather','m','1991-06-24');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'test','$2a$10$G6vyJO6l0lKJzXZ4Gm4LQ.yE0VNXK.emLdilHMf8MA9KnxmMxAgFa');
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

-- Dump completed on 2024-11-28 22:33:54
