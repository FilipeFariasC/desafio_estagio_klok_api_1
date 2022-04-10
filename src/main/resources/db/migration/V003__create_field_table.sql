CREATE TABLE IF NOT EXISTS t_field(
	field_id BIGSERIAL,
	name VARCHAR NOT NULL,
	required BOOLEAN NOT NULL DEFAULT false,
	product_id_fk BIGINT,
	
	CONSTRAINT field_pk PRIMARY KEY (field_id),
	CONSTRAINT product_id_fk_field FOREIGN KEY (product_id_fk) REFERENCES t_product (product_id)
);