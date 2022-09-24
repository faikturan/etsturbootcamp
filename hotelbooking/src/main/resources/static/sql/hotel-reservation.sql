DROP DATABASE IF EXISTS `hotel-reservation`;
CREATE DATABASE `hotel-reservation`;
USE `hotel-reservation`;


DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
                               `reservation_id` int NOT NULL AUTO_INCREMENT,
                               `reservation_from_date` datetime(6) DEFAULT NULL,
                               `reservation_num_of_children` int DEFAULT NULL,
                               `reservation_open_buffet` varchar(255) DEFAULT NULL,
                               `reservation_num_of_persons` int DEFAULT NULL,
                               `reservation_price` int DEFAULT NULL,
                               `reservation_room` varchar(255) DEFAULT NULL,
                               `reservation_num_of_rooms` int DEFAULT NULL,
                               `reservation_stay_days` int DEFAULT NULL,
                               `reservation_user_id` int DEFAULT NULL,
                               PRIMARY KEY (`reservation_id`),
                               KEY `FKdwl5vjb9x3rm7pjgyamd9ge8e` (`reservation_user_id`),
                               CONSTRAINT `FKdwl5vjb9x3rm7pjgyamd9ge8e` FOREIGN KEY (`reservation_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `reservation` WRITE;
UNLOCK TABLES;


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `role_id` bigint NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (1,'ROLE_EMPLOYEE'),(2,'ROLE_MANAGER'),(3,'ROLE_ADMIN');
UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `user_id` int NOT NULL AUTO_INCREMENT,
                        `user_username` varchar(45) DEFAULT NULL,
                        `user_email` varchar(45) DEFAULT NULL,
                        `user_password` varchar(60) DEFAULT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `client_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
                               `user_id` int NOT NULL,
                               `role_id` bigint NOT NULL,
                               KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
                               KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `users_roles` WRITE;
INSERT INTO `users_roles` VALUES (35,1);
UNLOCK TABLES;