INSERT INTO rekening (iban, saldo) VALUES
	('NL81DIVA2208729564',10000000),
    ('NL66DIVA6110267617',10000),
    ('NL88DIVA4776153180',1000),
    ('NL87DIVA3876584607',10000);
    
INSERT INTO asset (afkorting, naam) VALUES
	('BTC', 'Bitcoin'),
    ('ETH', 'Ethereum'),
    ('BNB', 'Binance Coin'),
    ('SOL', 'Solana'),
    ('ADA', 'Cardano'),
    ('XRP', 'XRP'),
    ('DOT', 'Polkadot'),
    ('DOGE', 'Dogecoin'),
    ('AVAX', 'Avalanche'),
    ('LUNA', 'Terra'),
    ('LTC', 'Litecoin'),
    ('MATIC', 'Polygon'),
    ('ALGO', 'Algorand'),
    ('BCH', 'Bitcoin Cash'),
    ('XLM', 'Stellar'),
    ('VET', 'VeChain'),
    ('ICP', 'Internet Computer'),
    ('UST', 'TerraUSD'),
    ('EGLD', 'Elrond'),
    ('FIL', 'Filecoin');
    
INSERT INTO eurokoers (assetId, datum, koers) VALUES
	(1, '2021-12-07', 45329.81),
    (2, '2021-12-07', 3839.95),
    (3, '2021-12-07', 516.24),
    (4, '2021-12-07', 173.79),
    (5, '2021-12-07', 1.25),
    (6, '2021-12-07', 0.735),
    (7, '2021-12-07', 27.01),
    (8, '2021-12-07', 0.1602),
    (9, '2021-12-07', 82.25),
    (10, '2021-12-07', 62.66),
    (11, '2021-12-07', 144.88),
    (12, '2021-12-07', 2.13),
    (13, '2021-12-07', 1.51),
    (14, '2021-12-07', 426.06),
    (15, '2021-12-07', 0.256),
    (16, '2021-12-07', 0.08331),
    (17, '2021-12-07', 26.68),
    (18, '2021-12-07', 0.8909),
    (19, '2021-12-07', 261.70),
    (20, '2021-12-07', 35.14);
    
INSERT INTO adres (adresId, postcode, huisnummer, toevoeging) VALUES
    (1, '1051NJ',24,'a'),
    (2, '1061BB',354,NULL),
    (3, '1837AB',1,'b');
    
INSERT INTO transactiepartij (rekeningId, adresId, tpType, emailadres, wachtwoord, voornaam, tussenvoegsel, achternaam, bsn, geboortedatum, refreshToken, banknaam, transactiePercentage) VALUES
    (2,1,'klant','marieke@gmail.com','$2a$10$FdzlhHHLg2ciuK6WMK21TudBBzDu9ybfWdQVH96sko/AjvkhBa53.','Marieke','de','Vries','080772547','1983-07-05',NULL,NULL,NULL),
    (3,2,'klant','karin@gmail.com','$2a$10$lIfec2ciQfvZ/uR.9/dy2O6l.NgzCmgdgrudKI6DjBnOO4GWGeAKK','Karin',NULL,'Schapendonck','405981028','1990-06-20',NULL,NULL,NULL),
    (4,3,'klant','klaas@hotmail.com','$2a$10$dXYCL5Uspuo6xTG1twG4Ee0venM7DnKRUxNOtFjS71rvZSUlrwyHS','Klaas',NULL,'Bakker','022296992','2000-01-20',NULL,NULL,NULL);

INSERT INTO transactie (koperId, verkoperId, assetId, aantal, datum, tijdstip) VALUES
	(10,1,1,0.524,'2021-12-20','13:23:44'),
    (11,1,2,0.34,'2021-12-20','15:45:21'),
    (10,1,2,1.159,'2021-12-20','17:56:59');
    
INSERT INTO portefeuille_item (tpId, assetId, aantal) VALUES
	(1,1,1000),(1,2,1000),(1,3,1000),(1,4,1000),(1,5,1000),(1,6,1000),(1,7,1000),(1,8,1000),(1,9,1000),(1,10,1000),(1,11,1000),(1,12,1000),(1,13,1000),(1,14,1000),(1,15,1000),(1,16,1000),(1,17,1000),(1,18,1000),(1,19,1000),(1,20,1000),
	(10,1,0.524),(10,2,1.159),(10,3,0),(10,4,0),(10,5,0),(10,6,0),(10,7,0),(10,8,0),(10,9,0),(10,10,0),(10,11,0),(10,12,0),(10,13,0),(10,14,0),(10,15,0),(10,16,0),(10,17,0),(10,18,0),(10,19,0),(10,20,0),
    (11,1,0),(11,2,0.34),(11,3,0),(11,4,0),(11,5,0),(11,6,0),(11,7,0),(11,8,0),(11,9,0),(11,10,0),(11,11,0),(11,12,0),(11,13,0),(11,14,0),(11,15,0),(11,16,0),(11,17,0),(11,18,0),(11,19,0),(11,20,0),
    (12,1,0),(12,2,0),(12,3,0),(12,4,0),(12,5,0),(12,6,0),(12,7,0),(12,8,0),(12,9,0),(12,10,0),(12,11,0),(12,12,0),(12,13,0),(12,14,0),(12,15,0),(12,16,0),(12,17,0),(12,18,0),(12,19,0),(12,20,0);