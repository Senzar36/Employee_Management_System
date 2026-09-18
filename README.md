# **Employee Management System**

## **1. Project Overview**

The Employee Management System (EMS) is a cross-platform desktop application that combines a Java-based backend with a modern web-based frontend. It utilizes JavaFX WebView to deliver a full-stack desktop experience, enabling efficient employee data management through an intuitive and responsive graphical interface.

#### Tech Stack
 - Java (Backend Logic)
 - JavaFX 21+ (Desktop Framework)
 - JavaScript ES6 (Frontend Logic)
 - HTML5 & CSS3 (UI Design)
 - CSV (Data Persistence)

 ---

## **2. System Architecture**

The application is designed using an Event-Driven Architecture, ensuring high responsiveness and smooth user interaction.

Unlike traditional sequential programs, this system continuously listens for user-triggered events from the UI and processes them asynchronously.

#### JSBridge (Communication Layer)

The JSBridge class serves as the core communication layer between the frontend and backend.
 - Injected into the browser environment as: javaConnector
 - Enables JavaScript to directly invoke Java methods
 - Acts as a bridge between:
    - UI interactions (frontend)
    - Business logic & file operations (backend)

#### Key Advantage

This architecture prevents thread blocking, ensuring:
 - Smooth UI rendering
 - Efficient background execution of file I/O operations

---

## **3. Data Model & Persistence**

The system uses a lightweight CSV-based storage mechanism (employees.csv) for managing employee records.

#### Design Optimizations

##### No Header Parsing

 - The first row is treated as raw data
 - Eliminates unnecessary string comparisons

##### ArrayList Caching

 - All records are loaded into memory at startup
 - Enables fast search, update, and delete operations

##### Atomic Writes

 - File is updated immediately after every:
    - Add
    - Update
    - Delete
 - Ensures data consistency

---

## **4. Technical Implementation**

#### Backend (Java)

##### Package: Employee_Management_System

##### Core Classes
 - Main
    - Initializes JavaFX Stage and WebView
    - Loads the frontend interface
 - Employee_Management
    - Manages employee records using ArrayList
    - Handles all file I/O operations
 - JSBridge
    - Exposes backend methods to JavaScript
    - Handles communication between UI and logic

---

### Frontend (Web Technologies)

#### Responsibilities

##### HTML

 - Defines structure of forms and tables

##### CSS

 - Implements a responsive, sidebar-based layout

##### JavaScript

 - Captures user input
 - Sends requests to backend via javaConnector
 - Updates UI dynamically

---

## **5. Installation & Setup**

#### Prerequisites
 - Java JDK (17 or above recommended)
 - JavaFX SDK (21+)

#### Configuration

Add JavaFX to your module path using:

--module-path [PATH_TO_FX] --add-modules javafx.controls,javafx.web

#### Steps to Run
1. Clone or download the project
2. Set up JavaFX SDK in your IDE
3. Configure VM options with the module path
4. Run the Main class

---

## **6. Key Features**

 - Full-stack desktop architecture using WebView
 - Real-time UI updates via Java-JavaScript bridge
 - Fast in-memory data operations
 - Lightweight and portable CSV storage
 - Responsive and modern user interface

---

## **7. Future Enhancements**

 - Database integration (MySQL / PostgreSQL)
 - Authentication & role-based access
 - REST API support
 - Advanced search and filtering
 - Export data (PDF / Excel)