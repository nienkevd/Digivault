CREATE SCHEMA `digivault`;

CREATE TABLE `digivault`.`rekening` (
  `rekeningId` INT NOT NULL AUTO_INCREMENT,
  `iban` CHAR(18) NOT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`rekeningId`),
  UNIQUE INDEX `iban_UNIQUE` (`iban` ASC) VISIBLE);

CREATE TABLE `digivault`.`naam` (
  `naamId` INT NOT NULL AUTO_INCREMENT,
  `voornaam` VARCHAR(45) NOT NULL,
  `tussenvoegsel` VARCHAR(45) NULL,
  `achternaam` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`naamId`));

CREATE TABLE `digivault`.`ziphuisnr` (
  `ziphuisnrId` INT NOT NULL AUTO_INCREMENT,
  `straat` VARCHAR(45) NOT NULL,
  `woonplaats` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ziphuisnrId`));

CREATE TABLE `digivault`.`asset` (
  `assetId` INT NOT NULL AUTO_INCREMENT,
  `afkorting` VARCHAR(10) NOT NULL,
  `naam` VARCHAR(45) NOT NULL,
  `dagkoers` DECIMAL(15,5) NOT NULL,
  PRIMARY KEY (`assetId`),
  UNIQUE INDEX `naam_UNIQUE` (`naam` ASC) VISIBLE,
  UNIQUE INDEX `afkorting_UNIQUE` (`afkorting` ASC) VISIBLE);

CREATE TABLE `digivault`.`adres` (
  `adresId` INT NOT NULL AUTO_INCREMENT,
  `postcode` CHAR(6) NOT NULL,
  `huisnummer` INT NOT NULL,
  `toevoeging` VARCHAR(45) NULL,
  `ziphuisnrId` INT NOT NULL,
  PRIMARY KEY (`adresId`),
  INDEX `verzinzelf1_idx` (`ziphuisnrId` ASC) VISIBLE);

CREATE TABLE `digivault`.`eurokoers` (
  `eurokoersId` INT NOT NULL AUTO_INCREMENT,
  `datum` DATE NOT NULL,
  `koers` DECIMAL(15,5) NOT NULL,
  `assetId` INT NOT NULL,
  PRIMARY KEY (`eurokoersId`),
  INDEX `verzinzelf7_idx` (`assetId` ASC) VISIBLE);

CREATE TABLE `digivault`.`klant` (
  `klantId` INT NOT NULL AUTO_INCREMENT,
  `bsn` CHAR(9) NOT NULL,
  `geboortedatum` DATE NOT NULL,
  `emailadres` VARCHAR(45) NOT NULL,
  `wachtwoord` VARCHAR(45) NOT NULL,
  `naamId` INT NOT NULL,
  `adresId` INT NOT NULL,
  `rekeningId` INT NOT NULL,
  PRIMARY KEY (`klantId`),
  UNIQUE INDEX `bsn_UNIQUE` (`bsn` ASC) VISIBLE,
  INDEX `verzinzelf2_idx` (`naamId` ASC) VISIBLE,
  INDEX `verzinzelf3_idx` (`adresId` ASC) VISIBLE,
  INDEX `verzinzelf4_idx` (`rekeningId` ASC) VISIBLE,
  UNIQUE INDEX `emailadres_UNIQUE` (`emailadres` ASC) VISIBLE);

CREATE TABLE `digivault`.`portefeuille_item` (
  `itemId` INT NOT NULL AUTO_INCREMENT,
  `aantal` DECIMAL(10,5) NOT NULL,
  `klantId` INT NOT NULL,
  `assetId` INT NOT NULL,
  PRIMARY KEY (`itemId`),
  INDEX `verzinzelf5_idx` (`klantId` ASC) VISIBLE,
  INDEX `verzinzelf6_idx` (`assetId` ASC) VISIBLE);