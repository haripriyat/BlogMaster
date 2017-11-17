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
    #posts{
    		margin-bottom:0.5mm;
    }
    #comments{
    		margin-left: 3cm;
    		margin-bottom:0.5mm;
    }
    
    #spacing{
    		margin-bottom:0.5cm;
    }
    #reply{
    		margin-bottom:1mm;
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
		   
		    <%
		    if (session.getAttribute("user") != null){
		    	%>
		    			<a class="nav-link" href="BlogMaster"> <img src="home.ico" width="30" height="30"/> Home</a>
		    			<a class="nav-link" href="Logout"> <img src="logout.gif" width="30" height="30"/> Logout</a>
		    <%
			    	}
			    	else{
			%>  		
					<a class="nav-link" href="Login"> <img src="logout.gif" width="30" height="30"/> Login</a>
					<a class="nav-link" href="Register"> <img src="Register.png" width="30" height="30"/> Register</a>
			    		
			<% 
			    	}
			 %>   	
			  <form action = "VisitorPage" method=POST > 
			    <nav class="nav nav-pills flex-column">
				<%
				List<String> listOfUsers = (List<String>) request.getAttribute("users");
			    if(listOfUsers !=null){
			    		System.out.println("list of users is not null");
			    		for(String user : listOfUsers){
			    			System.out.println(user + " printing the list of users");
			    	%>
			    		
			   		 <a class="nav-link ml-3 my-1" href="VisitorPage" id="BlogName"> <%= user %> </a> 
			   	<%
				    		}
				    }
			   	%>
				
			  </nav>
			  </form>
		  </nav>
		</nav>
	</div>
	<div class = "col-9">
		<%
				List<String> userNames = (List<String>) request.getAttribute("users");
			    if(listOfUsers !=null){
			    		System.out.println("list of users is not null");
			    		for(String user : userNames){
			    			System.out.println(user + " printing the list of users");
			    			if(user.BlogName){
			    	%>
			    		
			   		
					<h2 align="center"><%=user %></h2>
		
					<%
						}
			    		}
				    }
			   %>
		<div class = "row">
			<div class = "col-10">
			<div class="form-group">
				<div class = "row" id="spacing">
				</div>
				<div class="container" >
				  <form class = "form-inline" >
					  <div class="form-group mx-sm-3" id="posts">
					  <p > Assignment is due on Monday -- 9/12/2017 11.34am</p>
					  </div>
				  </form>
				  <!-- Comment box tag start-->
				  <div class="container" id="reply">
					  <a href="demooo"  data-toggle="collapse" data-target="#demo">Comment</a>
					  <div id="demo" class="collapse">
					    <div class = "row justify-content-between">
						<div class="col-9">
					       <input type="text" class="form-control" id="text">
						</div>
						<div class="col-3"> 
					      <button type="submit" class="btn btn-primary" >Post</button>
						</div>
					</div>
					  </div>
					</div>
					<!-- Comment box tag end-->
				  <form class = "form-inline">
					  <div class="form-group mx-sm-3" id="posts">
					  <p > Mentor meeting with grace at 300 south craig's street -- 9/12/2017 5.34pm</p>
					  </div>
				  </form>
				  <form class = "form-inline">
					  <div class="form-group" id="comments">
					  <p>CommentBy Grace: Which room is it? -- 9/12/2017 6.00pm </p>
					  </div>
				  </form>
				  <form class = "form-inline">
					  
					  <div class="form-group mx-sm-3" id="posts">
					  <p>Tight schedule this week, applying for jobs and attending toc -- 9/15/2017 1.00pm</p>
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