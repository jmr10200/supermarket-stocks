CREATE TABLE `item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `item_name` VARCHAR(20) NOT NULL,
  `price` INT NOT NULL,
  `quantity` INT NOT NULL,
  `open` TINYINT NOT NULL DEFAULT 0, -- open y = 1, n = 0
  `expiration_date` DATE NOT NULL,
  `delivery` VARCHAR(10) NULL, -- FAST, NORMAL, SLOW
  `item_type` VARCHAR(15) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user` VARCHAR(60) NULL,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_user` VARCHAR(60) NULL,
  `version` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

CREATE TABLE `commercial_channel` (
  `id` BIGINT NOT NULL auto_increment,
  `item_id` BIGINT NOT NULL,
  `channel_name` VARCHAR(15) NOT NULL,
  `use_yn` VARCHAR(1) NOT NULL DEFAULT '0', -- yes = 1, no = 0
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user` VARCHAR(60) NULL,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_user` VARCHAR(60) NULL,
  `version` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_channel_name`(`item_id`, `channel_name`),
  FOREIGN KEY (`item_id`) REFERENCES `item`(`id`) ON DELETE CASCADE);

-- ALTER TABLE commercial_channel ADD UNIQUE idx_channel_name(item_id, channel_name);

CREATE TABLE `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login_id` varchar(30) NOT NULL,
  `password` varchar(64) NOT NULL,
  `name` varchar(60) NOT NULL,
  `mail_address` varchar(80) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `created_user` varchar(60) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updated_user` varchar(60) DEFAULT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_id_UNIQUE` (`login_id`));
