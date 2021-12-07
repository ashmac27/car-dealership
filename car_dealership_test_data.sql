INSERT INTO `user` (FirstName, LastName, Email, `Role`) 
VALUES
("Admin_f1", "Admin_l1", "Admin_1@email.com", "admin"),
("Sales_f1", "Sales_l1", "Sales_1@email.com", "sales"),
("Sales_f2", "Sales_l2", "Sales_2@email.com", "sales"),
("Disabled_f1", "Disabled_l1", "Disabled_1@email.com", "disabled");

INSERT INTO specials (Title, `Description`)
VALUES
("Title_1", "Description_1"),
("Title_2", "Description_2"),
("Title_3", "Description_3"),
("Title_4", "Description_4");

INSERT INTO contact_message (`Name`, Message, Phone, Email)
VALUES
("Name_1", "Message_1","111-111-1111",""),
("Name_2", "Message_2","","email2@email.com"),
("Name_3", "Message_3","333-333-3333",""),
("Name_4", "Message_4","","email4@email.com");

INSERT INTO make (MakeName, UserId)
VALUES
("Ford", 1),
("Honda", 1),
("Toyota", 1),
("Mazda", 1);

INSERT INTO model (MakeId, ModelName, UserId)
VALUES
("1", "Fiesta", 1),
("1", "Focus", 1),
("2", "Accord", 1),
("2", "Pilot", 1),
("3", "Corolla", 1),
("3", "Camry", 1),
("4", "RX-7", 1),
("4", "RX-8", 1);

INSERT INTO vehicle (VIN, MakeId, ModelId, `Type`, BodyStyle, `Year`, Transmission, Color,
Interior, Mileage, SalePrice, MSRP, `Description`, IsFeatured, IsSold, Picture)
VALUES
("11111111111111111", 1, 1, "new", "SUV", 2000, "automatic", "blue", 
"black", 0, 39999.99, 59999.99, "This is car number 1", false, true, "picture1.png"),
("22222222222222222", 1, 2, "new", "SUV", 2010, "manual", "black", 
"white", 0, 49999.99, 69999.99, "This is car number 2", false, false, "picture2.png"),
("33333333333333333", 2, 4, "used", "SUV", 2020, "automatic", "red", 
"black", 35000, 59999.99, 79999.99, "This is car number 3", true, false, "picture3.png"),
("44444444444444444", 3, 5, "used", "SUV", 2015, "manual", "orange", 
"white", 25000, 89999.99, 99999.99, "This is car number 4", true, false, "picture4.png"),
("55555555555555555", 4, 7, "used", "SUV", 2002, "automatic", "grey", 
"black", 15000, 99999.99, 159999.99, "This is car number 5", false, true, "picture5.png");

INSERT INTO purchase (VIN, `Name`, PurchaseType, PurchasePrice, Phone, Email, Street1, 
Street2, City, State, Zip, SalesPersonId)
VALUES
("11111111111111111", "Martha Smith", "bank finance", 10000.00, "111-111-1111", "Marth@email.com", "Street_1",
"Street_2", "City_1", "State_1", "12345", 2),
("22222222222222222", "John Smith", "cash", 87123.99, "222-222-2222", "John@email.com", "Street_2",
"", "City_2", "State_2", "78945", 3),
("22222222222222222", "Joe Smith", "dealer finance", 12345.99, "333-333-3333", "John@email.com", "Street_2",
"", "City_3", "State_3", "78945", 3);


