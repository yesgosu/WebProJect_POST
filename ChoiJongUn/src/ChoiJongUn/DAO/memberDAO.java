package ChoiJongUn.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ChoiJongUn.DTO.memberDTO;

public class memberDAO {
	public Connection getConnection() throws ClassNotFoundException, SQLException {

		Connection con = null;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JONGUN");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	public ArrayList<memberDTO> list() throws ClassNotFoundException, SQLException {
		ArrayList<memberDTO> dtos = new ArrayList<memberDTO>();
		
		Connection con = getConnection();

		String sql = "SELECT * FROM MEMBER";
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			memberDTO item = new memberDTO(
					rs.getString("id"),
					rs.getString("name"),
					rs.getString("pwd"),
					rs.getString("email"),
					rs.getDate("joindate"));
			dtos.add(item);
		}
		if (st != null)
			st.close();
		if (con != null)
			con.close();

		return dtos;
	}
	public void insert(memberDTO dto) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		
		String sql = "Insert into member(id,pwd,name,email,joindate) values(?,?,?,?,sysdate)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getPwd());
		pstmt.setString(3, dto.getName());
		pstmt.setString(4, dto.getEmail());
		
		pstmt.executeUpdate();
	}
	public memberDTO view(String id) throws ClassNotFoundException, SQLException{
		
		String sql = "select * from member where id=?";
		
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		memberDTO dto = new memberDTO();
		
		dto.setId(id);
		dto.setPwd(rs.getString("pwd"));
		dto.setName(rs.getString("name"));
		dto.setEmail(rs.getString("email"));
		dto.setJoindate(rs.getDate("joindate"));
		
		return dto;
		
	}
	public boolean delete(String id) throws ClassNotFoundException, SQLException {
		String sql = "delete from member where id =?";
		boolean check = false;
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		int x = pstmt.executeUpdate();
		
		if (x<1) {
		}else {
			check = true;
		}
		pstmt.close();
		
		return check;		
	}
	public boolean update(memberDTO dto)throws ClassNotFoundException, SQLException {
		String sql = "update member set name=?,pwd=?,email=?,joindate=? where id=?";
		
		boolean check = false;
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getName());
		pstmt.setString(2, dto.getPwd());
		pstmt.setString(3, dto.getEmail());
		pstmt.setDate(4, dto.getJoindate());
		pstmt.setString(5, dto.getId());
		
		int x = pstmt.executeUpdate();
		if (x<1) {
		}else {
			check = true;
		}
		
		
		return check;
	}
	
}
