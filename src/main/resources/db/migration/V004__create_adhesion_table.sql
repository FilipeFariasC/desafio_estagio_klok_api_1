CREATE TABLE IF NOT EXISTS t_adhesion (
	adhesion_id BIGSERIAL,
	product_id_fk BIGINT,
	aquisition_date DATE NOT NULL,
	charging_day INTEGER NOT NULL,
	number_of_installments INTEGER NOT NULL,
	amount NUMERIC(11,2) NOT NULL,
	product_id_fk_adhesion BIGINT,
	user_id_fk BIGINT,
	
	CONSTRAINT adhesion_pk PRIMARY KEY (adhesion_id),
	CONSTRAINT product_id_fk_adhesion FOREIGN KEY (product_id_fk_adhesion) REFERENCES t_product (product_id),
	CONSTRAINT user_id_fk_adhesion FOREIGN KEY (user_id_fk) REFERENCES t_user (user_id)
);