package com.jdbc.postgresql.database;

public class DatabaseUtil {
	public static void main(String [] args) 
	{
		
		DataDAOImpl dao = new DataDAOImpl();
		
		String name = "Bala santhosh";
		String email = "balasanthosh@gmail.com";
		int age = 20;
		
		dao.connectDB(); // Connect the JDBC with the Postgresql DataBase 
		dao.getAllData(); // Get all employee data
		dao.setData(name, email, age); // Create new employee data
		dao.getData(email); // get existing employee data
		dao.DeleteData(email); // delete a specific employee data
		dao.updateData(name, email, age); // update a specific employee data
		
	}

}
