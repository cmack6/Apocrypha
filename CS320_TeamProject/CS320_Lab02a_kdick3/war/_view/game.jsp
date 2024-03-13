<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>game</title>
		
			<style>
			
				<header>
					padding: 100px;
					text-align: center; 
					font-size: 20px;
						</header> 
			
				type="text/css">
				
		td.label {
			text-align: right;
		}
		
		</style>
	</head>

	<body>
			
			
			<h2>game</h2>
			
			<form action="${pageContext.servletContext.contextPath}/addNumbers" method="get">
				<input type="Submit" name="submit" value="Add">
			</form>
		
			<h2>Multiply Numbers?</h2>
		
			<form action="${pageContext.servletContext.contextPath}/multiplyNumbers" method="get">
				<input type="Submit" name="submit" value="Multiply">
			</form>
			
			<h2>Guess?</h2>
		
			<form action="${pageContext.servletContext.contextPath}/guessingGame" method="get">		
				<input type="Submit" name="startGame" value="Guess">
			</form>
			
	</body>
</html>
