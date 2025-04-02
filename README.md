# Advanced Hostel Allotment and Management System

## Project Overview
The **Advanced Hostel Allotment and Management System** is a Java-based application designed to manage hostel room allotments efficiently. The system allows **admins** to allocate rooms to students, generate reports, and manage user credentials, while **students** can check their allotted rooms and details. The project follows Object-Oriented Programming (OOP) principles and implements **custom exceptions, file handling, and threading**.

## Features
- **User Authentication:** Admin and Student login with credentials stored in a text file.
- **Room Allotment System:** Each room accommodates up to three students.
- **Admin Functionalities:**
  - Allocate rooms to students
  - View all hostel room allocations
  - Generate a hostel report (room-wise student list)
- **Student Functionalities:**
  - View allocated room details
- **Data Storage:**
  - User credentials stored in a `users.txt` file
  - Room allotment details stored in `room_allotment.txt`
  - Room-wise student listing stored in `room_details.txt`
- **Exception Handling:**
  - `RoomNotAvailableException`: Raised when a room is full
  - `InvalidLoginException`: Raised when user credentials are incorrect
- **Threading:**
  - Used for report generation to optimize performance

## Project Structure
```
📦 Advanced Hostel Allotment and Management System
 ┣ 📂 src
 ┃ ┣ 📜 User.java
 ┃ ┣ 📜 Student.java
 ┃ ┣ 📜 Admin.java
 ┃ ┣ 📜 Room.java
 ┃ ┣ 📜 RoomManagement.java
 ┃ ┣ 📜 Hostel.java
 ┃ ┣ 📜 RoomNotAvailableException.java
 ┃ ┣ 📜 InvalidLoginException.java
 ┃ ┣ 📜 RoomAllocator.java
 ┃ ┣ 📜 HostelSingleton.java
 ┃ ┣ 📜 Main.java
 ┃ ┣ 📜 StudentGUI.java
 ┃ ┣ 📜 AdminGUI.java
 ┣ 📂 data
 ┃ ┣ 📜 users.txt
 ┃ ┣ 📜 room_allotment.txt
 ┃ ┣ 📜 room_details.txt

```

## Technologies Used
- **Programming Language:** Java
- **GUI Framework:** Swing (for Admin and Student GUI)
- **File Handling:** Text files for persistent storage
- **OOP Principles:** Encapsulation, Inheritance, Polymorphism, Abstraction
- **Exception Handling:** Custom exceptions for error management
- **Multithreading:** Report generation optimization

## Team Members and Responsibilities
| Name | Role | Responsibilities |
|------|------|-----------------|
| Prisha Vohra | Backend Developer | Implement Room Allotment |
| Aneeka Jain | Backend Developer | User Authentication & Threading |
| Rithikaa Redde | Frontend Developer | Develop GUI for Student & Admin |
| Pulkit Shrivastava | Documentation & Testing | Exception handling and Multithreading |

## Installation & Execution
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo-name.git
   ```
2. Open the project in an IDE (Eclipse/IntelliJ IDEA)
3. Compile and run `Main.java`
4. Login as Admin/Student using credentials from `users.txt`

## Contributing
- Each member have their **own branch**.
- Follow proper commit messages.
- Create a pull request before merging.

## License
This project is licensed under the MIT License.

