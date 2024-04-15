<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Authors</title>
		<style type="text/css">
			.error {
				color: red;
				font-weight: bold;			
			}
			
			td.label {
				text-align: right;
			}
			
			td.book {
				text-align: center;
				color: blue;
				font-weight: bold;
			}
			
			td.bookColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 400px;
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
			
			td.bookCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 400px;
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
	
		<form action="${pageContext.servletContext.contextPath}/authors" method="post">
			<table>
				<tr>
					<td class="label">Book Title:</td>
					<td><input type="text" name="book_title" size="50" value="${book_title}" /></td>
				</tr>
			</table>
			
			<table>
			    <tr>
					<td class="bookColHeading">Title</td>
       				<td class="nameColHeading">Last Name</td>
       				<td class="nameColHeading">First Name</td>       				
			    </tr>
			        
			    <c:forEach items="${authors}" var="author">
			        <tr class="authorRow">
			            <td class="bookCol">${book_title}</td>
			            <td class="nameCol">${author.lastname}</td>
			            <td class="nameCol">${author.firstname}</td>			            
			        </tr>
			    </c:forEach>
			</table>

			<input type="Submit" name="submitauthorsbytitle" value="Get Author By Title">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="submithome" value="Home">
		</form>		
	</body>
</html>