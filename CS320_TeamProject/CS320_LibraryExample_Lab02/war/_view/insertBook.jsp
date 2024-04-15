<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Add Book</title>
		<style type="text/css">
			.error {
				color: red;
				font-weight: bold;
			}
			
			.success {
				color: blue;
				font-weight: bold;
			}
			
			.success_title {
				color: darkblue;
				font-style: italic;
				font-weight: bold;			
			}
			
			tr.label {
				text-align: right;
			}
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<c:if test="${! empty successMessage}">
			<div class="success">Successfully added <span class="success_title">${successMessage}</span> to Library</div>
		</c:if>
			
		<form action="${pageContext.servletContext.contextPath}/insertBook" method="post">
			<table>
				<tr>
					<td class="label">First Name:</td>
					<td><input type="text" name="author_firstname" size="20" value="${author_firstname}" /></td>
				</tr>
				<tr>
					<td class="label">Last Name:</td>
					<td><input type="text" name="author_lastname" size="20" value="${author_lastname}" /></td>
				</tr>
				<tr>
					<td class="label">Title:</td>
					<td><input type="text" name="book_title" size="50" value="${book_title}" /></td>
				</tr>
				<tr>
					<td class="label">ISBN:</td>
					<td><input type="text" name="book_isbn" size="20" value="${book_isbn}" /></td>
				</tr>
				<tr>
					<td class="label">Published:</td>
					<td><input type="text" name="book_published" size="20" value="${book_published}" /></td>
				</tr>							
			</table>
			
			<input type="Submit" name="submitinsertbook" value="Add Book to Library">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="submithome" value="Home">
		</form>		
	</body>
</html>