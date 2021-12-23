package ChoiJongUn.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ChoiJongUn.command.MCommand;
import ChoiJongUn.command.MemberInsertPostCommand;
import ChoiJongUn.command.MemberListCommand;
import ChoiJongUn.command.MemberPostCommand;
import ChoiJongUn.command.MemberPostDeleteCommand;
import ChoiJongUn.command.MemberPostUpdateCommand;
import ChoiJongUn.command.MemberPostViewCommand;
import ChoiJongUn.command.MemberUpdateCommand;
import ChoiJongUn.command.MemberViewCommand;
import ChoiJongUn.command.MemberdeleteCommand;
import ChoiJongUn.command.MemberInsertCommand;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws SQLException,ClassNotFoundException,ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      String viewPage = null;
      MCommand command = null;
      
      String uri = request.getRequestURI();
      String com = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".do"));
      
      if(com != null && com.trim().equals("list")) {
         command = new MemberListCommand();
         command.execute(request, response);
         viewPage = "WEB-INF/view/memberList.jsp";
         
      }else if(com != null && com.trim().equals("insertForm")) {
    	 viewPage = "WEB-INF/view/memberInsertForm.jsp";
    	 
      }else if(com != null && com.trim().equals("insert")) {
    	  command = new MemberInsertCommand();
    	  command.execute(request,response);
    	  viewPage = "list.do";
      }else if(com != null && com.trim().equals("view")) {
    	  command = new MemberViewCommand();
    	  command.execute(request, response);
    	  viewPage = "WEB-INF/view/memberView.jsp";    	  
      }else if(com != null && com.trim().equals("delete")) {
    	  command = new MemberdeleteCommand();
    	  command.execute(request, response);
    	  viewPage = "list.do";    	  
      }else if(com != null && com.trim().equals("update")) {
    	  command = new MemberUpdateCommand();
    	  command.execute(request, response);
    	  viewPage = "list.do"; 
    	  
      }else if (com != null && com.trim().equals("post")) {    	  
    	  viewPage = "WEB-INF/view/memberInsertPost.jsp";
      }else if(com != null && com.trim().equals("Plist")) {
    	  command = new MemberPostCommand();
          command.execute(request, response);
          viewPage = "WEB-INF/view/memberPostList.jsp";   	  
      }else if(com != null && com.trim().equals("insertPost")) {
    	  command = new MemberInsertPostCommand();
          command.execute(request, response);
          viewPage = "Plist.do";   	  
      }else if(com != null && com.trim().equals("postview")) {
    	  command = new MemberPostViewCommand();
    	  command.execute(request, response);
    	  viewPage = "WEB-INF/view/memberPostView.jsp";
      }else if(com != null && com.trim().equals("Pupdate")) {
    	  command = new MemberPostUpdateCommand();
    	  command.execute(request, response);
    	  viewPage = "Plist.do";
      }else if(com != null && com.trim().equals("Pdelete")) {
    	  command = new MemberPostDeleteCommand();
    	  command.execute(request, response);
    	  viewPage = "Plist.do";
    	  }
      
      RequestDispatcher rd = request.getRequestDispatcher(viewPage);
      rd.forward(request, response);
      
   }

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	try {
		doHandle(request,response);
	} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	try {
		doHandle(request,response);
	} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
   
}
