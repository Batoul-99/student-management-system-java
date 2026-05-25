# Student Management System - Database Persistence Version

This is the database persistence version of a Java Object-Oriented Programming academic project.

The system manages students, courses, enrollments, grades, attendance, and academic reports using Java classes and database storage.

## Project Overview

This version extends the file persistence version by storing and retrieving data from a database instead of text files.

The project applies core Object-Oriented Programming concepts such as encapsulation, inheritance, polymorphism, abstraction, interfaces, collections, sorting, filtering, and reporting. It also introduces database persistence to manage university records in a more structured and scalable way.

## Features

- Add students
- Add courses
- Enroll students in courses
- Update student grades
- Update attendance
- Display all students
- Display all courses
- Generate academic reports
- Sort students by name and average
- Sort courses by name and number of enrolled students
- Filter students using lambda expressions
- Store and retrieve data using database persistence

## Main Classes

- `Student`
- `UndergraduateStudent`
- `GraduateStudent`
- `ExchangeStudent`
- `Course`
- `Enrollment`
- `StudentSystem`
- `Report`
- `StudentFilter`
- Sorting classes for students and courses

## OOP Concepts Used

- Classes and Objects
- Encapsulation
- Inheritance
- Polymorphism
- Abstract Classes
- Interfaces
- Collections
- Lambda Expressions
- Sorting and Filtering
- Class Associations

## Database Concepts Used

- Database persistence
- Student records
- Course records
- Enrollment records
- Relationships between students, courses, and enrollments
- Data insertion, update, and retrieval

## Technologies Used

- Java
- Maven
- Hibernate / JPA
- SQL
- Database Persistence
- Object-Oriented Programming

## Project Structure

```text
database-persistence-version/
├── src/
│   └── main/
│       └── java/
│           └── Project_OOP1_2026/
│               ├── Course.java
│               ├── Enrollment.java
│               ├── ExchangeStudent.java
│               ├── GraduateStudent.java
│               ├── Main.java
│               ├── Report.java
│               ├── Student.java
│               ├── StudentFilter.java
│               ├── StudentSystem.java
│               └── UndergraduateStudent.java
├── pom.xml
├── database-schema.sql
└── README.md