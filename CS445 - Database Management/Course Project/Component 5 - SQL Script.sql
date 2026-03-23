-- =============================================
-- Author:      Jerum Lee Hubbert
-- Create date: June 28, 2018
-- Description: CS445 - Component 5
-- Populate your database tables in Oracle
-- =============================================


-- Addresses
INSERT INTO "address"
VALUES (null,'8615', 'Ramblewood Ave', 'Dublin', 'GA', '31021');
INSERT INTO "address"
VALUES (null,'9199', 'Essex Court', 'Oshkosh', 'WI', '54901');
INSERT INTO "address"
VALUES (null,'609', 'Shore Drive', 'Glendale', 'AZ', '85302');
INSERT INTO "address"
VALUES (null,'504', '4th Street', 'Mountain View', 'CA', '94043');
INSERT INTO "address"
VALUES (null,'62', 'W. Branch St.', 'Elizabeth', 'NJ', '07202');
INSERT INTO "address"
VALUES (null,'48', 'Coffee Rd.', 'New Brunswick', 'NJ', '08901');
INSERT INTO "address"
VALUES (null,'469', 'West Oak St.', 'Frederick', 'MD', '21701');
INSERT INTO "address"
VALUES (null,'681', 'Sussex Drive', 'Clemmons', 'NC', '27012');
INSERT INTO "address"
VALUES (null,'581', 'Central St.', 'Smithtown', 'NY', '11787');
INSERT INTO "address"
VALUES (null,'81', 'Wilson Road', 'West Palm Beach', 'FL', '33404');
INSERT INTO "address"
VALUES (null,'7126', 'Wellington Ave.', 'Fayetteville', 'NC', '28303');
INSERT INTO "address"
VALUES (null,'709', 'Courtland Court', 'Schenectady', 'NY', '12302');

--payment_types
INSERT INTO "payment_types"
VALUES (null,'cash', 'a cash money payment');
INSERT INTO "payment_types"
VALUES (null,'master card', 'master card payment');
INSERT INTO "payment_types"
VALUES (null,'pay pal', 'online payment vendor');
INSERT INTO "payment_types"
VALUES (null,'visa', 'master card payment');
INSERT INTO "payment_types"
VALUES (null,'aid', 'financial aid');
INSERT INTO "payment_types"
VALUES (null,'cryptocurrency', 'the future of payments');

--relationship_type
INSERT INTO "relationship_type"
VALUES (null,'father', 'students father');
INSERT INTO "relationship_type"
VALUES (null,'mother', 'students mother');
INSERT INTO "relationship_type"
VALUES (null,'grandmother', 'grandmother of student');

--staff_position
INSERT INTO "staff_position"
VALUES (null,'office administrator', 'Keeps track of office data');
INSERT INTO "staff_position"
VALUES (null,'student administrator I', 'Keeps track of student data,  level I');
INSERT INTO "staff_position"
VALUES (null,'student administrator II', 'Keeps track of student data, level II');
INSERT INTO "staff_position"
VALUES (null,'advisor', 'A student advisor');

--Advisor
INSERT INTO "advisor"
VALUES (null,'Louis', 'Perez','LouisNPerez@rhyta.com',131, '315-333-9051', 1, 'accommodations', 4);
INSERT INTO "advisor"
VALUES (null,'Ken', 'Murdock','KenDMurdock@teleworm.us',678, '678-738-7796', 2, 'accommodations', 4);
INSERT INTO "advisor"
VALUES (null,'Jared', 'Kirkpatrick','JaredPKirkpatrick@rhyta.com',323, '323-375-3074', 3, 'accommodations', 4);

--student_category
INSERT INTO "student_category"
VALUES (null,'first-year undergraduate', 'freshmen');
INSERT INTO "student_category"
VALUES (null,'second-year undergraduate', 'sophomore');
INSERT INTO "student_category"
VALUES (null,'third-year undergraduate', 'junior');
INSERT INTO "student_category"
VALUES (null,'fourth-year undergraduate', 'senior');
INSERT INTO "student_category"
VALUES (null,'postgraduate', 'master student');

--status
INSERT INTO "status"
VALUES (null,'placed', 'student is currently in housing');
INSERT INTO "status"
VALUES (null,'waiting', 'student is on waiting list for housing');
INSERT INTO "status"
VALUES (null,'opt', 'student is housed on their own');

