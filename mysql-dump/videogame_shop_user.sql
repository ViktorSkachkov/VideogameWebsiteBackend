CREATE DATABASE  IF NOT EXISTS `videogame_shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `videogame_shop`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: videogame_shop
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(250) NOT NULL,
  `email` varchar(100) NOT NULL,
  `bank_account` varchar(250) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `deleted` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'username10','someemail@gmail.com','bankAccount2','$2a$10$Kk6fYPgP/YT3sUUsLGdv9OHu0BpA9PDB3sxV5Za5ubWr5up0BSVba',0),(17,'AwesomeName','email@gmail.com','bankAccount10','$2a$10$Kk6fYPgP/YT3sUUsLGdv9OHu0BpA9PDB3sxV5Za5ubWr5up0BSVba',0),(19,'username1','email','bank_account','$2a$10$kjk7UI.USi0vI/NCJXZRX.1mLzhZxw6FtXzv38a1iLzdZg9tjimRi',0),(20,'newUsername','newEmail','newBankAccount','$2a$10$a5EVLYSoGyCcmzRp0NxoCeY2cI3eyhWCuORPS/5UFwA6Ngva5Esm.',0),(41,'MyUsername','MyEmail1@gmail.com','MyBankAccount1','$2a$10$Z4Gzji.tNx65xtcma1wuD.o9TTvSthUiZr.1z1ZBjTyJyCsHgbVBa',0),(43,'MyUsername1','MyEmail','MyBankAccount','$2a$10$r6ToBF9aou/RKAGgGB78ruJD5NVdtIOZ7FKXJwsbb9BEM.ZuZgMnW',0),(45,'MyUsername3','viktor.skachkov01@gmail.com','bankAccount3','$2a$10$Vc4DkK8DRX0dPP3mNOqI6ODyFUPOdC/GtXfbB9d0fz7xYKM.fr4pC',0),(46,'MyNewUsername','mynewemail@gmail.com','MyNewBankAccount','$2a$10$MZgNGEwm7Svhv4frESmWQ.L/ecan8HS6J7ilESi7pyVyjaCYN4WXC',0);
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

-- Dump completed on 2023-05-27 11:04:13
