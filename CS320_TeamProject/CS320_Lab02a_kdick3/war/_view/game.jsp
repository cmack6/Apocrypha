<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Centered Rectangle with Text Box</title>



<style>
    body, html {
        font-size: 30px;
        margin: 0;
        padding: 0;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #f0f0f0;
    }

    .scroll-box {
  width: 1700px;
  height: 400px;
  overflow: auto;
  border: 5px solid #000000;
  padding: 10px;
}

    .container {
        width: 1800px;
        max-width: 2000px;
        height: 800px; /* Adjusted height */
        background-color: #ffffff;
        border: 2px solid #000000;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 20px;
    }


    .text-box {
  position: fixed;
  bottom: 100px;
  left: 75px;
  width: 1700px;
  background-color: #ffffff; /* Background color */
  padding: 10px; /* Padding around the text */
  border: 5px solid #000000; /* Border */
}


</style>
</head>
<body>

    <form action="${pageContext.servletContext.contextPath}/test" method="post"></form>


<div class="container">
    
<h1>Apocrypha</h1>
    <p>Your Adventure.</p>  
        <h1>
            <div class="scroll-box">
                <!-- Content goes here -->
                <td>${game.action}</td>          
             </div>
            <input type="text" class="text-box" name="userInput" placeholder="What would you like to do?" value="${game.action}">
            <input type="Submit" name="submit" value="Enter">
        </h1>
        
            </form>
    <!--<td><input type="text" name="stringThird" size="12" value="${numbers.stringThird}" /></td> -->
        </div>

    </body>
</html>