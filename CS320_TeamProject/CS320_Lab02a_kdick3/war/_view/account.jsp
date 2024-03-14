<!DOCTYPE html>

<html>
	<style>
		header{
		font-size:35px;
		}
	</style>
	<head>
		<title>Account view</title>
	</head>

	<body>
		This will contain account information, such as a log of previous actions, held items, health, other statistics, and more.
	</body>


	<form action="${pageContext.servletContext.contextPath}/game" method="get">
		<input type="Submit" name="submit" value="PLAY GAME">
	</form>
</html>
