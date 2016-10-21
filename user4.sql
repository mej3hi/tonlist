CREATE DATABASE  IF NOT EXISTS `accounts` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `accounts`;
-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: accounts
-- ------------------------------------------------------
-- Server version	5.7.15-0ubuntu0.16.04.1

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
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `location` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `imageurl` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `description` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (87,'maggiblo','mamamam','loka@loka.is','2016-10-13','12:00','static/images/blabla.jpg',NULL),(108,'12345678','ldldld','lkfsldkflsd@dsdf.com','2016-10-20','17:00','static/images/Screenshot from 2016-08-22 11-52-57.png',NULL),(129,'midi','Gulli Briem og Earth Affair','Gamla Bíó Salur','2016-10-20','20:29','https://d30qys758zh01z.cloudfront.net/images/medium/1.9729.jpg',NULL),(130,'midi','Gulli Briem og Earth Affair','Gamla Bíó','2016-10-20','20:30','https://d30qys758zh01z.cloudfront.net/images/medium/1.9729.jpg',NULL),(131,'midi','Moji and the Midnight Sons','Græni Hatturinn (Akureyri)','2016-10-20','21:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9813.jpg',NULL),(132,'midi','Örlítið meiri diskant','Græni Hatturinn (Akureyri)','2016-10-21','21:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9736.jpg',NULL),(133,'midi','Guitarama 2016','Háskólabíó','2016-10-22','20:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9709.jpg',NULL),(134,'midi','Örlítið meiri diskant','Græni Hatturinn (Akureyri)','2016-10-22','21:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9736.jpg',NULL),(135,'midi','HÁTÍÐARTÓNLEIKAR - 30 ára vígsluafmæli Hallgrímskirkju','Hallgrímskirkja','2016-10-29','19:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9788.jpg',NULL),(136,'midi','Eyþór Ingi - Tónleikar','Höllin (Vestmannaeyjum)','2016-10-29','20:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9769.jpg',NULL),(137,'midi','HÁTÍÐARTÓNLEIKAR - 30 ára vígsluafmæli Hallgrímskirkju','Hallgrímskirkja','2016-10-30','17:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9788.jpg',NULL),(138,'midi','Sigga Eyrún og Hvíta Kanínan','Græna Herbergið','2016-11-02','21:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9803.jpg',NULL),(139,'midi','Shostakovich - Beethoven nútímans','Hljóðberg (Hannesarholt)','2016-11-05','14:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9811.jpg',NULL),(140,'midi','Todmobile','Græni Hatturinn (Akureyri)','2016-11-05','20:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9792.jpg',NULL),(141,'midi','Todmobile','Græni Hatturinn (Akureyri)','2016-11-05','23:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9792.jpg',NULL),(142,'midi','A History of Film Music','Tjarnarbíó','2016-11-07','20:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9812.jpg',NULL),(143,'midi','Baraflokkurinn','Græni Hatturinn (Akureyri)','2016-11-10','21:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9786.jpg',NULL),(144,'midi','Requiem Verdis','Langholtskirkja','2016-11-11','20:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9820.jpg',NULL),(145,'midi','Axel O','Græni Hatturinn (Akureyri)','2016-11-12','22:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9815.jpg',NULL),(146,'midi','Eftirlætis ljóðasöngvar','Hljóðberg (Hannesarholt)','2016-11-19','13:30','https://d30qys758zh01z.cloudfront.net/images/medium/1.9732.jpg',NULL),(147,'midi','Moses Hightower','Græni Hatturinn (Akureyri)','2016-11-24','21:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9797.jpg',NULL),(148,'midi','Hjálmar','Græni Hatturinn (Akureyri)','2016-11-25','22:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.9714.jpg',NULL),(149,'12345678','dfsdfdsf','dfdddf','2016-10-20','17:00','static/images/model.jpg','gvfgdfgdgfdgdfgdf'),(150,'12345678','dsfsdfdf','dfdfdfd','2016-10-20','17:00','static/images/Screenshot from 2016-08-22 11-52-57.png','ssdsdfdsfsdf'),(151,'12345678','rrtert','trtrrr','2016-10-20','17:00','static/images/Screenshot from 2016-08-22 11-52-57.png','sadasdasds'),(152,'12345678','dasdasd','sdsdsd','2016-10-20','17:00','static/images/Screenshot from 2016-08-22 11-52-57.png','sadasdasd'),(153,'12345678','asdasd','sdsadasd','2016-10-20','17:00','static/images/Screenshot from 2016-08-22 11-52-57.png','sadsdasda'),(154,'12345678','sdasdasd','sadasdas','2016-10-20','17:00','static/images/Screenshot from 2016-08-22 11-52-57.png','adasdasd');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `resetpasswordtoken` varchar(255) DEFAULT NULL,
  `resetpasswordexpires` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'12345678','$2a$10$KTTRq757qcrBaEBMjyr3zOPjkqJ/2UiaPV.UlP/ls7vjoEijIdGjG',NULL,NULL,NULL,NULL),(5,'mej3123456','$2a$10$9b1DE.ufzDA8xrRuN.OCA.OinzbYHaRs/MgYtLhRr33KXzZ2jGCzy',NULL,NULL,'martin Einar','jensenmej3@gmail.com'),(6,'maggi1','$2a$10$6ZhMhU4zQ9RLmWJFDr7w9eiM6pozmg9gZmH0rZLYwHHl93lm6AJCS',NULL,NULL,'maggi 1','marrtrt@gfgf.com'),(7,'maggiblo','$2a$10$9MhE6J4mPsliHuXflJW1weaxkI/.d/Q.EDuUOg4f2JP7gkxUCIY/q',NULL,NULL,'maggiblo','mb2@hi.is'),(8,'mej31234','$2a$10$IX0oWZiRhuObSNpIyH/t9.9TaMhYu7CPAg6A0DblUDFqDQIiM4216',NULL,NULL,'martin 1','mej3@hi.is');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_role_roleid_idx` (`role_id`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (4,1),(5,1),(6,1),(7,1),(8,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-21 16:28:45
