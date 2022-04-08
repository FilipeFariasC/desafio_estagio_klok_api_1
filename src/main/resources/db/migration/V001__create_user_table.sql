
CREATE TABLE IF NOT EXISTS t_user (
	user_id BIGSERIAL,
	username VARCHAR NOT NULL,
	password VARCHAR NOT NULL,
	cpf VARCHAR NOT NULL,
	birth_date DATE,
	gender VARCHAR,
	marital_status VARCHAR,
	
	CONSTRAINT user_primary_key PRIMARY KEY (user_id),
	CONSTRAINT user_username_unique UNIQUE (username)
);
