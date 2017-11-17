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
		   
		   	<a class="nav-link" href="Login.do"> <img src="logout.gif" width="30" height="30"/> Login</a>
					<a class="nav-link" href="Register.do?PageSelected=Register"> <img src="Register.png" width="30" height="30"/> Register</a>
			    		
			 	
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
	<div class = "col-9">
		<h2 align="center">${visitorNamePage.firstName}'s Blog!</h2>
		<div class = "row">
			<div class = "col-10">
			<div class="form-group">
				<div class = "row" id="spacing">
				</div>
				<div class="container" >
					   <c:forEach var="post" items="${listOfPost}">
				            <dl>
				            
				              <dt>
							    
				                <div class="form-group" >
				                    <input type="hidden" name="postID" value="${ post.postID }" />
				                    
				                 </div>
				                      ${ post.post }
					                <span class="details">
					                    ${ post.postTimeStamp }
					                </span>
				               
							    		</dt>
				                <!-- Comment box tag start-->
				                 <c:forEach var="comm" items="${comments}">
				                 <c:choose>
				                 <c:when test= "${comm.postID==post.postID}">
				            		<dd>
				            		
				            		<form action="Login.do" method="POST">
				            		<div class="form-group" >
				                    <input type="hidden" name="commentID" value="${ comm.commentID }" />
				                    
				                 </div>
				                      ${ comm.comment }
					                <span class="details">
					                  ${ post.postTimeStamp }
					                </span>
				            		</form>
									  </dd>
									  </c:when>
									  </c:choose>
									  </c:forEach>
									   </dl>
									   	<form action="Login.do" method="POST">
									 <div class="container" id="reply">
									    <div class = "row justify-content-between">
										<div class="col-9">
											<input type="hidden" name="postID" value="${ post.postID }" />
										</div>	
										
									</div>
									  </div>
									  </form>
								</c:forEach>	
									
									<!-- Comment box tag end-->   
				  
				  
				  <div class="row" id="spacing"></div>
				  <p>Please Login to comment</p>
				  </form>
				 
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