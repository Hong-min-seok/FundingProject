package com.kosa.funding.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.kosa.funding.DBConnection;

import oracle.jdbc.OracleTypes;

public class MemberDAO {
	
	private Connection conn;
	private CallableStatement cstmt;
	private ResultSet rs;
	private int loginResultInt;
	
	public MemberVO getMyProfile(String id) {
		System.out.println("여긴 MemberDAO getMyProfile 입니다.. 들어온 아이디는 " + id);
		MemberVO bean = new MemberVO();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("커넥션 닫혀있니? " + conn.isClosed());
			
			String runSP = "{ call member_pack.procedure_select_myinfo_member(?, ?, ?, ?, ?) }";
			
			cstmt = conn.prepareCall(runSP);
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(4, java.sql.Types.DATE);
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			
			cstmt.execute();
			
			bean.setId(cstmt.getString(2));
			bean.setName(cstmt.getString(3));
			bean.setBirthday(cstmt.getString(4));
			bean.setImage_path(cstmt.getString(5));
			
		} catch (Exception e) {
			System.out.println("나의 프로필을 불러오지 못했습니다!");
			e.printStackTrace();
		} finally {
			try {
				//if (conn != null) conn.close();
				if (cstmt != null) cstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				System.out.println("close 오류!");
				e.printStackTrace();
			}
		}
		
		return bean;
	}
	
	public List<MemberVO> getFriendList(String id) {
		
		List<MemberVO> friendlist = new ArrayList<>();
		MemberVO bean;
		try {
			conn = DBConnection.getConnection();
			
			String runSP = "{ call member_pack.procedure_select_freinds_member(?, ?) }";
			
			cstmt = conn.prepareCall(runSP);
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(2);
			
			while(rs.next()) {
				bean = new MemberVO();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setBirthday(rs.getString("birthday"));
				bean.setImage_path(rs.getString("image_path"));
				
				friendlist.add(bean);
			}
			
		} catch (Exception e) {
			
			System.out.println("친구 목록을 불러오지 못했습니다.");
			e.printStackTrace();
			
		} finally {
			
			try {
				//if (conn != null) conn.close();
				if (cstmt != null) cstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				System.out.println("close 오류!");
				e.printStackTrace();
			}
		}
		
		return friendlist;
	}
	
	public List<MemberVO> getBirthFriendList(String id) {
		
		List<MemberVO> friendlist = new ArrayList<>();
		MemberVO bean;
		
		try {
			conn = DBConnection.getConnection();
			
			String runSP = "{ call member_pack.procedure_select_bfriends_member(?, ?) }";
			
			cstmt = conn.prepareCall(runSP);
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(2);
			
			while(rs.next()) {
				bean = new MemberVO();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setBirthday(rs.getString("birthday"));
				bean.setImage_path(rs.getString("image_path"));
				
				friendlist.add(bean);
			}
			
		} catch (Exception e) {
			
			System.out.println("생일인 친구 목록을 불러오지 못했습니다.");
			e.printStackTrace();
			
		} finally {
			
			try {
				//if (conn != null) conn.close();
				if (cstmt != null) cstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				System.out.println("close 오류!");
				e.printStackTrace();
			}
		}
		
		return friendlist;
	}
	
	public int login(MemberVO member){
		
		try {
			conn = DBConnection.getConnection();
			String runSP = "{? = call member_pack.function_login_member (?, ?) }";
			cstmt = conn.prepareCall(runSP);
			System.out.println("memberDao에서 호출!! : " + member.getId());
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, member.getId());
			cstmt.setString(3, member.getPassword());
			
			try {
				cstmt.executeQuery();
				loginResultInt = (int) cstmt.getInt(1);
				
				
				System.out.println();
				System.out.println("결과 출력 성공");
				
			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				//if (conn != null) conn.close();
				if (cstmt != null) cstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				System.out.println("close 오류!");
				e.printStackTrace();
			}
		}
		return loginResultInt;
	}
	
	public void Join(MemberVO member) {
		try {
			conn = DBConnection.getConnection();
			String runSP = "{call member_pack.procedure_insert_member(?, ?, ?, ?, ?) }";
			cstmt = conn.prepareCall(runSP);
			
			cstmt.setString(1, member.getId());
			cstmt.setString(2, member.getPassword());
			cstmt.setString(3, member.getName());
			// UtilDate -> sqlDate로 변환
			cstmt.setString(4, member.getBirthday());
			cstmt.setString(5, member.getAddress());

		
			try {
				cstmt.executeQuery();
				System.out.println("쿼리 성공");
				
			} catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				e.printStackTrace();
				//System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				//if (conn != null) conn.close();
				if (cstmt != null) cstmt.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				System.out.println("close 오류!");
				e.printStackTrace();
			}
		}
	}
	
	public MemberVO profile_info(String input_id) {

		String name;
		String birth;
		int point;
		int total_funding_money;
		String image_path;
		
		MemberVO data = new MemberVO();

		try {
			conn = DBConnection.getConnection();
			String runSP = "{ call member_pack.procedure_substract_member(?,?,?,?,?,?) }";
			cstmt = conn.prepareCall(runSP);
			cstmt.setString(1, input_id);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(3, java.sql.Types.DATE);
			cstmt.registerOutParameter(4, java.sql.Types.NUMERIC);
			cstmt.registerOutParameter(5, java.sql.Types.NUMERIC);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);

			try {
				cstmt.execute();

				name = cstmt.getString(2);
				birth = cstmt.getString(3);
				point = cstmt.getInt(4);
				total_funding_money = cstmt.getInt(5);
				image_path = cstmt.getString(6);

				data.setBirthday(birth);
				data.setName(name);
				data.setPoint(point);
				data.setTotal_funding_money(total_funding_money);
				data.setImage_path(image_path);
				
				System.out.println(name);
				System.out.println(birth);
				System.out.println(point);
				System.out.println(total_funding_money);
				System.out.println(image_path);
				System.out.println("성공");
				cstmt.close();

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
		return data;
	}
	
public MemberVO getMemberPointFundingMoney(String member_id) {
		
		MemberVO member = new MemberVO();
		
		try {
			conn = DBConnection.getConnection();
			String query = "{call member_pack.procedure_select_member_money(?,?,?) }";
			cstmt = conn.prepareCall(query);

			cstmt.setString(1, member_id);
			cstmt.registerOutParameter(2, java.sql.Types.NUMERIC);
			cstmt.registerOutParameter(3, java.sql.Types.NUMERIC);

			
			try {
				cstmt.executeQuery();
				
				int point = cstmt.getInt(2);
				int total_funding_money = cstmt.getInt(3);


				
				System.out.println("total_funding_money: " + total_funding_money);
				System.out.println("point: " + point);

				
				member.setTotal_funding_money(total_funding_money);
				member.setPoint(point);


			}catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				// System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}

			
		}catch (SQLException e) {
			e.printStackTrace(); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	public void substractFundingmonyPoint(String member_id, int product_code , int spend_funding_money, int spend_point) {
		
		try {
			conn = DBConnection.getConnection();
			String query = "{call member_pack.procedure_substract_money_member(?,?,?,?) }";
			
			cstmt = conn.prepareCall(query);
			
			cstmt.setString(1, member_id);
			cstmt.setInt(2, product_code);
			cstmt.setInt(3, spend_funding_money);
			cstmt.setInt(4, spend_point);
			
			try {
				cstmt.executeQuery();
				


			}catch (SQLException e) {
				System.out.println("프로시저에서 에러 발생!");
				// System.err.format("SQL State: %s", e.getSQLState());
				System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			}
			
		}catch (SQLException e) {
			e.printStackTrace(); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
