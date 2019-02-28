package com.lti.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.lti.module.Customer;

// data access object
public class CustomerDao {

	// public void addCustomer(int id,String name,String email)

		
	public void add(Customer customer) {
		String sql = "insert into customer values(?,?,?)";
		Connection con = null;
		try {
			Properties p = new Properties();
			p.load(this.getClass().getClassLoader().getResourceAsStream("dev-db.properties"));
			
			Class.forName(p.getProperty("driverClassName"));
			con = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
			
			PreparedStatement stmt = con.prepareStatement(sql);
			// Replacing place-holder with date
			stmt.setInt(1, customer.getId());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getEmail());
			stmt.executeUpdate();// performing DML
			System.out.println("record insert");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("record insertion failed");
			e.printStackTrace();
		} catch(IOException e)
		{
			e.printStackTrace();
		}finally {
			if (con != null)
				try {
					con.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
}
}