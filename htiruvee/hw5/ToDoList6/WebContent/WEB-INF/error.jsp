<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>ToDoList6 -- Error Page</title>
    </head>
    
	<body>
	
		<h2>ToDoList6 Error</h2>

		<h3 style="color:red"> ${error} </h3>

		<c:choose>
			<c:when test="${ (empty user) }">
				Click <a href="login.do">here</a> to login.
			</c:when>
			<c:otherwise>
				Click <a href="todolist.do">here</a> to return to the To Do List.
			</c:otherwise>
		</c:choose>

	</body>
</html>