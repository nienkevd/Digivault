INSERT INTO naam (voornaam, tussenvoegsel, achternaam) VALUES
	('Annie','de','Rooij'),
    ('Marieke','de','Vries'),
    ('Karin',NULL,'Schapendonck'),
    ('Klaas',NULL,'Bakker');
    
INSERT INTO adres (straat, huisnummer, toevoeging, postcode, woonplaats) VALUES
	('Ceramstraat',6,NULL,'1782CC','Den  Helder'),
    ('Van Galenstraat',24,'a','1051NJ','Amsterdam'),
    ('Marcantilaan',354,NULL,'1061BB','Amsterdam'),
    ('Mediapark',1,'b','1837AB','Amsterdam');

INSERT INTO rekening (iban, saldo) VALUES
	('NL19DIVA0000876951',10000),
    ('NL20DIVA0000876952',10000),
    ('NL21DIVA0000876953',1000),
    ('NL22DIVA0000876954',10000);

INSERT INTO account (emailadres, wachtwoord) VALUES
	('annie@gmail.com','$2a$10$.2TNjKLf6FxIECt6C6jsb.66Gc.k60eHSnTyFn24QgL7j1v22OTLm'),
    ('marieke@gmail.com','$2a$10$FdzlhHHLg2ciuK6WMK21TudBBzDu9ybfWdQVH96sko/AjvkhBa53.'),
    ('karin@gmail.com','$2a$10$lIfec2ciQfvZ/uR.9/dy2O6l.NgzCmgdgrudKI6DjBnOO4GWGeAKK'),
    ('klaas@hotmail.com','$2a$10$dXYCL5Uspuo6xTG1twG4Ee0venM7DnKRUxNOtFjS71rvZSUlrwyHS');

INSERT INTO asset (afkorting, naam, dagkoers) VALUES
	('BTC', 'Bitcoin', 45329.81),
    ('ETH', 'Ethereum', 3839.95),
    ('BNB', 'Binance Coin', 516.24),
    ('SOL', 'Solana', 173.79),
    ('ADA', 'Cardano', 1.25),
    ('XRP', 'XRP', 0.735),
    ('DOT', 'Polkadot', 27.01),
    ('DOGE', 'Dogecoin', 0.1602),
    ('AVAX', 'Avalanche', 82.25),
    ('LUNA', 'Terra', 62.66),
    ('LTC', 'Litecoin', 144.88),
    ('MATIC', 'Polygon', 2.13),
    ('ALGO', 'Algorand', 1.51),
    ('BCH', 'Bitcoin Cash', 426.06),
    ('XLM', 'Stellar', 0.256),
    ('VET', 'VeChain', 0.08331),
    ('ICP', 'Internet Computer', 26.68),
    ('UST', 'TerraUSD', 0.8909),
    ('EGLD', 'Elrond', 261.70),
    ('FIL', 'Filecoin', 35.14);

INSERT INTO eurokoers (datum, koers, assetId) VALUES
	('2021-12-07', 45329.81, 1),
    ('2021-12-07', 3839.95, 2),
    ('2021-12-07', 516.24, 3),
    ('2021-12-07', 173.79, 4),
    ('2021-12-07', 1.25, 5),
    ('2021-12-07', 0.735, 6),
    ('2021-12-07', 27.01, 7),
    ('2021-12-07', 0.1602, 8),
    ('2021-12-07', 82.25, 9),
    ('2021-12-07', 62.66, 10),
    ('2021-12-07', 144.88, 11),
    ('2021-12-07', 2.13, 12),
    ('2021-12-07', 1.51, 13),
    ('2021-12-07', 426.06, 14),
    ('2021-12-07', 0.256, 15),
    ('2021-12-07', 0.08331, 16),
    ('2021-12-07', 26.68, 17),
    ('2021-12-07', 0.8909, 18),
    ('2021-12-07', 261.70, 19),
    ('2021-12-07', 35.14, 20);

INSERT INTO klant (bsn, geboortedatum, naamId, adresId, rekeningId, accountId) VALUES
	('123456789','1980-05-27',1,1,1,1),
	('123789456','1983-07-05',2,2,2,2),
	('456789123','1990-06-20',3,3,3,3),
	('789123456','2000-01-20',4,4,4,4);

INSERT INTO portefeuille_item (aantal,klantId,assetId) VALUES
	(0, 1, 1),(0, 1, 2),(0, 1, 3),(0, 1, 4),(0, 1, 5),(0, 1, 6),(0, 1, 7),(0, 1, 8),(0, 1, 9),(0, 1, 10),(0, 1, 11),(0, 1, 12),(0, 1, 13),(0, 1, 14),(0, 1, 15),(0, 1, 16),(0, 1, 17),(0, 1, 18),(0, 1, 19),(0, 1, 20),
	(0, 2, 1),(0, 2, 2),(0, 2, 3),(0, 2, 4),(0, 2, 5),(0, 2, 6),(0, 2, 7),(0, 2, 8),(0, 2, 9),(0, 2, 10),(0, 2, 11),(0, 2, 12),(0, 2, 13),(0, 2, 14),(0, 2, 15),(0, 2, 16),(0, 2, 17),(0, 2, 18),(0, 2, 19),(0, 2, 20),
    (0.134, 3, 1),(0.541, 3, 2),(0.582, 3, 3),(0.864, 3, 4),(0.915, 3, 5),(0.493, 3, 6),(0.679, 3, 7),(0.349, 3, 8),(0.369, 3, 9),(0.333, 3, 10),(0.459, 3, 11),(0.378, 3, 12),(0.397, 3, 13),(0.888, 3, 14),(0.598, 3, 15),(0.456, 3, 16),(0.377, 3, 17),(0.844, 3, 18),(0.955, 3, 19),(0.655, 3, 20),
    (0, 4, 1),(0, 4, 2),(0, 4, 3),(0, 4, 4),(0, 4, 5),(0, 4, 6),(0, 4, 7),(0, 4, 8),(0, 4, 9),(0, 4, 10),(0, 4, 11),(0, 4, 12),(0, 4, 13),(0, 4, 14),(0, 4, 15),(0, 4, 16),(0, 4, 17),(0, 4, 18),(0, 4, 19),(0, 4, 20);