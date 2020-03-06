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
<form method="post" action="../Voter">
<table>
<tr>
<th colspan="2"><img src="../images/vote1.png">Bonjour <%=vlogin %> </th>
</tr>

<tr>
<td>Nom</td>
<td>
<select name="nom">
<%
String driver = "com.mysql.jdbc.Driver";
String con = "jdbc:mysql://localhost:3306/vote";
String req = "select * from candidats";
try {
	Class.forName(driver);
	Connection conn=DriverManager.getConnection(con, "root" ,"");
	Statement st=conn.createStatement();		
	ResultSet res = st.executeQuery(req);
	while(res.next()){
		out.println("<option value='"+res.getInt(1)+"'>"+res.getString(2));
	}
} 
catch(ClassNotFoundException e){ 
	out.println("Pb de driver");
}
catch(SQLException e){ 
	out.println("Pb de requête");
}
%>
</select>
</td>
</tr>

<tr>
<td>Appréciation</td>
<td> <input type="radio" name="app" value="0">Nul<br>
<input type="radio" name="app" value="2" checked>Moyen<br>
<input type="radio" name="app" value="4">Excellent<br>
</td>
</tr>
<tr>
<td><input type="submit" value="OK"></td>
<td><input type="reset" value="Annuler"></td>
</tr>

</table>
</form>
</body>
</html>