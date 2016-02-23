-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: restaurant_management
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (75,76),(75,77);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `friend_rating`
--

LOCK TABLES `friend_rating` WRITE;
/*!40000 ALTER TABLE `friend_rating` DISABLE KEYS */;
INSERT INTO `friend_rating` VALUES (1,3,76,0,0),(2,3,75,3,1),(3,3,77,0,0);
/*!40000 ALTER TABLE `friend_rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `invitation`
--

LOCK TABLES `invitation` WRITE;
/*!40000 ALTER TABLE `invitation` DISABLE KEYS */;
INSERT INTO `invitation` VALUES (1,75,16,1,NULL),(2,75,17,1,NULL),(3,75,18,1,4),(4,76,19,1,NULL),(5,76,20,1,NULL),(6,77,21,1,3),(7,75,22,1,NULL),(8,76,22,NULL,NULL),(9,77,22,0,NULL),(10,76,22,1,NULL),(11,77,22,NULL,NULL);
/*!40000 ALTER TABLE `invitation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,NULL,'2016-02-23 21:31:15','GET','/api/accessToken'),(2,NULL,'2016-02-23 21:31:39','POST','/api/login'),(3,68,'2016-02-23 21:31:39','GET','/api/restaurant'),(4,68,'2016-02-23 21:31:39','GET','/api/log'),(5,68,'2016-02-23 21:32:08','GET','/api/user/profile'),(6,68,'2016-02-23 21:32:12','PUT','/api/user/profile'),(7,68,'2016-02-23 21:32:12','GET','/api/user/profile'),(8,68,'2016-02-23 21:32:19','PUT','/api/user/profile'),(9,68,'2016-02-23 21:32:19','GET','/api/user/profile'),(10,68,'2016-02-23 21:32:21','GET','/api/restaurant'),(11,68,'2016-02-23 21:32:21','GET','/api/user'),(12,68,'2016-02-23 21:32:24','GET','/api/restaurant'),(13,68,'2016-02-23 21:32:50','GET','/api/restaurant/undefined/manager'),(14,NULL,'2016-02-23 21:34:31','GET','/api/accessToken'),(15,68,'2016-02-23 21:34:31','GET','/api/restaurant'),(16,NULL,'2016-02-23 21:36:30','GET','/api/accessToken'),(17,68,'2016-02-23 21:36:30','GET','/api/restaurant'),(18,NULL,'2016-02-23 21:39:22','GET','/api/accessToken'),(19,68,'2016-02-23 21:39:22','GET','/api/restaurant'),(20,NULL,'2016-02-23 21:41:14','GET','/api/accessToken'),(21,68,'2016-02-23 21:41:14','GET','/api/restaurant'),(22,NULL,'2016-02-23 21:42:00','GET','/api/accessToken'),(23,68,'2016-02-23 21:42:00','GET','/api/restaurant'),(24,NULL,'2016-02-23 21:43:06','GET','/api/accessToken'),(25,68,'2016-02-23 21:43:06','GET','/api/restaurant'),(26,NULL,'2016-02-23 21:43:53','GET','/api/accessToken'),(27,68,'2016-02-23 21:43:53','GET','/api/restaurant'),(28,68,'2016-02-23 21:45:19','POST','/api/restaurant'),(29,68,'2016-02-23 21:45:19','GET','/api/restaurant'),(30,68,'2016-02-23 21:45:37','POST','/api/restaurant'),(31,68,'2016-02-23 21:45:37','GET','/api/restaurant'),(32,68,'2016-02-23 21:45:51','POST','/api/restaurant'),(33,68,'2016-02-23 21:45:52','GET','/api/restaurant'),(34,68,'2016-02-23 21:46:02','POST','/api/restaurant'),(35,68,'2016-02-23 21:46:02','GET','/api/restaurant'),(36,68,'2016-02-23 21:46:17','GET','/api/restaurant/3/manager'),(37,68,'2016-02-23 21:46:39','POST','/api/restaurant/3/manager'),(38,68,'2016-02-23 21:46:39','GET','/api/restaurant/3/manager'),(39,68,'2016-02-23 21:46:54','POST','/api/restaurant/3/manager'),(40,68,'2016-02-23 21:46:54','GET','/api/restaurant/3/manager'),(41,68,'2016-02-23 21:46:59','PUT','/api/restaurant/3'),(42,68,'2016-02-23 21:47:14','GET','/api/restaurant/4/manager'),(43,68,'2016-02-23 21:47:25','POST','/api/restaurant/4/manager'),(44,68,'2016-02-23 21:47:25','GET','/api/restaurant/4/manager'),(45,68,'2016-02-23 21:47:33','GET','/api/restaurant/5/manager'),(46,68,'2016-02-23 21:47:46','POST','/api/restaurant/5/manager'),(47,68,'2016-02-23 21:47:46','GET','/api/restaurant/5/manager'),(48,68,'2016-02-23 21:48:00','POST','/api/restaurant/5/manager'),(49,68,'2016-02-23 21:48:00','GET','/api/restaurant/5/manager'),(50,68,'2016-02-23 21:48:13','POST','/api/restaurant/5/manager'),(51,68,'2016-02-23 21:48:17','POST','/api/restaurant/5/manager'),(52,68,'2016-02-23 21:48:17','GET','/api/restaurant/5/manager'),(53,68,'2016-02-23 21:48:23','GET','/api/user/profile'),(54,68,'2016-02-23 21:48:24','GET','/api/user'),(55,68,'2016-02-23 21:48:28','GET','/api/restaurant'),(56,68,'2016-02-23 21:48:29','GET','/api/user/profile'),(57,NULL,'2016-02-23 21:49:24','POST','/api/login'),(58,69,'2016-02-23 21:49:24','GET','/api/restaurant'),(59,69,'2016-02-23 21:49:26','GET','/api/restaurant'),(60,69,'2016-02-23 21:49:29','GET','/api/user/profile'),(61,69,'2016-02-23 21:49:30','GET','/api/restaurant'),(62,69,'2016-02-23 21:49:31','GET','/api/restaurant/3/meal'),(63,69,'2016-02-23 21:49:31','GET','/api/restaurant/3/table'),(64,69,'2016-02-23 21:49:47','POST','/api/restaurant/3/meal'),(65,69,'2016-02-23 21:49:47','GET','/api/restaurant/3/meal'),(66,69,'2016-02-23 21:50:03','POST','/api/restaurant/3/meal'),(67,69,'2016-02-23 21:50:03','GET','/api/restaurant/3/meal'),(68,69,'2016-02-23 21:50:06','PUT','/api/restaurant/meal/2'),(69,69,'2016-02-23 21:50:06','GET','/api/restaurant/3/meal'),(70,69,'2016-02-23 21:50:15','PUT','/api/restaurant/3'),(71,69,'2016-02-23 21:50:15','GET','/api/restaurant'),(72,69,'2016-02-23 21:50:17','GET','/api/restaurant/3/meal'),(73,69,'2016-02-23 21:50:17','GET','/api/restaurant/3/table'),(74,69,'2016-02-23 21:51:03','POST','/api/restaurant/3/table'),(75,69,'2016-02-23 21:51:03','GET','/api/restaurant/3/table'),(76,69,'2016-02-23 21:51:15','PUT','/api/restaurant/3'),(77,69,'2016-02-23 21:51:15','GET','/api/restaurant'),(78,69,'2016-02-23 21:51:17','GET','/api/restaurant/4/meal'),(79,69,'2016-02-23 21:51:17','GET','/api/restaurant/4/table'),(80,69,'2016-02-23 21:51:25','GET','/api/restaurant/3/meal'),(81,69,'2016-02-23 21:51:25','GET','/api/restaurant/3/table'),(82,NULL,'2016-02-23 21:51:43','GET','/api/accessToken'),(83,69,'2016-02-23 21:51:43','GET','/api/restaurant'),(84,NULL,'2016-02-23 21:52:07','POST','/api/oauth2'),(85,75,'2016-02-23 21:52:08','GET','/api/restaurant'),(86,75,'2016-02-23 21:52:08','GET','/api/invitation/visits'),(87,75,'2016-02-23 21:52:10','GET','/api/restaurant'),(88,75,'2016-02-23 21:52:24','POST','/api/reservation/tables'),(89,75,'2016-02-23 21:52:47','POST','/api/reservation'),(90,75,'2016-02-23 21:52:47','GET','/api/user/friend'),(91,75,'2016-02-23 21:52:51','GET','/api/restaurant'),(92,75,'2016-02-23 21:52:51','GET','/api/invitation/visits'),(93,75,'2016-02-23 21:52:56','GET','/api/restaurant'),(94,75,'2016-02-23 21:53:04','POST','/api/reservation/tables'),(95,75,'2016-02-23 21:53:29','POST','/api/reservation/tables'),(96,75,'2016-02-23 21:53:36','POST','/api/reservation'),(97,75,'2016-02-23 21:53:37','GET','/api/user/friend'),(98,75,'2016-02-23 21:53:40','GET','/api/restaurant'),(99,75,'2016-02-23 21:53:40','GET','/api/invitation/visits'),(100,75,'2016-02-23 21:53:51','GET','/api/restaurant'),(101,75,'2016-02-23 21:54:08','POST','/api/reservation/tables'),(102,75,'2016-02-23 21:54:16','POST','/api/reservation'),(103,75,'2016-02-23 21:54:16','GET','/api/user/friend'),(104,75,'2016-02-23 21:54:18','GET','/api/restaurant'),(105,75,'2016-02-23 21:54:18','GET','/api/invitation/visits'),(106,NULL,'2016-02-23 21:54:46','GET','/api/accessToken'),(107,75,'2016-02-23 21:54:46','GET','/api/restaurant'),(108,75,'2016-02-23 21:54:46','GET','/api/invitation/visits'),(109,75,'2016-02-23 21:54:49','POST','/api/invitation/3/rating'),(110,75,'2016-02-23 21:54:51','GET','/api/restaurant'),(111,75,'2016-02-23 21:54:53','GET','/api/restaurant'),(112,75,'2016-02-23 21:54:53','GET','/api/invitation/visits'),(113,NULL,'2016-02-23 21:58:29','POST','/api/signup'),(114,NULL,'2016-02-23 21:58:57','GET','/api/activate'),(115,NULL,'2016-02-23 21:59:08','POST','/api/login'),(116,76,'2016-02-23 21:59:08','GET','/api/restaurant'),(117,76,'2016-02-23 21:59:08','GET','/api/invitation/visits'),(118,76,'2016-02-23 21:59:13','GET','/api/restaurant'),(119,76,'2016-02-23 21:59:37','POST','/api/reservation/tables'),(120,76,'2016-02-23 22:00:26','POST','/api/reservation/tables'),(121,76,'2016-02-23 22:01:49','POST','/api/reservation/tables'),(122,76,'2016-02-23 22:02:19','POST','/api/reservation/tables'),(123,76,'2016-02-23 22:02:31','POST','/api/reservation'),(124,76,'2016-02-23 22:02:32','GET','/api/user/friend'),(125,76,'2016-02-23 22:02:33','GET','/api/invitation/visits'),(126,76,'2016-02-23 22:02:33','GET','/api/restaurant'),(127,76,'2016-02-23 22:02:38','GET','/api/restaurant'),(128,76,'2016-02-23 22:02:58','POST','/api/reservation/tables'),(129,76,'2016-02-23 22:03:04','POST','/api/reservation/tables'),(130,NULL,'2016-02-23 22:08:35','GET','/api/accessToken'),(131,76,'2016-02-23 22:08:41','POST','/api/reservation/tables'),(132,76,'2016-02-23 22:08:44','POST','/api/reservation'),(133,76,'2016-02-23 22:08:44','GET','/api/user/friend'),(134,76,'2016-02-23 22:08:46','GET','/api/invitation/visits'),(135,76,'2016-02-23 22:08:46','GET','/api/restaurant'),(136,76,'2016-02-23 22:08:47','GET','/api/restaurant'),(137,76,'2016-02-23 22:08:55','POST','/api/reservation/tables'),(138,76,'2016-02-23 22:09:40','POST','/api/reservation/tables'),(139,76,'2016-02-23 22:10:01','POST','/api/reservation/tables'),(140,76,'2016-02-23 22:11:09','POST','/api/reservation/tables'),(141,76,'2016-02-23 22:11:23','POST','/api/reservation/tables'),(142,NULL,'2016-02-23 22:12:24','GET','/api/accessToken'),(143,75,'2016-02-23 22:14:20','GET','/api/restaurant'),(144,76,'2016-02-23 22:14:30','POST','/api/reservation/tables'),(145,76,'2016-02-23 22:14:36','POST','/api/reservation/tables'),(146,76,'2016-02-23 22:17:22','POST','/api/reservation/tables'),(147,75,'2016-02-23 22:19:49','POST','/api/reservation/tables'),(148,NULL,'2016-02-23 22:20:59','GET','/api/accessToken'),(149,75,'2016-02-23 22:21:04','POST','/api/reservation/tables'),(150,75,'2016-02-23 22:21:13','POST','/api/reservation/tables'),(151,75,'2016-02-23 22:21:38','GET','/api/user/profile'),(152,75,'2016-02-23 22:21:38','GET','/api/user'),(153,75,'2016-02-23 22:21:44','GET','/api/restaurant'),(154,75,'2016-02-23 22:21:45','GET','/api/user/profile'),(155,75,'2016-02-23 22:21:45','GET','/api/user'),(156,NULL,'2016-02-23 22:24:08','GET','/api/accessToken'),(157,75,'2016-02-23 22:24:08','GET','/api/user/profile'),(158,75,'2016-02-23 22:24:08','GET','/api/user'),(159,75,'2016-02-23 22:24:12','GET','/api/restaurant'),(160,75,'2016-02-23 22:24:15','GET','/api/user/profile'),(161,75,'2016-02-23 22:24:15','GET','/api/user'),(162,75,'2016-02-23 22:24:16','POST','/api/user/friend/76'),(163,75,'2016-02-23 22:24:16','GET','/api/user'),(164,75,'2016-02-23 22:24:18','GET','/api/restaurant'),(165,NULL,'2016-02-23 22:24:24','GET','/api/accessToken'),(166,75,'2016-02-23 22:24:24','GET','/api/restaurant'),(167,75,'2016-02-23 22:24:26','GET','/api/user/profile'),(168,75,'2016-02-23 22:24:26','GET','/api/user'),(169,75,'2016-02-23 22:24:30','GET','/api/restaurant'),(170,75,'2016-02-23 22:24:30','GET','/api/invitation/visits'),(171,75,'2016-02-23 22:24:32','GET','/api/user/profile'),(172,75,'2016-02-23 22:24:32','GET','/api/user'),(173,76,'2016-02-23 22:25:09','GET','/api/user'),(174,76,'2016-02-23 22:25:09','GET','/api/user/profile'),(175,76,'2016-02-23 22:25:16','GET','/api/restaurant'),(176,76,'2016-02-23 22:25:18','GET','/api/user/profile'),(177,76,'2016-02-23 22:25:18','GET','/api/user'),(178,76,'2016-02-23 22:25:19','POST','/api/user/friend/75'),(179,76,'2016-02-23 22:25:19','GET','/api/user'),(180,76,'2016-02-23 22:25:20','GET','/api/restaurant'),(181,76,'2016-02-23 22:25:25','GET','/api/user'),(182,76,'2016-02-23 22:25:25','GET','/api/user/profile'),(183,76,'2016-02-23 22:25:26','DELETE','/api/user/friend/75'),(184,76,'2016-02-23 22:25:26','GET','/api/user'),(185,76,'2016-02-23 22:25:27','GET','/api/restaurant'),(186,76,'2016-02-23 22:25:33','GET','/api/restaurant/3/meal'),(187,76,'2016-02-23 22:25:33','GET','/api/restaurant/3/table'),(188,NULL,'2016-02-23 22:26:48','POST','/api/signup'),(189,NULL,'2016-02-23 22:26:54','POST','/api/login'),(190,NULL,'2016-02-23 22:29:18','POST','/api/login'),(191,NULL,'2016-02-23 22:29:25','GET','/api/activate'),(192,NULL,'2016-02-23 22:29:31','POST','/api/login'),(193,77,'2016-02-23 22:29:31','GET','/api/restaurant'),(194,77,'2016-02-23 22:29:31','GET','/api/invitation/visits'),(195,77,'2016-02-23 22:29:32','GET','/api/restaurant'),(196,77,'2016-02-23 22:29:43','POST','/api/reservation/tables'),(197,77,'2016-02-23 22:29:51','POST','/api/reservation'),(198,77,'2016-02-23 22:29:51','GET','/api/user/friend'),(199,77,'2016-02-23 22:29:53','GET','/api/invitation/visits'),(200,77,'2016-02-23 22:29:53','GET','/api/restaurant'),(201,NULL,'2016-02-23 22:30:20','GET','/api/accessToken'),(202,77,'2016-02-23 22:30:20','GET','/api/restaurant'),(203,77,'2016-02-23 22:30:20','GET','/api/invitation/visits'),(204,77,'2016-02-23 22:30:23','POST','/api/invitation/6/rating'),(205,75,'2016-02-23 22:30:25','GET','/api/restaurant'),(206,75,'2016-02-23 22:30:32','GET','/api/user/profile'),(207,75,'2016-02-23 22:30:32','GET','/api/user'),(208,75,'2016-02-23 22:30:36','POST','/api/user/friend/77'),(209,75,'2016-02-23 22:30:36','GET','/api/user'),(210,75,'2016-02-23 22:30:38','GET','/api/restaurant'),(211,77,'2016-02-23 22:30:43','GET','/api/restaurant'),(212,77,'2016-02-23 22:30:47','GET','/api/user/profile'),(213,77,'2016-02-23 22:30:47','GET','/api/user'),(214,77,'2016-02-23 22:30:49','POST','/api/user/friend/76'),(215,77,'2016-02-23 22:30:49','GET','/api/user'),(216,77,'2016-02-23 22:30:50','GET','/api/restaurant'),(217,75,'2016-02-23 22:30:53','GET','/api/user/profile'),(218,75,'2016-02-23 22:30:53','GET','/api/user'),(219,75,'2016-02-23 22:30:55','GET','/api/restaurant'),(220,77,'2016-02-23 22:30:56','GET','/api/user'),(221,77,'2016-02-23 22:30:56','GET','/api/user/profile'),(222,77,'2016-02-23 22:30:58','DELETE','/api/user/friend/76'),(223,77,'2016-02-23 22:30:58','GET','/api/user'),(224,77,'2016-02-23 22:30:59','POST','/api/user/friend/75'),(225,77,'2016-02-23 22:30:59','GET','/api/user'),(226,77,'2016-02-23 22:31:00','GET','/api/restaurant'),(227,77,'2016-02-23 22:31:01','GET','/api/user/profile'),(228,77,'2016-02-23 22:31:01','GET','/api/user'),(229,77,'2016-02-23 22:31:02','DELETE','/api/user/friend/75'),(230,77,'2016-02-23 22:31:02','GET','/api/user'),(231,77,'2016-02-23 22:31:03','GET','/api/restaurant'),(232,NULL,'2016-02-23 22:31:09','GET','/api/accessToken'),(233,75,'2016-02-23 22:31:09','GET','/api/restaurant'),(234,75,'2016-02-23 22:31:11','GET','/api/user/profile'),(235,75,'2016-02-23 22:31:11','GET','/api/user'),(236,75,'2016-02-23 22:31:15','GET','/api/restaurant'),(237,75,'2016-02-23 22:31:16','GET','/api/restaurant'),(238,75,'2016-02-23 22:31:16','GET','/api/invitation/visits'),(239,75,'2016-02-23 22:31:18','GET','/api/restaurant'),(240,75,'2016-02-23 22:31:30','POST','/api/reservation/tables'),(241,75,'2016-02-23 22:31:37','POST','/api/reservation'),(242,75,'2016-02-23 22:31:37','GET','/api/user/friend'),(243,75,'2016-02-23 22:31:46','POST','/api/reservation/22'),(244,75,'2016-02-23 22:32:47','POST','/api/reservation/22'),(245,NULL,'2016-02-23 22:33:19','GET','/api/accessToken'),(246,77,'2016-02-23 22:33:19','GET','/api/invitation/9/reservation'),(247,77,'2016-02-23 22:33:21','PUT','/api/invitation/9'),(248,77,'2016-02-23 22:33:21','GET','/api/invitation/visits'),(249,77,'2016-02-23 22:33:21','GET','/api/restaurant'),(250,NULL,'2016-02-23 22:33:35','GET','/api/accessToken'),(251,76,'2016-02-23 22:33:35','GET','/api/invitation/10/reservation'),(252,76,'2016-02-23 22:33:40','PUT','/api/invitation/10'),(253,76,'2016-02-23 22:33:40','GET','/api/invitation/visits'),(254,76,'2016-02-23 22:33:40','GET','/api/restaurant');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` VALUES (1,3,'Meal A1','Meal A1 description.',1000),(2,3,'Meal A2','Meal A2 description',2000);
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `oauth2_account`
--

LOCK TABLES `oauth2_account` WRITE;
/*!40000 ALTER TABLE `oauth2_account` DISABLE KEYS */;
INSERT INTO `oauth2_account` VALUES (1,75,'GP','104421600167073042334');
/*!40000 ALTER TABLE `oauth2_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (16,75,3,'2016-02-24 08:00:00','2016-02-24 10:59:59'),(17,75,3,'2016-02-24 09:00:00','2016-02-24 10:59:59'),(18,75,3,'2016-02-22 22:00:00','2016-02-22 23:59:59'),(19,76,3,'2016-02-24 17:00:00','2016-02-24 18:59:59'),(20,76,3,'2016-02-25 17:00:00','2016-02-25 18:59:59'),(21,77,3,'2016-02-21 17:00:00','2016-02-21 17:59:59'),(22,75,3,'2016-02-24 17:00:00','2016-02-24 17:59:59');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `resevated_table`
--

LOCK TABLES `resevated_table` WRITE;
/*!40000 ALTER TABLE `resevated_table` DISABLE KEYS */;
INSERT INTO `resevated_table` VALUES (15,16),(16,16),(19,16),(20,16),(24,16),(25,16),(26,16),(17,17),(21,17),(27,17),(28,17),(14,18),(15,18),(18,18),(19,18),(22,18),(23,18),(24,18),(31,19),(32,19),(33,19),(37,19),(38,19),(41,19),(42,19),(25,20),(32,20),(19,21),(24,21),(25,21),(26,21),(27,22),(34,22);
/*!40000 ALTER TABLE `resevated_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (3,'Restoran A','Opis restorana A',7,2,45.254301545772414,19.841480255126953,7,7,4),(4,'Restoran B','Opis restorana B.',0,0,45.25386348826584,19.850428104400635,0,0,0),(5,'Restoran Z','Opis restorana Z.',0,0,45.255,19.844722,0,0,0),(6,'Restoran H',NULL,0,0,45.255,19.844722,0,0,0);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `table`
--

LOCK TABLES `table` WRITE;
/*!40000 ALTER TABLE `table` DISABLE KEYS */;
INSERT INTO `table` VALUES (14,3,'1',0,0),(15,3,'3',0,2),(16,3,'5',0,4),(17,3,'7',0,6),(18,3,'8',1,0),(19,3,'10',1,2),(20,3,'12',1,4),(21,3,'14',1,6),(22,3,'15',2,0),(23,3,'16',2,1),(24,3,'17',2,2),(25,3,'18',2,3),(26,3,'19',2,4),(27,3,'20',2,5),(28,3,'21',2,6),(29,3,'29',4,0),(30,3,'30',4,1),(31,3,'31',4,2),(32,3,'32',4,3),(33,3,'33',4,4),(34,3,'34',4,5),(35,3,'35',4,6),(36,3,'36',5,0),(37,3,'38',5,2),(38,3,'40',5,4),(39,3,'42',5,6),(40,3,'43',6,0),(41,3,'45',6,2),(42,3,'47',6,4),(43,3,'49',6,6);
/*!40000 ALTER TABLE `table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,68,'okc9ti21pqv6vg5dp8cddpkim2fp5g2culj2hm1i8pc34jq5tccrf9ppud8nfjgoi4u0g177am2ts8qrvv6rlge3bohm9homk0bq4i9rhkom2vn5blntgknchjudpuag',NULL,'AT'),(2,69,'43iv382ca2nd1aqqh435s4rb4v9e00i5hh00146d9afqgu41a48uouqup1msrop4904ijg878b16h2n2tlbddggr8j42hdrrca1nf3fi0vi1r87t1h8ouueqls9ea3qr',NULL,'AT'),(3,75,'kn8mj9kch0pd0qdb9m5i91m67b5em9unv5s7v301927eu167fjjgtbgjvitn3h816o663e5hla56quklpa6sa5o21245bm634q829t26uln7imlsrbvuk5945v7r10pt',NULL,'CR'),(4,75,'1cp8ci5vtnn5c5421a35rvecki7v6ra9kfra22e71ipf74e9r6idj20h6krj15m6n1999ek28pphue8gp1btkrq3e6m8nf1hqk2lqt6q5tb01hkq3t04ntq6pq746l46',NULL,'AT'),(5,76,'prkc0kqga6grlt4qlf7sqn4t24a50tpem00li8at5je8cachf891m9adacjnm0i03r0tuhag5tjruo94effsqokjbff0hiidbs9vm8qf32m7dg4rfqmrtpmftn2gbc2',NULL,'CR'),(6,76,'m6td9ofuaiqjdrp27nm4urftnigeitf0lgqb1onmhta9k6mntfsnlte7fdi9fo439aubqe204dr39l4rk52390j464kbf99jjubbis5d3i8ovd2g69q5v78m9g73aqfn',NULL,'AT'),(7,77,'oqf5v2johv9ce4es25uime6ppa5qm6kld49vp8p9gdkcqovl2cjagrvvitne555192js1i1on5q600f70kjagnk1u1i6cu3kb01e88alae4ts02vr9k4190d96mnlvnp',NULL,'CR'),(8,77,'7hbvvfm03us4epst94j1eqj9co7sjnjcfep7q755c74o960mrog2bbt1odahurlt743ah3kdpn2orjmde4lclnqq838qhufetj2m3e5h336e62md4to2mvt0phmgrkgi',NULL,'AT'),(9,76,'d43tadchi2qtek24nisu8bci27ci6h5tlnc1dpn6vgf6vojln29n23n81hkrpg6kjrtreqpfpccn9qg4m3f6u3adhrrdom0t5ilctg10bi2cmn4h1r9q2kj3u4ev6ms',NULL,'AT'),(10,77,'83a6f09cp53jeo4ir1pq8qti4qimavb4m12kvaulpd61mr4m2lj4ltk5gfabv6qdpk75mtvgle485ae3mqmqfdrmjbb6eleactm61jv95vsqd35rr7aja9fagkl953r2',NULL,'AT'),(11,76,'lfbs3e8gedf7vhrinkgjpgthv3kqh0egftmmba73dmfdd37tij3q6nsnr3a771i9dkh871oqig577buibs5jfi5tjnem2a2idl4gf5rc0vfk12jm9vr9buepakapjk56',NULL,'AT'),(12,77,'4vj804uve88j96q9i4ajbn78ki26e0sr41qt9608t4nn9aebv331v8ejh7jtl6fbf6j7bu1jfj96jc87pttljn0j6euka6i0nh1tn23l55ppjhcnj8h98jur88m7fue7',NULL,'AT');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (68,NULL,'admin@isa.com','test','administrator',1,'Marko','Ivetic',0),(69,3,'man_a1@isa.com','f21h06qda9af','manager',1,'Man','A1',0),(70,3,'man_a2@isa.com','j2svc7a445lp','manager',1,'Man','A2',0),(71,4,'man_b1@isa.com','6qgnve9hait0','manager',1,'Man','B1',0),(72,5,'man_c1@isa.com','oe1dujpkijq9','manager',1,'Man','C1',0),(73,5,'man_c2@isa.com','mchmfh524naj','manager',1,'Man','C2',0),(74,5,'man_c3@isa.com','g3mo9jsuoc39','manager',1,'Man','C3',0),(75,NULL,'srkixxl@gmail.com','oj66nb5394v7','customer',1,'Srđan','Milaković',1),(76,NULL,'srki.accounts@outlook.com','testSM','customer',1,'Srđan','Milaković',0),(77,NULL,'srki.subscriptions@outlook.com','test','customer',1,'Srki','Subscriptions',1);
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

-- Dump completed on 2016-02-23 22:37:30
