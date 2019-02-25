<%@ page language="java" session="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="dao.DAOFactory"%> 
<%@ page import="bean.User"%>
<%@ page import="bean.Cart"%>
<
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	HttpSession session = request.getSession(false);
	User user = (User) session.getAttribute("user");
%>
</head>
<body>
	<%
		if (session != null) {

			int product_id = Integer.parseInt(request.getParameter("value"));
			int user_id = user.getUsr_id();
			Cart cart = new Cart();
			cart.setProduct_id(product_id);
			cart.setUser_id(user_id);

			System.out.println(cart.getProduct_id() + " and This is session id  " + cart.getUser_id());

			DAOFactory dao = DAOFactory.getDao();
			boolean b = dao.insertCart(cart);
			if (b == true) {
             System.out.println("succesfully Done !");
			}

		} 
	%>

</body>
</html>