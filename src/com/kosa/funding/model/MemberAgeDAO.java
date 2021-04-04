package com.kosa.funding.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.kosa.funding.DBConnection;



public class MemberAgeDAO {
	private Connection conn = DBConnection.getConnection();
	private CallableStatement callableStatement;
	
	public int birth_age(String input_id) {
		System.out.println("birth_age 실행됨");
		int age = 0;
	
		try {
			String runSP = "{ call fundingjoin_member_age(?,?) }";
			callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, input_id);
			callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);
			
			try {
				callableStatement.execute();
				age = callableStatement.getInt(2);
				System.out.println("age : " + age);
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
		return age;
	}
	
}
