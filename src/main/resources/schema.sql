CREATE TABLE if not exists naam (
  naamId INT NOT NULL AUTO_INCREMENT,
  voornaam VARCHAR(45) NOT NULL,
  tussenvoegsel VARCHAR(45) NULL,
  achternaam VARCHAR(45) NOT NULL);


CREATE TABLE if not exists adres (
  adresId INT NOT NULL AUTO_INCREMENT,
  straat VARCHAR(45) NOT NULL,
  huisnummer INT NOT NULL,
  toevoeging VARCHAR(45) NULL,
  postcode CHAR(6) NOT NULL,
  woonplaats VARCHAR(45) NOT NULL);

CREATE TABLE if not exists rekening (
  rekeningId INT NOT NULL AUTO_INCREMENT,
  iban CHAR(18) NOT NULL,
  saldo DECIMAL(10,2) NOT NULL);

CREATE TABLE if not exists account (
  accountId INT NOT NULL AUTO_INCREMENT,
  emailadres VARCHAR(45) NOT NULL,
  wachtwoord VARCHAR(45) NOT NULL);

CREATE TABLE if not exists asset (
  assetId INT NOT NULL AUTO_INCREMENT,
  afkorting VARCHAR(10) NOT NULL,
  naam VARCHAR(45) NOT NULL,
  dagkoers DECIMAL(15,5) NOT NULL);

CREATE TABLE if not exists eurokoers (
  eurokoersId INT NOT NULL AUTO_INCREMENT,
  datum DATE NOT NULL,
  koers DECIMAL(15,5) NOT NULL,
  assetId INT NOT NULL);

CREATE TABLE if not exists klant (
  klantId INT NOT NULL AUTO_INCREMENT,
  bsn CHAR(9) NOT NULL,
  geboortedatum DATE NOT NULL,
  naamId INT NOT NULL,
  adresId INT NOT NULL,
  rekeningId INT NOT NULL,
  accountId INT NOT NULL);

CREATE TABLE if not exists portefeuille_item (
  itemId INT NOT NULL AUTO_INCREMENT,
  aantal DECIMAL(10,5) NOT NULL,
  klantId INT NOT NULL,
  assetId INT NOT NULL);