<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Scrolling Box Example</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
    }
    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }
    .title {
    	top: 0;
        font-size: 45px;
        margin-top: 0px;
        margin-bottom: 20px;
    }

    .mini_title {
        font-size: 35px;
        text-align: center;
        margin-bottom: 30px;
    }
    .scroll-box {
        font-size: 20px;
        width: 80%;
        height: 200px;
        overflow-y: auto;
        border: 1px solid #ccc;
        padding: 10px;
    }
    .textbox {
        position: fixed;
        bottom: 10px;
        left: 10px;
        width: 200px;
        padding: 10px;
    }
</style>
</head>
<body>
	
	<form action="${pageContext.servletContext.contextPath}/game" method="post">

<div class="container">
    <h1 class="title">Apocrypha</h1>
        <h2 class="mini_title">Your Adventure.</h2>
        <td>${gamemodel.action}</td>
    <div class="scroll-box">
    	<p>${gamemodel.action}</p>
        <!-- You can add more paragraphs here -->
    </div>
</div>

<!--<textarea class="textbox" placeholder="Enter text here..."></textarea>-->
<input class="textbox" placeholder="What will you do?" type="text" name="input" value="${gamemodel.action}" />

</body>
</html>