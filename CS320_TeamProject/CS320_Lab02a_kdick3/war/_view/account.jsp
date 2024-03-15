<!DOCTYPE html>

<html>
	<style>
		.header{
		font-size:50px;
		text-align:center;
		margin-bottom:100px;
		}
		.body{
		font-size:20px;
		text-align:center;
		margin-bottom:100px;
		}
		.button{
		text-align:center;
		}
	</style>
	<head>
		<title>Account view</title>
	</head>

	<div class="header">
			Account Page
	</div>
	<body>
		<div class="body">
				This page will contain account information, such as a log of previous actions, held items, health, other statistics, and more.
		</div>
	</body>

	<div class="button">
	<form action="${pageContext.servletContext.contextPath}/game" method="get">
		<input type="Submit" name="submit" value="PLAY GAME">
	</form>
	</div>
</html>
