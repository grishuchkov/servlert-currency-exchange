INSERT INTO currencies (id, code, full_name, sign)
VALUES (1, 'USD', 'United States dollar', '$'),
       (2, 'EUR', 'Euro', '€'),
       (3, 'RUB', 'Russian Ruble', '₽'),
       (4, 'ASD', 'Australian dollars', 'A$'),
       (5, 'CNY', 'Yuan Renminbi', '¥');


INSERT INTO exchange_rates(base_currency_id, target_currency_id, rate)
VALUES (1, 2, 0.99),
       (1, 3, 100),
       (3, 5, 12);