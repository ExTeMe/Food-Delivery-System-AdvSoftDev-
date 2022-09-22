USE db;

# USER ROWS
INSERT INTO USER VALUES
    (DEFAULT, 'Benz', 'Chua', 'Ro0T!', 'benzchua31@gmail.com', 256367826, '2003-05-11', 11,
    'Kentucky', 2689, 'NSE', 'Bonword', 'Austrelie', TRUE);

# APPSTAFF ROWS
INSERT INTO appstaff (UserID)
    SELECT UserID FROM USER WHERE EMAIL = 'benzchua31@gmail.com';

# RES CATEGORY ROWS
INSERT INTO rcategory VALUES (DEFAULT,'Fast Food', 'fast food');
INSERT INTO rcategory VALUES (DEFAULT,'Faster Food', 'faster food');
INSERT INTO rcategory VALUES (DEFAULT,'Slow Food', 'slow food');

# RES ROWS
INSERT INTO restaurant VALUES
    (DEFAULT,'Obama Fried Chicken', './src/main/webapp/images/ofc.png', 91, 'Obama',
     1728, 'NSE', 'Chinatown', 'Austrelie', 1, 11122233344, 'Benz Chua',
     345678, 928718);

INSERT INTO restaurant VALUES
    (DEFAULT,'Obama Fish Chips', './src/main/webapp/images/ofc.png', 18, 'Obama',
     1578, 'NSE', 'Chinatown', 'Austrelie', 1, 12123233544, 'Benzz Chua',
     342978, 920978);

# RES-RCAT ROWS
INSERT INTO restaurant_rcategory (RCategory_ID, Restaurant_ID)
    SELECT RC.RCategory_ID, Res.Restaurant_ID
    FROM (SELECT RCategory_ID FROM rcategory WHERE RCategory_Name = 'Fast Food') AS RC
    CROSS JOIN (SELECT Restaurant_ID FROM restaurant WHERE ABN = 11122233344) AS RES;

INSERT INTO restaurant_rcategory (RCategory_ID, Restaurant_ID)
SELECT RC.RCategory_ID, Res.Restaurant_ID
FROM (SELECT RCategory_ID FROM rcategory WHERE RCategory_Name = 'Faster Food') AS RC
         CROSS JOIN (SELECT Restaurant_ID FROM restaurant WHERE ABN = 11122233344) AS RES;

INSERT INTO restaurant_rcategory (RCategory_ID, Restaurant_ID)
SELECT RC.RCategory_ID, Res.Restaurant_ID
FROM (SELECT RCategory_ID FROM rcategory WHERE RCategory_Name = 'Slow Food') AS RC
         CROSS JOIN (SELECT Restaurant_ID FROM restaurant WHERE ABN = 12123233544) AS RES;