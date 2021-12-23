package ChoiJongUn.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ReplyDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ReplyDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/JONGUN");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {if(con != null) {con.close(); con=null;}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}
	

	public void register(String id, String reply) {
		CallableStatement cstmt = null;
		String sql = "{call pro_reply2(?,?)}";
		
		try {
			con = ds.getConnection();			
			cstmt = con.prepareCall(sql);			
			cstmt.setString(1, id);
			cstmt.setString(2, reply);
			cstmt.execute();
			
			cstmt.close();	
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
		}finally {
			close();
		}
			
	}
	public JSONArray rlist(String id) {
		String sql = "select id, num, reply from reply2 where id = ? order by num desc";	
		JSONArray  list = new JSONArray();
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				JSONObject json = new JSONObject();
				json.put("num", rs.getInt("num"));
				json.put("reply", rs.getString("reply"));
				
				list.add(json);
			}
			rs.close();
			pstmt.close();		
		
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
		}finally {
			close();
		}		
		return list;
	}
		
}
	

