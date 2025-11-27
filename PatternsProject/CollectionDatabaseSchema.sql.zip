CREATE DATABASE IF NOT EXISTS CollectionDatabase;
USE CollectionDatabase;

CREATE TABLE Users (
    userId INT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(255) NOT NULL UNIQUE,
    passwordEncrypted VARCHAR(255) NOT NULL,
    PRIMARY KEY (userId)
) AUTO_INCREMENT = 100000;

CREATE TABLE Friendships (
    friendshipId INT NOT NULL AUTO_INCREMENT,
    userId INT NOT NULL,
    friendId INT NOT NULL,
    PRIMARY KEY (friendshipId),
    FOREIGN KEY (userId) REFERENCES Users(userId),
    FOREIGN KEY (friendId) REFERENCES Users(userId)
) AUTO_INCREMENT = 200000;

CREATE TABLE Collection (
    collectionId INT NOT NULL AUTO_INCREMENT,
    collectionTitle VARCHAR(255),
    collectionCreatorId INT NOT NULL,
    PRIMARY KEY (collectionId),
    FOREIGN KEY (collectionCreatorId) REFERENCES Users(userId)
) AUTO_INCREMENT = 300000;

CREATE TABLE CollectionTracker (
    trackerId INT NOT NULL AUTO_INCREMENT,
    collectionId INT NOT NULL,
    userId INT NOT NULL,
    PRIMARY KEY (trackerId),
    FOREIGN KEY (collectionId) REFERENCES Collection(collectionId),
    FOREIGN KEY (userId) REFERENCES Users(userId)
) AUTO_INCREMENT = 400000;

CREATE TABLE Collectible (
    collectibleId INT NOT NULL AUTO_INCREMENT,
    collectibleName VARCHAR(255) NOT NULL,
    thumbnail VARBINARY(8000),
    PRIMARY KEY (collectibleId)
) AUTO_INCREMENT = 500000;

CREATE TABLE CollectionConstituent (
    constituentId INT NOT NULL AUTO_INCREMENT,
    collectionId INT NOT NULL,
    collectibleId INT NOT NULL,
    PRIMARY KEY (constituentId),
    FOREIGN KEY (collectionId) REFERENCES Collection(collectionId),
    FOREIGN KEY (collectibleId) REFERENCES Collectible(collectibleId)
) AUTO_INCREMENT = 600000;

CREATE TABLE CollectedItem (
    collectedId INT NOT NULL AUTO_INCREMENT,
    userId INT NOT NULL,
    collectibleId INT NOT NULL,
    PRIMARY KEY (collectedId),
    FOREIGN KEY (userId) REFERENCES Users(userId),
    FOREIGN KEY (collectibleId) REFERENCES Collectible(collectibleId)
) AUTO_INCREMENT = 700000;
