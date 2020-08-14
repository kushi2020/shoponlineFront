<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />




<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">





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
		<%@include file="./shared/navbar.jsp"%>
		<div class="content">
			<!-- home -->
			<c:if test="${userClick == true}">
				<%@include file="home.jsp"%>

			</c:if>

			<!-- about -->
			<c:if test="${userClickAbout == true}">
				<%@include file="about.jsp"%>

			</c:if>

			<!-- contact -->
			<c:if test="${userClickContact == true}">
				<%@include file="contact.jsp"%>
			</c:if>

			<c:if
				test="${userClickAllProducts == true or userClickCategoryProducts == true}">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<!-- Load only when user clicks view  -->
			<c:if test="${userClickShowProduct == true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>

			<!-- Load only when user clicks view  cart -->
			<c:if test="${userClickShowCart == true}">
				<%@include file="cart.jsp"%>
			</c:if>





<!-- Load only when user clicks manage products  -->
			<c:if test="${userClickManageProducts == true}">
				<%@include file="manageProducts.jsp"%>
			</c:if>

			<!-- /.container -->
		</div>

		<!-- Footer  -->

		<%@include file="./shared/footer.jsp"%>
		<!-- /.container -->

		<!-- JavaScript -->
		<script src="${js}/jquery-1.10.2.js"></script>

		<!-- Jquery validator -->
		<script src="${js}/jquery.validate.js"></script>





		<script src="${js}/bootstrap.js"></script>
		<!-- DataTable -->
		<script src="${js}/jquery.dataTables.js"></script>

		<!-- bootstrap -->
		<script src="${js}/dataTables.bootstrap.js"></script>
		
		<!-- Bootbox -->
		<script src="${js}/bootbox.min.js"></script>
		
		<!-- Self coded javaScript file -->
		<script src="${js}/myApp.js"></script>
		
		

	</div>

</body>

</html>
