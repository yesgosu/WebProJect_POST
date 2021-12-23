package ChoiJongUn.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ChoiJongUn.DAO.postDAO;
import ChoiJongUn.DTO.postDTO;

public class MemberPostViewCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		postDAO dao = new postDAO();
		String title = request.getParameter("title");
		postDTO dto = dao.view(title);
		request.setAttribute("dto", dto);
	}

}
