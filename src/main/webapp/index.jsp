<%@ page import="java.util.List" %>
<%@ page import="com.example.uzd1.entities.Author" %>

<html>
<head>
    <title>Authors</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- Add jQuery -->
</head>

<script>
    function toggleForm() {
        var form = document.getElementById('addAuthorForm');
        if (form.style.display === "none" || form.style.display === "") {
            form.style.display = "block";
        } else {
            form.style.display = "none";
        }
    }



    $(document).ready(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/author",
            type: "GET",
            success: function (authors) {
                $("ul").empty();
                authors.forEach(function (author) {
                    $("ul").append('<li><a href="' +
                        '${pageContext.request.contextPath}/books.jsp?authorId=' + author.id +
                        '">'+ author.name + '</a></li>');
                });
            },
            error: function () {
                alert("Nepavyko užkrauti autorių sąrašo");
            }
        });
    });

    function submitForm(event) {
        event.preventDefault();  // Prevent the form from reloading the page
        var name = $("#name").val();

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/author",
            data: { name: name },
            success: function(response) {
                // Check the response from the backend
                if (response.name) {
                    var newAuthorItem = '<li><a href="' +
                        '${pageContext.request.contextPath}/books.jsp?authorId=' + response.id +
                        '">' + response.name + '</a></li>';
                    $('ul').append(newAuthorItem);
                    document.getElementById("authorForm").reset();
                    toggleForm();
                } else {
                    alert("Error: Could not add author.");
                }
            },
            error: function(xhr, status, error) {
                alert("Error: " + error);
            }
        });
    }
</script>

<body>
<h1>Authors List</h1>
<ul>
</ul>

<button onClick="toggleForm()">Prideti nauja autoriu</button>

<div id="addAuthorForm" style="display:none;">
    <form id="authorForm" onsubmit="submitForm(event)">
        <label for="name">Author Name:</label>
        <input type="text" id="name" name="name" required />
        <br><br>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
