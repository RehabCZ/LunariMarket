CREATE TABLE IF NOT EXISTS `markets` (
	`id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID of the market',
	`market_owner` VARCHAR(16) NOT NULL COMMENT 'Owner of the market',
	`location_x` FLOAT NOT NULL COMMENT 'X location of the market',
	`location_y` FLOAT NOT NULL COMMENT 'Y location of the market',
	`location_z` FLOAT NOT NULL COMMENT 'Z location of the market',
	`sell_item` JSON NOT NULL COMMENT 'Item to sell in market',
	`price_item` JSON NOT NULL COMMENT 'Price item of market',
	`stock_amount` INT NOT NULL COMMENT 'Amount of items in stock',
	`date_created` DATE NOT NULL COMMENT 'Date the market was created',
	PRIMARY KEY (`id`)
);