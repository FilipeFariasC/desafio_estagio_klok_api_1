CREATE TABLE IF NOT EXISTS reply (
	reply_id BIGSERIAL,
	value VARCHAR NOT NULL,
	field_id_fk_reply BIGINT,
	adhesion_id_fk BIGINT,
	
	CONSTRAINT reply_pk PRIMARY KEY (reply_id),
	CONSTRAINT field_id_fk_reply FOREIGN KEY (field_id_fk_reply) REFERENCES t_field (field_id),
	CONSTRAINT adhesion_id_fk_reply FOREIGN KEY (adhesion_id_fk) REFERENCES t_adhesion (adhesion_id)
);