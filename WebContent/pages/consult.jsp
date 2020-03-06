<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/styles.css">
</head>
<body>
<%
String vlogin="";
if(session.getAttribute("login")!=null){
	 vlogin = session.getAttribute("login").toString();
}
else{
	response.sendRedirect("auth.jsp");
}
%>
<img src="../images/vote1.png">Bonjour <%=vlogin %>
<table>
<tr>
<th colspan="3">Liste des candidats</th>
</tr>
<%
String driver = "com.mysql.jdbc.Driver";
String con = "jdbc:mysql://localhost:3306/vote";
String req = "select * from candidats order by score desc";
try {
	Class.forName(driver);
	Connection conn=DriverManager.getConnection(con, "root" ,"");
	Statement st=conn.createStatement();		
	ResultSet res = st.executeQuery(req);
	while(res.next()){
		int vid=res.getInt(1);
		String vnom=res.getString(2);
		int vscore=res.getInt(3);
%>
<tr><td><%=vid %>  </td><td><%=vnom %></td><td><%=vscore %></td></tr>
<%		
	}
} 
catch(ClassNotFoundException e){ 
	out.println("Pb de driver");
}
catch(SQLException e){ 
	out.println("Pb de requête");
}


%>
</table>
</body>
</html>