package com.kosa.funding.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.kosa.funding.DBConnection;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;


public class ProductDAO {

	private Connection conn = DBConnection.getConnection();
	private CallableStatement callableStatement;
	private ResultSet resultSet;
	
	
	public ArrayList<ProductVO> list(){

		int code;
		String name;
		int price;
		String image_path;
		String description;
	    ArrayList<ProductVO> list = new ArrayList<ProductVO>();
	try {
		String runSP = "{ call procedure_select_multiple_product(?) }";
		callableStatement = conn.prepareCall(runSP);
		callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		
		try {
			callableStatement.execute();
			resultSet = (ResultSet) callableStatement.getObject(1);
			
			while (resultSet.next()) {
				code = resultSet.getInt(1);
				name = resultSet.getString(2);
				price = resultSet.getInt(3);
				image_path = resultSet.getString(4);
				description = resultSet.getString(5);
				
				ProductVO data = new ProductVO(code, name, price, image_path, description);
				list.add(data);
//				System.out.println(code);
//				System.out.println(name);
//				System.out.println(price);
//				System.out.println(image_path);
//				System.out.println(description);
//				System.out.println();
			}
			
			System.out.println("성공");
			resultSet.close();
			callableStatement.close();
			//conn.close();
			
		} catch (SQLException e) {
			System.out.println("프로시저에서 에러 발생!");
			// System.err.format("SQL State: %s", e.getSQLState());
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return list;
}
}
