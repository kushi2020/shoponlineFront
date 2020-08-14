<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<%-- <spring:url var="js" value="/resources/js" /> --%>





<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<script>
	window.menu = '${title}';

	window.contextRoot = '${contextRoot}'
</script>


<title>Online Shopping - ${title}</title>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.css" rel="stylesheet">

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap-readable.css" rel="stylesheet">

<!-- Bootstrap core CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">


<!-- Add custom CSS here -->
<link href="${css}/myApp.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">

		<!-- navigation comes here  -->
		
		
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<li id="home">
				<a class="navbar-brand"  href="${contextRoot}/home">Home</a>
				</li>
			</div>
<div class="content">
			<div class="container">

				<div class="row">

					<div class="col-xs-12">
					  <div class="jumbotron">
					<h1>  ${errorTitle} </h1>
					  <hr/>
					  
					  <blockquote>
					  
					     ${errorDescription}
					  
					  
					  </blockquote>
					  
					  
					  </div>
					
					
					
					</div>


				</div>





			</div>




			<!-- /.container -->
		</div>

		<!-- Footer  -->

		<%@include file="./shared/footer.jsp"%>
		<!-- /.container -->

		

	</div>

</body>

</html>
