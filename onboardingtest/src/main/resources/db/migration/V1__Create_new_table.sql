-- public.customer definition

-- Drop table

-- DROP TABLE public.customer;

CREATE TABLE public.customer (
	id int4 NOT NULL,
	customer_name varchar NULL,
	phone_number varchar NULL,
	email varchar NULL,
	address varchar NULL,
	CONSTRAINT customer_pk PRIMARY KEY (id)
);

-- public.product definition

-- Drop table

-- DROP TABLE public.product;

CREATE TABLE public.product (
	id int4 NOT NULL,
	"name" varchar NULL,
	qty int4 NULL,
	price int4 NULL,
	CONSTRAINT product_pk PRIMARY KEY (id)
);	

-- public.order_h definition

-- Drop table

-- DROP TABLE public.order_h;

CREATE TABLE public.order_h (
	id int4 NOT NULL,
	customer_name varchar NULL,
	customer_phone varchar NULL,
	customer_email varchar NULL,
	customer_address varchar NULL,
	shipping_id varchar NULL,
	order_status int4 NULL,
	CONSTRAINT order_h_pk PRIMARY KEY (id)
);

-- public.order_d definition

-- Drop table

-- DROP TABLE public.order_d;

CREATE TABLE public.order_d (
	row_detail_id int4 NOT NULL,
	id int4 NOT NULL,
	product_name varchar NULL,
	product_price int4 NULL,
	product_qty int4 NULL,
	product_total int4 NULL,
	CONSTRAINT order_d_pk PRIMARY KEY (row_detail_id),
	CONSTRAINT order_d_fk FOREIGN KEY (id) REFERENCES public.order_h(id)
);

-- public.payment definition

-- Drop table

-- DROP TABLE public.payment;

CREATE TABLE public.payment (
	payment_id int4 NOT NULL,
	order_id int4 NOT NULL,
	payment_value int4 NULL,
	CONSTRAINT payment_pk PRIMARY KEY (payment_id)
);