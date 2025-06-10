# 📚 University Management System

This is a simple *Java-based University Management System* project built using:

- Java Swing (GUI)
- MySQL Database
- JDBC (Java Database Connectivity)
- MVC-inspired architecture (refactored version)
- NetBeans / IntelliJ / Eclipse compatible

---

## 🎁 Features

✅ *Login system* (with username & password)  
✅ *Splash screen*  
✅ *Student Details* (Add / Update / Search / Print student records)  
✅ *Enter Marks* (Input subjects & marks for each student)  
✅ *View Marks / Examination Result*  
✅ *Fee Structure viewer*  
✅ *Professional & modular code*  
✅ *Proper error handling* and *user-friendly dialogs*

---

## 🎨 User Interface

### Screens:

- Splash Screen
- Login Screen
- Main Dashboard (Project.java)
- Add Student
- Update Student
- Student Details (with Search & Print)
- Enter Marks
- View Marks / Examination Result
- Fee Structure

---

## 🗄 Database Schema

### 1️⃣ student table

| Column        | Type    |
|---------------|---------|
| rollno        | VARCHAR |
| name          | VARCHAR |
| father's name | VARCHAR |
| ... other fields as needed |

### 2️⃣ subject table

| Column    | Type    |
|-----------|---------|
| rollno    | VARCHAR |
| semester  | VARCHAR |
| subject1  | VARCHAR |
| subject2  | VARCHAR |
| subject3  | VARCHAR |
| subject4  | VARCHAR |
| subject5  | VARCHAR |

### 3️⃣ marks table

| Column    | Type    |
|-----------|---------|
| rollno    | VARCHAR |
| semester  | VARCHAR |
| marks1    | INT     |
| marks2    | INT     |
| marks3    | INT     |
| marks4    | INT     |
| marks5    | INT     |

### 4️⃣ fee table

| Column    | Type    |
|-----------|---------|
| course    | VARCHAR |
| semester  | VARCHAR |
| fee       | DECIMAL |

### 5️⃣ login table

| Column    | Type    |
|-----------|---------|
| username  | VARCHAR |
| password  | VARCHAR |

---

## ⚙ How to Run

1️⃣ Install MySQL and create a database:  
```sql
CREATE DATABASE universitymanagementsystem;