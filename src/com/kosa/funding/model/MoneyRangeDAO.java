package com.kosa.funding.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kosa.funding.DBConnection;

import oracle.jdbc.OracleTypes;


public class MoneyRangeDAO {
	
	private Connection conn = DBConnection.getConnection();
	private CallableStatement callableStatement;
	private ResultSet resultSet;

	public ArrayList<MoneyRangeVO> list(String ID) {
		System.out.println("list 함수 실행됨");
		int code;
		String member_id;
		int fund_money;

		
		ArrayList<MoneyRangeVO> list = new ArrayList<MoneyRangeVO>();

		try {
			String runSP = "{ call graph_pack.funding_count_money_range(?,?) }";
			callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, ID);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

			try {
				callableStatement.execute();
				resultSet = (ResultSet) callableStatement.getObject(2);

				while (resultSet.next()) {
					code = resultSet.getInt(1);
					member_id = resultSet.getString(2);
					fund_money = resultSet.getInt(3);
					
					System.out.println("code" + code);
					System.out.println("memberid" + member_id);
					System.out.println("fund" + fund_money);

					MoneyRangeVO data = new MoneyRangeVO(code, member_id, fund_money);
					list.add(data);

				}

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
		return list;
	}
}
