# CS 349 Project – Customer Billing & Management System

This is a Java Swing-based GUI application designed to manage customer data, generate invoices, and process billing. It connects to a SQL Server database via JDBC.

---

## 📂 Project Structure Overview

```
CS_349_Proj-master/
├── src/
│   ├── GUIs/                   # All primary GUI forms
│   ├── EditCustomerOptions/    # Customer attribute UI elements
│   ├── db_connection/          # Database connection logic
│   ├── testGUIs/               # GUI testing/demo class
├── CheckBoxList/              # Custom JCheckbox list widget
├── jdatepicker-1.3.4.jar     # External date picker library
├── mssql-jdbc_auth-12.2.0.x64.dll  # SQL Server auth DLL (Windows)
├── database.sql              # SQL script to initialize DB
├── .project, .classpath      # Eclipse project configs
```

---

## 🚀 Features

- User Authentication (Login)
- Add / View / Edit Customer Details
- Invoice Generation
- Billing and Payment Management
- Date picker with `jdatepicker` library
- Custom checkbox rendering for editable fields

---

## 🛠️ How to Run This Project

### 1. Prerequisites

- Java 8+
- Eclipse IDE (or IntelliJ IDEA with Swing support)
- SQL Server (LocalDB or full version)
- SQL Server JDBC Driver (JAR already included)
- Git (optional, for cloning)

---

### 2. Clone or Extract

If you have a `.zip`, extract it.

Or, clone it:
```bash
git clone <your-repo-url>
```

---

### 3. Import Into Eclipse

1. Open Eclipse
2. Go to **File > Import > Existing Projects into Workspace**
3. Select the extracted folder
4. Click **Finish**

---

### 4. Database Setup (SQL Server)

1. Open SQL Server Management Studio or Azure Data Studio
2. Run the provided `database.sql` script

   - This creates necessary tables and test data

3. Note your **SQL Server instance name**, **username**, and **password**

---

### 5. Update DB Credentials

Open the file:
```
src/db_connection/DB_Connect.java
```

And update these lines:
```java
String url = "jdbc:sqlserver://localhost;databaseName=YourDBName";
String user = "yourUsername";
String password = "yourPassword";
```

---

### 6. Build Path Configuration

Right-click project > Build Path > Configure Build Path:

- Add `jdatepicker-1.3.4.jar` to the **Classpath**
- Make sure the SQL Server JDBC JAR is also included (or download latest from Microsoft)

---

### 7. Windows Only – DLL Setup

If you're on **Windows**, place this DLL:

```
mssql-jdbc_auth-12.2.0.x64.dll
```

Into:
```
C:\Program Files\Java\jdk1.x.x_xx\bin
```

This enables Windows authentication (if used).

---

### 8. Run the Application

Run `Login.java` in the `GUIs` package to launch the app.

Use the provided dummy credentials (if added via `database.sql`) or insert records manually.

---

## 🧪 Testing

- You can use `testGUIs/TestMenuBar.java` for UI experiments.
- GUI tests are mostly manual.

---

## ✅ Dependencies

- [jdatepicker-1.3.4](https://sourceforge.net/projects/jdatepicker/)
- SQL Server JDBC Driver
- Java Swing
