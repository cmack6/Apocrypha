<!DOCTYPE html>

<html>
  <style>
    body {
      background-color: black;
      color: green;
      font-family: Arial, sans-serif;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }

    #Stats {
      width: fit-content;
      background-color: rgba(75, 66, 66, 0.371);
      border: none;
      padding: 10px 20px;
      border-radius: 5px;
      text-align: left;
      margin-top: 10px;
    }
    #statsTitle {
      text-align: center;
      font-size: 25px;
    }

    input {
      background-color: rgba(75, 66, 66, 0.371);
      border: none;
      color: green;
      padding: 10px 20px;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.5s;
    }
    input:hover {
      background-color: rgba(75, 66, 66, 0.589);
    }

    .header {
      font-size: 45px;
      text-align: center;
    }
    .body {
      font-size: 20px;
      text-align: center;
      margin-bottom: 20px;
    }
    .button {
      text-align: center;
    }
  </style>
  <head>
    <title>Account view</title>
    <h1>Account Page</h1>
  </head>

  <body>
    <div id="Stats" class="body">
      <div id="statsTitle">Stats</div>
      <br />
      <div>Score: ${player.score}</div>
      <br />
      <div>Health: ${player.health}</div>
      <br />
      <div>Items: ${items}</div>
      <br />
    </div>
  </body>

  <div class="button">
    <form action="${pageContext.servletContext.contextPath}/game" method="get">
      <input type="Submit" name="submit" value="PLAY GAME" />
    </form>
  </div>
</html>
