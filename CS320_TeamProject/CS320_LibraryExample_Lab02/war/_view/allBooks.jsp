<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 All Books</title>
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
				max-width: 500px;
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

			td.nameColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 300px;
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
				max-width: 500px;
				padding-left: 20px;				
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
					<td class="bookColHeading">Title</td>
       				<td class="isbnColHeading">ISBN</td>
       				<td class="publishedColHeading">Published</td>
					<td class="nameColHeading">Last Name</td>
       				<td class="nameColHeading">First Name</td>
			    </tr>
			        
			    <c:forEach items="${books}" var="pair">
			        <tr class="bookRow">
			            <td class="bookCol">${pair.right.title}</td>
			            <td class="isbnCol">${pair.right.isbn}</td>
			            <td class="publishedCol">${pair.right.published}</td>
			            <td class="nameCol">${pair.left.lastname}</td>
			            <td class="nameCol">${pair.left.firstname}</td>			            
			        </tr>
			    </c:forEach>
			</table>

			<input type="Submit" name="submithome" value="Home">
		</form>
	</body>
</html>