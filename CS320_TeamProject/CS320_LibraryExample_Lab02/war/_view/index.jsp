<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Library</title>
		<style type="text/css">
			.error {
				color: red;
				font-weight: bold;				
			}
			
			tr.button {
				padding-left: 20px;
				margin: 20 px;
			}
		</style>
	</head>

	<body>
		<br>
		<form action="${pageContext.servletContext.contextPath}/books" method="get">
			<input type="Submit" name="submitbooksbyauthor" value="Search for Books By Author">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/authors" method="get">
			<input type="Submit" name="submitauthorsbybook" value="Search for Authors By Book">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/allBooks" method="post">
			<input type="Submit" name="submitallbooks" value="Get All Books in Library">
		</form>	
		<br>			
		<form action="${pageContext.servletContext.contextPath}/allAuthors" method="post">
			<input type="Submit" name="submitallauthors" value="Get All Authors in Library">
		</form>	
		<br>			
		<form action="${pageContext.servletContext.contextPath}/insertBook" method="get">
			<input type="Submit" name="submitinsertnewbook" value="Add New Book To Library">
		</form>
		<br>
	</body>
</html>