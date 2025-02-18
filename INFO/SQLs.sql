
-- БАЗА ДАНИХ
-- Можливість створення БД з метою уникнення некваліфікованих
-- дій, краще залишити за розробником.
-- Тому такий функціонал у додатку не прописуємо.
CREATE DATABASE lesson42;

-- ТАБЛИЦІ
-- Можливість створення таблиць БД, з метою уникнення некваліфікованих
-- дій, краще залишити за розробником.
-- Тому такий функціонал у додатку не прописуємо.
-- Попередньо, необхідно спроектувати таблиці та їх зв'язки,
-- на основі сутностей реального світу.

CREATE TABLE IF NOT EXISTS products
( id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  in_stock BOOLEAN
);


INSERT INTO products
(name, description, in_stock)
VALUES
('Mango', 'A raw mango is 84% water, 15% carbohydrates.', true),
('Pear', 'Raw pear is 84% water, 15% carbohydrates.', false),
('Apricot', 'Raw apricots supply 48 Calories and are composed of 11% carbohydrates.', true),
('Apple', 'Raw apple is 86% water and 14% carbohydrates.', true),
('Watermelon', 'Watermelon fruit is 91% water, contains 6% sugars.', false),
('Avocado', 'Raw avocado flesh is 73% water, 15% fat.', true),
('Grape', 'Raw grapes are 81% water, 18% carbohydrates.', false),
('Orange', 'Orange flesh is 87% water, 12% carbohydrates.', true);


SELECT * FROM products
