<%@ page language="java" session="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dao.DAOFactory" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<% 
HttpSession session = request.getSession(false);

%>
</head>
<body>

<%
if(session != null){
	int cart_prod_id = Integer.parseInt(request.getParameter("value"));
	DAOFactory dao = DAOFactory.getDao();
	boolean b = dao.deleteCartProd(cart_prod_id);
	if(b==true){
		System.out.println("successfully Deleted");
		response.sendRedirect("cart.jsp");
	}
}

%>

</body>
</html>