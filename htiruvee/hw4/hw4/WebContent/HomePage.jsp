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
    #list-example{
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
    .btn{
    		width:5dx;
    		height:5dx;
    }
    #comments{
    		margin-left: 3cm;
    }
    
    #spacing{
    		margin-bottom:1cm;
    }
    
    	</style>
</head>
<body>
 <!-- Image and text -->
<nav class="navbar navbar-light">
  <a class="navbar-brand" href="#">
    <h2 align="center">  <img src="logo.jpg" width="30" height="30"/> Blog Master</h2>
    <p align="center">Tell your story</p>
  </a>
</nav> 
<div class = "row">
	<div id="sideMenu" class = "col-3">
		<nav id="navbar-example3" class="navbar navbar-light bg-light">
		  <a class="navbar-brand" href="#"></a>
		  <nav class="nav nav-pills flex-column">
		    <a class="nav-link" href="BlogMaster"> <img src="home.ico" width="30" height="30"/> Home</a>
		    <a class="nav-link" href="Logout"> <img src="logout.gif" width="30" height="30"/> Logout</a>
			     <form action = "VisitorPage" method=POST> 
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
			  </form>
		  </nav>
		</nav>
	</div>
	<br>
	<%
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
	%>		
				<p class="error"> <%= error %> </p>
	<%
			}
		}
	%>	
	<div class = "col-9">
		<h2 align="center">${user.firstName}'s Blog!</h2>
		<div class = "row">
			<div class = "col-10">
			<div class="form-group">
				<div class = "row" id="spacing">
				</div>
				<div class="container" >
				  <form class = "form-inline" >
					  <div class="form-group">
					  <button type="button" class="btn btn-secondary">x</button>
					  </div>
					  <div class="form-group mx-sm-3">
					  <p> J2EE class is fun -- 9/12/2017 11.34am</p>
					  </div>
				  </form>
				  <form class = "form-inline">
					  <div class="form-group">
					  <button type="button" class="btn btn-secondary">x</button>
					  </div>
					  <div class="form-group mx-sm-3">
					  <p> All the rooms in the library are booked -- 9/12/2017 5.34pm</p>
					  </div>
				  </form>
				  <form class = "form-inline">
					  <div class="form-group" id="comments">
					  <button type="button" class="btn btn-secondary">x</button>
					  <p>CommentBy Jeff: Oops! there's one at hunt library! -- 9/12/2017 8.00pm </p>
					  </div>
				  </form>
				  <form class = "form-inline">
					  <div class="form-group">
					  <button type="button" class="btn btn-secondary">x</button>
					  </div>
					  <div class="form-group mx-sm-3">
					  <p> I waited in the line for 15mins to get by lunch -- 9/15/2017 1.00pm</p>
					  </div>
				  </form>
				  <div class="row" id="spacing"></div>
				  <div class="form-group" >
				  	<label for="exampleTextarea" >New Post:</label>
				  </div>
				  <div class="form-group" >
				    <textarea class="form-control" id="exampleTextarea" rows="3"></textarea>
				  </div>
				   <div class="form-group">
				  <button type="submit" class="btn btn-primary" >Post</button>
				  </div>
				</div>
	  		</div>
	  		</div>
  		</div>
	</div>
</div>
<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>