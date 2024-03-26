<!DOCTYPE html>
<html>
    <style>
        .submitBtn
        {
            background-color: greenyellow;
            color: black;
            cursor: pointer;
        }
        .submitBtn:hover{
            background-color: rgb(0, 199, 13);
        }
    </style>
    <head>
        <h1>Create Account</h1>
    </head>
    <body>
        <h2>Haven't signed up yet? Create an account below:</h2>
        
        <table>
            <td>
                <p>Username: </p>
                <input type="text" class="text-box" placeholder="Username" name="accountName">
            </td>
            <td>
                <p>Password: </p>
                <input type="password" class="text-box" placeholder="Password" name="accountPass">
            </td>
            <td>
                <p>Confirm Password: </p>
                <input type="password" class="text-box" placeholder="Confirm Password" name="confirmPass">
            </td>
        </table>
        <button>Create Account</button>
    </body>
</html>