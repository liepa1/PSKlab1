<%@ page import="java.util.List" %>
<%@ page import="com.example.uzd1.entities.Book" %>
<%@ page import="com.example.uzd1.entities.Author" %>

<html>
<head>
    <title>Books by Author </title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<script>



    $(document).ready(function () {
        const authorId = new URLSearchParams(window.location.search).get("authorId");
        console.log("Author ID from URL:", authorId);
        if (!authorId) {
            alert("authorId is missing or empty");
            return;
        }

        const contextPath = '<%= request.getContextPath() %>';
        const authorId2 = new URLSearchParams(window.location.search).get("authorId");
        const apiUrl = contextPath + '/books?authorId=' + authorId2;
        console.log("API url:", apiUrl);

        $.ajax({
            url: apiUrl,
            type: "GET",
            success: function (response) {
                $("ul").empty();
                console.log("Response:", response);
                response.books.forEach(function (book) {
                    $("ul").append('<li>' + book.title + '</li>');
                });
                $("h1").text("Books by " + response.authorName);
            },
            error: function () {
                alert("Error: Could not load books.");
            }
        });
    });

    function submitForm(event) {
        event.preventDefault();  // Prevent the form from reloading the page
        var title = $("#title").val();

        const contextPath = '<%= request.getContextPath() %>';
        const authorId2 = new URLSearchParams(window.location.search).get("authorId");
        const apiUrl = contextPath  + "/books";

        $.ajax({
            type: "POST",
            url: apiUrl,
            data: { title: title,
            authorId: authorId2},
            success: function(response) {
                if (response.title) {
                    var newBookItem = '<li>' + response.title + '</li>';

                    $('ul').append(newBookItem);
                    //$("ul")[0].reset();
                    document.getElementById("bookForm").reset();
                    toggleForm();
                } else {
                    alert("Error: Could not add book.");
                }
            },
            error: function(xhr, status, error) {
                alert("Error: " + error);
            }
        });
    }
</script>
<body>
<h1></h1>
<ul>

</ul>

<div id="addBookForm">
    <form id="bookForm" onsubmit="submitForm(event)">
        <label for="title">Book title:</label>
        <input type="text" id="title" name="title" required />
        <br><br>
        <input type="submit" value="Submit" />
    </form>
</div>

<br>
<a href="${pageContext.request.contextPath}">Back to Author List</a>
</body>
</html>
