package com.kosa.funding.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.kosa.funding.DBConnection;



public class AddWishListDAO {
	private Connection conn = DBConnection.getConnection();
	private CallableStatement callableStatement;

	public int add_wishlist(String member_id, int prod_code) {
		int return_val = -1;
		
		try {
			String runSP = "{? = call wishlist_pack.procedure_insert_wishlist(?,?) }";
			callableStatement = conn.prepareCall(runSP);

			callableStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER); 
			callableStatement.setString(2, member_id);
			callableStatement.setInt(3, prod_code);
			 

			try {
				

				callableStatement.execute();
				
				return_val = (int) callableStatement.getInt(1);
				System.out.println(return_val);
				
				System.out.println("성공");
				callableStatement.close();

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
		
		return return_val;
	}
}
