<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Apocrypha</title>
			<style type="text/css">
		
		td.label {
			text-align: right;
		}
		
		</style>
	</head>

	<body>
		
	
		<form action="${pageContext.servletContext.contextPath}/game" method="post">
			<table>
				<tr>
					<td class="label">First number:</td>
					<td><input type="text" name="stringFirst" size="12" value="${numbers.stringFirst}" /></td>
				</tr>
				<tr>
					<td class="label">Second number:</td>
					<td><input type="text" name="stringSecond" size="12" value="${numbers.stringSecond}" /></td>
				</tr>
				<tr>
					<td class="label">Third number:</td>
					<td><input type="text" name="stringThird" size="12" value="${numbers.stringThird}" /></td>
				</tr>
				<tr>
					<td class="label">Result:</td>
					<td>${numbers.result}</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Add Three Numbers!">
		</form>
	</body>
</html>