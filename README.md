# CourseScheduler221
Final project for CMPSC 221 at Penn State University. Uses Java and Java Swing components to create an interactive GUI using a three tier application development approach. GUI java code interacts with query classes that use SQL which then interact with a Java Derby Database.

-----------------------------------------------------------------------------------------------------------------------------------------
Database consists of 5 tables: Class, Course, Schedule, Semester, Student

Class table columns:
- Semester (PK)
- CourseCode (PK)
- Seats

Course table columns:
- CourseCode (PK)
- Description

Schedule table columns:
- Semester (PK)
- CourseCode (PK)
- StudentID (PK)
- Status
- Timestamp

Semester table columns:
- Semester (PK)

Student table columns:
- StudentID (PK)
- FirstName
- LastName

Note: Status is a char which denotes whether a schedule entry is scheduled (S) or waitlisted (W). Timestamp is an SQL Timestamp which denotes when the schedule entry was entered. These are used when a student is dropped from the database or when a student drops a class. The timestamp ensures the student who was waitlisted the longest receives the open spot if there are students waitlisted for the class.
-----------------------------------------------------------------------------------------------------------------------------------------
GUI supports two sets of interactions for either admin or student user.

Admin functions include:
- Add semester
- Add course
- Add student
- Add class (instance of a course in a specific semester)
- Display class list (displays all classes offered for a given semester)
- Drop student
- Drop class

Student functions include:
- Display classes (display all classes offered for current semester)
- Schedule class
- Display schedule (displays a student's schedule for the current semester)
- Drop class
