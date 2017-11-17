<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>ToDoList3 -- Error Page</title>
    </head>
    
	<body>
	
		<h2>ToDoList3 Error</h2>
<%
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
%>		
				<h3 style="color:red"> <%= error %> </h3>
<%
			}
		}
		
		if (session.getAttribute("user") == null && session.getAttribute("password") == null) {
%>
			Click <a href="Login.jsp">here</a> to login.
<%
		} else {
%>
			Click <a href="HomePage">here</a> to return to the To Do List.
<%
		}
%>	
	
	</body>
</html>