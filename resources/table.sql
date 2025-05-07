CREATE TABLE kafka_test.orders (
	id serial4 NOT NULL PRIMARY KEY,
	product varchar(255) NULL,
	amount float4 NULL,
	last_update timestamp NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id)
);