# CRUD - JDBC & Postgresql

This repository demonstrates the usage of the Data Access Object (DAO) pattern in a Java application for managing employee data in a PostgreSQL database.

## Installation

1. Install Postgresql, if it's not in your local machine. ( [Download](https://www.postgresql.org/download/) )

2. Install the postgresql JDBC Driver from the official website. ( [postgresql Driver](https://jdbc.postgresql.org/download/) )

## Setup
 1. Clone the project.
```
git clone https://github.com/BALASANTHOSH01/JDBC-CRUD-Postgresql.git
cd folder_name
```

2. Setup the PostgreSQL Database in your local machine.
 - #### Open 'pgAdmin 4' or 'SQL Shell ( psql ) '
  - #### Create a database.
  - #### Create a table employee with the following schema:
  ```
CREATE TABLE employee (
    name VARCHAR(100),
    email VARCHAR(100) PRIMARY KEY,
    age INT
);
  ```
- #### Insert data .
```
INSERT INTO employee (name, email, age) VALUES ('santhosh', 'example@gmail.com', 20);
```

3. Configure the Database Connection:
 - #### Update the database URL, username, and password in the 'DataDAOImpl' class 
```
final String url = "jdbc:postgresql://localhost:5432/YOUR_DB_NAME";
final String username = "YOUR_USERNAME";
final String password = "YOUR_PASSWORD";
```
4. Compile and Run the Application:
- #### Navigate to the src directory and compile the code.
```
javac com/jdbc/postgresql/database/*.java
```
- #### Run the main class.
```
java com.jdbc.postgresql.database.DatabaseUtil
```

## Usage

```java
package com.jdbc.postgresql.database;

public class DatabaseUtil {
    public static void main(String[] args) {

        DataDAO dao = new DataDAOImpl();
        String name = "YOUR_NAME";
        String email = "YOUR_EMAIL";
        int age = 20;

        dao.connectDB();
        
        //Example of Create new employee data 
        dao.setData(name, email, age);

        //Example of get data using email
        Data employeeData = dao.getData(email);

        // Example of updating data
        employeeData.name = "Updated Name";
        employeeData.age = 21;
        dao.updateData(email, employeeData);

        // Example of deleting data
        dao.deleteData(email);

        // Example of retrieving all data
        List<Data> allData = dao.getAllData();
        for (Data data : allData) {
            System.out.println("name: " + data.name + " || email: " + data.email + " || age: " + data.age);
        }
    }
}

```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://github.com/BALASANTHOSH01/JDBC-CRUD-Postgresql/blob/main/LICENSE.text)
