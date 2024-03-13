<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Add Numbers</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/addNumbers" method="post">
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