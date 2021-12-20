CREATE TABLE if not exists rekening (
  rekeningId INT NOT NULL AUTO_INCREMENT,
  iban CHAR(18) NOT NULL,
  saldo DECIMAL(10,2) NOT NULL);

CREATE TABLE if not exists asset (
  assetId INT NOT NULL AUTO_INCREMENT,
  afkorting VARCHAR(10) NOT NULL,
  naam VARCHAR(45) NOT NULL);
  
  CREATE TABLE if not exists eurokoers (
  eurokoersId INT NOT NULL AUTO_INCREMENT,
  assetId INT NOT NULL,
  datum DATE NOT NULL,
  koers DECIMAL(15,5) NOT NULL);
  
  CREATE TABLE if not exists adres (
  adresId INT NOT NULL AUTO_INCREMENT,
  postcode CHAR(6) NOT NULL,
  huisnummer INT NOT NULL,
  toevoeging VARCHAR(45) NULL);
  
  CREATE TABLE if not exists transactiepartij (
  tpId INT NOT NULL AUTO_INCREMENT,
  rekeningId INT NOT NULL,
  adresId INT NULL,
  tpType VARCHAR(45) NOT NULL,
  emailadres VARCHAR(45) NULL,
  wachtwoord CHAR(60) NULL,
  voornaam VARCHAR(45) NULL,
  tussenvoegsel VARCHAR(45) NULL,
  achternaam VARCHAR(45) NULL,
  bsn CHAR(9) NULL,
  geboortedatum DATE NULL,
  refreshToken VARCHAR(100) NULL,
  banknaam VARCHAR(45) NULL,
  transactiePercentage DECIMAL(5,2) NULL);
  
  CREATE TABLE IF NOT EXISTS transactie (
  transactieId INT NOT NULL AUTO_INCREMENT,
  koperId INT NOT NULL,
  verkoperId INT NOT NULL,
  assetId INT NOT NULL,
  aantal DECIMAL(10,5) NOT NULL,
  datum DATE NOT NULL,
  tijdstip TIME NOT NULL);
  
  CREATE TABLE IF NOT EXISTS portefeuille_item (
  itemId INT NOT NULL AUTO_INCREMENT,
  tpId INT NOT NULL,
  assetId INT NOT NULL,
  aantal DECIMAL(10,5) NOT NULL);