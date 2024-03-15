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
    #actionInput{
        padding: 10px;
        width: 200px;
        font-size: 10px;
    }
    #actionList{
        text-align: center;
    }

</style>
</head>
<body>
	
	<form action="${pageContext.servletContext.contextPath}/game" method="post">

<div class="container">
    <h1 class="title">Apocrypha</h1>
        <h2 class="mini_title">Your Adventure.</h2>
        <td></td>
    <div class="scroll-box" id="scrollBox">
        <!-- You can add more paragraphs here -->
    </div>
</div>

<!--<textarea class="textbox" placeholder="Enter text here..."></textarea>-->
<input class="textbox" placeholder="What will you do?" type="text" id="actionInput" /> 

<script>
    var inputs = []; // Array to store inputs
    
    function printToBox() {
        var inputText = document.getElementById("actionInput").value;
        if (inputText.trim() !== '') {
            inputs.push(inputText); // Add input to array
            updateScrollBox();
            document.getElementById("actionInput").value = ''; // Clear input box
        }
    }

    function updateScrollBox() {
        var scrollBox = document.getElementById("scrollBox");
        scrollBox.innerHTML = ''; // Clear existing content
        var ul = document.createElement("ul"); // Create unordered list
        inputs.slice().reverse().forEach(function(input) { // Reverse and iterate over inputs
            var li = document.createElement("li"); // Create list item
            li.textContent = input; // Set text content of list item
            ul.appendChild(li); // Append list item to unordered list
        });
        scrollBox.appendChild(ul); // Append unordered list to scroll box
    }

    function handleKeyPress(event) {
        if (event.keyCode === 13) { // Enter key pressed
            printToBox();
        }
    }
</script>
</body>
</html>