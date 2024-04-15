<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Books</title>
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
			
			td.isbnColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 200px;
				padding-left: 20px;
			}
			
			td.publishedColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 100px;
				padding-left: 20px;
			}

			tr.bookRow {
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
			
			td.isbnCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 200px;
				padding-left: 20px;
			}
			
			td.publishedCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 100px;
				padding-left: 40px;
			}				
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/books" method="post">
			<table>
				<tr>
					<td class="label">First Name:</td>
					<td><input type="text" name="author_firstname" size="20" value="${author_firstname}" /></td>
				</tr>
				<tr>
					<td class="label">Last Name:</td>
					<td><input type="text" name="author_lastname" size="20" value="${author_lastname}" /></td>
				</tr>
			</table>
			
			<table>
			    <tr>
					<td class="bookColHeading">Title:</td>
       				<td class="isbnColHeading">ISBN:</td>
       				<td class="publishedColHeading">Published:</td>       				
			    </tr>
			        
			    <c:forEach items="${books}" var="book">
			        <tr class="bookRow">
			            <td class="bookCol">${book.title}</td>
			            <td class="isbnCol">${book.isbn}</td>
			            <td class="publishedCol">${book.published}</td>			            
			        </tr>
			    </c:forEach>
			</table>

			<input type="Submit" name="submitbooksbyauthor" value="Get Books By Author">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="submithome" value="Home">
		</form>		
	</body>
</html>