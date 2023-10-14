INSERT INTO currencies (id, code, full_name, sign)
VALUES (1, 'USD', 'United States dollar', '$'),
       (2, 'EUR', 'Euro', '€'),
       (3, 'RUB', 'Russian Ruble', '₽'),
       (4, 'AUD', 'Australian dollars', 'A$'),
       (5, 'CNY', 'Yuan Renminbi', '¥');
ALTER SEQUENCE currencies_id_seq RESTART WITH 6;


INSERT INTO exchange_rates(id, base_currency_id, target_currency_id, rate)
VALUES (1, 1, 2, 0.941377),
       (2, 1, 3, 96.990000),
       (3, 1, 4, 1.560000),
       (4, 3, 5, 0.075555);
ALTER SEQUENCE exchange_rates_id_seq RESTART WITH 5;