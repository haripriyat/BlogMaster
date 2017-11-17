<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" crossorigin="anonymous">
    <style>
	    #sideMenu{
	    		padding-right:0px;
	    		background-color: #e3f2fd;
	    }
	    .navbar{
	    		background-color: #e3f2fd;
	    }
	    h2{
	    		font-style: italic;
	    		font-family: "Georgia",serif;
	    		
	    }
	    p{
	    		font-style: italic;
	    		font-family: "Georgia",serif;
	    }
    	</style>
</head>
<body>
<nav class="navbar navbar-light">
  <a class="navbar-brand" href="#">
    <h2 align="center">  <img src="logo.jpg" width="30" height="30"/> Blog Master</h2>
    <p align="center">Tell your story</p>
  </a>
</nav>
  <div class="row">
    <div id="sideMenu" class = "col-3">
		<nav id="navbar-example3" class="navbar navbar-light bg-light">
		  <a class="navbar-brand" href="#"></a>
		   <nav class="nav nav-pills flex-column">
		    <a class="nav-link" href="Login.do"> <img src="logout.gif" width="30" height="30"/> Login</a>
			    <c:forEach var="eachuser" items="${listOfUsers}">
				<form action = "VisitorPage.do?PageSelectedVisitor=VisitorPage" method=POST >
				<nav class="nav nav-pills flex-column">
				<li>
			 <input type="hidden" name="usersPresent" value="${eachuser.userName}" />
				<input type="submit" value="${eachuser.firstName}">
				</li>
				</nav>
				</form>
				</c:forEach>
			  </nav>
		</nav>
	</div>
    <div class="col-6">
     <div class = "container">
     <br>
	     <%
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
		%>		
						<p style="color:red"> <%= error %> </p>
		<%
					}
				}
		%>	
	 <form action= "Register.do?PageSelected=Register" method="POST" >
		  <div class="form-group">
		   <div class="form-group">
		  	<label for="exampleInputfirstName1">First Name</label>
		    <input type="text" class="form-control" id="exampleInputfirstName1" placeholder="First Name" name="firstName" value="${form.firstName}">
		  </div>
		  <div class="form-group">
		  	<label for="exampleInputLastName1">Last Name</label>
		    <input type="text" class="form-control" id="exampleInputLastName1" placeholder="Last Name" name="lastName" value="${form.lastName}">
		  </div>
		    <label for="exampleInputEmail1">Email Address</label>
		    <input type="text" class="form-control" name="userName" id="exampleInputEmail1" placeholder="Enter email" value="${form.userName}">
		  </div>
		  <div class= "row justify-content-between">
			  <div class="col-6">
			    <label for="exampleInputPassword1">Password</label>
			    <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password" value="${form.password}">
			  </div>
			  <div class="col-6">
			    <label for="exampleInputPassword2">Confirm Password</label>
			    <input type="password" class="form-control" name="confirmPassword" id="exampleInputPassword2" placeholder="Password" value="${form.confirmPassword}">
			  </div>
		  </div>
		  <br>
		  <div>
		  <button type="submit" name="button" class="btn btn-primary" value="Register">Sign Up</button>
		  </div> 
	</form>
	</div>
   </div>
   <div class="col-3">
   </div>
  </div>
     <!-- Optional JavaScript 
    jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
  
</body>
</html>