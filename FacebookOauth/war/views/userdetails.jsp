<%@ page language="java" import="com.adaptavant.dto.UserDetails;" session="true" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="user" class="com.adaptavant.dto.UserDetails" scope="session"/>
 <table border="0"  cellpadding="0" cellspacing="0" 
height="40%" width="40%">
          <tr>
             <td><FONT SIZE="4" COLOR="#333399">We fetch the Following Information from FaceBook</FONT><BR></td>
          </tr>
          <tr>
             <td><B>Name:</B></td>
             <td> <%=user.getName() %></td>
          </tr>
          <tr>
             <td><B>FirstName</B></td>
             <td> <%=user.getFirst_name()%></td>
          </tr>
          <tr>
             <td><B>LastName</B></td>
             <td> <%=user.getLast_name() %></td>
          </tr>
          <tr>
             <td><B>Id</B></td>
             <td><%=user.getId()%></td>
          </tr>
           <tr>
             <td><B>Location</B></td>
             <td> <%=user.getLocale()%></td>
          </tr>
           
           
           <tr>
             <td><B>Click To view</B></td>
             <td><a href="<%=user.getLink()%>">Click</a></td>
          </tr>
           <tr>
             <td><B>Gender</B></td>
             <td><%=user.getGender()%></td>
          </tr>
          
</table>
</body>
</html>