package com.jdbc.postgresql.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataDAOImpl implements DataDAO {

 private Connection connection = null ;
	
    @Override
	public void connectDB() {
		final String url = "jdbc:postgresql://<Hostname>:<Port>/<DB>" ;
		final String username = "<DB-Username>" ;
		final String password = "<DB-Password>" ;
		try 
		{
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
    @Override
	public void setData(String name, String email, int age) {
		String query = "INSERT INTO employee (name,email,age) VALUES(?,?,?)" ;
		PreparedStatement preparedSt = null;
		
		try 
		{
			preparedSt = connection.prepareStatement(query);
			preparedSt.setString(1, name);
			preparedSt.setString(2, email);
			preparedSt.setInt(3, age);
			preparedSt.executeUpdate();
			System.out.println("New Employee Data was create successfully");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} finally
		{
			try {
				preparedSt.close();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
    @Override
	public Data getData(String email) {
		Data employeeData = new Data();
		String query = "SELECT * FROM employee WHERE email = ?";
		PreparedStatement preparedSt = null;
		
		try 
		{
			preparedSt = connection.prepareStatement(query);
			preparedSt.setString(1,email);
			ResultSet result = preparedSt.executeQuery();
			result.next();
			
			employeeData.name = result.getString("name");
			employeeData.email = result.getString("email");
			employeeData.age = result.getInt("age");
			
			System.out.println("name : "+employeeData.name+" || email: "+employeeData.email+" || age: "+employeeData.age);
			
		} catch (SQLException e) 
		{
			System.out.println("User Not found!.");
		} finally
		{
			try {
				preparedSt.close();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return employeeData;
	}



	@Override
	public void updateData(String name, String email, int age) {
		// TODO Auto-generated method stub
		String query = "UPDATE employee SET name= ?,age= ? WHERE email = ?" ;
		PreparedStatement preparedSt = null ;
				
		try{
			preparedSt = connection.prepareStatement(query);
			preparedSt.setString(1,name);
			preparedSt.setInt(2, age);
			preparedSt.setString(3, email);
			preparedSt.executeUpdate();
			System.out.println("Employee Data updated successfully");
			
		} catch(SQLException ex) {
			System.out.println("Something is wrong in your input.");
		} finally
		{
			try {
				preparedSt.close();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void DeleteData(String email) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM employee WHERE email = ?" ;
		PreparedStatement preparedSt = null ;
		
		try {
			preparedSt = connection.prepareStatement(query);
			preparedSt.setString(1, email);
			preparedSt.executeUpdate();
			System.out.println("Employee Data deleted successfully.");
		} catch (SQLException e) {
			System.out.println("Something is wrong in your input.");
		} finally
		{
			try {
				preparedSt.close();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Data> getAllData() {
		// TODO Auto-generated method stub
		List<Data> dataList = new ArrayList<>();
		Data employeeData = new Data();
		
		String query = "SELECT * FROM employee" ;
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				employeeData.name = result.getString("name");
				employeeData.email = result.getString("email");
				employeeData.age = result.getInt("age");
				dataList.add(employeeData);
				
				System.out.println("name: "+employeeData.name+" || email: "+employeeData.email+" || age: "+employeeData.age);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				statement.close();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dataList;
	}
}
