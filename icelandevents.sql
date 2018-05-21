CREATE DATABASE  IF NOT EXISTS `accounts` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `accounts`;
-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: accounts
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
  `musicgenres` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=342 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (325,'Username','KBE Kynnir Herra Hnetusmjör','Hard Rock Café','2018-05-25','21:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.10469.jpg','Til að fagna opnun KBE Studios ætlar Herra Hnetusmjör að halda tónleika föstudaginn 25.maí  í tónleikasal Hard Rock Cafe (staðsettur undir Hard Rock).','Other'),(326,'Username','Reynir Hauksson – Flamenco','Reykholtskirkja, Borgarfirði','2018-05-29','20:30','https://d30qys758zh01z.cloudfront.net/images/medium/1.10472.jpg','Reynir býr í Granada, Spáni og starfar þar sem Flamenco gítarleikari. \r\nÞað heyrir til tíðinda að Flamenco tónlist sé flutt á Íslandi, svo sjaldgæft er það. \r\nDraumur hans er að kynna og tengja þessa mögnuðu list við Ísland.','Other'),(335,'Username','CCR Heiðurstónleikar','Bæjarbíó (Hafnarfirði)','2018-06-08','20:30','https://d30qys758zh01z.cloudfront.net/images/medium/1.10436.jpg','Það er okkur sönn ánægja að tilkynna Heiðurstónleika CCR Bandsins í Bæjarbíói Hafnarfjarðar föstudagskvöldið 8. Júní.\r\nStrákarnir í CCR Bandinu hafa það að aðalsmerki að heiðra hina mögnuðu sveit Creedence Clearwater Revival.\r\n\r\n','Other'),(336,'Username','Akureyri Dance Festival','Sjallinn (Akureyri)','2018-06-15','20:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.10470.jpg','Akureyri Events ehf. kynnir með stolti Akureyri Dance Festival, nýja raftónlistarhátíð sem haldin verður í Sjallanum á Akureyri dagana 15. og 16. júní 2018.','Other'),(339,'Username','Extreme Chill Festival 2018 ','Reykjavík','2018-09-06','21:00','https://d30qys758zh01z.cloudfront.net/images/medium/1.10457.jpg','Íslenska tónlistarhátíðin Extreme Chill Festival verður haldin 6-9 september næstkomandi og ætlum við að fagna því með því að bjóða upp á Early bird passa, 100 fyrstu passarnir á einstöku verði - 5900 Kr en fullt passa verð er 8900 kr.','Other'),(340,'Username','The Las Vegas Christmas ','Gamla Bíó Salur','2018-12-06','20:30','https://d30qys758zh01z.cloudfront.net/images/medium/1.10380.jpg','Þessir einstöku tónleikar eru nú orðnir fastur liður á aðventu landsmanna og hafa fengið feikna góða dóma fyrir frábæran flutning, skemmtanagildi og fagmennsku í alla staði. Geir sjálfur er frábær söngvari og skemmtikraftur.','Other');
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (23,'Username','$2a$10$k6Cu4N99sgsEvdiqqThXHeHNQYH2vRrEsOwijFhEflF5QQIzwFzIi',NULL,NULL,'User_Name','User_Email@gmail.com');
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
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role_roleid` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (23,1);
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

-- Dump completed on 2018-05-21  3:41:13
