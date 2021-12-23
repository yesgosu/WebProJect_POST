package ChoiJongUn.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ChoiJongUn.DAO.memberDAO;
import ChoiJongUn.DTO.memberDTO;

public class MemberListCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException,SQLException,ServletException, IOException {
		// TODO Auto-generated method stub
		memberDAO dao = new memberDAO();
		
		ArrayList<memberDTO> dtos = dao.list();
		
		request.setAttribute("dtos", dtos);
	}

}
