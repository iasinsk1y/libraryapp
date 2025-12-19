CREATE TABLE t_category (
    id BIGSERIAL PRIMARY KEY,
    year INTEGER NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE t_book (
    id BIGSERIAL PRIMARY KEY,
    pages INTEGER NOT NULL,
    title VARCHAR(255)
);

CREATE TABLE t_author (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    age INTEGER NOT NULL
);

CREATE TABLE t_author_book (
    author_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    PRIMARY KEY (author_id, book_id),
    FOREIGN KEY (author_id) REFERENCES t_author(id),
    FOREIGN KEY (book_id) REFERENCES t_book(id)
);

ALTER TABLE t_author
ADD COLUMN category_id BIGINT;

ALTER TABLE t_author
ADD CONSTRAINT FK_author_category FOREIGN KEY (category_id) REFERENCES t_category(id);
