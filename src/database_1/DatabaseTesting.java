package database_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DatabaseTesting {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs;

	@BeforeTest
	public void mybeforetest() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3307/classicmodels", "root", "yasoo12345");
	}

	@Test(priority =1)
	public void ReadData() throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from customers where customerNumber=103");

		while (rs.next()) {
			int customernumber = rs.getInt("customerNumber");
			String customername = rs.getString("customerName");
			String custmerlastname = rs.getString("contactLastName");
			String custmerfirstname=rs.getString("contactFirstName");

			System.out.println("this is the customer number " + customernumber);
			System.out.println("this is the customername  " + customername);
			System.out.println("this is the custmerlastname  " + custmerlastname);
			System.out.println("this is the first name of the customer  "+custmerfirstname);
			
		}
		
	}
	
	@Test(priority=2)
	public void AddnewData() throws SQLException {
		stmt=con.createStatement();
		String insertQuery=" INSERT INTO customers (customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,city,postalCode,country)"
		+"values (123,'yasmeen','alhazaimeh','yasoo',0299368555,'amman','jordan',1234,'zarqa')";
		
		int insertedRow=stmt.executeUpdate(insertQuery);
		
		if(insertedRow>0) {
			System.out.println("data has been added");
		}
		
	}
	@Test(priority=3)
	public void UpdateData() throws SQLException{
		stmt=con.createStatement();
		String UpdateQuery="update customers set city='irbid' where customerNumber=123";
		int updateRow=stmt.executeUpdate(UpdateQuery);
				
		if(updateRow>0) {
			System.out.println("user has been updated");
			
		}
		else {
			System.out.println("nothing updated");
		}
		
	}
	@Test(priority=4)
	public void DeletData() throws SQLException {
		stmt=con.createStatement();
		String deletQuery="delete from customers where customerNumber=123";
		int DeleteRow=stmt.executeUpdate(deletQuery);
		
		if(DeleteRow>0) {
			System.out.println("the user has been deleted sucessfully");
		}
		else {
			System.out.println("this user has not been deleted");
		}
		
	}

	@AfterTest
	public void myaftertest() {

	}

}
