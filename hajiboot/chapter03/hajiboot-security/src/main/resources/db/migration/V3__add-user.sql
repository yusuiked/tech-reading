CREATE TABLE users (username VARCHAR(100) NOT NULL PRIMARY KEY, encoded_password VARCHAR(255));
INSERT INTO users (username, encoded_password) VALUES ('user1', '$2a$10$Thp48drWZCpAEs4gm3QvB.lo.JXkwjooTIArHZFmIpMVrpF7GT9s2');
INSERT INTO users (username, encoded_password) VALUES ('user2', '$2a$10$3yo2PT7CEpWBurDuHgOvIOb/Yl0qWtymzJpBiIYf8pdwiHlsPhera');
ALTER TABLE customers ADD username VARCHAR(100) NOT NULL DEFAULT 'user1';
ALTER TABLE customers ADD CONSTRAINT FK_CUSTOMERS_USERNAME FOREIGN KEY (username) REFERENCES users;
