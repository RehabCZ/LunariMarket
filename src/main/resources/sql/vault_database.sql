CREATE TABLE IF NOT EXISTS `vaults` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID of the vault',
	`vault_owner` VARCHAR(16) NOT NULL COMMENT 'Vault owner',
	`location_x` FLOAT NOT NULL COMMENT 'X location of the vault',
	`location_y` FLOAT NOT NULL COMMENT 'Y location of the vault',
	`location_z` FLOAT NOT NULL COMMENT 'Z location of the vault',
	`items` JSON NOT NULL COMMENT 'Items in vault',
	PRIMARY KEY (`id`)
);