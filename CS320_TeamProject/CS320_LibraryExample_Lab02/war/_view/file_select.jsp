<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Save Files</title>
<style>
  h1
  {
  	color: #00fa21;
    font-family: Impact, fantasy;
    font-size: 75px;
    }
    body {
      font-family: Courier New, monospace;
      background-color: black;
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
    background-color: rgb(0, 163, 44);
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    transition: background-color 0.3s ease;
    color: black;
    font-size: 20px;
  }
  .box:hover {
    background-color: #79fa00;
  }
</style>
</head>
<body>
<h1>Choose your File</h1>
<div class="container" id="container">
  <!-- Boxes will be dynamically generated here -->
</div>

<script>
  // Example array of save files (replace this with your actual data)
  // Example array of save files with corresponding links
var saveFiles = [
  { name: "Save 1", id: "1", link: "http://localhost:8081/lab02/account" },
  { name: "Save 2", id: "1", link: "http://localhost:8081/lab02/account" },
  { name: "Save 3", id: "1", link: "http://localhost:8081/lab02/account" },
];

// Function to generate boxes based on the save files array
function generateBoxes() {
  var container = document.getElementById("container");
  container.innerHTML = ""; // Clear existing content
  
  // Add "New Game" box
  var newGameBox = createBox2("New Game", "newGameBox");
  container.appendChild(newGameBox);
  
  // Generate boxes for save files
  saveFiles.forEach(function(save, index) {
    var box = createBox(save.name, "saveBox_" + index, save.link);
    container.appendChild(box);
  });
  
  // Add "Upload Game" box
  var uploadGameBox = createBox2("Upload Game", "uploadGameBox");
  uploadGameBox.addEventListener("click", openFileExplorer);
  container.appendChild(uploadGameBox);
}

// Function to create a box element
function createBox(text, id, link) {
  var box = document.createElement("div");
  box.classList.add("box");
  box.textContent = text;
  box.id = id; // Assign unique ID
  
  // Add click event listener
  box.addEventListener("click", function() {
    window.location.href = link; // Navigate to the corresponding link
  });
  
  return box;
}

function createBox2(text, id) {
  var box = document.createElement("div");
  box.classList.add("box");
  box.textContent = text;
  box.id = id; // Assign unique ID
  
  return box;
}

  // Function to open file explorer
  function openFileExplorer() {
    // Create a file input element
    var input = document.createElement("input");
    input.type = "file";
    input.accept = ".zip"; // Specify accepted file types if needed
    input.addEventListener("change", function(event) {
      // Handle selected file
      var file = event.target.files[0];
      if (file) {
        // Perform upload or processing here
        alert("File uploaded: " + file.name);
      }
    });
    // Trigger click event to open file explorer
    input.click();
  }

  // Call the function to generate initial boxes
  generateBoxes();
</script>
</body>
</html>
