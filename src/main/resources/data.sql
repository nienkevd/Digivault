INSERT INTO rekening (iban, saldo) VALUES ('NL19DIVAB00876951',809.00),('NL20DIVAB00876952',100020.00),('NL21DIVAB00876953',20.00),('NL22DIVAB00876954',2321.00);

INSERT INTO naam (voornaam, tussenvoegsel, achternaam) VALUES ('Annie','de','Rooij'),('Marieke','de','Vries'),('Karin',NULL,'Schapendonck'),('Klaas',NULL,'Bakker');

INSERT INTO ziphuisnr (straat, woonplaats) VALUES ('Ceramstraat','Den  Helder'),('Van Galenstraat','Amsterdam'),('Marcantilaan','Amsterdam'),('Mediapark','Amsterdam');

INSERT INTO asset (afkorting, naam, dagkoers) VALUES ('BNB','Binance_Coin',566.00000),('BTC','Bitcoin',50.67500),('ETH','Etherium',4.14600);

INSERT INTO adres (postcode, huisnummer, toevoeging, ziphuisnrId) VALUES ('1782CC',6,NULL,1),('1051NJ',24,'a',2),('1061BB',354,NULL,3),('1837AB',1,'b',4);

INSERT INTO eurokoers (datum, koers, assetId) VALUES ('2021-12-02',566.00000,1),('2021-12-02',50.67500,2),('2021-12-02',4.14600,3);

INSERT INTO klant (bsn, geboortedatum, emailadres, wachtwoord, naamId, adresId, RekeningId) VALUES
('123456789','1980-05-27','annie@gmail.com','Annie7890',1,1,1),
('123789456','1983-07-05','marieke@gmail.com','cryptoMaster77',2,2,2),
('456789123','1990-06-20','karin@gmail.com','K1',3,3,3),
('789123456','2000-01-20','klaas@hotmail.com','MIW_77_##',4,4,4);

INSERT INTO portefeuille_item (aantal,klantId,assetId) VALUES (1.00000,1,1),(2.00000,1,2),(4.00000,2,1),(2.00000,2,2),(1.00000,2,3),(3.00000,3,2),(6.00000,3,3),(2.00000,4,1),
(1.00000,4,2),(2.00000,4,3);