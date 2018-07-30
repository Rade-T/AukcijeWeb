INSERT INTO items (name, description, picture, sold) VALUES ('Predmet', 'Neki predmet', '', false);
INSERT INTO items (name, description, picture, sold) VALUES ('Predmet2', 'Neki predmet 2', '', false);
INSERT INTO items (name, description, picture, sold) VALUES ('Predmet3', 'Neki predmet 3', '', false);
INSERT INTO items (name, description, picture, sold) VALUES ('Predmet4', 'Neki predmet 4', '', false);

INSERT INTO users (name, email, password, picture, address, phone, role) VALUES ('Pera Peric', 'pp@email.com', 'lozinka', '', 'Adresa', '123456', 'administrator');
INSERT INTO users (name, email, password, picture, address, phone, role) VALUES ('Mika Peric', 'mp@email.com', 'lozinka', '', 'Adresa', '123456', 'prodavac');
INSERT INTO users (name, email, password, picture, address, phone, role) VALUES ('Zika Peric', 'zp@email.com', 'lozinka', '', 'Adresa', '123456', 'ponudjac');

INSERT INTO auctions (start_price, start_date, end_date, user_id, item_id) VALUES (1000, '2017-01-01 00:00:00', '2017-02-02 00:00:00', 1, 1);
INSERT INTO auctions (start_price, start_date, end_date, user_id, item_id) VALUES (2000, '2017-02-02 00:00:00', '2017-03-03 00:00:00', 1, 1);
INSERT INTO auctions (start_price, start_date, end_date, user_id, item_id) VALUES (3000, '2016-04-04 00:00:00', '2017-05-05 00:00:00', 1, 1);
INSERT INTO auctions (start_price, start_date, end_date, user_id, item_id) VALUES (4000, '2017-06-06 00:00:00', '2017-06-06 00:00:00', 1, 1);


INSERT INTO bids (price, date_time, auction_id, user_id) VALUES (1200, '2017-01-03 00:00:00', 1, 1);
INSERT INTO bids (price, date_time, auction_id, user_id) VALUES (1300, '2017-01-04 00:00:00', 1, 1);
INSERT INTO bids (price, date_time, auction_id, user_id) VALUES (1400, '2017-01-05 00:00:00', 1, 1);