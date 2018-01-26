<%--
  Created by IntelliJ IDEA.
  User: Tekilovich
  Date: 24.01.2018
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Anketa</title>
</head>
<body>
<form action="/anketa" method="post">
  <div>
    <div>First name</div>
    <div><input type="text" name="firstName"></div>

    <div>Last name</div>
    <div><input type="text" name="lastName"></div>

    <div>Age</div>
    <div><input type="number" name="age"></div>

    <p><b>What do you prefer to drink?</b></p>
    <p><input type="radio" name="drink" value="beer">Beer<Br>
      <input type="radio" name="drink" value="tea">Tea<Br>
      <input type="radio" name="drink" value="coffee">Coffee</p>

    <p><b>What do you prefer to eat?</b></p>
    <p><input type="checkbox" name="dish" value="pizza">Pizza<Br>
      <input type="checkbox" name="dish" value="sushi">Sushi<Br>
      <input type="checkbox" name="dish" value="borscht">Borscht</p>

  </div>
  <input type="submit" value="Send">
</form>
</body>
</html>
