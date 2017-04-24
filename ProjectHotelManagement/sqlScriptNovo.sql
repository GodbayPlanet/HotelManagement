INSERT INTO `hotelmanagamenttest`.`admin` (`ID`, `Name`, `Password`) VALUES ('1', 'Admin', 'admin');

INSERT INTO `hotelmanagamenttest`.`users` (`ID`, `UserName`, `FirstName`, `LastName`, `Password`, `Gender`, `Age`, `IsLogged`) VALUES ('1', 'Mentalica', 'James', 'Hatefild', '111', 'M', '45', '1');
INSERT INTO `hotelmanagamenttest`.`users` (`ID`, `UserName`, `FirstName`, `LastName`, `Password`, `Gender`, `Age`, `IsLogged`) VALUES ('2', 'Dejo', 'Dejan', 'Radeljic', '222', 'M', '25', '1');
INSERT INTO `hotelmanagamenttest`.`users` (`ID`, `UserName`, `FirstName`, `LastName`, `Password`, `Gender`, `Age`, `IsLogged`) VALUES ('3', 'Dejj', 'Dejan', 'Petrovic', '333', 'M', '25', '1');
INSERT INTO `hotelmanagamenttest`.`users` (`ID`, `UserName`, `FirstName`, `LastName`, `Password`, `Gender`, `Age`, `IsLogged`) VALUES ('4', 'Iva', 'Ivana', 'Ivi', '444', 'F', '25', '0');
INSERT INTO `hotelmanagamenttest`.`users` (`ID`, `UserName`, `FirstName`, `LastName`, `Password`, `Gender`, `Age`, `IsLogged`) VALUES ('5', 'Amer', 'Sarajlic', 'amer', '555', 'M', '25', '1');
INSERT INTO `hotelmanagamenttest`.`users` (`ID`, `UserName`, `FirstName`, `LastName`, `Password`, `Gender`, `Age`, `IsLogged`) VALUES ('6', 'Majid', 'Hajric', 'majid', '666', 'M', '25', '0');
INSERT INTO `hotelmanagamenttest`.`users` (`ID`, `UserName`, `FirstName`, `LastName`, `Password`, `Gender`, `Age`, `IsLogged`) VALUES ('7', 'Jasmin', 'Mustafic', 'jasko', '777', 'M', '20', '1');
INSERT INTO `hotelmanagamenttest`.`users` (`ID`, `UserName`, `FirstName`, `LastName`, `Password`, `Gender`, `Age`, `IsLogged`) VALUES ('8', 'Tamara', 'Paocic', 'tamara', '888', 'F', '20', '1');


INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('1', '0', 'Single Room', '20');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('2', '0', 'Double Room', '40');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('3', '0', 'Double Room', '40');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('4', '0', 'Single Room', '20');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('5', '0', 'Apartment', '60');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('6', '1', 'Apartment', '60');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('7', '1', 'Apartment', '60');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('8', '1', 'Apartment', '60');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('9', '1', 'Apartment', '60');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('10', '1', 'Single Room', '20');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('11', '1', 'Single Room', '20');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('12', '1', 'Single Room', '20');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('13', '1', 'Double Room', '40');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('14', '1', 'Double Room', '40');
INSERT INTO `hotelmanagamenttest`.`rooms` (`RoomNumber`, `IsFree`, `RoomType`, `RoomPrice`) VALUES ('15', '1', 'Double Room', '40');


INSERT INTO `hotelmanagamenttest`.`services` (`ID`, `Name`, `Price`) VALUES ('1', 'Restaurant', '20');
INSERT INTO `hotelmanagamenttest`.`services` (`ID`, `Name`, `Price`) VALUES ('2', 'Sauna', '10');
INSERT INTO `hotelmanagamenttest`.`services` (`ID`, `Name`, `Price`) VALUES ('3', 'Swimming Pool', '10');
INSERT INTO `hotelmanagamenttest`.`services` (`ID`, `Name`, `Price`) VALUES ('4', 'Gym', '10');
INSERT INTO `hotelmanagamenttest`.`services` (`ID`, `Name`, `Price`) VALUES ('5', 'Cinema', '10');

INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `StartDate`, `RoomID`) VALUES ('1', '1', '2017-04-18', '1');
INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `StartDate`) VALUES ('1', '2', '2017-04-18');
INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `StartDate`) VALUES ('1', '3', '2017-04-18');
INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `StartDate`, `RoomID`) VALUES ('2', '1', '2017-04-19', '2');
INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `StartDate`) VALUES ('2', '2', '2017-04-18');

/*
UPDATE `hotelmanagamenttest`.`hotelstatus` SET `EndDate`='2017-04-21' WHERE `UserID`='2' and`ServiceID`='0' and`StartDate`='2017-04-20';
INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `StartDate`, `RoomID`) VALUES ('2', '0', '2017-04-21', '8');

UPDATE `hotelmanagamenttest`.`hotelstatus` SET `StartDate`='2017-04-19', `EndDate`='2017-04-20' WHERE `UserID`='2' and`ServiceID`='0' and`StartDate`='2017-04-20';
UPDATE `hotelmanagamenttest`.`hotelstatus` SET `StartDate`='2017-04-20' WHERE `UserID`='2' and`ServiceID`='0' and`StartDate`='2017-04-21';
*/

/*
INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `RoomID`, `Date`) VALUES ('1', '1', '1', '2017-04-18');
INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `RoomID`, `Date`) VALUES ('2', '2', '2', '2017-04-18');
INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `RoomID`, `Date`) VALUES ('3', '3', '3', '2017-04-18');
INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `RoomID`, `Date`) VALUES ('4', '4', '4', '2017-04-18');
INSERT INTO `hotelmanagamenttest`.`hotelstatus` (`UserID`, `ServiceID`, `RoomID`, `Date`) VALUES ('5', null, '10', '2017-04-18');
*/
/*
ALTER TABLE hotelstatus MODIFY COLUMN ServiceID INT NULL;
ALTER TABLE hotelstatus MODIFY COLUMN RoomID INT NULL;
*/



