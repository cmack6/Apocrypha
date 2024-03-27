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
    <title>Create Account</title>
</head>
<body>
    <h2>Haven't signed up yet? Create an account below:</h2>
    <form action=CreateAccountServlet method = doPost>
    <table>
    <tr>
        <td>
            <p>Username: </p>
            <input type="text" class="text-box" placeholder="Username" name="accountName">
        </td>
     </tr>
     <tr>
        <td>
            <p>Password: </p>
            <input type="password" class="text-box" placeholder="Password" name="accountPass">
        </td>
     </tr>
     <tr>
        <td>
            <p>Confirm Password: </p>
            <input type="password" class="text-box" placeholder="Confirm Password" name="confirmPass">
        </td>
     </tr>
     <tr>
        <td>
             <input type=submit value=CreateAccount>
         </td>
       </tr>
    </table>
   </form>
</body>
    </body>
</html>