--instructor
INSERT INTO "instructor"
VALUES (null,'Michael', 'Oneil','MichaelLOneil@rhyta.com','science','452B');
INSERT INTO "instructor"
VALUES (null,'Kendrick', 'Dejesus','KendrickWDejesus@dayrep.com','science','452B');
INSERT INTO "instructor"
VALUES (null,'Thomas', 'Scott','ThomasDScott@armyspy.com','science','452B');
INSERT INTO "instructor"
VALUES (null,'Daniel', 'Jackson','DanielMJackson@teleworm.us','science','452B');
INSERT INTO "instructor"
VALUES (null,'David', 'Conroy','DavidEConroy@dayrep.com','science','452B');
INSERT INTO "instructor"
VALUES (null,'John', 'Fast','JohnRFast@rhyta.com','science','452B');

--course
INSERT INTO "course"
VALUES (null, 058, 'Chemistry - Marine Chemistry', 1, '212-367-6365');
INSERT INTO "course"
VALUES (null, 040, 'Law - Civil Law : Paralegal Studies', 2, '860-783-9269');
INSERT INTO "course"
VALUES (null, 229, 'Sociology - Community Informatics : Social Network Analysis', 3, '757-376-0015');
INSERT INTO "course"
VALUES (null, 495, 'Economics - Social Choice Theory', 4, '816-861-7495');
INSERT INTO "course"
VALUES (null, 605, 'Architecture And Design - User Experience Design : Interaction Design', 5, '760-647-3503');
INSERT INTO "course"
VALUES (null, 467, 'Philosophy - Applied Philosophy : Systems Philosophy', 6, '806-538-7462');
INSERT INTO "course"
VALUES (null, 022, 'Social Work - Medical Social Work', 1, '413-842-6067');
INSERT INTO "course"
VALUES (null, 191, 'Psychology - Occupational Health Psychology', 2, '412-353-4880');
INSERT INTO "course"
VALUES (null, 690, 'Religion - Indian Religions : Hinduism', 3, '910-733-8462');
INSERT INTO "course"
VALUES (null, 592, 'Economics - Behavioral Economics', 4, '954-861-3929');

--student
INSERT INTO "student"
VALUES (null, 'Carlyn', 'Patterson', 'CarlynRPatterson@dayrep.com',TO_DATE('11/13/1979', 'mm/dd/yyyy'),
 0, '270-362-8816', 'United States', '','','Performing Arts', 'Biology', 1, 2, 1, 3, 1, 2);

INSERT INTO "student"
VALUES (null, 'Sean', 'Robinson', 'SeanJRobinson@armyspy.com',TO_DATE('12/07/1958', 'mm/dd/yyyy'),
 1, '530-421-1163', 'United States','','','Military Sciences', 'Physics', 0, 1, 2, 4, 2, 3);

INSERT INTO "student"
VALUES (null, 'James', 'Whiteside', 'JamesYWhiteside@rhyta.com',TO_DATE('01/02/1973', 'mm/dd/yyyy'),
 1, '832-269-8892', 'United States','','','Computer Sciences', 'Medicine', 0, 1, 3, 3, 3, 1);

--next_of_kin
INSERT INTO "next_of_kin"
VALUES (null, 'James', 'Patterson', '307-437-6353',1,1,2);
INSERT INTO "next_of_kin"
VALUES (null, 'Kimberly', 'Robinson', '516-213-7512',2,2,3);
INSERT INTO "next_of_kin"
VALUES (null, 'Hester', 'Whiteside', '941-885-0971',3,3,1);

--staff
INSERT INTO "staff"
VALUES (null, 'Kurt', 'Nieves', 'KurtRNieves@jourrapide.com',TO_DATE('06/07/1982', 'mm/dd/yyyy'),'203-913-0601',1,3,7);
INSERT INTO "staff"
VALUES (null, 'Jayne', 'Hogans', 'JayneRHogans@rhyta.com',TO_DATE('11/02/1964', 'mm/dd/yyyy'),'701-303-5967',0,2,8);
INSERT INTO "staff"
VALUES (null, 'Jose', 'Powell', 'JoseSPowell@teleworm.us',TO_DATE('05/23/1986', 'mm/dd/yyyy'),'518-725-6573',1,2,9);

