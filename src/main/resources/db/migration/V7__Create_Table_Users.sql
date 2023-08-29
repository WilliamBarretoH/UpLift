CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
--  `account_non_expired` bit(1) DEFAULT NULL,
--  `account_non_locked` bit(1) DEFAULT NULL,
--  `credentials_non_expired` bit(1) DEFAULT NULL,
--  `enabled` bit(1) DEFAULT NULL,
    `provider` VARCHAR(50) NOT NULL DEFAULT 'LOCAL',
    `provider_id` VARCHAR(255),
    CONSTRAINT `chk_provider` CHECK (`provider` IN ('GOOGLE', 'FACEBOOK', 'LOCAL')),
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_name` (`user_name`)
) ENGINE=InnoDB;