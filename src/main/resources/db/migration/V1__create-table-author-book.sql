CREATE TABLE IF NOT EXISTS Author (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(150) NOT NULL,
	email VARCHAR(255) NOT NULL,
	birth_date DATE NOT NULL,
	mini_resume VARCHAR(255) NULL,
	PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Book (
	id BIGINT NOT NULL AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
	release_date DATE NOT NULL,
	number_of_pages INT NOT NULL,
	author_id BIGINT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (author_id) REFERENCES Author (id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
) ENGINE = InnoDB;
