<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Save Files</title>
<style>
  h1
  {
  	color: green;
    font-size: 75px;
    }
    body {
      background-color: black;
      font-family: Arial, sans-serif;
      color: green;
      text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      font-weight: bold;
    }
  .container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    padding: 20px;
  }
  .box {
    width: 200px;
    height: 200px;
    margin: 10px;
    background-color: green;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    transition: background-color 0.3s ease;
    color: black;
    font-size: 20px;
    border-radius: 10px;
  }
  .box:hover {
    background-color: rgb(0, 163, 44);
  }
</style>
</head>
<body>
<h1>Choose your File</h1>
<div class="container" id="container">
  <!-- Boxes will be dynamically generated here -->
</div>

<script>
  // Function to generate boxes based on the count variable
  function generateBoxes(count) {
    var container = document.getElementById("container");
    container.innerHTML = ""; // Clear existing content

    // Add "New Game" box
    var newGameBox = createBox("New Game");
    container.appendChild(newGameBox);

    // Generate boxes for save files
    for (var i = 1; i <= count; i++) {
      var box = createBox("Save " + i);
      container.appendChild(box);
    }
  }

  // Function to create a box element
  function createBox(value) {
    var box = document.createElement("input");
    box.classList.add("box");
    box.type = "submit"; // Set type to submit
    box.name = "submit"; // Set name attribute to "submit"
    box.value = value; // Set value attribute to the value passed


    return box;
  }

  // Fetch count value from servlet and generate boxes
  var count = ${count}; // Assuming count is retrieved from the servlet and assigned here
  generateBoxes(count);
</script>
</body>
</html>