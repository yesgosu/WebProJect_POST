package ChoiJongUn.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ChoiJongUn.DTO.postDTO;

public class postDAO {
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
	public ArrayList<postDTO> postlist() throws ClassNotFoundException, SQLException {
		ArrayList<postDTO> dtos = new ArrayList<postDTO>();

		Connection con = getConnection();

		String sql = "SELECT * FROM POST";
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			postDTO item = new postDTO(
					rs.getString("title"),
					rs.getString("content"),					
					rs.getDate("joindate"));
			dtos.add(item);
		}
		if (st != null)
			st.close();
		if (con != null)
			con.close();
		return dtos;
	}
	public void insert(postDTO dto) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		
		String sql = "Insert into post(title,content,joindate) values(?,?,sysdate)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getTitle());
		pstmt.setString(2, dto.getContent());
		
		
		pstmt.executeUpdate();
	}
public postDTO view(String title) throws ClassNotFoundException, SQLException{
	
		String sql = "select * from post where title=?";
		
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		postDTO dto = new postDTO();
		
		dto.setTitle(title);
		dto.setContent(rs.getString("content"));
		
		dto.setJoindate(rs.getDate("joindate"));
		
		return dto;
		
	}
public boolean update(postDTO dto)throws ClassNotFoundException, SQLException {
	String sql = "update post set content=?,joindate=? where title=?";
	
	boolean check = false;
	Connection con = getConnection();
	PreparedStatement pstmt = con.prepareStatement(sql);
	pstmt.setString(1, dto.getContent());	
	pstmt.setDate(2, dto.getJoindate());
	pstmt.setString(3, dto.getTitle());
	
	
	int x = pstmt.executeUpdate();
	if (x<1) {
	}else {
		check = true;
	}
	
	
	return check;
}
public boolean delete(String title) throws ClassNotFoundException, SQLException {
	String sql = "delete from post where title =?";
	boolean check = false;
	Connection con = getConnection();
	PreparedStatement pstmt = con.prepareStatement(sql);
	pstmt.setString(1, title);
	int x = pstmt.executeUpdate();
	
	if (x<1) {
	}else {
		check = true;
	}
	pstmt.close();
	
	return check;		
}
}
