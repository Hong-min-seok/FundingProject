package com.kosa.funding.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.kosa.funding.DBConnection;

public class OrderDAO {
	private Connection conn = DBConnection.getConnection();
	
	
	
	public void insertOrder(String member_id, int product_code, int wish_code) {
		
		try {
			String query = "{call procedure_insert_product_orders(?,?) }";
			CallableStatement callableStatement = conn.prepareCall(query);

			callableStatement.setString(1, member_id);
			callableStatement.setInt(2, product_code);
			
			try {
				callableStatement.executeQuery();
				
				System.out.println(member_id);
				System.out.println(product_code);
				

			}catch (SQLException e) {
				System.out.println("procedure_insert_product_orders 프로시저에서 에러 발생!");
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
			
			query = "{ call wishlist_pack.procedure_end_wishlist(?) }";
			callableStatement = conn.prepareCall(query);
			callableStatement.setInt(1, wish_code);
			
			try {
				callableStatement.executeQuery();

			}catch (SQLException e) {
				System.out.println("procedure_end_wishlist 프로시저에서 에러 발생!");
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
			

		}catch (SQLException e) {
			e.printStackTrace(); 
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
	
