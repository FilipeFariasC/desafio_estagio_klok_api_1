CREATE TABLE IF NOT EXISTS t_product (
	product_id BIGSERIAL,
	name VARCHAR NOT NULL,
	CONSTRAINT product_pk PRIMARY KEY (product_id)
);