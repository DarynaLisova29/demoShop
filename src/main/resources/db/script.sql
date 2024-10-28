CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(20) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(50) NOT NULL UNIQUE,
                       age INTEGER CHECK (age >= 18)
);

CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          price NUMERIC(10, 2) NOT NULL,
                          url VARCHAR(255)
);

-- Початкові дані для користувачів
INSERT INTO users (username, password, email, age) VALUES
                                                       ('john_doe', 'password123', 'john@example.com', 25),
                                                       ('jane_doe', 'password456', 'jane@example.com', 30);

-- Початкові дані для продуктів
INSERT INTO products (name, price, url) VALUES
    ('Ручка синя', 25,'https://images.prom.ua/6023051329_w400_h400_ruchka-kulkova-dog.jpg'),
    ('Зошит в клітинку',33,'https://images.avrora.ua/images/detailed/23/36976_001.JPG'),
    ('Коректор',60,'https://images.prom.ua/3912739605_w640_h640_korektor-2-v.jpg');

INSERT INTO products(name, price, url) VALUES
    ('Шкільний щоденник',90,'https://images.prom.ua/4734446360_w700_h500_dnevnik-dlya-shkoly.jpg'),
    ('Папка з кнопкою',15,'https://image.maudau.com.ua/size/origin/products/93/03/47/9303471c-5de7-4000-a584-8f3a91e97169.jpg'),
    ('Планувальник блокнот',150,'https://a.allegroimg.com/original/1115fa/1a75c13447c4b04fe4ec54b3c6da/Notatnik-z-terminarzem-na-rok-2024-Planista-miesieczny-Profesjonalne-czasopisma-Notatnik-w-kolorze-rozowym'),
    ('Закладки пластикові',55,'https://www.officetime.com.ua/files/2018/10_18/08_24/u_files_store_1_587841.jpg'),
    ('Жовтий маркер',25,'https://ua.papirus.com.ua/img/goods/143081.jpg'),
    ('Оранжевий маркер',25,'https://ua.papirus.com.ua/img/goods/143082.jpg'),
    ('Рожевий маркер',25,'https://id.gifts/image/data/products/pen/227.jpg')
