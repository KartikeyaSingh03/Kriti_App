# Learn.IITG

Learn.IITG is an android application based E - learning platform for the students of IIT Guwahati. It will be used to aid the learning and teaching processes by uploading of courses, books and reading materials.

### Libraries Used

Firebase - https://firebase.google.com/

## How does this app work?

After opening the app for the first time, user is directed to the Sign In or Register Page.
 
There are two types of users: 

                              1) General User

                              2) Clubs and Departments
                             
Any general user can register themselves and their data and credentials would be saved in the databse.
But Clubs and Departments cannot register. Their are pre - registered by the Admin who provides them with their login credentials.

Any type of user can view all of the videos and pdfs uploaded, also they can add a new Book, Slides, Reading Material (All in PDF format).
General users cannot upload a video to a course or create a new course. Only Clubs and Departments can do that.


# Setup instructions




# Overview of Learn.IITG

After logging in, a 'Home' fragment is displayed which is a part of the Navigation Drawer. This fragment diaplays the departments and clubs using tab layout, each tab implementing a recycler view. Only 2 departments and 2 clubs have been displayed for prototyping purposes. More can be added by tweaking the database.

Clicking on any of the department or clubs redirects to another fragment which implements a bottom navigation bar, with 2 tabs, one of Courses and the other of Books and Reading Material. They contain the list of courses and books associated with the club which is retrieved from the databse.

The Navigation Drawer has one fragment for displaying the profile, and the other for logout which pops an alert dialog box.


