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


public class myProductDAO {

	private Connection conn = DBConnection.getConnection();
	private CallableStatement callableStatement;
	private ResultSet resultSet;
	
	
	public ArrayList<myProductVO> list(String input_id){

		int wish_code;
		int code;
		String name;
		int price;
		String image_path;
		String description;
	    ArrayList<myProductVO> list = new ArrayList<myProductVO>();
	try {
		String runSP = "{ call wishlist_pack.wishlist_product_join_info(?,?) }";
		callableStatement = conn.prepareCall(runSP);
		callableStatement.setString(1, input_id);
		callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		
		try {
			callableStatement.execute();
			resultSet = (ResultSet) callableStatement.getObject(2);
			
			while (resultSet.next()) {
				wish_code = resultSet.getInt(1);
				code = resultSet.getInt(2);
				name = resultSet.getString(3);
				price = resultSet.getInt(4);
				image_path = resultSet.getString(5);
				description = resultSet.getString(6);
				
				
				myProductVO data = new myProductVO(wish_code,code, name, price, image_path, description);
				list.add(data);
				System.out.println(wish_code);
				System.out.println(code);
				System.out.println(name);
				System.out.println(price);
				System.out.println(image_path);
				System.out.println(description);
				System.out.println();
			}
			
			System.out.println("성공");


			
		} catch (SQLException e) {
			System.out.println("프로시저에서 에러 발생!");
			 System.err.format("SQL State: %s", e.getSQLState());
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
