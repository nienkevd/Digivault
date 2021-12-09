CREATE TABLE `naam` (
  `naamId` INT NOT NULL AUTO_INCREMENT,
  `voornaam` VARCHAR(45) NOT NULL,
  `tussenvoegsel` VARCHAR(45) NULL,
  `achternaam` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`naamId`)
                    );

CREATE TABLE `adres` (
  `adresId` INT NOT NULL AUTO_INCREMENT,
  `straat` VARCHAR(45) NOT NULL,
  `huisnummer` INT NOT NULL,
  `toevoeging` VARCHAR(45) NULL,
  `postcode` CHAR(6) NOT NULL,
  `woonplaats` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`adresId`));

CREATE TABLE `rekening` (
  `rekeningId` INT NOT NULL AUTO_INCREMENT,
  `iban` CHAR(18) NOT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`rekeningId`),
  UNIQUE INDEX `iban_UNIQUE` (`iban` ASC) VISIBLE);

CREATE TABLE `account` (
  `accountId` INT NOT NULL AUTO_INCREMENT,
  `emailadres` VARCHAR(45) NOT NULL,
  `wachtwoord` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`accountId`));

CREATE TABLE `asset` (
  `assetId` INT NOT NULL AUTO_INCREMENT,
  `afkorting` VARCHAR(10) NOT NULL,
  `naam` VARCHAR(45) NOT NULL,
  `dagkoers` DECIMAL(15,5) NOT NULL,
  PRIMARY KEY (`assetId`),
  UNIQUE INDEX `naam_UNIQUE` (`naam` ASC) VISIBLE,
  UNIQUE INDEX `afkorting_UNIQUE` (`afkorting` ASC) VISIBLE);

CREATE TABLE `eurokoers` (
  `eurokoersId` INT NOT NULL AUTO_INCREMENT,
  `datum` DATE NOT NULL,
  `koers` DECIMAL(15,5) NOT NULL,
  `assetId` INT NOT NULL,
  PRIMARY KEY (`eurokoersId`),
  INDEX `verzinzelf7_idx` (`assetId` ASC) VISIBLE);

CREATE TABLE `klant` (
  `klantId` INT NOT NULL AUTO_INCREMENT,
  `bsn` CHAR(9) NOT NULL,
  `geboortedatum` DATE NOT NULL,
  `naamId` INT NOT NULL,
  `adresId` INT NOT NULL,
  `rekeningId` INT NOT NULL,
  `accountId` INT NOT NULL,
  PRIMARY KEY (`klantId`),
  UNIQUE INDEX `bsn_UNIQUE` (`bsn` ASC) VISIBLE,
  INDEX `verzinzelf2_idx` (`naamId` ASC) VISIBLE,
  INDEX `verzinzelf3_idx` (`adresId` ASC) VISIBLE,
  INDEX `verzinzelf4_idx` (`rekeningId` ASC) VISIBLE,
  INDEX `verzinzelf1_idx` (`accountId` ASC) VISIBLE);

CREATE TABLE `portefeuille_item` (
  `itemId` INT NOT NULL AUTO_INCREMENT,
  `aantal` DECIMAL(10,5) NOT NULL,
  `klantId` INT NOT NULL,
  `assetId` INT NOT NULL,
  PRIMARY KEY (`itemId`),
  INDEX `verzinzelf5_idx` (`klantId` ASC) VISIBLE,
  INDEX `verzinzelf6_idx` (`assetId` ASC) VISIBLE);