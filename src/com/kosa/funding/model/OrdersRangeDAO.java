package com.kosa.funding.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kosa.funding.DBConnection;

import oracle.jdbc.OracleTypes;


public class OrdersRangeDAO {
	private Connection conn = DBConnection.getConnection();
	private CallableStatement callableStatement;
	private ResultSet resultSet;
	
	public ArrayList<OrdersRangeVO> order_range(String input_id) {

		int count =0;
		String month;
		ArrayList<OrdersRangeVO> list = new ArrayList<OrdersRangeVO>();
	
		try {
			String runSP = "{ call orders_range_count(?,?) }";
			callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, input_id);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			
			try {
				callableStatement.execute();
				resultSet = (ResultSet) callableStatement.getObject(2);

				while (resultSet.next()) {
					count = resultSet.getInt(1);
					month = resultSet.getString(2);

					OrdersRangeVO ndata = new OrdersRangeVO(count, month);
					list.add(ndata);

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