--hall_of_residence
INSERT INTO "hall_of_residence"
VALUES (null, 'Ashley R. ONeill hall', '202-682-8458',1,4);
INSERT INTO "hall_of_residence"
VALUES (null, 'Robert S. Garcia hall', '216-432-1162',2,5);
INSERT INTO "hall_of_residence"
VALUES (null, 'Donna D. Rowe hall', '360-942-6602',3,6);

--room
INSERT INTO "room"
VALUES (null, 23, 909.70, 1);
INSERT INTO "room"
VALUES (null, 20, 1002.70, 2);
INSERT INTO "room"
VALUES (null, 11, 1200.70, 3);

--room_inspection
INSERT INTO "room_inspection"
VALUES (null, 1, TO_DATE('01/07/2018', 'mm/dd/yyyy'), 1, 'everything looked great', 1);

INSERT INTO "room_inspection"
VALUES (null, 2, TO_DATE('02/07/2018', 'mm/dd/yyyy'), 1, 'need to paint the walls', 2);

INSERT INTO "room_inspection"
VALUES (null, 3, TO_DATE('03/07/2018', 'mm/dd/yyyy'), 1, 'needs floor cleaning', 3);

INSERT INTO "room_inspection"
VALUES (null, 1, TO_DATE('06/07/2018', 'mm/dd/yyyy'), 0, 'Large stain on carpet, needs to be repaired!', 1);

INSERT INTO "room_inspection"
VALUES (null, 2, TO_DATE('06/07/2018', 'mm/dd/yyyy'), 0, 'Paint is starting to product a bad smell, needs to be looked at', 2);

INSERT INTO "room_inspection"
VALUES (null, 3, TO_DATE('06/07/2018', 'mm/dd/yyyy'), 0, 'There is a noticeable bug infection, need to call exterminator', 3);

--lease
INSERT INTO "lease"
VALUES (null, TO_DATE('01/07/2018', 'mm/dd/yyyy'), TO_DATE('01/07/2019', 'mm/dd/yyyy'),
 TO_DATE('02/01/2018', 'mm/dd/yyyy'),TO_DATE('01/07/2019', 'mm/dd/yyyy'),1, 1, 0 );
INSERT INTO "lease"
VALUES (null, TO_DATE('02/07/2018', 'mm/dd/yyyy'), TO_DATE('02/07/2019', 'mm/dd/yyyy'),
 TO_DATE('02/07/2018', 'mm/dd/yyyy'),TO_DATE('02/07/2019', 'mm/dd/yyyy'),2, 2, 0 );
INSERT INTO "lease"
VALUES (null, TO_DATE('03/07/2018', 'mm/dd/yyyy'), TO_DATE('03/07/2019', 'mm/dd/yyyy'),
 TO_DATE('03/12/2018', 'mm/dd/yyyy'),TO_DATE('03/07/2019', 'mm/dd/yyyy'),3, 3, 0 );


--invoice
INSERT INTO "invoice"
VALUES (null,TO_DATE('01/01/2018', 'mm/dd/yyyy'), TO_DATE('03/01/2018', 'mm/dd/yyyy'), TO_DATE('02/10/2018', 'mm/dd/yyyy'),
    TO_DATE('02/01/2018', 'mm/dd/yyyy'), TO_DATE('02/25/2018', 'mm/dd/yyyy'), 1, 1, 1, 909.70);
INSERT INTO "invoice"
VALUES (null,TO_DATE('01/01/2018', 'mm/dd/yyyy'), TO_DATE('03/01/2018', 'mm/dd/yyyy'), TO_DATE('02/11/2018', 'mm/dd/yyyy'), 
    TO_DATE('02/01/2018', 'mm/dd/yyyy'), TO_DATE('02/25/2018', 'mm/dd/yyyy'), 2, 2, 2, 1002.70);
INSERT INTO "invoice"
VALUES (null,TO_DATE('01/01/2018', 'mm/dd/yyyy'), TO_DATE('03/01/2018', 'mm/dd/yyyy'), TO_DATE('02/12/2018', 'mm/dd/yyyy'), 
    TO_DATE('02/01/2018', 'mm/dd/yyyy'), TO_DATE('02/25/2018', 'mm/dd/yyyy'), 4, 3, 3, 1200.70);












