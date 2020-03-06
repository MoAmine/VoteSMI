package Metier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Voter
 */
@WebServlet("/Voter")
public class Voter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Voter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter  out=response.getWriter();
		String vid=request.getParameter("nom");
		String vapp=request.getParameter("app");
		String driver = "com.mysql.jdbc.Driver";
		String con = "jdbc:mysql://localhost:3306/vote";
		String req = "update candidats set score=score+"+vapp+" where id="+vid;
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(con, "root" ,"");
			Statement st=conn.createStatement();		
			st.executeUpdate(req);
			response.sendRedirect("pages/consult.jsp");
		} 
		catch(ClassNotFoundException e){ 
			out.println("Pb de driver");
		}
		catch(SQLException e){ 
			out.println("Pb de requête");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
