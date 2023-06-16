drop table if exists `autorzy_to_ksiazki`;
drop table if exists `ksiazki`;
drop table if exists `kategorie`;
drop table if exists `autorzy`;

CREATE TABLE `kategorie`
(
    `id`    int NOT NULL AUTO_INCREMENT,
    `nazwa` varchar(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `autorzy`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `imie`     varchar(30) DEFAULT NULL,
    `nazwisko` varchar(40)
                           DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `ksiazki`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `nazwa`        varchar(100) DEFAULT NULL,
    `wydawnictwo`  varchar(50)  DEFAULT NULL,
    `cena`         float        DEFAULT NULL,
    `kategoria_id` int          DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`kategoria_id`) REFERENCES `kategorie` (`id`)
);

CREATE TABLE `autorzy_to_ksiazki`
(
    `ksiazka_id` int NOT NULL,
    `autor_id`   int NOT NULL,
    primary key (`ksiazka_id`, `autor_id`),
    FOREIGN KEY (`ksiazka_id`) REFERENCES `ksiazki` (`id`),
    FOREIGN KEY (`autor_id`) REFERENCES `autorzy` (`id`)
);

INSERT INTO `kategorie`
VALUES (1, 'brak');
INSERT INTO `kategorie`
VALUES (2, 'Literatura polska');
INSERT INTO `kategorie`
VALUES (3, 'Literatura zagraniczna');

INSERT INTO `ksiazki`
VALUES (1, 'Hamlet', 'AAA', 6.5, 3);
INSERT INTO `ksiazki`
VALUES (2, 'Makbet', 'AAA', 6.8, 2);
INSERT INTO `ksiazki`
VALUES (3, 'Potop', 'BBB', 18.4, 2);
INSERT INTO `ksiazki`
VALUES (4, 'Quo vadis', 'BBB', 17.99, 2);
INSERT INTO `ksiazki`
VALUES (5, 'Pan Tadeusz', 'CCC', 13.78, 2);
INSERT INTO `ksiazki`
VALUES (6, 'Nad Niemnem', 'CCC', 15.45, 2);

INSERT INTO `autorzy` (`imie`, `nazwisko`)
VALUES ('William', 'Shakespeare'),
       ('Henryk', 'Sienkiewicz'),
       ('Adam', 'Mickiewicz'),
       ('Eliza', 'Orzeszkowa'),
       ('Jan', 'Kowalski');

INSERT INTO `autorzy_to_ksiazki`
VALUES (1, 1);
INSERT INTO `autorzy_to_ksiazki`
VALUES (1, 5);
INSERT INTO `autorzy_to_ksiazki`
VALUES (2, 1);
INSERT INTO `autorzy_to_ksiazki`
VALUES (2, 5);
INSERT INTO `autorzy_to_ksiazki`
VALUES (3, 2);
INSERT INTO `autorzy_to_ksiazki`
VALUES (4, 2);
INSERT INTO `autorzy_to_ksiazki`
VALUES (5, 3);
INSERT INTO `autorzy_to_ksiazki`
VALUES (6, 4);


drop table if exists `users`;
CREATE TABLE users
(
    username VARCHAR(25) NOT NULL,
    password VARCHAR(60) NOT NULL,
    enabled  BOOLEAN     NOT NULL DEFAULT TRUE,
    PRIMARY KEY (username)
);

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    UNIQUE KEY (`username` , `authority`),
    FOREIGN KEY (`username`)
        REFERENCES `users` (`username`)
);

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `id`        INT(10) NOT NULL AUTO_INCREMENT,
    `price`     FLOAT       DEFAULT NULL,
    `status`    VARCHAR(25) DEFAULT NULL,
    `date_time` TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    `username`  VARCHAR(25) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`username`)
        REFERENCES `users` (`username`)
);

drop table if exists zamowienia_to_ksiazki;
CREATE TABLE `zamowienia_to_ksiazki`
(
    `ksiazka_id` int(11) NOT NULL,
    `order_id`   int(10) NOT NULL,
    PRIMARY KEY (`order_id`, `ksiazka_id`),
    CONSTRAINT `FK_ORDER` FOREIGN KEY (`order_id`)
        REFERENCES `orders` (`id`),
    CONSTRAINT `FK_KSIAZKA` FOREIGN KEY (`ksiazka_id`)
        REFERENCES `ksiazki` (`id`)
);