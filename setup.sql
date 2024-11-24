
CREATE DATABASE IF NOT EXISTS `family_tree_db` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `family_tree_db`;

CREATE USER IF NOT EXISTS 'user'@'localhost' IDENTIFIED BY 'user';

GRANT ALL PRIVILEGES ON `family_tree_db`.* TO 'user'@'localhost';

FLUSH PRIVILEGES;
