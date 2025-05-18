<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Author</title>
</head>
<body>
<h2>Add Author</h2>
<form action="addAuthor" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required/><br/>

    <input type="submit" value="Add Author"/>
</form>

<button onClick="window.location.href='index.jsp'">Visi Autoriai</button>
</body>
</html>
