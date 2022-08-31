# CREATE DATABASE db;

USE db;

CREATE TABLE `User`
(
    UserID INT NOT NULL,
    First_Name VARCHAR(10) NOT NULL,
    Last_Name VARCHAR(10) NOT NULL,
    `Password` VARCHAR(10) NOT NULL,
    Email VARCHAR(20) UNIQUE NOT NULL,
    PhoneNo INT NOT NULL,
    DOB DATE, /* YYYY-MM-DD */
    Street_Number INT NOT NULL,
    Street_Name VARCHAR(20) NOT NULL,
    Postcode INT NOT NULL,
    `State` VARCHAR(20) NOT NULL,
    Suburb VARCHAR(20) NOT NULL,
    Country VARCHAR(20) NOT NULL,
    Activated BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (UserID)
);

CREATE TABLE Restaurant
(
    Restaurant_ID INT NOT NULL,
    Restaurant_Name VARCHAR(20) NOT NULL,
    Street_Number INT NOT NULL,
    Street_Name VARCHAR(20) NOT NULL,
    Postcode INT NOT NULL,
    `State` VARCHAR(20) NOT NULL,
    Suburb VARCHAR(20) NOT NULL,
    Country VARCHAR(20) NOT NULL,
    Activated BOOLEAN NOT NULL DEFAULT FALSE,
    ABN BIGINT NOT NULL,
    Account_Name VARCHAR(20) NOT NULL,
    BSB INT NOT NULL,
    Account_Number INT NOT NULL,
    PRIMARY KEY (Restaurant_ID)
);

CREATE TABLE Staff
(
    Staff_ID INT NOT NULL,
    UserID INT NOT NULL,
    Restaurant_ID INT NOT NULL,
    Privilege INT NOT NULL DEFAULT 0,
    Position VARCHAR(10),
    PRIMARY KEY  (Staff_ID),
    FOREIGN KEY (UserID) REFERENCES `User`(UserID),
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID)
);

CREATE TABLE AppStaff
(
    A_Staff_ID INT NOT NULL,
    UserID INT NOT NULL,
    Privilege INT NOT NULL DEFAULT 0,
    PRIMARY KEY  (A_Staff_ID),
    FOREIGN KEY (UserID) REFERENCES `User`(UserID)
);

CREATE TABLE `Log`
(
    Log_ID INT NOT NULL,
    A_Staff_ID INT NOT NULL,
    `Description` VARCHAR(100),
    PRIMARY KEY (Log_ID),
    FOREIGN KEY (A_Staff_ID) REFERENCES AppStaff(A_Staff_ID)
);

CREATE TABLE Request
(
    Request_ID INT NOT NULL,
    Restaurant_ID INT NOT NULL,
    Request_Type VARCHAR(10) NOT NULL,
    Request_Date DATETIME NOT NULL DEFAULT NOW(),
    Request_Status INT NOT NULL DEFAULT 0,
    PRIMARY KEY (Request_ID),
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID)
);

CREATE TABLE RCategory
(
    RCategory_ID INT NOT NULL,
    RCategory_Name VARCHAR(20) NOT NULL,
    RCategory_Description VARCHAR(100),
    PRIMARY KEY (RCategory_ID)
);

CREATE TABLE Restaurant_RCategory
(
    RCategory_ID INT NOT NULL,
    Restaurant_ID INT NOT NULL,
    PRIMARY KEY (RCategory_ID, Restaurant_ID),
    FOREIGN KEY (RCategory_ID) REFERENCES RCategory(RCategory_ID),
    FOREIGN KEY (Restaurant_ID) REFERENCES Restaurant(Restaurant_ID)
);