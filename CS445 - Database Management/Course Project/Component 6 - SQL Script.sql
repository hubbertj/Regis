-- =============================================
-- Author:      Jerum Lee Hubbert
-- Create date: June 28, 2018
-- Description: CS445 - Component 6
-- a. Present a report listing the Manager's name and telephone number for each hall of residence.  
-- b. Present a report listing the names and ID numbers of students with the details of their lease agreements.  
-- c. Display the total number of students in each student category.   
-- d. Display the details of housing inspections where the property was found to be in unsatisfactory condition.  
-- e. Present a report of the names and student IDs with their room number and hall of residence. Group by hall of residence. 
-- =============================================

-- Present a report listing the Manager's name and telephone number for each hall of residence. 

SELECT "st"."first_name" 
       || ' ' 
       ||"st"."last_name"  AS "Manager's name", 
       "st"."phone_number" AS "Telephone #", 
       "hr"."name"         AS "Hall Name" 
FROM   "staff" "st" 
       left join "hall_of_residence" "hr" 
              ON "hr"."manager" = "st"."id"; 

               
-- Present a report listing the names and ID numbers of students with the details of their lease agreements. 

SELECT "st"."first_name" 
       || ' ' 
       ||"st"."last_name"  AS "Student's name", 
       "ls"."lease_number" AS "lease #", 
       "ls"."start"         AS "Start Date",
       "ls"."end"         AS "End Date",
       "ls"."place_number" AS "Room #",
       (SELECT "rm"."monthly_rent_rate" FROM "room" "rm" WHERE "rm"."place_number" = "ls"."place_number" ) AS "Unit per month"

FROM   "student" "st"
       left join "lease" "ls" 
              ON "ls"."student_id" = "st"."id"; 

-- Display the total number of students in each student category.

SELECT "sc"."name"        AS "Name", 
       "sc"."description" AS "Description", 
       Count("st"."id")   AS "Total students" 
FROM   "student_category" "sc" 
       left join "student" "st" 
              ON "st"."category" = "sc"."id" 
GROUP  BY "sc"."name", 
          "sc"."description"; 

-- Display the details of housing inspections where the property was found to be in unsatisfactory condition. 

SELECT "ri"."date_of_inspection"                          AS "Inspection date", 
       "ri"."comments"                                    AS 
       "Inspection's notes", 
       (SELECT "rm"."room_number" 
        FROM   "room" "rm" 
        WHERE  "rm"."place_number" = "ri"."place_number") AS "Unit #", 
       (SELECT "hr"."name" 
        FROM   "hall_of_residence" "hr" 
        WHERE  "hr"."id" = "rm"."hall_of_residence")      AS "Hall of Residence" 
FROM   "room_inspection" "ri" 
       left join "room" "rm" 
              ON "rm"."place_number" = "ri"."place_number" 
WHERE  "ri"."satisfactory_condition" = 0 
ORDER  BY "ri"."date_of_inspection"; 


-- Present a report of the names and student IDs with their room number and hall of residence. Group by hall of residence.

SELECT "st"."first_name" 
       || ' ' 
       ||"st"."last_name"  AS "Student's Name", 
       "st"."id"           AS "Student's ID", 
       "ls"."place_number" AS "Room #", 
       "hr"."name"         AS "Hall of Residence" 
FROM   "student" "st" 
       left join "lease" "ls" 
              ON "ls"."student_id" = "st"."id" 
       left join "room" "rm" 
              ON "ls"."place_number" = "rm"."place_number" 
       left join "hall_of_residence" "hr" 
              ON "hr"."id" = "rm"."hall_of_residence" 
WHERE  "ls"."is_expired" = 0 
GROUP  BY "hr"."name", 
          "ls"."place_number", 
          "st"."id", 
          "st"."last_name", 
          "st"."first_name"; 







