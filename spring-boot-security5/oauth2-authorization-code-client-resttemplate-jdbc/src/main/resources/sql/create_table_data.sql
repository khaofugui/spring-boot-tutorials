CREATE DATABASE if not exists `oauth2_client_db` default character set utf8mb4 collate utf8mb4_unicode_ci; /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
*/ /*!80016
DEFAULT ENCRYPTION='N' */;

-- oauth2_client_db.client_user definition
CREATE TABLE `client_user` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
   `password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
   `access_token` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   `validate_token_expire` datetime DEFAULT NULL,
   `refresh_token` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


INSERT INTO oauth2_client_db.client_user (username,password,access_token,validate_token_expire,refresh_token) VALUES
('hellxz','$2a$10$iAEfWxvrLKt6bhBdkNsdCeMn6JEqrS8c6MF/pbbeAeCxVrgkH92Re',NULL,NULL,NULL);