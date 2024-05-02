# UBC_CPSC_210_Students_Performance_Tracking_System  

## A system designed for professors to evaluate students' performance in CPSC 210  

The key points of this personal project is summarized as follows:  

- ***Purpose and original interest***

Computer Science is a practical subject including both theory knowledge and hands-on practices. Therefore, the course design can be very challenging and usually includes various components such as lectures and labs. With hundreds of students taking this course every year, it is difficult to tell how students doing in this course, and how the knowledge and information have been absorbed by the students. Are they doing well/bad in particular session? Which session the professor should put more information or practice so the students can absorb the inforamation more effectively and efficiently? The purpose of this system is to provide insight for professors on the student's performance in this course.  

- ***End user and potential functions***  
    
This application is mainly designed for professors to store and evaluate the information of students in CPSC 210 course. Ideally, this application should be able to carry out the following functions:  
1. When the user start the application, the app will ask user to load saved data or not. The user can choose "Y" to load data or "N" to create the list from scratch;
2. The app store individual student's information, such as StudentID, and student's grades including project, midterm, final, total grade and total performance (A, B, C, D, F).
3. Professor can use this app to add, find, and remove students on the list.
4. When user select quit option from the application menu, another menu will be prompted to ask to save the changes or not. If user select "Y", data changes will be saved to file; otherwise, changes will not be saved.

## You can add multiple students information to the system
- Run the main application 
- Click Yes button to load previously saved data (At this step, please do not click No button which will give you an empty table without data) 
- You can directly see all the current students information listed on the display panel (as you add, find, remove students, results will be displayed on this panel simultaneously). 
- In the lower left area, you can enter student details in each text field:
- Add student 1: studentID: 30075678; project grade: 20; midterm grade: 25; final grade: 30; Then click Add New Student button. You will find the student was added to the display panel. A dialog box will prompt up to let you know that this student is added. Click OK to close the dialog box.
- Delete the text field manually and enter the information of student 2;
- Add student 2: studentID: 30075670; project grade: 15; midterm grade: 20; final grade: 35; Then click Add New Student button. You will find the student was added to the display panel.
- You can keep adding as many students as you want;
- If you add a student which already exist in the list (same student ID), when you hit the Add New Student button, this student would not be added and a dialog box will prompt up, indicating that student already exist. For example, if you add student ID: 30075678; Project grade: 15; Midterm grade: 20; Final grade: 25. Click the Add New Student button again, this student will not ba added and a dialog box will prompt up to remind you this student already exists. Click OK button to close the dialog box;
## You can find a student in the current student list by student ID
- In the lower centre area, you have a Find Student button. You need to enter the student ID in the text field under this button.
- For example, you can enter the student ID 30075678, then click Find Student button. You will see the display panel will only display the information for this student;
- To go back to the original list of all students, you just need to clean/delete the text in the text field, and click the Find Student button again. Then, all students information will appear again;
- If you enter a student ID which does not exist in this system, when you click the Find Student button, a dialog box will prompt up, indicating this student does not exist. For example, if you enter student ID:30030328 then click Find Student button. A dialog will prompt, indicating cannot find this student. Click OK button to close the dialog box. Manually delete text in the text field, and click Find Student button again to go back to student lists.
## You can delete a student in the current student list by student ID 
- In the  lower right area, you have a Remove Student button with a text field underneath for you to enter the student ID;
- Enter the student ID in the text field. For example, enter 30075678, then click the Remove Student button, you can see this student is removed from the display panel. A dialog will prompt up to let you know the student has been removed. Click OK button to close the dialog box.
- If you want to remove a student who does not exist in the system, for example, you can enter 30075678 again in the text field, when you click the Remove Student button, a dialog box will prompt, indicating this student does not exist. Click OK button to close the dialog box.
## You can save the state of my application by:
- When you finish and try to exit the application. You can click the cross button in the up right corner. A dialog box will prompt up and ask if you want to save the data;
- If you choose Yes, the changes will be saved to Json file. You can find this file by going to "./data/ProjectData.json".
- If you choose No, the changes will not be saved. 
## You can reload the state of my application by:
- If you choose to save your data. Next time when you start the application, the application will first ask you if you want to load the previous data, you can click Yes. Then the previous saved data will be loaded.
- If you choose No, you will start with an empty table. You can still do all the functions described above.

### The following events will be logged when the user:
1. Load the previous saved data
2. Add new student
3. Remove existing student
4. Save data when exist the application 

### A example of the event log print out will like follows:
- Thu Nov 30 21:58:53 PST 2023
- Saved data is imported.


- Thu Nov 30 21:58:53 PST 2023
- Student 30047899 is added.


- Thu Nov 30 21:58:53 PST 2023
- Student 30047901 is added.


- Thu Nov 30 21:59:11 PST 2023
- Student 30047898 is added.


- Thu Nov 30 21:59:24 PST 2023
- Student 30047899 is removed.


- Thu Nov 30 22:00:11 PST 2023
- Student 30047899 is added.


- Thu Nov 30 22:00:31 PST 2023
- Student 30047901 is removed.


- Thu Nov 30 22:00:56 PST 2023
- Student 30047900 is added.


- Thu Nov 30 22:01:31 PST 2023
- New changes has been saved.
