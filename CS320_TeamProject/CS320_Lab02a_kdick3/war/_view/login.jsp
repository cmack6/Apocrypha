<!DOCTYPE html>

<html>
  <style>
    body {
      background-color: black;
      color: green;
      text-align: center;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }

    form {
      margin-top: 20px;
      align-items: center;
      justify-content: center;
      display: inline-block;
      text-align: center;
      background-color: rgba(75, 66, 66, 0.3);
      padding: 20px;
      border-radius: 10px;
    }
    #loginTitle {
      font-size: 20px;
    }

    input {
      background-color: rgba(75, 66, 66, 0.371);
      color: green;
    }

    input[type="Submit"],
    input#loginButton {
      margin-top: 10px;
      background-color: rgba(75, 66, 66, 0.371);
      color: green;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.5s;
    }

    input[type="Submit"]:hover,
    button#loginButton:hover {
      background-color: rgba(75, 66, 66, 0.589);
    }

    .header {
      font-size: 50px;
      text-align: center;
    }

    .body {
      font-size: 20px;
      text-align: center;
      margin-bottom: 10px;
    }
    .button {
      text-align: center;
    }
    .error {
      color: red;
    }
  </style>
  <head>
    <h1>Apocrypha</h1>
  </head>
  <body>
    <c:if test="${! empty errorMessage}">
      <div class="error">${errorMessage}</div>
    </c:if>

    <form action="" method="post">
      <div name="login-info">
        <div id="loginTitle">login Info</div>
        <br />
        <table>
          <tr>
            <td>Username:</td>
            <td>
              <input
                id="username"
                type="text"
                name="username"
                size="12"
                value="${username}"
              />
            </td>
          </tr>
          <tr>
            <td>Password:</td>
            <td>
              <input
                type="password"
                name="password"
                size="12"
                value="${password}"
              />
            </td>
          </tr>
        </table>
        <input id="LoginButton" type="Submit" name="submit" value="Login" />
      </div>
    </form>
  </body>
</html>
