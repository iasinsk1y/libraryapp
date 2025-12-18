INSERT INTO authors (name) VALUES ('Fyodor Dostoevsky'), ('Leo Tolstoy');
INSERT INTO categories (name) VALUES ('Classics'), ('Fiction');
INSERT INTO books (title, year, author_id) VALUES ('Crime and Punishment', 1866, 1), ('War and Peace', 1869, 2);
INSERT INTO book_categories (book_id, category_id) VALUES (1,1), (1,2), (2,1);
