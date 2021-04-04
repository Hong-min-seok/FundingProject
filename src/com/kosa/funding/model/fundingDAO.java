package com.kosa.funding.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kosa.funding.DBConnection;

import oracle.jdbc.OracleTypes;

public class fundingDAO {

	private Connection conn = DBConnection.getConnection();
	private CallableStatement callableStatement;
	private ResultSet resultSet;
	
	
	public TrioClass list(int input_code){

		int fund_money;
		String member_id;
		int sum = 0;
		TrioClass tc = new TrioClass();
	    ArrayList<fundingVO> list = new ArrayList<fundingVO>();
	try {
		String runSP = "{ call funding_join_list(?,?) }";
		callableStatement = conn.prepareCall(runSP);
		callableStatement.setInt(1,input_code);
		callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		
		try {
			callableStatement.execute();
			resultSet = (ResultSet) callableStatement.getObject(2);
			
			while (resultSet.next()) {
				fund_money = resultSet.getInt(1);
				member_id = resultSet.getString(2);
				
				sum += fund_money;
				fundingVO data = new fundingVO(fund_money, member_id);
				list.add(data);
//				
				System.out.println("funding_list");
				System.out.println(fund_money);
				System.out.println(member_id);
				System.out.println(sum);
			}

			tc.setFundingVO(list);
			tc.setSum(sum);
			tc.setCode(input_code);
			System.out.println("성공");
			resultSet.close();
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
	return tc;
}
}
