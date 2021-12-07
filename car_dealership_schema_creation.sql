DROP SCHEMA IF EXISTS CarDealership;
CREATE SCHEMA CarDealership;

USE CarDealership;

CREATE TABLE IF NOT EXISTS `user` (
  UserId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  FirstName VARCHAR(45) NOT NULL,
  LastName VARCHAR(45) NOT NULL,
  Email VARCHAR(45) NOT NULL,
  `Role` VARCHAR(8) NOT NULL
);

CREATE TABLE IF NOT EXISTS specials(
  SpecialId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Title VARCHAR(30) NOT NULL,
  `Description` TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS contact_message(
    ContactMessageId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(50) NOT NULL,
    Message TEXT NOT NULL,
	Phone CHAR(12) DEFAULT NULL,
	Email VARCHAR(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS make(
  MakeId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  MakeName VARCHAR(30) NOT NULL,
  DateAdded DATE DEFAULT NULL DEFAULT (CURRENT_DATE),
  UserId INT NOT NULL,
  FOREIGN KEY (UserId) REFERENCES `user`(UserId)
);

CREATE TABLE IF NOT EXISTS model(
  ModelId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  MakeId INT NOT NULL,
  ModelName VARCHAR(45) NOT NULL,
  DateAdded DATE NOT NULL DEFAULT (CURRENT_DATE),
  UserId INT NOT NULL,
  FOREIGN KEY (MakeId) REFERENCES make(MakeId),
  FOREIGN KEY (UserId) REFERENCES `user`(UserId)
);

CREATE TABLE IF NOT EXISTS vehicle (
  VIN CHAR(17) NOT NULL PRIMARY KEY,
  MakeId INT NOT NULL,
  ModelId INT NOT NULL,
  `Type` VARCHAR(4) NOT NULL,
  BodyStyle VARCHAR(30) NOT NULL,
  `Year` INT NOT NULL,
  Transmission VARCHAR(20) NOT NULL,
  Color VARCHAR(20) NOT NULL,
  Interior VARCHAR(45) NOT NULL,
  Mileage INT NOT NULL,
  SalePrice DECIMAL(10,2) NOT NULL,
  MSRP DECIMAL(10,2) NOT NULL,
  `Description` TEXT,
  IsFeatured BOOLEAN NOT NULL,
  IsSold BOOLEAN NOT NULL,
  Picture BLOB,
  FOREIGN KEY (MakeId) REFERENCES make(MakeId),
  FOREIGN KEY (ModelId) REFERENCES model(ModelId)
);

CREATE TABLE IF NOT EXISTS purchase(
  PurchaseId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  VIN CHAR(17) NOT NULL,
  `Name` VARCHAR(50) NOT NULL,
  PurchaseType VARCHAR(14) NOT NULL,
  PurchasePrice DECIMAL(10,2) NOT NULL,
  Phone CHAR(12) DEFAULT NULL,
  Email VARCHAR(45) DEFAULT NULL,
  Street1 VARCHAR(45) NOT NULL,
  Street2 VARCHAR(45) DEFAULT NULL,
  City VARCHAR(45) NOT NULL,
  State VARCHAR(25) NOT NULL,
  Zip CHAR(5) NOT NULL,
  DateOfPurchase DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  SalesPersonId INT NOT NULL,
  FOREIGN KEY (VIN) REFERENCES vehicle(VIN),
  FOREIGN KEY (SalesPersonId) REFERENCES `user`(UserId)
);