package com.jdbc.postgresql.database;

import java.util.List;

public interface DataDAO {

	void connectDB();
	Data getData(String email);
	void setData(String name, String email, int age);
	void updateData(String name, String email, int age);
	void DeleteData(String email);
	List<Data> getAllData();
}
