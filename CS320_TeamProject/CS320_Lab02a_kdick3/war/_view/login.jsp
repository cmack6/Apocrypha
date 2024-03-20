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

    .header {
      font-size: 50px;
      text-align: center;
      margin-bottom: 100px;
    }
    .body {
      font-size: 20px;
      text-align: center;
      margin-bottom: 100px;
    }
    .button {
      text-align: center;
    }
    .error {
      color: red;
    }
  </style>
  <head>
    <h1>Apocrypha Login</h1>
  </head>
  <body>
    <c:if test="${! empty errorMessage}">
      <div class="error">${errorMessage}</div>
    </c:if>

    <form action="" method="post">
      <div name="login-info">
        <table>
          <tr>
            <td>Username:</td>
            <td>
              <input
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
                type="text"
                name="password"
                size="12"
                value="${password}"
              />
            </td>
          </tr>
        </table>
        <input type="Submit" name="submit" value="Login" />
      </div>
    </form>
  </body>
</html>
