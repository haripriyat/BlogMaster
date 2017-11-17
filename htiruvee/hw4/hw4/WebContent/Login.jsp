<%@page import="java.util.List"%>
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
  <!-- Image and text -->
 
<nav class="navbar navbar-light" >
  <a class="navbar-brand" href="#">
    <h2 align= "center" >  <img src="logo.jpg" width="30" height="30"/> Blog Master</h2>
    <p align="center">Tell your story</p>
  </a>
</nav>
  <div class="row">
    <div id="sideMenu" class = "col-3">
		<nav id="navbar-example3" class="navbar navbar-light bg-light">
		  <a class="navbar-brand" href="#"></a>
		  <nav class="nav nav-pills flex-column">
		    <a class="nav-link" href="Login"> <img src="logout.gif" width="30" height="30" /> Login</a>
		    <a class="nav-link" href="Register"> <img src="Register.png" width="30" height="30"/> Register</a>
			    <nav class="nav nav-pills flex-column">
			     <%
				List<String> listOfUsers = (List<String>) request.getAttribute("users");
			    if(listOfUsers !=null){
			    		System.out.println("list of users is not null");
			    		for(String user : listOfUsers){
			    			System.out.println(user+ "printing the list of users");
			    	%>
			    		
			   		 <a class="nav-link ml-3 my-1" href=VisitorPage> <%= user %> </a> 
			   		
				<%
				    		}
				    }
			   	%>
			  </nav>
			  
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
						<p style="color:red" > <%= error %> </p>
		<%
					}
				}
		%>	
	 <form action="Login" method="POST">
		  <div class="form-group">
		    <label for="exampleInputEmail1">Email address</label>
		    <input type="text" class="form-control" name="userName" id="exampleInputEmail1" placeholder="Enter email" value="${form.userName}">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password">
		  </div>
		  <div class= "row justify-content-between">
		  <div class = "col-md-4 center-block">
		  <button type="submit" class="btn btn-primary center-block" name="button" value="Login" >Login</button>
		  </div>
		  </div>
	</form>
</div>
    </div>
    <div class="col-3">
    </div>
  </div>
  
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
  </body>
</html>