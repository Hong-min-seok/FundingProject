package com.kosa.funding.model;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.kosa.funding.DBConnection;


public class AddFundingjoinDAO {
	private Connection conn = DBConnection.getConnection();
	private CallableStatement callableStatement;

	public int add_fundingjoin(int fund_money, int wish_code, String member_id) {
		int return_val = -1;
		System.out.println(fund_money + " " + wish_code + " " + member_id);

		try {
			String runSP = "{? = call fundingjoin_pack.function_choice_fundingjoin(?,?,?) }";
			callableStatement = conn.prepareCall(runSP);

			callableStatement.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER); 
			callableStatement.setInt(2, fund_money);
			callableStatement.setInt(3, wish_code);
			callableStatement.setString(4, member_id);
			


			try {
				callableStatement.execute();
				return_val =callableStatement.getInt(1);
				callableStatement.close();

			} catch (SQLException e) {
				System.out.println("function_choice_fundingjoin 프로시저에서 에러 발생!");
				System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
			
			runSP = "{call member_pack.procedure_substract_funding_money_member(?,?) }";
			callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, member_id);
			callableStatement.setInt(2, fund_money);
			
			try {
				callableStatement.execute();
				return_val =callableStatement.getInt(1);
				callableStatement.close();

			} catch (SQLException e) {
				System.out.println("procedure_substract_funding_money_member 프로시저에서 에러 발생!");
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
