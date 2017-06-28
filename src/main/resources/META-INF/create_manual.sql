CREATE TABLE book
(
    id BIGINT PRIMARY KEY NOT NULL,
    description VARCHAR(2000),
    isbn VARCHAR(255),
    price REAL,
    title VARCHAR(255) NOT NULL
);
CREATE SEQUENCE book_id_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE book ALTER COLUMN id SET DEFAULT nextval('public.book_id_seq');
ALTER SEQUENCE public.book_id_seq OWNED BY public.book.id;

