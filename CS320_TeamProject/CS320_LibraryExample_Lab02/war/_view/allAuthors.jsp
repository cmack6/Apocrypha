<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 All Authors</title>
		<style type="text/css">
			.error {
				color: red;
				font-weight: bold;				
			}
			
			td.label {
				text-align: right;
			}
	
			td.nameColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 200px;
				padding-left: 20px;
			}
			
			tr.authorRow {
				text-align: left;
				color: blue;
				font-weight: bold;
			}

			td.nameCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 400px;
				padding-left: 20px;
			}				
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<table>
			    <tr>
       				<td class="nameColHeading">Last Name</td>
       				<td class="nameColHeading">First Name</td>       				
			    </tr>
			        
			    <c:forEach items="${authors}" var="author">
			        <tr class="authorRow">
			            <td class="nameCol">${author.lastname}</td>
			            <td class="nameCol">${author.firstname}</td>			            
			        </tr>
			    </c:forEach>
			</table>

			<input type="Submit" name="submithome" value="Home">
		</form>
	</body>
</html>