
@author: mebegu

^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

		*26 March 2015   21:00*
_________________________________________________________

1. Description:
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

- args[0] => The location of file that contain hash map 
 capacity information. 

- args[1] => The location of file that contain 
 instructions.

- Program writes results on output.txt.

- Given instructions will be executed line by line.

- Controller class handles the recognation of 
 instructions, and applies proper method to change data
 on InfoStorage class.

- InfoStorage class holds the necessary data.

- There are three Hash Maps in InfoStorage. 
 registrationList, allStudents, allCourses.

- registrationList is a Has Map. 
         HashList<Integer, HashList<Integer, Double>>
 Means: 
        HashList<StudentID, HashList<CouresID, Grade>>

- allStudents is a Has Map. 
         HashList<Integer, Student>
 Means: 
        HashList<StudentID, StudentObject>

- allCourses is a Has Map. 
         HashList<Integer, Course>
 Means: 
        HashList<CourseID, CourseObject>

-For further information check JavaDoc of project.
 It can be found under "Project2\doc" folder.
 Openning the JavaDoc from "index.html" is suggested.

- I had written more specific output texts for executions,
 but, as it was requested, I changed them back to old basic
 format. You can still find the my enhanced versions as
 commented out in controller class.


_________________________________________________________

2. Discovered Issues & Bugs:
---------------------------------------------------------

-No major issue, or bug is known.
