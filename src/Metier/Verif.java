package Metier;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Verif
 */
@WebServlet("/Verif")
public class Verif extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verif() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//Récupération des données
		String vlogin=request.getParameter("login");
		String vpwd=request.getParameter("pwd");
		//////////////Base de données/////////////////////
		String driver = "com.mysql.jdbc.Driver";
		String con = "jdbc:mysql://localhost:3306/vote";
		String req = "select * from comptes where login='"+vlogin+"' and password='"+vpwd+"'";
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(con, "root" ,"");
			Statement st=conn.createStatement();		
			ResultSet res = st.executeQuery(req);
			HttpSession session=request.getSession();
			if (res.next()) {				
				session.setAttribute("login", vlogin);
				response.sendRedirect("pages/vote.jsp");
			}
			else {
				session.setAttribute("login", null);
				response.sendRedirect("pages/auth.jsp");
			}
		} 
		catch(ClassNotFoundException e){ 
			out.println("Pb de driver");
		}
		catch(SQLException e){ 
			out.println("Pb de requête");
		}
		///////////////////////////////////////////////////
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
