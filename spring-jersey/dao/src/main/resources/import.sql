-- SET statement_timeout = 0;
-- SET client_encoding = 'UTF8';
-- SET standard_conforming_strings = on;
-- SET check_function_bodies = false;
-- SET client_min_messages = warning;

CREATE TABLE category (
    id character varying(255) NOT NULL,
    name character varying(255)
);

CREATE TABLE product (
    id bigint NOT NULL,
    name character varying(255),
    price numeric(19,2),
    sku character varying(255)
);

CREATE TABLE product_x_category (
    product_id bigint NOT NULL,
    category_id character varying(255) NOT NULL
);


insert into category(id, name) values (1, 'Fruit');
insert into category(id, name) values (2, 'Dairy');

insert into product(id, name, price, sku) values (10, 'Apple', 0.59, 'product-10');
insert into product(id, name, price, sku) values (20, 'Milk', 2.99, 'product-20');

insert into product_x_category(product_id, category_id) values (10,1);
insert into product_x_category(product_id, category_id) values (20,2);

ALTER TABLE ONLY category ADD CONSTRAINT category_pkey PRIMARY KEY (id);
ALTER TABLE ONLY product ADD CONSTRAINT product_pkey PRIMARY KEY (id);

ALTER TABLE ONLY product_x_category ADD CONSTRAINT fk92a76235845e174d FOREIGN KEY (product_id) REFERENCES product(id);
ALTER TABLE ONLY product_x_category ADD CONSTRAINT fk92a76235fac7e767 FOREIGN KEY (category_id) REFERENCES category(id);
